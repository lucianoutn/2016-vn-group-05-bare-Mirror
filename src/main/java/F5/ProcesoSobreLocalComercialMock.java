package F5;

import java.time.LocalDate;

public class ProcesoSobreLocalComercialMock extends ProcesoSobreLocalComercialClaseAbst {

	private Planificador planificador;

	public ProcesoSobreLocalComercialMock(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map,
			int horaPlanificacion, Planificador unPlanificador) {
		this.setRuta(rutaArchivo);
		setUnMapa(map);
		this.setFecha(horarioDeEjecucion);
		this.inicializarEstado();
		planificador = unPlanificador;
	}

	public void hacerOperacionesDeCadaProceso() { // este era el EX ejecutar()
		// this.pasarAEnEspera(); //TODO
		planificador.solicitarEjecucion(this);
	}

	public void ejecutarPosta() {

		if (LocalDate.now().equals(this.getFecha()))
			this.actualizarLocalComercial();
		planificador.liberarEjecucion();

	}

	public void actualizarLocalComercial() {
		this.seActualizaronLosLocalesComerciales();
	}

}
