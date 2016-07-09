package F5;



public class NotificadorDeAdministradorMock implements NotificadorDeBusqueda {

	// atributos
	public boolean notificado = false;
	
	// métodos
	
	public void notificarBusqueda(Busqueda unaBusqueda) {

		if (unaBusqueda.excedioDemora()) {
			//no hago nada xq soy un mock. cambio la siguiente variable para tests
			this.notificado = true;
			
		}

	}

	
}
