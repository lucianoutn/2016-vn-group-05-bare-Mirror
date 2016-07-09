package F5;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotificadorDeDemoraDeBusquedaTest {

	private Mapa map;
	private Terminal miTerminal;
	private NotificadorDeAdministradorMock notiDeAdminPorMailMock;
	private int tiempoDeDemoraOriginal = Busqueda.tiempoParaNotificar;

	@Before
	public void Initialize() {
		map = new Mapa();
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
