package F5;

import java.time.LocalDate;

public abstract class ProcesoSobreLocalComercialClaseAbst extends Proceso {

	private LocalDate fecha;
	private boolean losLocalesComercialesEstanActualizados = false;
	private int horarioPlanificacion;
	private String ruta;
	private RepositorioDePOIs unMapa;
	
	
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
	
	public void seActualizaronLosLocalesComerciales(){
		losLocalesComercialesEstanActualizados = true;
	}

	
	
	public boolean isLocalesComercialesActualizados() {
		return losLocalesComercialesEstanActualizados;
	}
	
	public void anteCambioDeHorario(int horario) {
		if(horario == horarioPlanificacion)
			ejecutar();
	}
}
