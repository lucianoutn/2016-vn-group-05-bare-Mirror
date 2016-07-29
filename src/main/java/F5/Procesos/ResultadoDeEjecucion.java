package F5.Procesos;

import java.time.LocalDateTime;

public class ResultadoDeEjecucion {
	private int cantidadDeElementosAfectados;
	private LocalDateTime fechaDeEjecucion;
	private EstadosDelProceso estadoDelProcesoAlFinalizar;
	
	
	public int getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}
	
	public void setCantidadDeElementosAfectados(int cantidadDeElementosAfectados) {
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
	}
	
	
	public LocalDateTime getFechaDeEjecucion() {
		return fechaDeEjecucion;
	}
	
	public void setFechaDeEjecucion(LocalDateTime fechaDeEjecucion) {
		this.fechaDeEjecucion = fechaDeEjecucion;
	}


	public EstadosDelProceso getEstadoDelProcesoAlFinalizar() {
		return estadoDelProcesoAlFinalizar;
	}
	
	public void setEstadoDelProcesoAlFinalizar(EstadosDelProceso estadoDelProcesoAlFinalizar) {
		this.estadoDelProcesoAlFinalizar = estadoDelProcesoAlFinalizar;
	}
}
