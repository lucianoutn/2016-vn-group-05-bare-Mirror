package F5.Procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import F5.Procesos.Proceso;
import F5.Terminal.RepositorioDePOIs;

public class ProcesoSobreLocalComercial extends Proceso {

	private File archivo;
	private ILectorArchivoLocalComercial traductor;
	private Planificador planificador;
	private LocalDate fecha;
	private boolean losLocalesComercialesEstanActualizados = false;
	private String ruta;
	private RepositorioDePOIs unMapa;
	

	public ProcesoSobreLocalComercial(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map,
			int horaPlanificacion, Planificador unPlanificador, ILectorArchivoLocalComercial lector) {

		this.setRuta(rutaArchivo);
		setUnMapa(map);
		this.setFecha(horarioDeEjecucion);
		traductor = lector;
		this.inicializarEstado();
		planificador = unPlanificador;
	}

	public void hacerOperacionesDeCadaProceso() { // este era el EX ejecutar()
		// this.pasarAEnEspera(); //TODO
		planificador.solicitarEjecucion(this);
	}

	public void ejecutar() {
		this.pasarAEjecutando();
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
	
	public void seActualizaronLosLocalesComerciales(){
		losLocalesComercialesEstanActualizados = true;
	}

	public boolean isLocalesComercialesActualizados() {
		return losLocalesComercialesEstanActualizados;
	}
	
	
	
	public void setUnMapa(RepositorioDePOIs unMapa) {
		this.unMapa = unMapa;
	}
	
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public RepositorioDePOIs getUnMapa() {
		return unMapa;
	}

	public String getRuta() {
		return ruta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate unaFecha) {
		fecha = unaFecha;
	}

}
