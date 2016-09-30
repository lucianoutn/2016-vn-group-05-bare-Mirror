package F5.Pois;

import org.uqbar.geodds.Point;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Punto {
	@Id
	@GeneratedValue
	private int id_Punto;

	private double x;

	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	private double y;

	public Punto() {

	}

	public Punto(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public double latitude() {
		return x;
	}

	public double longitude() {
		return y;
	}

	public double distance(Punto otroPunto) {
		Point unPoint = new Point(this.x, this.y);
		Point otroPoint = new Point(otroPunto.latitude(), otroPunto.longitude());
		return unPoint.distance(otroPoint);
	}

	public Point getPoint() {
		return new Point(this.x, this.y);
	}

}
