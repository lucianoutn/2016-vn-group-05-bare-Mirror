package F5;

import java.nio.charset.UnmappableCharacterException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.INITIALIZE;

import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;


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
		Busqueda unaBusq = new Busqueda(new Usuario("pedro"),"flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		
	}
	
	@Test
	public void generarReporteVacio(){
		Assert.assertEquals(0, reporteroDeBusquedas.generarReporte().size());
	}
	
	@Test
	public void generarReporteDeDosBusquedas(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		Busqueda unaBusq = new Busqueda(new Usuario("pedro"),"flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		Busqueda segundaBusqueda= new Busqueda(new Usuario("carlos"), "recoleta", "101", listaDeUnReportero);
		segundaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
	}
	
}
