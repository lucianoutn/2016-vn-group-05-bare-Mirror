package F5;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class ReportesMultiplesTest {
	public BusquedasPorFecha reporteroDeBusquedas = new BusquedasPorFecha();
	public ArrayList<NotificadorDeBusqueda> listaDeDosReporteros = new ArrayList<>();
	public ResultadosPorTerminal reporteroDeResultadosPorTerminal = new ResultadosPorTerminal();
	public RepositorioDePOIs unMapa;
	@Before
	public void initialize() {
		listaDeDosReporteros.add(reporteroDeBusquedas);
		listaDeDosReporteros.add(reporteroDeResultadosPorTerminal);
		ConsultorBancos consultorBancos = new ConsultorBancos(new SistemaExternoBancoMock());
		ConsultorCGP consultorCgp = new ConsultorCGP(new SistemaExternoCGPMock());
		unMapa=new RepositorioDePOIs(consultorBancos, consultorCgp);
	}
	
	

	@Test
	public void reportesVacio() {
		Assert.assertEquals(0, reporteroDeBusquedas.generarReporte().size());
		Assert.assertTrue(reporteroDeResultadosPorTerminal.generarReporte(null).isEmpty());
	}
		
	@Test
	public void reportesConUnaBusqueda() {
		Busqueda unaBusq = new Busqueda(2,1,new Usuario("pedro", null), "flores", "101", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reportesConDosBusquedaEnUnaMismaTerminalParaFlores() {
		
		Busqueda unaBusq = new Busqueda(2,2,new Usuario("pedro", null),"flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda(2,3,new Usuario("pedro", null), "flores", "cgp", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa); 
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte("flores").size());
	}
	
	@Test
	public void reportesConDosBusquedaEnUnaMismaTerminal() {
		unMapa.limpiarPuntosDeInteres();
		Busqueda unaBusq = new Busqueda(2,4,new Usuario("pedro", null),"flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda(2,5,new Usuario("pedro", null), "flores", "cgp", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa); 
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size()); 
	}
	@Test
	public void reportesConDosBusquedaEnDosTerminalesDistintas() {
		unMapa.limpiarPuntosDeInteres();
		Busqueda unaBusq = new Busqueda(2,6,new Usuario("pedro", null), "flores", "101", listaDeDosReporteros);
		Busqueda unaBusq2 = new Busqueda(2,7,new Usuario("alexis", null), "lugano", "cgp", listaDeDosReporteros);
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa);
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	
}
