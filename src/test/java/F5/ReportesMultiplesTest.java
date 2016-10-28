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
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;
import Reportes.ResultadosPorTerminal;

public class ReportesMultiplesTest {
	
	private BusquedasPorFecha reporteroDeBusquedas = new BusquedasPorFecha();
	private ArrayList<NotificadorDeBusqueda> listaDeDosReporteros = new ArrayList<>();
	private ResultadosPorTerminal reporteroDeResultadosPorTerminal = new ResultadosPorTerminal();
	private RepositorioDePOIs unMapa;
	private Terminal unaTerminal, otraTerminal;
	
	
	
	@Before
	public void initialize() {
		listaDeDosReporteros.add(reporteroDeBusquedas);
		listaDeDosReporteros.add(reporteroDeResultadosPorTerminal);
		ConsultorBancos consultorBancos = new ConsultorBancos(new SistemaExternoBancoMock("011"));
		ConsultorCGP consultorCgp = new ConsultorCGP(new SistemaExternoCGPMock("012"));
		unMapa=new RepositorioDePOIs(consultorBancos, consultorCgp);
		unaTerminal = new Terminal("flores",unMapa);
		unaTerminal.setListaObservadores(listaDeDosReporteros);
		otraTerminal = new Terminal("lugano",unMapa);
		otraTerminal.setListaObservadores(listaDeDosReporteros);
	}
	
	@After
	public void limpiarKvs(){
		KvsCache.clear();
	}
	

	@Test
	public void reportesVacio() {
		Assert.assertEquals(0, reporteroDeBusquedas.generarReporte().size());
		Assert.assertTrue(reporteroDeResultadosPorTerminal.generarReporte(null).isEmpty());
	}
		
	@Test
	public void reportesConUnaBusqueda() {
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		unaBusq.buscoFrase("101", unMapa);
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	@Test
	public void reportesConDosBusquedaEnUnaMismaTerminalParaFlores() {
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(unaTerminal,new Usuario("pedro", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa); 
		Assert.assertEquals(1, reporteroDeResultadosPorTerminal.generarReporte(unaTerminal).size());
	}
	
	@Test
	public void reportesConDosBusquedaEnUnaMismaTerminal() {
		unMapa.limpiarPuntosDeInteres();
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(unaTerminal,new Usuario("pedro", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa); 
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size()); 
	}
	@Test
	public void reportesConDosBusquedaEnDosTerminalesDistintas() {
		unMapa.limpiarPuntosDeInteres();
		Busqueda unaBusq = new Busqueda(unaTerminal,new Usuario("pedro", null),"101");
		Busqueda unaBusq2 = new Busqueda(otraTerminal,new Usuario("alexis", null),"cgp");
		unaBusq.buscoFrase("101", unMapa);
		unaBusq2.buscoFrase("cgp", unMapa);
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		Assert.assertEquals(2, reporteroDeResultadosPorTerminal.generarReporte(null).size());
	}
	
	
}
