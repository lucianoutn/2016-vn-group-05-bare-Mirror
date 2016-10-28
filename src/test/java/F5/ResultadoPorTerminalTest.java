package F5;

import java.util.ArrayList;

import org.junit.After;
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
import Kvs.KvsCache;
import Reportes.NotificadorDeBusqueda;
import Reportes.ResultadosPorTerminal;

public class ResultadoPorTerminalTest {
	
	private ResultadosPorTerminal reporteroDeResultadosPorTerminal= new ResultadosPorTerminal();
	private ArrayList<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<>();
	private RepositorioDePOIs unMapa;
	private Terminal unaTerminal, otraTerminal;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("017"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("018"));
				
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		unaTerminal = new Terminal("flores",unMapa);
		otraTerminal = new Terminal("lugano",unMapa);
	}
	
	@After
	public void limpiarKvs(){
		KvsCache.clear();
	}
	
	@Test
	public void reporteVacio(){
		Assert.assertEquals(0, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeUnaBusquedaEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null), "101");
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEnUnaTerminal(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(unaTerminal,new Usuario("pedro", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reporteDeDosBusquedasEndosTerminalesDistintas(){
		listaDeUnReportero.add(reporteroDeResultadosPorTerminal);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		otraTerminal.setListaObservadores(listaDeUnReportero);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(otraTerminal,new Usuario("tony", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("101", unMapa);
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
}
