package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.uqbar.geodds.Punto;
import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.LocalComercial;
import F5.Pois.Punto;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorBusqueda;
import TestFactory.BancoFactory;


public class PersistenciaDeBusquedaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private Busqueda unaBusqueda;
	private Usuario unUsuario;
	private Comuna comuna3;
	private Terminal unaTerminal;
	private SucursalDeBanco unaSucursalDeBanco;
	private LocalComercial unLocalComercial;
	private RepositorioDePOIs unRepositorioDePOIs;
	private ResultadoDeBusqueda unaPersistenciaDeBusqueda;
	private List<ResultadoDeBusqueda> busquedas;
	
	@Before
	public void Initialize(){
		comuna3 = new Comuna(3,(Polygon)null);
		unUsuario = new Usuario("Juan",comuna3);
		unaSucursalDeBanco = new SucursalDeBanco("Rio", new Punto(10, 10), new ArrayList<DiaAtencion>());
		unLocalComercial = new LocalComercial("Macowins", "Pedernera", "10", "Ropa", new ArrayList<DiaAtencion>(),new Punto(10, 10));
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		unRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		unRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		unRepositorioDePOIs.anadirPOI(unLocalComercial);

		unaTerminal = new Terminal(2, "Terminal Dos", unRepositorioDePOIs);		
		unaBusqueda = new Busqueda(1, 2, unUsuario, "Terminal Dos", "Macowi", new ArrayList<NotificadorDeBusqueda>());	
		unaPersistenciaDeBusqueda = new ResultadoDeBusqueda();
		busquedas = new ArrayList<>();
		
		
	}
	
	@Test 
	public void almacenoBusquedaEnBaseDeDatos(){
		SucursalDeBanco banco =BancoFactory.BancoHSBC();
		List<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(banco);
		unaBusqueda.setRepositorioDeBusquedas(unaPersistenciaDeBusqueda);
		
		unaBusqueda.buscoFrase("Macow", unRepositorioDePOIs);
		unaPersistenciaDeBusqueda.setPoisEncontrados(pois);
		entityManager().persist(unaPersistenciaDeBusqueda);
		
		busquedas = entityManager().createQuery("from ResultadoDeBusqueda", ResultadoDeBusqueda.class).getResultList();
		
		Assert.assertEquals(unaPersistenciaDeBusqueda.getCd_Busqueda(), busquedas.get(0).getCd_Busqueda());
		Assert.assertEquals(unaPersistenciaDeBusqueda.getCd_Terminal(), busquedas.get(0).getCd_Terminal());
		Assert.assertEquals(unaPersistenciaDeBusqueda.getFraseBuscada(), busquedas.get(0).getFraseBuscada());
		Assert.assertEquals(unaPersistenciaDeBusqueda.getCantidadDeResultados(), busquedas.get(0).getCantidadDeResultados());
		Assert.assertEquals(unaPersistenciaDeBusqueda.getPoisEncontrados().size(), busquedas.get(0).getPoisEncontrados().size());
		
	}
	
	
}
