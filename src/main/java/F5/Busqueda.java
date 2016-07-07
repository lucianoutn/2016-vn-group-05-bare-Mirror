package F5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Busqueda {

	// atributos
	public static int tiempoParaNotificar = 10;
	private String fraseBuscada;
	private String terminal;
	private String usuario;
	private int cantResultados;
	private LocalTime fecha;
	private int tiempoBusqueda;
	private ArrayList<Observers> listaObservers;

	// metodos
	
	public void setListaObservers(ArrayList<Observers> obs){
		listaObservers= obs;
	}
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public String getUsuario() {
		return usuario;
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

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public void setTiempoBusqueda(int unTiempo) {
		// debe ser parametrizable
		this.tiempoBusqueda = unTiempo; // en segundos
	}

	public void unaBusqueda(String user, String terminal, String frase) {
		fecha = LocalTime.now();
		usuario = user;
		this.terminal = terminal;
		fraseBuscada = frase;
		this.buscoFrase(frase);
		this.avisarAObservers();
		Mapa.agregarBusqueda(this);
	}

	
	private void avisarAObservers() {
		this.listaObservers.stream().forEach(x -> Observers.notificarBusqueda(this));
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

	public boolean buscoFrase(String unaFrase) {
		if (unaFrase == null || fraseBuscada == null)
			return true;
		this.setFraseBuscada(unaFrase);
		cantResultados = (int) Mapa.cantidadDeMatcheosConPois(unaFrase);
		return (Mapa.cantidadDeMatcheosConPois(unaFrase)>0);
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

	public void aplicarFiltros(List criterios, Busqueda unaBusqueda){
	}
	

}
