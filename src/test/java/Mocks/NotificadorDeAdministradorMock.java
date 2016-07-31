package Mocks;

import F5.Busqueda;
import F5.Procesos.NotificadorDeBusqueda;

public class NotificadorDeAdministradorMock implements NotificadorDeBusqueda {

	public int tiempoParaNotificar; // debe ser parametrizable
	public boolean notificado = false;

	public boolean isNotificado() {
		return notificado;
	}

	public void notificarBusqueda(Busqueda unaBusqueda) {

		if (this.excedioDemora(unaBusqueda)) {
			this.notificado = true;
		}
	}

	public void setTiempoParaNotificar(int tiempoParaNotificar) {
		this.tiempoParaNotificar = tiempoParaNotificar;
	}

	public boolean excedioDemora(Busqueda unaBusqueda) {
		return unaBusqueda.getTiempoBusqueda() >= this.tiempoParaNotificar;
	}

}
