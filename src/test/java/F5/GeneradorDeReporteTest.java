package F5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GeneradorDeReporteTest {
	
	private GeneradorDeReporte generador = new GeneradorDeReporte();
	
	@Before
	public void initialize() {
		
		
		
	}
	
	
	@Test
	public void sinNingunaBusqueda(){
		Assert.assertEquals(0,generador.generarReporte("", "", null, null));
		
	}
	
	
	@Test
	public void reporteConNombrePepe(){
		Busqueda busquedaSegunUsuario = new Busqueda();
		busquedaSegunUsuario.setUsuario("pepe");
		RepositorioImpostor.setBusquedas(busquedaSegunUsuario);
	
		Assert.assertEquals(1,generador.generarReporte("pepe", "", null, ""));

	}
	
	@Test
	public void reporteSiEstaEnFlores(){
		Busqueda busquedaSegunTerminal = new Busqueda();
		busquedaSegunTerminal.setTerminal("flores");
		RepositorioImpostor.setBusquedas(busquedaSegunTerminal);
	
		Assert.assertEquals(1, generador.generarReporte("", "flores", null, ""));
	}
	
}
