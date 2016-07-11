package F5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import F5.ProcesoSobrePOIS;
import InterfacesExternas.IBajaPoi;

public class ProcesoDeBajaPOI implements ProcesoSobrePOIS {

	private List<PuntoDeInteres> puntosDeInteresParaBajas = new ArrayList<>();
	private RepositorioDePOIs repoDePOIs;
	
	private IBajaPoi servicioBaja;
	// TODO para mi en el constructor iria la fecha de la baja.
	// tmb hay q poner la instancia de mapa que tiene el repo

	public ProcesoDeBajaPOI(RepositorioDePOIs mapa, LocalDate fecha, IBajaPoi servicio) {
		this.repoDePOIs = mapa;
		servicioBaja = servicio;
	}
	
	public RepositorioDePOIs getRepoDePOIs() {
		return repoDePOIs;
	}

	public void ejecutar() {
		this.pedirBajas();
		this.darDeBajaAPOI();
	}

	public void pedirBajas() {
		puntosDeInteresParaBajas = servicioBaja.poisADarDeBaja(); // el mock de rest esta devolviendo una lista vacia
	}

	public List<PuntoDeInteres> getPuntosDeInteresParaBajas() {
		return puntosDeInteresParaBajas;
	}

	public void darDeBajaAPOI() {
		this.puntosDeInteresParaBajas.forEach(poi -> repoDePOIs.eliminarPOI(poi));
	}

}
