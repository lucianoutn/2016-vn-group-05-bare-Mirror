package F5;

public class AccionEjemplo implements AccionPostBusqueda{

	
	private boolean accionEjecutada = false;
	
	@Override
	public void ejecutar() {
		System.out.println("Se ejecuto la accion");
		accionEjecutada = true;
	}

	public boolean isAccionEjecutada() {
		return accionEjecutada;
	}
}
