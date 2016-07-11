package F5;

import java.io.File;
import java.time.LocalDate;

public class ProcesoSobreLocalComercialMock implements ProcesoSobreLocalComercial {
	private String ruta;
	private LocalDate fecha;
	private RepositorioDePOIs unMapa;
	private boolean localesComercialesActualizados= false;
	private int horarioPlanificacion;

	
	
	public boolean isLocalesComercialesActualizados() {
		return localesComercialesActualizados;
	}

	public ProcesoSobreLocalComercialMock(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map, int horaPlanificacion) {
		horarioDeEjecucion = horarioDeEjecucion;
		ruta = rutaArchivo;
		unMapa = map;
		fecha = horarioDeEjecucion;
	}

	public void ejecutar() {
		if( LocalDate.now().equals(fecha))
		this.actualizarLocalComercial();
	}
	
	public void actualizarLocalComercial(){
		localesComercialesActualizados=true;
		
	}
	
	@Override
	public void anteCambioDeHorario(int horario) {
		if(horario == horarioPlanificacion)
			ejecutar();
		
	}


}
