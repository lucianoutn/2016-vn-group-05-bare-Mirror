package F5;

import java.util.ArrayList;
import java.util.List;

import F5.Pois.PuntoDeInteres;

public class DatosDeBusquedaParaPersistir {

	private int cd_Busqueda;
	private int cd_Terminal;
	private int tiempoBusqueda;
	private int cantidadDeResultados;
	private String fraseBuscada;
	private List<PuntoDeInteres> poisEncontrados = new ArrayList<>();

	public int getCd_Busqueda() {
		return cd_Busqueda;
	}
	
	public int getCd_Terminal() {
		return cd_Terminal;
	}

	public int getTiempoBusqueda() {
		return tiempoBusqueda;
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public List<PuntoDeInteres> getPoisEncontrados() {
		return poisEncontrados;
	}

	public void almacenarDatosDeBusqueda(int cd_Busqueda, Integer cd_Terminal, String fraseBuscada, int tiempoBusqueda,
			List<PuntoDeInteres> poisEncontrados) {
		this.cd_Busqueda = cd_Busqueda;
		this.cd_Terminal = cd_Terminal;
		this.fraseBuscada = fraseBuscada;
		this.tiempoBusqueda = tiempoBusqueda;
		this.poisEncontrados = poisEncontrados;
		cantidadDeResultados = poisEncontrados.size();
	}
	
	

}
