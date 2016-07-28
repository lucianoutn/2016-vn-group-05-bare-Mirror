package F5;

import java.time.LocalDate;

public class ProcesoSobreLocalComercialMock extends ProcesoSobreLocalComercialClaseAbst {
	
	public void ejecutar() {
		if( LocalDate.now().equals(this.getFecha())){
			this.pasarAEjecutando();
			this.actualizarLocalComercial();
			this.almacenarResultadoDeEjecucion();
		}
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
