package F5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResultadoPorTerminalTest {
	public ResultadosPorTerminal reporteroDeResultadosPorTerminal= new ResultadosPorTerminal();
	public ArrayList<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<>();
	public Mapa unMapa;
	
	@Before
	public void Initialize(){
		unMapa=new Mapa();
	}
	
	@Test
	public void reporteVacio(){
		Assert.assertEquals(0, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeUnaBusquedaEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",listaDeUnReportero);
		Busqueda unaBusq2 = new Busqueda("pedro","flores", "cgp",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEndosTerminalesDistintas(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",listaDeUnReportero);
		Busqueda unaBusq2 = new Busqueda("tony","lugano", "cgp",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
}
