package TestFactory;

import org.uqbar.geodds.Point;

public final class PointFactory {

	public static Point PuntoOrigen() {
		return new Point(0, 0);
	}

	public static Point PuntoPositivo() {
		return new Point(2, 2);
	}

	public static Point PuntoNegativo() {
		return new Point(-2, -2);
	}

	public static Point PuntoMuyGrande() {
		return new Point(1000000000, 1000000000);
	}

	public static Point PuntoMuyNegativo() {
		return new Point(-1000000000, -1000000000);
	}
	
	public static Point CercaBancoSantander(){
		return new Point(0, 4);
	}
	
	
	public static Point LejosBancoSantander(){
		return new Point(0, 6);
	}
	

}