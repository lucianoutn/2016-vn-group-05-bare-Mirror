package F5;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class BusquedaTest {
	
	
	
	@Test
	public void buscarEnMapaVacia(){
		Busqueda unaBusqueda = new Busqueda("pepe","flores", "cgp",null);
		Assert.assertEquals(0, unaBusqueda.getCantResultados());
	}
	
	@Test
	public void buscarEnMapaConUnElemento(){
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		Mapa.setPuntosDeInteres(pois);
		
		Busqueda unaBusqueda = new Busqueda("pedro","flores", "101",null);
		Assert.assertEquals(1, unaBusqueda.getCantResultados());
	}
	
	@Test
	public void buscarEnMapaConDosElementos(){
		
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		Mapa.setPuntosDeInteres(pois);
		
		Busqueda unaBusqueda = new Busqueda("pepe","flores", "cgp", null);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",null);
		Assert.assertEquals(1, unaBusq.getCantResultados());
	}

}
