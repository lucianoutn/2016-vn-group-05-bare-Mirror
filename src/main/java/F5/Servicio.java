package F5;

import java.util.ArrayList;
import java.util.List;

public class Servicio { 
	
	private String nombre;
	public String getNombre() {
		return nombre;
	}

	private List<DiaAtencion> atencionAlPublico;
	
	
	public Servicio(String unNombre, List<DiaAtencion> diasDeAtencion){
		nombre = unNombre;
		atencionAlPublico = diasDeAtencion;
	}
	
	public boolean estaAbierto(Dias unDia, int unaHora){
		return atencionAlPublico.stream().anyMatch(d-> d.getDia().equals(unDia) && d.estaAbierto(unaHora));
	}
	
	public boolean contiene(String textoLibre){
		return nombre.contains(textoLibre); 
	}
}

