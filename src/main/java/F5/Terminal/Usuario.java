package F5.Terminal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import F5.AccionPostBusqueda;
import F5.Persistencia.PersistidorDePOIs;
import F5.Persistencia.PersistidorDeUsuarios;
import F5.Pois.Comuna;

@Entity
@Table(name = "Usuarios")
public class Usuario {
	@Id
	@GeneratedValue
	private Integer id;

	private String nombre;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne
	private Comuna comuna;

	public Comuna getComuna() {
		return comuna;
	}

	public String getNombre() {
		return nombre;
	}

	@Transient
	private List<AccionPostBusqueda> accionesRealizables = new ArrayList<AccionPostBusqueda>();

	public List<AccionPostBusqueda> getAccionesRealizables() {
		return accionesRealizables;
	}

	public Usuario(String unNombre, Comuna unaComuna) {
		nombre = unNombre;
		comuna = unaComuna;
	}
	
	public Usuario(String unNombre, Comuna unaComuna, String password) {
		nombre = unNombre;
		comuna = unaComuna;
		this.password = password;
	}

	public Usuario() {

	}

	public void ejecutarAcciones() {
		accionesRealizables.forEach(accion -> accion.ejecutar());

	}

	public void agregarAcciones(List<AccionPostBusqueda> acciones) {
		if (acciones != null)
			accionesRealizables.addAll(acciones);
	}

	public void agregarAccion(AccionPostBusqueda unaAccion) {
		accionesRealizables.add(unaAccion);
	}

	public void quitarAcciones(List<AccionPostBusqueda> acciones) {
		if (acciones != null)
			accionesRealizables.removeAll(acciones);
	}
}
