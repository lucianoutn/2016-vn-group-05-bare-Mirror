package F5;

import java.util.ArrayList;
import java.util.List;

import InterfacesExternas.OrigenDeDatos;

public class Mapa {

	// atributos
	private static List<PuntoDeInteres> puntosDeInteres = new ArrayList<>();
	private List<OrigenDeDatos> origenesDeDatos = new ArrayList<>();
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();

	// metodos

	
	
	public void anadirPOI(PuntoDeInteres poi) {
		puntosDeInteres.add(poi);
	}

	public static List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}

	public void setPuntosDeInteres(List<PuntoDeInteres> puntosDeInteres) {
		this.puntosDeInteres = puntosDeInteres;
	}

	public void eliminarPOI(PuntoDeInteres poi) {
		puntosDeInteres.remove(poi);
	}

	public boolean contiene(PuntoDeInteres unPoi) {
		return (puntosDeInteres.stream().anyMatch(x -> x == unPoi));
	}

	public void aniadirOrigenDeDatos(OrigenDeDatos origen) {
		origenesDeDatos.add(origen);
	}

	public static void limpiar() {
		busquedas.clear();
	}
	
	public static double cantidadDeMatcheosConPois(String unaFrase){
		return puntosDeInteres.stream().filter(x -> x.encuentra(unaFrase)).count();
	}
	
	public static List<Busqueda> getBusquedas() {
		return busquedas;
	}

	public static void agregarBusqueda(Busqueda busq) {
		busquedas.add(busq);
	}

}
