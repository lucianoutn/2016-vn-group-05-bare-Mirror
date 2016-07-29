package F5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class ResultadoPorTerminalTest {
	public ResultadosPorTerminal reporteroDeResultadosPorTerminal= new ResultadosPorTerminal();
	public ArrayList<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<>();
	public RepositorioDePOIs unMapa;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
	}
	
	@Test
	public void reporteVacio(){
		Assert.assertEquals(0, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeUnaBusquedaEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda(new Usuario("pedro", null),"flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda(new Usuario("pedro", null),"flores", "101",listaDeUnReportero);
		Busqueda unaBusq2 = new Busqueda(new Usuario("pedro", null),"flores", "cgp",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEndosTerminalesDistintas(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda(new Usuario("pedro", null),"flores", "101",listaDeUnReportero);
		Busqueda unaBusq2 = new Busqueda(new Usuario("tony", null),"lugano", "cgp",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
}
