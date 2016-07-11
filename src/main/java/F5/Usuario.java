package F5;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	
	private List<AccionPostBusqueda> accionesRealizables = new ArrayList<AccionPostBusqueda>();
	
	public Usuario(String unNombre){
		nombre=unNombre;
	}

	public void ejecutarAcciones() {
		accionesRealizables.forEach(accion-> accion.ejecutar());
		
	}

	public void agregarAcciones(List<AccionPostBusqueda> acciones) {
		accionesRealizables.addAll(acciones);		
	}

	public void quitarAcciones(List<AccionPostBusqueda> acciones) {
		accionesRealizables.removeAll(acciones);
	}
}
