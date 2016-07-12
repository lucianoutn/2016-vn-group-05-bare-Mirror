package F5;

import java.io.File;
import java.time.LocalDate;

public class ProcesoSobreLocalComercialMock extends ProcesoSobreLocalComercialClaseAbst {
	
	private int horarioPlanificacion;

	public void ejecutar() {
		if( LocalDate.now().equals(this.getFecha()))
		this.actualizarLocalComercial();
	}
	
	public ProcesoSobreLocalComercialMock(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map, int horaPlanificacion) {
		horarioDeEjecucion = horarioDeEjecucion;
		this.setRuta(rutaArchivo);
		setUnMapa(map);
		this.setFecha(horarioDeEjecucion);
	}
	
	public void actualizarLocalComercial(){
		this.seActualizaronLosLocalesComerciales();
	}
	
}
