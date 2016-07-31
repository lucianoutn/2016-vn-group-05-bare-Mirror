package F5;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Reportes.RepositorioDeBusquedas;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class BusquedaTest {
	
	RepositorioDePOIs unMapa;
	Busqueda unaBusqueda;	
	Usuario unUsuario, otroUsuario;
	Comuna unaComuna;
	Terminal terminalDeFlores;
	RepositorioDeBusquedas unRepositorioDeBusquedas;
	
	@Before
	public void Initialize(){
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		unaComuna = new Comuna(3,null);
		
		unUsuario = new Usuario("pepe",unaComuna);
		otroUsuario = new Usuario("pedro",unaComuna);
		
		unRepositorioDeBusquedas = new RepositorioDeBusquedas();
		
		terminalDeFlores = new Terminal("flores",unMapa,unRepositorioDeBusquedas);		
	}
	
	@Test
	public void buscarUnaSucursalDeBancoEnUnMapaYEncontrarla(){
		Busqueda unaBusqueda = new Busqueda(unUsuario,terminalDeFlores,"cgp",unRepositorioDeBusquedas,null);	
		SucursalDeBanco unaSucursalDeBanco = new SucursalDeBanco("Servicios Financieros", null, new ArrayList<DiaAtencion>());
		unMapa.anadirPOI(unaSucursalDeBanco);
		Assert.assertTrue("Deberia encontrar el banco",unaBusqueda.buscoFrase("Servicios Financieros", unMapa).contains(unaSucursalDeBanco));
	}
	
	@Test
	public void buscarEnMapaConUnElementoYEncontrarlo(){

		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
		
		Busqueda unaBusqueda = new Busqueda(otroUsuario,terminalDeFlores,"101",unRepositorioDeBusquedas,null);
		unaBusqueda.buscoFrase("101", unMapa);

		Assert.assertTrue(unaBusqueda.buscoFrase("101",unMapa).contains(parada101));
	}
	
	
	@Test
	public void buscarEnMapaConDosElementosYEncuentroUnoDeEllos(){
		
		unMapa.limpiarPuntosDeInteres();
		ParadaDeColectivo parada101 = new ParadaDeColectivo("Mozart", "2500", null, "101");
		ArrayList<PuntoDeInteres> pois = new ArrayList<PuntoDeInteres>();
		pois.add(parada101);
		unMapa.setPuntosDeInteres(pois);
	
		Busqueda unaBusqueda = new Busqueda(unUsuario,terminalDeFlores, "cgp", unRepositorioDeBusquedas,null);
		Busqueda unaBusq = new Busqueda(otroUsuario, terminalDeFlores, "101", unRepositorioDeBusquedas,null);
		Assert.assertTrue(unaBusq.buscoFrase("101",unMapa).contains(parada101));
	}

}
