package F5;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;


public class BusquedasPorFechaTest {
	public BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
	public List<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<NotificadorDeBusqueda>();
	public RepositorioDePOIs unMapa;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);		
	}

	@Test
	public void generarReporteDeUnaBusqueda(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		Busqueda unaBusq = new Busqueda(1,1,new Usuario("pedro", null),"flores", "101",listaDeUnReportero);
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
		Busqueda unaBusq = new Busqueda(1,2,new Usuario("pedro", null),"flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		Busqueda segundaBusqueda= new Busqueda(1,2,new Usuario("carlos", null), "recoleta", "101", listaDeUnReportero);
		segundaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
	}
	
}
