package F5.Terminal;

import java.util.ArrayList;
import java.util.List;

import F5.AccionPostBusqueda;
import F5.Pois.Comuna;

public class Usuario {
	private String nombre;
	private Comuna comuna;
	
	public Comuna getComuna() {
		return comuna;
	}

	public String getNombre() {
		return nombre;
	}
	
	private List<AccionPostBusqueda> accionesRealizables = new ArrayList<AccionPostBusqueda>();
	
	public List<AccionPostBusqueda> getAccionesRealizables() {
		return accionesRealizables;
	}

	public Usuario(String unNombre, Comuna unaComuna){
		nombre=unNombre;
		comuna = unaComuna;
	}

	public void ejecutarAcciones() {
		accionesRealizables.forEach(accion-> accion.ejecutar());
		
	}

	public void agregarAcciones(List<AccionPostBusqueda> acciones) {
		if(acciones!=null)
			accionesRealizables.addAll(acciones);		
	}
	
	public void agregarAccion(AccionPostBusqueda unaAccion){
		accionesRealizables.add(unaAccion);
	}

	public void quitarAcciones(List<AccionPostBusqueda> acciones) {
		if(acciones!=null)
			accionesRealizables.removeAll(acciones);
	}
}