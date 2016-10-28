package F5;

import java.util.ArrayList;
import java.util.List;

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
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;


public class BusquedasPorFechaTest {
	
	private BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
	private List<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<NotificadorDeBusqueda>();
	private RepositorioDePOIs unMapa;
	private Terminal unaTerminal;
	private Terminal otraTerminal;
	
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("027"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("028"));
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);		
		
		unaTerminal = new Terminal("flores",unMapa);
		otraTerminal = new Terminal("recoleta",unMapa);
	}

	@After
	public void limpiarKvs(){
		KvsCache.clear();
	}
	
	@Test
	public void generarReporteDeUnaBusqueda(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null), "101");
		unaBusq.buscoFrase("101", unMapa);
		
		Assert.assertEquals(1,reporteroDeBusquedas.generarReporte().size());
		
	}
	
	@Test
	public void generarReporteVacio(){
		Assert.assertTrue(reporteroDeBusquedas.generarReporte().isEmpty());
	}
	
	@Test
	public void generarReporteDeDosBusquedas(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		otraTerminal.setListaObservadores(listaDeUnReportero);
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		unaBusq.buscoFrase("101", unMapa);
		Busqueda segundaBusqueda= new Busqueda(otraTerminal,new Usuario("carlos", null),"101");
		segundaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
	}
	
}
