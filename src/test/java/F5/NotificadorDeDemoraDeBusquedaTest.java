package F5;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class NotificadorDeDemoraDeBusquedaTest {

	private RepositorioDePOIs map;
	private Terminal miTerminal;
	private NotificadorDeAdministradorMock notiDeAdminPorMailMock;
	private int tiempoDeDemoraOriginal = Busqueda.tiempoParaNotificar;

	@Before
	public void Initialize() {
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		map = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		String nombre = "terminal1";
		miTerminal = new Terminal(nombre, map);
		notiDeAdminPorMailMock = new NotificadorDeAdministradorMock();
		miTerminal.activarAccion(notiDeAdminPorMailMock);

	}

	@Test
	public void siUnaBusquedaDemoraMasDelTiempoEstipuladoSeEnviaNoti() {
		Busqueda.setTiempoParaNotificar(-1);
		miTerminal.buscarEnTerminal("unString", "unUser");
		Assert.assertTrue(notiDeAdminPorMailMock.notificado);

	}

	@Test
	public void siUnaBusquedaDemoraMenosDelTiempoEstipuladoNoSeNotifica() {
		Busqueda.setTiempoParaNotificar(10);
		miTerminal.buscarEnTerminal("unString", "unUser");
		Assert.assertFalse(notiDeAdminPorMailMock.notificado);
	}
	
	@After
	public void volverASetearLaDemoraComoEstaba(){
		Busqueda.setTiempoParaNotificar(this.tiempoDeDemoraOriginal);
	}

}
