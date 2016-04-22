package F5;

import java.util.ArrayList;

public class Servicio { //TODO
	
	private String nombre;
	public String getNombre() {
		return nombre;
	}

	private ArrayList<DiaAtencion> atencionAlPublico;
	
	
	public Servicio(String unNombre, ArrayList<DiaAtencion> diasDeAtencion){
		nombre = unNombre;
		atencionAlPublico = diasDeAtencion;
	}
	
	public boolean estaAbierto(Dias unDia, int unaHora){
		return atencionAlPublico.stream().anyMatch(d-> d.getDia().equals(unDia) && d.estaAbierto(unaHora));
	}
	
	public boolean contiene(String textoLibre){
		return nombre.contains(textoLibre); //TODO testear
	}
}

