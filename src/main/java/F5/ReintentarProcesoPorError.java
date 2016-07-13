package F5;

public class ReintentarProcesoPorError implements IManejadorDeError{

	private int cantidadDeReintentos;
	private int contadorDeIntentos = 0;
	private boolean mandarMailSiFalla;
	private NotificadorDeAdministrador unNotificadorDeAdmin;
	private boolean mailEnviado = false;
	
	public ReintentarProcesoPorError(int reintentos, boolean enviaMailSiFalla, NotificadorDeAdministrador unNotificador) {
		cantidadDeReintentos = reintentos;
		mandarMailSiFalla = enviaMailSiFalla;
		if(enviaMailSiFalla==false)
			unNotificador=null;
		else
			unNotificadorDeAdmin = unNotificador;		
	}
	
	@Override
	public void manejarError(Proceso unProceso) {
		
		this.iterarProceso(unProceso);
		if(unProceso.getEstado()!=EstadosDelProceso.FinalizadoConExito)
			this.enviarMailSiCorresponde(unProceso);
	}

	private void iterarProceso(Proceso unProceso) {
		
		while(unProceso.getEstado()!=EstadosDelProceso.FinalizadoConExito && contadorDeIntentos<cantidadDeReintentos){
			unProceso.ejecutar();
			contadorDeIntentos++;
		}
	}//Fin de iterarProceso
	
	private void enviarMailSiCorresponde(Proceso unProceso) {
		
		if(mandarMailSiFalla){
			this.enviarMail();
		}	
	}//Fin de enviarMailSiCorresponde

	private void enviarMail() {
		//unNotificadorDeAdmin.enviarNotificacion();	//Uso el envio por mail porque en ese caso tambien se le enviaba al Admin
		mailEnviado = true;
	}//Fin de enviarMail

	public int getContadorDeIntentos() {
		return contadorDeIntentos;
	}
	
	public boolean isMailEnviado() {
		return mailEnviado;
	}

}
