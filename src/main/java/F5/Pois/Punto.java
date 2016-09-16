package F5.Pois;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Punto {
	@Id
	@GeneratedValue
	private int id;
	
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
	
	public Punto(){
		
	}
	
	
	
}
