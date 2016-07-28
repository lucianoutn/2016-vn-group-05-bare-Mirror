package F5;

import java.time.LocalDate;

public class ProcesoSobreLocalComercialMock extends ProcesoSobreLocalComercialClaseAbst {
	
	public void hacerOperacionesDeCadaProceso() {
		if(LocalDate.now().equals(this.getFecha()))
			this.actualizarLocalComercial();
	}
	
	public ProcesoSobreLocalComercialMock(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map, int horaPlanificacion) {
		this.setRuta(rutaArchivo);
		setUnMapa(map);
		this.setFecha(horarioDeEjecucion);
		this.inicializarEstado();
	}
	
	public void actualizarLocalComercial(){
		this.seActualizaronLosLocalesComerciales();
	}
	
}
