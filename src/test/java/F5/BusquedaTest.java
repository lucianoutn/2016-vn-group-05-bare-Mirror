package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Kvs.KvsCache;

public class BusquedaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	RepositorioDePOIs unMapa;
	private Terminal unaTerminal, otraTerminal;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("001"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("002"));
		
		unMapa = new RepositorioDePOIs(consultorBanco, null);
		
		unaTerminal = new Terminal("flores",unMapa);
		otraTerminal = new Terminal("recoleta",unMapa);
	}
	
	@After
	public void limpiarKvs(){
		KvsCache.clear();
	}
	
	@Test
	public void buscarEnMapaVacia(){
		
		Busqueda unaBusqueda = new Busqueda(unaTerminal,new Usuario("pepe", null),"cgp");	
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
		
		Busqueda unaBusqueda = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
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
	
		Busqueda unaBusqueda = new Busqueda(unaTerminal,new Usuario("pepe", null),"cgp");
		Busqueda unaBusq = new Busqueda(otraTerminal,new Usuario("pedro", null),"101");
		Assert.assertTrue(unaBusq.buscoFrase("101",unMapa).contains(parada101));
	}

	
	@Test
	public void persistenciaDeBusqueda(){
	
		Busqueda busqueda = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		entityManager().persist(busqueda);		
		
		List<Busqueda> busquedasDb = entityManager()
									.createQuery("from Busqueda", Busqueda.class)
									.getResultList();
		
		Assert.assertEquals(busqueda.getTerminal(), busquedasDb.get(0).getTerminal());
		Assert.assertEquals(busqueda.getFraseBuscada(), busquedasDb.get(0).getFraseBuscada());
	}
	
	
	@Test 
	public void testKvsDespuesDeVariasBusquedas(){
		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
	
		
		Busqueda unaBusq = new Busqueda(otraTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusqueda = new Busqueda(unaTerminal,new Usuario("pepe", null),"cgp");
		
		unaBusq.buscoFrase("101",unMapa);
		Assert.assertTrue(KvsCache.trues == 0);
		
		unaBusq.buscoFrase("101",unMapa);
		Assert.assertTrue(KvsCache.trues == 1);
		
		unaBusqueda.buscoFrase("Hola", unMapa);
		Assert.assertTrue(KvsCache.trues == 1);
		
		unaBusqueda.buscoFrase("pepe", unMapa);
		Assert.assertTrue(KvsCache.trues == 1);
		
		unaBusqueda.buscoFrase("Hola", unMapa);
		Assert.assertTrue(KvsCache.trues == 2);
		
		
		unaBusqueda.buscoFrase("newton", unMapa);
		Assert.assertTrue(KvsCache.trues == 2);
		
		
		
		
		
	}
}
