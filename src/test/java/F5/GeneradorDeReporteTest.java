package F5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GeneradorDeReporteTest {
	
	private GeneradorDeReporte generador = new GeneradorDeReporte();
	
	@Before
	public void Initialize() {
		
		
		Busqueda busquedaJuan = new Busqueda();
		Busqueda busquedaPedro = new Busqueda();
		Busqueda busquedaEnBoedo = new Busqueda();
		
		busquedaEnBoedo.setTerminal("boedo");
		busquedaJuan.setUsuario("juan");
		busquedaPedro.setUsuario("pedro");
		
		RepositorioImpostor.limpiar();
		RepositorioImpostor.agregarBusqueda(busquedaEnBoedo);
		RepositorioImpostor.agregarBusqueda(busquedaJuan);
		RepositorioImpostor.agregarBusqueda(busquedaPedro);
		
	}
	
	
	@Test
	public void sinNingunaBusqueda(){
		Assert.assertEquals(0,generador.generarReporte("", "", null, null));
		
	}
	
	
	@Test
	public void reporteConNombrePepe(){
		Busqueda busquedaSegunUsuario = new Busqueda();
		busquedaSegunUsuario.setUsuario("pepe");
		RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);
	
		Assert.assertEquals(1,generador.generarReporte("pepe", "", null, ""));

	}
	
	@Test
	public void reporteSiEstaEnFlores(){
		Busqueda busquedaSegunTerminal = new Busqueda();
		busquedaSegunTerminal.setTerminal("flores");
		RepositorioImpostor.agregarBusqueda(busquedaSegunTerminal);
	
		Assert.assertEquals(1, generador.generarReporte("", "flores", null, ""));
	}
	
	@Test
	public void reporteSiEstaPedro(){
	
		Assert.assertEquals(1, generador.generarReporte( "pedro","",null,""));
	}
	
	@Test
	public void reporteSiEstaJuan(){
	
		Assert.assertEquals(1, generador.generarReporte("juan", "",null,""));
	}
	
	@Test
	public void reporteSiEstaEnBoedo(){
	
		Assert.assertEquals(1, generador.generarReporte("", "boedo",null,""));
	}
	
}
