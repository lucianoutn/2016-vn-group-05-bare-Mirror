package F5;

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
import Mocks.NotificadorDeAdministradorMock;

public class NotificadorDeDemoraDeBusquedaTest {

	private RepositorioDePOIs map;
	private Terminal miTerminal, unaTerminal, otraTerminal;
	private NotificadorDeAdministradorMock notiDeAdminPorMailMock;
	
	

	@Before
	public void Initialize() {
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("013"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("014"));

		map = new RepositorioDePOIs(consultorBanco, unConsultorCGP);

		miTerminal = new Terminal("terminal1",map);
		notiDeAdminPorMailMock = new NotificadorDeAdministradorMock();
		miTerminal.activarAccion(notiDeAdminPorMailMock);

		unaTerminal = new Terminal("flores",map);
		otraTerminal = new Terminal("recoleta",map);
	}
	
	@After
	public void limpiarKvs(){
		KvsCache.clear();
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
}
