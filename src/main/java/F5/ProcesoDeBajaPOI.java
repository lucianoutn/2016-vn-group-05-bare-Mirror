package F5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import F5.Proceso;
import InterfacesExternas.IBajaPoi;

public class ProcesoDeBajaPOI extends Proceso {

	private List<PuntoDeInteres> puntosDeInteresParaBajas = new ArrayList<>();
	private RepositorioDePOIs repoDePOIs;
	private int horarioPlanificacion;
	private EstadosDelProceso estado;
	
	private IBajaPoi servicioBaja;
	// TODO para mi en el constructor iria la fecha de la baja.
	// tmb hay q poner la instancia de mapa que tiene el repo

	public ProcesoDeBajaPOI(RepositorioDePOIs mapa, LocalDate fecha, IBajaPoi servicio, int horaPlanificacion) {
		this.repoDePOIs = mapa;
		servicioBaja = servicio;
		horarioPlanificacion = horaPlanificacion;
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

	@Override
	public void anteCambioDeHorario(int horario) {
		if(horario == horarioPlanificacion)
			ejecutar();
		
	}
	

}
