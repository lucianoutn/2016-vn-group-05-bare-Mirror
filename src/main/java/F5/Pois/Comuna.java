package F5.Pois;

import java.util.List;

import javax.persistence.*;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
@Entity
public class Comuna {
	
	@Transient
	private Polygon limites;
	
	@OneToMany
	@JoinColumn(name = "id")
	private List<Punto> puntos;
	
	public List<Punto> getPuntos() {
		return puntos;
	}

	@Id
	private int nroComuna;

	public int getNroComuna() {
		return nroComuna;
	}
	public Polygon getComuna() {
		if(limites == null)
			reconstruirComuna();
		return limites;
	}
	private void reconstruirComuna() {
		limites = new Polygon();
		puntos.forEach(p-> limites.add(new Point(p.getX(), p.getY())));
		
	}
	public Comuna(){
		
	}
	public Comuna(int nombreComuna, List<Point> limitesComuna){
		nroComuna = nombreComuna;
		limites = new Polygon();
		limitesComuna.forEach(p-> procesarPunto(p));
		//limites = limitesComuna;
	}
	public Comuna(int nombreComuna, Polygon poligono){
		
	}
	
	private void procesarPunto(Point point) {
		limites.add(point);
		Punto punto = new Punto();
		punto.setX(point.longitude());
		punto.setY(point.latitude());
		puntos.add(punto);
	}
}
