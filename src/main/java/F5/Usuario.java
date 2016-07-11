package F5;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	private int nroComuna;
	private List<AccionPostBusqueda> accionesRealizables = new ArrayList<AccionPostBusqueda>();
	
	public Usuario(String unNombre){
		nombre=unNombre;
	}
}
