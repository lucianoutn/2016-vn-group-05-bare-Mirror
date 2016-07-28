package F5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import F5.Proceso;
import InterfacesExternas.IBajaPoi;

public class ProcesoDeBajaPOI extends Proceso {

	private List<PuntoDeInteres> puntosDeInteresParaBajas = new ArrayList<>();
	private RepositorioDePOIs repoDePOIs;
	private IBajaPoi servicioBaja;
	private Planificador planificador;
	// TODO para mi en el constructor iria la fecha de la baja.
	// tmb hay q poner la instancia de mapa que tiene el repo

	public ProcesoDeBajaPOI(RepositorioDePOIs mapa, IBajaPoi servicio, int horaPlanificacion,
			Planificador unPlanificador) {
		repoDePOIs = mapa;
		servicioBaja = servicio;
		planificador = unPlanificador;
		this.setHorarioPlanificacion(horaPlanificacion);
		this.inicializarEstado();
	}

	public RepositorioDePOIs getRepoDePOIs() {
		return repoDePOIs;
	}

	public void ejecutar() {
		// this.pasarAEnEspera(); //TODO
		planificador.solicitarEjecucion(this);
	}

	public void ejecutarPosta() {
		this.pasarAEjecutando();
		this.pedirBajas();
		this.darDeBajaAPOI();
		this.almacenarResultadoDeEjecucion();
		planificador.liberarEjecucion();
	}

	public void pedirBajas() {
		puntosDeInteresParaBajas = servicioBaja.poisADarDeBaja(); // el mock de
																	// rest esta
																	// devolviendo
																	// una lista
																	// vacia
		this.setCantidadDeElementosAfectados(puntosDeInteresParaBajas.size());
	}

	public List<PuntoDeInteres> getPuntosDeInteresParaBajas() {
		return puntosDeInteresParaBajas;
	}

	public void darDeBajaAPOI() {
		this.puntosDeInteresParaBajas.forEach(poi -> repoDePOIs.eliminarPOI(poi));
	}

}
