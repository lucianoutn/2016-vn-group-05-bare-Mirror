package F5;

public class MandarMailPorError implements IManejadorDeError{
	
	private NotificadorDeAdministrador notificadorDeAdministrador;
	private boolean mailEnviado;

	public MandarMailPorError(NotificadorDeAdministrador unNotificadorDeAdmin){
		notificadorDeAdministrador = unNotificadorDeAdmin;
		mailEnviado = false;
	}
	
	@Override
	public void manejarError(Proceso unProceso) {
		//notificadorDeAdministrador.enviarNotificacion();	De estar habilitado el envio por correo, esto va descomentado
		mailEnviado = true;
	}
	
	public boolean isMailEnviado() {
		return mailEnviado;
	}
	
}
