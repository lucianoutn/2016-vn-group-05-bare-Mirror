package F5.Pois;

import javax.persistence.*;

import org.uqbar.geodds.Polygon;
@Entity
public class Comuna {
	@Transient
	private Polygon limites;
	@Id
	private int nroComuna;


	public int getNroComuna() {
		return nroComuna;
	}
	public Polygon getComuna() {
		return limites;
	}
	public Comuna(){
		
	}
	public Comuna(int nombreComuna, Polygon limitesComuna){
		nroComuna = nombreComuna;
		limites = limitesComuna;
	}
}
