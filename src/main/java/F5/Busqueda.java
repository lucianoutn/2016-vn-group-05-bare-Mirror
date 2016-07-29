package F5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Busqueda {

	// atributos

	private String fraseBuscada;
	private String terminal;
	private Usuario usuario;
	private int cantResultados;
	private LocalTime fecha;
	public int tiempoBusqueda;
	private List<NotificadorDeBusqueda> listaObservers;

	// metodos

	public void setListaObservers(List<NotificadorDeBusqueda> obs) {
		listaObservers = obs;
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public LocalTime getFecha() {
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

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Busqueda(Usuario user, String terminal, String frase, List<NotificadorDeBusqueda> listaObservadores) {
		listaObservers = listaObservadores;
		fecha = LocalTime.now();
		usuario = user;
		this.terminal = terminal;
		fraseBuscada = frase;
	}

	private void avisarAObservers() {
		if (listaObservers != null)
			this.listaObservers.stream().forEach(x -> x.notificarBusqueda(this));
	}

	public List<PuntoDeInteres> buscoFrase(String unaFrase, RepositorioDePOIs unMapa) {

		this.setFraseBuscada(unaFrase);

		if (unaFrase == null || fraseBuscada == null)
			return unMapa.getPuntosDeInteres();
		else
			cantResultados = (int) unMapa.cantidadDeMatcheosConPois(unaFrase);

		actualizarTiempoBusqueda();
		notificarBusqueda();
		return unMapa.buscaPuntosDeInteresEnSistemaySistemasExternos(unaFrase, null);
	}

	private void notificarBusqueda() {
		this.avisarAObservers();
		this.usuario.ejecutarAcciones();
	}

	private void actualizarTiempoBusqueda() {
		LocalTime tiempoFinBusqueda = LocalTime.now();
		this.tiempoBusqueda = tiempoFinBusqueda.toSecondOfDay() - this.fecha.toSecondOfDay();
	}

	public String getTerminal() {
		return terminal;
	}

	public int getCantResultados() {
		return cantResultados;
	}
}
