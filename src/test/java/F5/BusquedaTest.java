package F5;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class BusquedaTest {
	RepositorioDePOIs unMapa;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
	}
	
	@Test
	public void buscarEnMapaVacia(){
		
		Busqueda unaBusqueda = new Busqueda(new Usuario("pepe"),"flores", "cgp",null);	
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
		
		Busqueda unaBusqueda = new Busqueda(new Usuario("pedro"),"flores", "101",null);
		unaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(1, unaBusqueda.buscoFrase("101",unMapa).size());

	}
	
	@Test
	public void buscarEnMapaConDosElementosYEncuentroUnoDeEllos(){

		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
	
		Busqueda unaBusqueda = new Busqueda(new Usuario("pepe"),"flores", "cgp", null);
		Busqueda unaBusq = new Busqueda(new Usuario("pedro"),"flores", "101",null);
		Assert.assertTrue(unaBusq.buscoFrase("101",unMapa).contains(parada101));
	}

}
