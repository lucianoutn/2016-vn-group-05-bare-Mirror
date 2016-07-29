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
	// private int tiempoDeDemoraOriginal = Busqueda.tiempoParaNotificar; //no
	// hace falta xq no es mas estatico

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
		notiDeAdminPorMailMock.setTiempoParaNotificar(-1);
		miTerminal.buscarEnTerminal("unString", new Usuario("unUser", null));
		Assert.assertTrue(notiDeAdminPorMailMock.notificado);

	}

	@Test
	public void siUnaBusquedaDemoraMenosDelTiempoEstipuladoNoSeNotifica() {
		notiDeAdminPorMailMock.setTiempoParaNotificar(10);
		miTerminal.buscarEnTerminal("unString", new Usuario("unUser", null));
		Assert.assertFalse(notiDeAdminPorMailMock.notificado);
	}

	// no hace falta xq no es mas estatico
	/*
	 * @After public void volverASetearLaDemoraComoEstaba() {
	 * notiDeAdminPorMailMock.setTiempoParaNotificar(this.tiempoDeDemoraOriginal
	 * ); }
	 */
}
