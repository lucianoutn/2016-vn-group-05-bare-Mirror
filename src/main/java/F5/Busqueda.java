package F5;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import F5.Pois.PuntoDeInteres;
import F5.Procesos.NotificadorDeBusqueda;
import F5.Reportes.RepositorioDeBusquedas;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import Mocks.NotificadorDeAdministradorMock;

public class Busqueda {

	// atributos

	private String fraseBuscada;
	private Terminal terminal;
	private Usuario usuario;
	private int cantResultados;
	private LocalDateTime fecha;
	private int tiempoBusqueda;
	private RepositorioDeBusquedas repositorioDeBusquedas;
	private NotificadorDeBusqueda notificadorDeBusqueda;

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public int getTiempoBusqueda() {
		return tiempoBusqueda;
	}

	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void agregarNotificadorDeBusqueda(NotificadorDeBusqueda unNotificador){
		notificadorDeBusqueda = unNotificador;
	}

	public Busqueda(Usuario user, Terminal unaTerminal, String frase, RepositorioDeBusquedas unRepoDeBusquedas, NotificadorDeBusqueda unNotificador) {
		fecha = LocalDateTime.now();
		usuario = user;
		terminal = unaTerminal;
		fraseBuscada = frase;
		repositorioDeBusquedas = unRepoDeBusquedas;
		notificadorDeBusqueda = unNotificador;
	}

	public List<PuntoDeInteres> buscoFrase(String unaFrase, RepositorioDePOIs unMapa) {

		this.setFraseBuscada(unaFrase);

		if (unaFrase == null || fraseBuscada == null)
			return unMapa.getPuntosDeInteres();
		else
			cantResultados = (int) unMapa.cantidadDeMatcheosConPois(unaFrase);

		actualizarTiempoBusqueda();
		this.notificarBusquedaSiCorresponde();
		repositorioDeBusquedas.agregarBusqueda(this);
		this.usuario.ejecutarAcciones();
		return unMapa.buscaPuntosDeInteresEnSistemaySistemasExternos(unaFrase, null);
	}

	private void notificarBusquedaSiCorresponde() {
		if (notificadorDeBusqueda!=null)
			notificadorDeBusqueda.notificarBusqueda(this);
	}

	private void actualizarTiempoBusqueda() {
		LocalDateTime tiempoFinBusqueda = LocalDateTime.now();
		tiempoBusqueda = this.calculaDiferenciaDeTiempo(tiempoFinBusqueda);
	}

	private int calculaDiferenciaDeTiempo(LocalDateTime tiempoFinBusqueda) {
		return fecha.toLocalTime().toSecondOfDay()-tiempoFinBusqueda.toLocalTime().toSecondOfDay();
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public int getCantResultados() {
		return cantResultados;
	}
}
