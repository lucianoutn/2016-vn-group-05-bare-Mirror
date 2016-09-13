package F5.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Servicio { 
	
	@Id
	@GeneratedValue
	private double id_Servicio;
	
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	
	@OneToMany
	@JoinColumn(name="id_diaAtencion")
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

