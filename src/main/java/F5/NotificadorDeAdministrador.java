package F5;

public class NotificadorDeAdministrador implements Observers {
	static int demora;

	public void setDemora(int demora) {
		NotificadorDeAdministrador.demora = demora;
	}
	
	public static void notificarBusqueda(Busqueda unaBusqueda){
		
		if(unaBusqueda.getTiempoBusqueda()>demora){
			//notifica al admin.
		}

	
	}

}
