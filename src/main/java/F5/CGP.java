package F5;

import java.util.ArrayList;


import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements PuntoDeInteres {

	private String calle;
	private String altura;
	private Point posicion;
	private Polygon comuna;
	private ArrayList<Servicio> servicios;

	public CGP(Point point, Polygon unaComuna) {
		comuna = unaComuna;
		posicion = point;
	}

	@Override
	public boolean estaDisponible(Dias dia, int hora, Servicio unServicio) {
		if (unServicio == null)
			return servicios.stream().anyMatch(s-> s.estaAbierto(dia, hora));
		return servicios.stream().anyMatch(s -> s.getNombre().equals(unServicio) && unServicio.estaAbierto(dia, hora));
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
		return encuentraCalle(textoLibre) || servicios.stream().anyMatch(s -> s.contiene(textoLibre));
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}

}
