package F5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import F5.Pois.PuntoDeInteres;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Usuario;

public class Busqueda {

	// atributos

	private int cd_Busqueda;	//Busqueda va a tener clave compuesta, siendo sus componentes cd_Terminal y cd_Busqueda
	private Integer cd_Terminal;
	private String fraseBuscada;
	private String terminal;
	private Usuario usuario;
	private int cantResultados;
	private LocalTime fecha;
	public int tiempoBusqueda;
	private List<NotificadorDeBusqueda> listaObservers;
	private List<PuntoDeInteres> poisEncontrados = new ArrayList<PuntoDeInteres>();
	private DatosDeBusquedaParaPersistir datosDeBusquedaParaPersistir = new DatosDeBusquedaParaPersistir();
	private PersistenciaDeBusquedas repositorioDeBusquedas = new PersistenciaDeBusquedas();

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

	public Busqueda(int id_Busqueda,int id_Terminal,Usuario user,String terminal,String frase,List<NotificadorDeBusqueda> listaObservadores) {
		cd_Busqueda = id_Busqueda;
		cd_Terminal = this.asignarCodigoDeTerminal(id_Terminal);
		listaObservers = listaObservadores;
		fecha = LocalTime.now();
		usuario = user;
		this.terminal = terminal;
		fraseBuscada = frase;
	}

	private Integer asignarCodigoDeTerminal(Integer id_Terminal) {
		if(id_Terminal == null)
			return -1;
		return id_Terminal;
	}	//Con este metodo podemos crear Busquedas sin un cd_Terminal, para testear y usar Busqueda de manera desacoplada de Terminal

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
		this.enviarLosResultadosDeBusquedaAlRepositorioDeBusquedas();
		
		return poisEncontrados;
	}

	private void enviarLosResultadosDeBusquedaAlRepositorioDeBusquedas() {
		datosDeBusquedaParaPersistir.almacenarDatosDeBusqueda(cd_Busqueda, cd_Terminal,fraseBuscada, tiempoBusqueda,poisEncontrados);
		repositorioDeBusquedas.almacenarDatosEnAtributosParaPersistir(datosDeBusquedaParaPersistir);
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
