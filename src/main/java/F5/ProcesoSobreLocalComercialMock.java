package F5;

import java.io.File;
import java.time.LocalDate;

public class ProcesoSobreLocalComercialMock implements ProcesoSobreLocalComercial {
	private String ruta;
	private LocalDate fecha;
	private File archivo;
	private RepositorioDePOIs unMapa;
	private boolean localesComercialesActualizados;

	public ProcesoSobreLocalComercialMock(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map) {
		ruta = rutaArchivo;
		unMapa = map;
		fecha = horarioDeEjecucion;
	}

	public void ejecutar() {

		this.actualizarLocalComercial();
	}
	
	public void actualizarLocalComercial(){
		localesComercialesActualizados=true;
		
	}


}
