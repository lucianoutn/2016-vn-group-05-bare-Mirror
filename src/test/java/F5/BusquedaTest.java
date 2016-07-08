package F5;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class BusquedaTest {
	
	
	@Test
	public void buscarEnMapaVacia(){
		
		Busqueda unaBusqueda = new Busqueda("pepe","flores", "cgp",null);
		Assert.assertEquals(0, unaBusqueda.getCantResultados());
		Mapa unMapa = new Mapa();
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
		Busqueda unaBusqueda = new Busqueda("pedro","flores", "101",new ArrayList<NotificadorDeBusqueda>());
		Assert.assertTrue(unaBusqueda.buscoFrase("101", unMapa).contains(parada101));
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
