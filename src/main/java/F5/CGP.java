package F5;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends PuntoDeInteres {

	private Polygon comuna;
	
	public Polygon getComuna() {
		return comuna;
	}

	private List<Servicio> servicios = new ArrayList<>();
	
	
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public CGP(Point point, Polygon unaComuna) {
		comuna = unaComuna;
		posicion = point;
	}

	public void anadirServicio(Servicio servic) {
		servicios.add(servic);

	}

	@Override
	public boolean estaDisponible(Dias dia, int hora, Servicio unServicio) {
		if (unServicio == null)
			return servicios.stream().anyMatch(s -> s.estaAbierto(dia, hora));
		return servicios.stream().anyMatch(s -> s.getNombre().equals(unServicio.getNombre()) && unServicio.estaAbierto(dia, hora));
	}

	public boolean estaDisponible(Dias date, int hora) {
		return estaDisponible(date, hora, null);
	}

	@Override
	public boolean estaCerca(Point point) {
		return comuna.isInside(point);
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return servicios.stream().anyMatch(s -> s.contiene(textoLibre)) || encuentraCalle(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}
	
	public void setComuna(Polygon unPolygon) {
		this.comuna = unPolygon;
	}

}
