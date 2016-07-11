package F5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Busqueda {

	// atributos
	public static int tiempoParaNotificar = 10;
	private String fraseBuscada;
	private String terminal;
	private Usuario usuario;
	private int cantResultados;
	private LocalTime fecha;
	private int tiempoBusqueda;
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

	/*
	 * public void setFecha(LocalTime fecha) { this.fecha = fecha; }
	 */

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/*
	 * public void setTiempoBusqueda(int unTiempo) { this.tiempoBusqueda =
	 * unTiempo; // en segundos }
	 */

	public static void setTiempoParaNotificar(int tiempoParaNotificar) {
		// debe ser parametrizable
		Busqueda.tiempoParaNotificar = tiempoParaNotificar; // en segundos
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

	public boolean realizadaPor(String unUsuario) {
		if (unUsuario == null || usuario == null)
			return true;
		return usuario.equals(unUsuario);
	}

	public boolean realizadaEn(String unaTerminal) {
		if (unaTerminal == null || terminal == null)
			return true;
		return terminal.equals(unaTerminal) || unaTerminal == null;
	}

	public boolean realizadaEnLaFecha(LocalTime unaFecha) {

		if (unaFecha == null)
			return true;
		return unaFecha.equals(fecha);
	}

	public List<PuntoDeInteres> buscoFrase(String unaFrase,RepositorioDePOIs unMapa) {

		this.setFraseBuscada(unaFrase);

		if (unaFrase == null || fraseBuscada == null)
			return unMapa.getPuntosDeInteres();
		else
			cantResultados = (int) unMapa.cantidadDeMatcheosConPois(unaFrase);

		LocalTime tiempoFinBusqueda = LocalTime.now();
		this.tiempoBusqueda = tiempoFinBusqueda.toSecondOfDay() - this.fecha.toSecondOfDay();
		this.avisarAObservers();
		this.usuario.ejecutarAcciones();
		return unMapa.buscaPuntosDeInteresEnSistemaySistemasExternos(unaFrase, null);
	}

	public boolean excedioDemora() {
		return tiempoBusqueda >= tiempoParaNotificar;
	}

	public String getTerminal() {
		return terminal;
	}

	public int getCantResultados() {
		return cantResultados;
	}
}
