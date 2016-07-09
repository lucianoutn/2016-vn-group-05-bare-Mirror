package F5;

import java.util.Properties;
import javax.mail.Session;

public class NotificadorDeAdministrador implements NotificadorDeBusqueda {

	// atributos
	
	
	// métodos
	
	public void notificarBusqueda(Busqueda unaBusqueda) {

		if (unaBusqueda.excedioDemora()) {
			
			this.enviarNotificacion();
			
		}

	}

	private void enviarNotificacion(){
		
		String smtpHostServer = "smtp.grupoF5.com.ar";
	    String emailID = "administrador@grupoF5.com.ar";
	    
	    Properties props = System.getProperties();

	    props.put("mail.smtp.host", smtpHostServer);

	    Session session = Session.getInstance(props, null);
	    
	    EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
		
	}
}
