package F5.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import F5.Persistidor;


@Entity
public class Servicio {

	@Id
	@GeneratedValue
	private Long id_Servicio;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Servicio_DiaAtencion",joinColumns=@JoinColumn(name="id_Servicio"),inverseJoinColumns=@JoinColumn(name="id_diaAtencion"))
	private List<DiaAtencion> atencionAlPublico;

	public Servicio() {

	}

	public Long getId() {
		return id_Servicio;
	}

	public Servicio(String unNombre, List<DiaAtencion> diasDeAtencion) {
		nombre = unNombre;
		atencionAlPublico = diasDeAtencion;
	}

	public boolean estaAbierto(Dias unDia, int unaHora) {
		return atencionAlPublico.stream().anyMatch(d -> d.getDia().equals(unDia) && d.estaAbierto(unaHora));
	}

	public boolean contiene(String textoLibre) {
		return nombre.contains(textoLibre);
	}
}
