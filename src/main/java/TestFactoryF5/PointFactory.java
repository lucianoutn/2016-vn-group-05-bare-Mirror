package TestFactoryF5;

//import org.uqbar.geodds.Punto;

import F5.Pois.Punto;

public final class PointFactory {

	public static Punto PuntoOrigen() {
		return new Punto(0, 0);
	}

	public static Punto PuntoPositivo() {
		return new Punto(2, 2);
	}

	public static Punto PuntoNegativo() {
		return new Punto(-2, -2);
	}

	public static Punto PuntoMuyGrande() {
		return new Punto(1000000000, 1000000000);
	}

	public static Punto PuntoMuyNegativo() {
		return new Punto(-1000000000, -1000000000);
	}
	
	public static Punto CercaBancoSantander(){
		return new Punto(0, 4);
	}
	
	
	public static Punto LejosBancoSantander(){
		return new Punto(0, 6);
	}
	

}