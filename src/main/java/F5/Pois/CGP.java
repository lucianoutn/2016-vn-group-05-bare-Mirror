package F5.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Id;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import F5.Persistidor;

@Entity
@Table(name = "CGPES")
public class CGP extends PuntoDeInteres {

	@OneToOne
	private Comuna comuna;
	@OneToMany
	@JoinColumn(name = "nroServicio")
	private List<Servicio> servicios = new ArrayList<>();

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public CGP() {// constructor vacio para persistencia

	}

	public CGP(Punto point, Comuna unaComuna) {
		comuna = unaComuna;
		posicion = point;
	}

	public void anadirServicio(Servicio servic) {
		if (servicios == null)
			servicios = new ArrayList<Servicio>();
		servicios.add(servic);

	}

	@Override
	public boolean estaDisponible(Dias dia, int hora, Servicio unServicio) {
		if (unServicio == null)
			return servicios.stream().anyMatch(s -> s.estaAbierto(dia, hora));
		return servicios.stream()
				.anyMatch(s -> s.getNombre().equals(unServicio.getNombre()) && unServicio.estaAbierto(dia, hora));
	}

	public boolean estaDisponible(Dias date, int hora) {
		return estaDisponible(date, hora, null);
	}

	@Override
	public boolean estaCerca(Punto point) {
		return comuna.getComuna().isInside(point.getPoint());
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return servicios.stream().anyMatch(s -> s.contiene(textoLibre)) || encuentraCalle(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

}
