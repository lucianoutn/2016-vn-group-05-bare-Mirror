package Mocks;

import F5.Busqueda;
import F5.NotificadorDeBusqueda;

public class NotificadorDeAdministradorMock implements NotificadorDeBusqueda {

	// atributos
	public int tiempoParaNotificar = 10; // debe ser parametrizable
	public boolean notificado = false;

	// métodos

	public void notificarBusqueda(Busqueda unaBusqueda) {

		if (this.excedioDemora(unaBusqueda)) {
			// no hago nada xq soy un mock. cambio la siguiente variable para
			// tests
			this.notificado = true;

		}

	}

	public void setTiempoParaNotificar(int tiempoParaNotificar) {
		// debe ser parametrizable
		this.tiempoParaNotificar = tiempoParaNotificar; // en segundos
	}

	public boolean excedioDemora(Busqueda unaBusqueda) {
		return unaBusqueda.tiempoBusqueda >= this.tiempoParaNotificar;
	}

}
