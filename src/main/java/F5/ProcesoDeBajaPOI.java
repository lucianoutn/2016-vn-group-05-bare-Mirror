package F5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import F5.ProcesoSobrePOIS;

public class ProcesoDeBajaPOI implements ProcesoSobrePOIS {

	private List<PuntoDeInteres> puntosDeInteresParaBajas = new ArrayList<>();
	private RepositorioDePOIs mapa;
	// TODO para mi en el constructor iria la fecha de la baja.
	// tmb hay q poner la instancia de mapa que tiene el repo

	public ProcesoDeBajaPOI(RepositorioDePOIs mapa, LocalDate fecha) {
		this.mapa = mapa;
	}

	public void ejecutar() {
		this.pedirBajas();
		this.darDeBajaAPOI();
	}

	public void pedirBajas() {
		// TODO usa un servicio REST que devuelve los POIS (alguna forma de
		// identificarlo y la fecha en la que le tenemos que dar la baja

	}

	public void darDeBajaAPOI() {
		this.puntosDeInteresParaBajas.forEach(poi -> mapa.eliminarPOI(poi));

	}

}
