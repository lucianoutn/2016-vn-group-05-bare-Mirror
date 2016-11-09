package F5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import F5.Pois.PuntoDeInteres;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import Reportes.NotificadorDeBusqueda;

@Entity
public class Busqueda {

	@Id
	@GeneratedValue
	private int id_Busqueda;

	public int getId_Busqueda() {
		return id_Busqueda;
	}

	private String fraseBuscada;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_Terminal")
	private Terminal terminal;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Usuario usuario;

	private int cantResultados;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setCantResultados(int cantResultados) {
		this.cantResultados = cantResultados;
	}

	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}

	private LocalTime fecha;
	public int tiempoBusqueda;

	@ManyToMany
	@JoinTable(name = "Busqueda_Notificadores", joinColumns = @JoinColumn(name = "id_Busqueda"), inverseJoinColumns = @JoinColumn(name = "id"))
	private List<NotificadorDeBusqueda> listaObservers;

	public List<NotificadorDeBusqueda> getListaObservers() {
		return listaObservers;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Busqueda_POIs", joinColumns = @JoinColumn(name = "id_Busqueda"), inverseJoinColumns = @JoinColumn(name = "id_Poi"))
	private List<PuntoDeInteres> poisEncontrados = new ArrayList<PuntoDeInteres>();

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

	public void setTerminal(Terminal unaTerminal) {
		terminal = unaTerminal;
	}

	public Busqueda(Terminal unaTerminal, Usuario user, String frase) {
		listaObservers = unaTerminal.getListaObservadores();
		fecha = LocalTime.now();
		usuario = user;
		terminal = unaTerminal;
		fraseBuscada = frase;
	}

	public Busqueda() {

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
		poisEncontrados = unMapa.buscaPuntosDeInteresEnSistemaySistemasExternos(unaFrase, null);

		return poisEncontrados;
	}

	private void notificarBusqueda() {
		this.avisarAObservers();
		this.usuario.ejecutarAcciones();
	}

	private void actualizarTiempoBusqueda() {
		LocalTime tiempoFinBusqueda = LocalTime.now();
		this.tiempoBusqueda = tiempoFinBusqueda.toSecondOfDay() - this.fecha.toSecondOfDay();
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public int getCantResultados() {
		return cantResultados;
	}

	public String getNombresPoisEncontrados() {
		String nombresPoisEncontrados = null;
		this.poisEncontrados.stream().forEach(e -> nombresPoisEncontrados.concat(e.getNombrePoi()));

		return nombresPoisEncontrados;
	}
}
