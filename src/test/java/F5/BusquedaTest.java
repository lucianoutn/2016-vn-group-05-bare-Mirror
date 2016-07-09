package F5;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusquedaTest {
	Mapa unMapa;
	
	@Before
	public void Initialize(){
		
	}
	
	@Test
	public void buscarEnMapaVacia(){
		
		Mapa unMapa = new Mapa();
		Busqueda unaBusqueda = new Busqueda("pepe","flores", "cgp",null);	
		SucursalDeBanco unaSucursalDeBanco = new SucursalDeBanco("Servicios Financieros", null, new ArrayList<DiaAtencion>());
		unMapa.anadirPOI(unaSucursalDeBanco);
		Assert.assertTrue(unaBusqueda.buscoFrase("Servicios Financieros", unMapa).contains(unaSucursalDeBanco));
	}
	
	@Test
	public void buscarEnMapaConUnElementoYEncontrarlo(){
		
		Mapa unMapa = new Mapa();
		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
		
		Busqueda unaBusqueda = new Busqueda("pedro","flores", "101",null);
		unaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(1, unaBusqueda.buscoFrase("101",unMapa).size());

	}
	
	@Test
	public void buscarEnMapaConDosElementosYEncuentroUnoDeEllos(){
		Mapa unMapa = new Mapa();
		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
	
		Busqueda unaBusqueda = new Busqueda("pepe","flores", "cgp", null);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",null);
		Assert.assertTrue(unaBusq.buscoFrase("101",unMapa).contains(parada101));
	}

}
