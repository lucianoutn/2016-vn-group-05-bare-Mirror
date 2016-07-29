package F5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import F5.Proceso;

public class ProcesoSobreLocalComercial extends ProcesoSobreLocalComercialClaseAbst {

	private File archivo;
	private lectorArchivoLocalComercial traductor;
	private Planificador planificador;

	public ProcesoSobreLocalComercial(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map,
			int horaPlanificacion, Planificador unPlanificador) {

		this.setRuta(rutaArchivo);
		setUnMapa(map);
		this.setFecha(horarioDeEjecucion);
		traductor = new lectorArchivoLocalComercial();
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
		archivo = new File(this.getRuta());
		this.seActualizaronLosLocalesComerciales();
		try {
			traductor.leerArchivo(archivo, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
