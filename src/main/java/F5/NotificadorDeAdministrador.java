package F5;

public class NotificadorDeAdministrador implements NotificadorDeBusqueda {
	static int demora;
	public Boolean busquedaRealizada;

	public void setDemora(int demora) {
		NotificadorDeAdministrador.demora = demora;
	}

	public void notificarBusqueda(Busqueda unaBusqueda) {

		if (unaBusqueda.getTiempoBusqueda() > demora) {
			// notifica al admin.
			busquedaRealizada = true;
		}

	}

}
