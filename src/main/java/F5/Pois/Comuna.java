package F5.Pois;

import org.uqbar.geodds.Polygon;

public class Comuna {
	private Polygon limites;
	private int nroComuna;


	public int getNroComuna() {
		return nroComuna;
	}
	public Polygon getComuna() {
		return limites;
	}
	public Comuna(int nombreComuna, Polygon limitesComuna){
		nroComuna = nombreComuna;
		limites = limitesComuna;
	}
}
