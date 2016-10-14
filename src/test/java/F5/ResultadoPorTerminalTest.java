package F5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Reportes.NotificadorDeBusqueda;
import Reportes.ResultadosPorTerminal;

public class ResultadoPorTerminalTest {
	
	private ResultadosPorTerminal reporteroDeResultadosPorTerminal= new ResultadosPorTerminal();
	private ArrayList<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<>();
	private RepositorioDePOIs unMapa;
	private Terminal unaTerminal;
	private Terminal otraTerminal;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		unaTerminal = new Terminal("flores", unMapa);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		
		otraTerminal = new Terminal("lugano", unMapa);
		otraTerminal.setListaObservadores(listaDeUnReportero);
	}
	
	@Test
	public void reporteVacio(){
		Assert.assertEquals(0, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeUnaBusquedaEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(unaTerminal,new Usuario("pedro", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEndosTerminalesDistintas(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(otraTerminal,new Usuario("tony", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
}
