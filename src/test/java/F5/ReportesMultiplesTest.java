package F5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReportesMultiplesTest {
	public BusquedasPorFecha reporteroDeBusquedas = new BusquedasPorFecha();
	public ArrayList<NotificadorDeBusqueda> listaDeDosReporteros = new ArrayList<>();
	public ResultadosPorTerminal reporteroDeResultadosPorTerminal = new ResultadosPorTerminal();
	public Mapa unMapa;
	@Before
	public void initialize() {
		listaDeDosReporteros.add(reporteroDeBusquedas);
		listaDeDosReporteros.add(reporteroDeResultadosPorTerminal);
		unMapa=new Mapa();
	}

	@Test
	public void reportesVacio() {
		Assert.assertEquals(0, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(0, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reportesConUnaBusqueda() {
		Busqueda unaBusq = new Busqueda("pedro", "flores", "101", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	//Revisar este test
	@Test
	public void reportesConDosBusquedaEnUnaMismaTerminal() {
		Busqueda unaBusq = new Busqueda("pedro", "flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda("pedro", "flores", "cgp", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa);
		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte("flores").size());
	}
	
	@Test
	public void reportesConDosBusquedaEnDosTerminalesDistintas() {
		Busqueda unaBusq = new Busqueda("pedro", "flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda("alexis", "lugano", "cgp", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa);
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	
}
