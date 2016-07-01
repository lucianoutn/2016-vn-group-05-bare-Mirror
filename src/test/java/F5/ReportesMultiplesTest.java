package F5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReportesMultiplesTest {
	public BusquedasPorFecha reporteroDeBusquedas = new BusquedasPorFecha();
	public ArrayList<Observers> listaDeDosReporteros = new ArrayList<>();
	public ResultadosPorTerminal reporteroDeResultadosPorTerminal = new ResultadosPorTerminal();

	@Before
	public void initialize() {
		listaDeDosReporteros.add(reporteroDeBusquedas);
		listaDeDosReporteros.add(reporteroDeResultadosPorTerminal);
	}

	@Test
	public void reportesVacio() {
		Assert.assertEquals(0, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(0, reporteroDeResultadosPorTerminal.generarReporte().size());
	}
	
	@Test
	public void reportesConUnaBusqueda() {
		Busqueda unaBusq = new Busqueda("pedro", "flores", "101", listaDeDosReporteros);

		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte().size());
	}
	
	
	@Test
	public void reportesConDosBusquedaEnUnaMismaTerminal() {
		Busqueda unaBusq = new Busqueda("pedro", "flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda("pedro", "flores", "cgp", listaDeDosReporteros);

		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte().size());
	}
	
	@Test
	public void reportesConDosBusquedaEnDosTerminalesDistintas() {
		Busqueda unaBusq = new Busqueda("pedro", "flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda("alexis", "lugano", "cgp", listaDeDosReporteros);

		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte().size());
	}
	
	
}
