package F5;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Reportes.RepositorioDeBusquedas;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Mocks.NotificadorDeAdministradorMock;

public class NotificadorDeDemoraDeBusquedaTest {

	private RepositorioDePOIs map;
	private Terminal miTerminal;
	private NotificadorDeAdministradorMock notiDeAdminPorMailMock;
	private RepositorioDeBusquedas repositorioDeBusquedas;
	// private int tiempoDeDemoraOriginal = Busqueda.tiempoParaNotificar; //no
	// hace falta xq no es mas estatico

	@Before
	public void Initialize() {
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		map = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		notiDeAdminPorMailMock = new NotificadorDeAdministradorMock();
		repositorioDeBusquedas = new RepositorioDeBusquedas();
		miTerminal = new Terminal("terminal1", map, repositorioDeBusquedas);
		miTerminal.setUnNotificadorDeBusqueda(notiDeAdminPorMailMock);
	}

	@Test
	public void siUnaBusquedaDemoraMasDelTiempoEstipuladoSeEnviaNoti() {
		notiDeAdminPorMailMock.setTiempoParaNotificar(-1);
		miTerminal.buscarEnTerminal("unString", new Usuario("unUser", null));
		Assert.assertTrue(notiDeAdminPorMailMock.isNotificado());
	}

	@Test
	public void siUnaBusquedaDemoraMenosDelTiempoEstipuladoNoSeNotifica() {
		notiDeAdminPorMailMock.setTiempoParaNotificar(10);
		miTerminal.buscarEnTerminal("unString", new Usuario("unUser", null));
		Assert.assertFalse(notiDeAdminPorMailMock.notificado);
	}
}
