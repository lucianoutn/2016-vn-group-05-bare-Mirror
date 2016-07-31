package F5;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.SucursalDeBanco;
import F5.Reportes.Reporte;
import F5.Reportes.ReportePorFecha;
import F5.Reportes.ReportePorTerminal;
import F5.Reportes.RepositorioDeBusquedas;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import junit.framework.Assert;

public class ReportePorTerminalTest {

	private RepositorioDeBusquedas unRepositorioDeBusquedas;
	private Usuario unUsuario, otroUsuario;
	private Terminal unaTerminal, otraTerminal;
	private RepositorioDePOIs unRepositorioDePOIs;
	private ConsultorCGP unConsultorCGP;
	private ConsultorBancos unConsultorDeBancos;
	private Comuna unaComuna;
	private SucursalDeBanco unaSucursalDeBanco;
	private ReportePorTerminal unReportePorTerminal;
	
	
	@Before
	public void initialize(){
		
		unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		unConsultorDeBancos= new ConsultorBancos(new SistemaExternoBancoMock());
		unaSucursalDeBanco = new SucursalDeBanco("Servicios Financieros", null, new ArrayList<DiaAtencion>());
		unRepositorioDePOIs = new RepositorioDePOIs(unConsultorDeBancos, unConsultorCGP);
		unRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		
		unaComuna = new Comuna(3,null);
		
		unUsuario = new Usuario("pepe",unaComuna);
		otroUsuario = new Usuario("pedro",unaComuna);
		
		unRepositorioDeBusquedas = new RepositorioDeBusquedas();
		
		unaTerminal = new Terminal("flores", unRepositorioDePOIs, unRepositorioDeBusquedas);
		otraTerminal = new Terminal("caballito", unRepositorioDePOIs, unRepositorioDeBusquedas);
		
		unReportePorTerminal = new ReportePorTerminal(unRepositorioDeBusquedas);
								
	}
	
	@Test
	public void hayUnaBusquedaEnUnaTerminalYDosBusquedasEnOtraTerminal(){

		unaTerminal.buscarEnTerminal("Servicios Financieros", unUsuario);
		otraTerminal.buscarEnTerminal("Busco a Wally", otroUsuario);
		otraTerminal.buscarEnTerminal("CGP", otroUsuario);
		
		unReportePorTerminal.generarReporte();
		Assert.assertEquals(1,unReportePorTerminal.numeroDeBusquedasPara(unaTerminal));
		Assert.assertEquals(2, unReportePorTerminal.numeroDeBusquedasPara(otraTerminal));
	}
	
	@Test
	public void noHayBusquedas(){
		
		unReportePorTerminal.generarReporte();
		
		Assert.assertTrue(unReportePorTerminal.isNoHayReportes());
	}
	
	@After
	public void end(){
		unRepositorioDeBusquedas.limpiarBusquedas();
	}
	
}
