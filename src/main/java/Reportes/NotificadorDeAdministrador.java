package Reportes;

import java.util.Properties;
import javax.mail.Session;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import F5.Busqueda;
import F5.Persistencia.PersistidorDePOIs;
import F5.Persistencia.PersistidorDeReportes;
import F5.Procesos.EmailUtil;

@Entity

public class NotificadorDeAdministrador extends NotificadorDeBusqueda {

	public int tiempoParaNotificar = 10; // debe ser parametrizable
	
	//metodos

	public NotificadorDeAdministrador() {


	}

	public void notificarBusqueda(Busqueda unaBusqueda) {
		if (this.excedioDemora(unaBusqueda)) {
			this.enviarNotificacion();
		}
	}

	public void enviarNotificacion() {

		String smtpHostServer = "smtp.grupoF5.com.ar";
		String emailID = "administrador@grupoF5.com.ar";

		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHostServer);

		Session session = Session.getInstance(props, null);

		EmailUtil.sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	public void setTiempoParaNotificar(int tiempoParaNotificar) {
		this.tiempoParaNotificar = tiempoParaNotificar; // en segundos
	}

	public boolean excedioDemora(Busqueda unaBusqueda) {
		return unaBusqueda.tiempoBusqueda >= this.tiempoParaNotificar;
	}
}
