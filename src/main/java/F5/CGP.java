package F5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements PuntoDeInteres {

	private String calle;
	private String altura;
	private Point posicion;
	private Polygon comuna;
	private Collection<Servicio> servicios;



	public CGP(Point point, Polygon unaComuna) {
		comuna = unaComuna;
		posicion = point;

	}

	@Override
	public boolean estaDisponible(LocalDate date, Servicio unServicio) {
		return servicios.stream().anyMatch(s-> s.getName().equals(unServicio) && unServicio.estaAbierto(date));
	}

	@Override
	public boolean estaCerca(Point point) {
		return comuna.isInside(point); 
	}

	@Override
	public boolean encuentra(String textoLibre) {
		
		return
			encuentraCalle(textoLibre) &&
			encuentraAltura(textoLibre) &&
			servicios.stream().anyMatch(s-> encuentraServicio(s, textoLibre));
		
	}

	private boolean encuentraServicio(Servicio s, String textoLibre) {
		return s.getName().equals(textoLibre);
	}


	private boolean encuentraAltura(String textoLibre) {
		return altura.equals(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}

}


