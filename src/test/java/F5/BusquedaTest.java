package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class BusquedaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	RepositorioDePOIs unMapa;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
	}
	
	@Test
	public void buscarEnMapaVacia(){
		
		Busqueda unaBusqueda = new Busqueda(1,2,new Usuario("pepe", null),"flores", "cgp",null);	
		SucursalDeBanco unaSucursalDeBanco = new SucursalDeBanco("Servicios Financieros", null, new ArrayList<DiaAtencion>());
		unMapa.anadirPOI(unaSucursalDeBanco);
		Assert.assertTrue(unaBusqueda.buscoFrase("Servicios Financieros", unMapa).contains(unaSucursalDeBanco));
	}
	
	@Test
	public void buscarEnMapaConUnElementoYEncontrarlo(){

		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
		
		Busqueda unaBusqueda = new Busqueda(1,3,new Usuario("pedro", null),"flores", "101",null);
		unaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(parada101, unaBusqueda.buscoFrase("101",unMapa).get(0));
	}
	
	@Test
	public void buscarEnMapaConDosElementosYEncuentroUnoDeEllos(){

		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
	
		Busqueda unaBusqueda = new Busqueda(1,1,new Usuario("pepe", null),"flores", "cgp", null);
		Busqueda unaBusq = new Busqueda(1,4,new Usuario("pedro", null),"flores", "101",null);
		Assert.assertTrue(unaBusq.buscoFrase("101",unMapa).contains(parada101));
	}

	
	@Test
	public void persistenciaDeBusqueda(){
		/*Point puntoA = new Point(0, 0);
		Point puntoB = new Point(10, 0);
		Point puntoC = new Point(10, 10);
		Point puntoD = new Point(0, 10);
		List<Point> points = new ArrayList<Point>();
		points.add(puntoA);
		points.add(puntoB);
		points.add(puntoC);
		points.add(puntoD);
		Comuna comuna = new Comuna(1, points);
	
		entityManager().persist(comuna);
		Usuario ezequiel = new Usuario("Ezequiel", comuna);
		
		entityManager().persist(ezequiel);*/
		
		
		Busqueda busqueda = new Busqueda(1,2,null,"flores", "101",null);
		entityManager().persist(busqueda);
		
		
		
		List<Busqueda> busquedasDb = entityManager()
									.createQuery("from Busqueda", Busqueda.class)
									.getResultList();
		
		Assert.assertEquals(busqueda.getTerminal(), busquedasDb.get(0).getTerminal());
		Assert.assertEquals(busqueda.getFraseBuscada(), busquedasDb.get(0).getFraseBuscada());
	}
	
}
