package F5;

import java.time.LocalTime;

import org.junit.After;
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
		

		RepositorioImpostor.agregarBusqueda(busquedaEnBoedo);
		RepositorioImpostor.agregarBusqueda(busquedaJuan);
		RepositorioImpostor.agregarBusqueda(busquedaPedro);

	}

	@After
	public void limpiar() {
		RepositorioImpostor.limpiar();
	}
	
	@Test
	public void busquedaConFechaDistintaTest(){
		LocalTime fecha = LocalTime.of(8, 20);

		Busqueda busquedaPorFecha = new Busqueda();
		
		busquedaPorFecha.setFecha(fecha);
		
		RepositorioImpostor.agregarBusqueda(busquedaPorFecha);
		
		Assert.assertEquals(0,generador.generarReporte("", "", LocalTime.now(), ""));
	}
	
	@Test
	public void busquedaConFechaActual(){
		LocalTime fecha = LocalTime.now();

		Busqueda busquedaPorFecha = new Busqueda();
		
		busquedaPorFecha.setFecha(fecha);
		
		RepositorioImpostor.agregarBusqueda(busquedaPorFecha);
		
		Assert.assertEquals(1,generador.generarReporte("", "",fecha, ""));
	}
	
	@Test
	public void sinNingunaBusqueda() {
		Assert.assertEquals(0, generador.generarReporte("", "", null, null));
	}

	@Test
	public void reporteConNombrePepe() {

		Busqueda busquedaSegunUsuario = new Busqueda();
		busquedaSegunUsuario.setUsuario("roberto");
		RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);

		Assert.assertEquals(1, generador.generarReporte("roberto", "", null, ""));

	}

	@Test
	public void reporteSiEstaEnFlores() {

		Busqueda busquedaSegunTerminal = new Busqueda();
		busquedaSegunTerminal.setTerminal("flores");
		RepositorioImpostor.agregarBusqueda(busquedaSegunTerminal);

		Assert.assertEquals(1, generador.generarReporte("", "flores", null, ""));
	}

	@Test
	public void reporteSiEstaPedro() {

		Assert.assertEquals(1, generador.generarReporte("pedro", "", null, ""));
	}

	@Test
	public void reporteSiEstaJuan() {
		Assert.assertEquals(1, generador.generarReporte("juan", "", null, ""));
	}

	@Test
	public void reporteSiEstaEnBoedo() {
		Assert.assertEquals(1, generador.generarReporte("", "boedo", null, ""));
	}

	@Test
	public void reporteCon5DePepe() {

		for (int i = 0; i < 5; i++) {
			Busqueda busquedaSegunUsuario = new Busqueda();
			busquedaSegunUsuario.setUsuario("pepe");
			Busqueda busqueda = new Busqueda();
			busqueda.setUsuario("juan");
			RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);
			RepositorioImpostor.agregarBusqueda(busqueda);
		}
		Assert.assertEquals(5, generador.generarReporte("pepe", "", null, ""));
	}

	@Test
	public void reporteDeTodasLasBusquedasDevuelve13() {

		for (int i = 0; i < 5; i++) {
			Busqueda busquedaSegunUsuario = new Busqueda();
			busquedaSegunUsuario.setUsuario("alberto");
			Busqueda busqueda = new Busqueda();
			busqueda.setUsuario("rodrigo");
			RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);
			RepositorioImpostor.agregarBusqueda(busqueda);
		}
		Assert.assertEquals(13, generador.generarReporte(null, null, null, null));
	}

	@Test
	public void reporteDeTodasLasBusquedasDeUnaFecha() {
		RepositorioImpostor.limpiar();
		for (int i = 0; i < 5; i++) {
			Busqueda busquedaSegunUsuario = new Busqueda();
			busquedaSegunUsuario.setUsuario("pepe");
			busquedaSegunUsuario.setFecha(LocalTime.now());
			Busqueda busqueda = new Busqueda();
			busqueda.setUsuario("juan");
			RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);
			RepositorioImpostor.agregarBusqueda(busqueda);
		}
		Assert.assertEquals(5, generador.generarReporte(null, null, LocalTime.now(), null));
	}

	@Test
	public void reporteDe5HechasEnAbasto() {
		RepositorioImpostor.limpiar();
		for (int i = 0; i < 3; i++) {
			Busqueda busquedaSegunUsuario = new Busqueda();
			busquedaSegunUsuario.setUsuario("pepe");
			busquedaSegunUsuario.setFecha(LocalTime.now());
			busquedaSegunUsuario.setTerminal("abasto");
			Busqueda busqueda = new Busqueda();
			busqueda.setUsuario("juan");
			busquedaSegunUsuario.setTerminal("campus");
			RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);
			RepositorioImpostor.agregarBusqueda(busqueda);
		}
		Assert.assertEquals(3, generador.generarReporte(null, "abasto", null, null));
	}

	@Test
	public void reporteBusco6VecesFraseRenta() {
		RepositorioImpostor.limpiar();
		for (int i = 0; i < 3; i++) {
			Busqueda busquedaSegunUsuario = new Busqueda();
			busquedaSegunUsuario.setUsuario("pepe");
			busquedaSegunUsuario.setFecha(LocalTime.now());
			busquedaSegunUsuario.setTerminal("abasto");
			busquedaSegunUsuario.setFraseBuscada("Rentas");
			Busqueda busqueda = new Busqueda();
			busqueda.setUsuario("juan");
			busqueda.setFraseBuscada("pagar Rentas");
			busquedaSegunUsuario.setTerminal("campus");
			RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);
			RepositorioImpostor.agregarBusqueda(busqueda);
		}

		for (int i = 0; i < 3; i++) {
			Busqueda busquedaSegunUsuario = new Busqueda();
			busquedaSegunUsuario.setUsuario("pepe");
			busquedaSegunUsuario.setFecha(LocalTime.now());
			busquedaSegunUsuario.setTerminal("abasto");
			busquedaSegunUsuario.setFraseBuscada("Impuestos");
			Busqueda busqueda = new Busqueda();
			RepositorioImpostor.agregarBusqueda(busquedaSegunUsuario);

		}
		Assert.assertEquals(6, generador.generarReporte(null, null, null, "Renta"));
	}

}