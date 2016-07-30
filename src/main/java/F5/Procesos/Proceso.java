package F5.Procesos;

import java.time.LocalDateTime;

import F5.Terminal.INotificarCambioHorario;

public abstract class Proceso implements INotificarCambioHorario {

	private EstadosDelProceso estado;
	private IManejadorDeError manejadorDeError;
	private ResultadoDeEjecucion resultadoDeEjecucion = new ResultadoDeEjecucion();
	private int cantidadDeElementosAfectados, horarioPlanificacion;


	public abstract void ejecutarPosta();

	
	public void ejecutar(){
		this.pasarAEnEspera();
		this.hacerOperacionesDeCadaProceso();
		this.almacenarResultadoDeEjecucion();
	}
	
	public abstract void hacerOperacionesDeCadaProceso();

	public void pasarAEjecutando(){

		estado = EstadosDelProceso.Ejecutando; //puede tambien quedar en espera en realidad.lucho
	}

	public void pasarAFinalizadoConExito() {
		estado = EstadosDelProceso.FinalizadoConExito;
	}

	public void pasarAFinalizadoConError() {
		estado = EstadosDelProceso.FinalizadoConError;
	}
	
	public void pasarAEnEspera(){
		estado = EstadosDelProceso.EnEspera;
	}

	public EstadosDelProceso getEstado() {
		return estado;
	}

	public IManejadorDeError getManejadorDeError() {
		return manejadorDeError;
	}

	public void setManejadorDeError(IManejadorDeError manejadorDeError) {
		this.manejadorDeError = manejadorDeError;
	}

	public void inicializarEstado() {
		estado = EstadosDelProceso.SinIniciar;
	}

	public void almacenarResultadoDeEjecucion() {
		resultadoDeEjecucion.setFechaDeEjecucion(LocalDateTime.now());
		resultadoDeEjecucion.setEstadoDelProcesoAlFinalizar(estado);
		resultadoDeEjecucion.setCantidadDeElementosAfectados(cantidadDeElementosAfectados);
	}

	public int getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}

	public void setCantidadDeElementosAfectados(int cantidadDeElementosAfectados) {
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
	}

	public LocalDateTime getHoraDeEjecucion() {
		return this.resultadoDeEjecucion.getFechaDeEjecucion();
	}

	public int getHorarioPlanificacion() {
		return horarioPlanificacion;
	}

	public void setHorarioPlanificacion(int horarioPlanificacion) {
		this.horarioPlanificacion = horarioPlanificacion;
	}

	public void anteCambioDeHorario(int horario) {
		if (horario == this.getHorarioPlanificacion())
			ejecutar();
	}

}
