package F5;


public abstract class Proceso implements INotificarCambioHorario{
		
	private EstadosDelProceso estado;
	
	public abstract void ejecutar();	
	
	public void pasarAEjecutando(EstadosDelProceso Ejecutando){
		estado = Ejecutando;
	}
	
	public void pasarAFinalizadoConExito(EstadosDelProceso FinalizadoConExito) {
		estado = FinalizadoConExito;
	}
	
	public void pasarAFinalizadoConError(EstadosDelProceso FinalizadoConError) {
		estado = FinalizadoConError;
	}

}
