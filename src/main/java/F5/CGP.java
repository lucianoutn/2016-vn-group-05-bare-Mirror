package F5;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements PuntoDeInteres {

	private String calle;
	private String altura;
	private Point posicion;
	private Polygon comuna;
	private List<Servicio> servicios = new ArrayList<>();


	public CGP(Point point, Polygon unRadio) {
		comuna = unRadio;
		posicion = point;
	}

	public void anadirServicio(Servicio servic){
		servicios.add(servic);
		
	}
	
	@Override
	public boolean estaDisponible(Dias dia, int hora, Servicio unServicio) {
		if (unServicio == null)
			return servicios.stream().anyMatch(s-> s.estaAbierto(dia, hora));
		return servicios.stream().anyMatch(s -> s.getNombre().equals(unServicio.getNombre()) && unServicio.estaAbierto(dia, hora));
	}
	
	//Esta opcion de estaDisponible no forma parte de la interface. Si se desea hacer la busqueda
	//debe ingresarse null al servicio en la interface
	public boolean estaDisponible(Dias date, int hora){
		return estaDisponible(date, hora, null);
	}
	
	@Override
	public boolean estaCerca(Point point) {
		return comuna.isInside(point);
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return  servicios.stream().anyMatch(s -> s.contiene(textoLibre)) || encuentraCalle(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}

}
