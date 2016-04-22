package F5;



import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Dispositivo {

	// atributos
	private Point point;
	private Polygon comunaDelimitadaPor;
	
	public Dispositivo(Point unPunto, Polygon unLimite)
	{
		this.point = unPunto;
		this.comunaDelimitadaPor = unLimite;
	}

	// metodos
	public Point getPoint() {
		return point;
	}

	//public void setPoint(Point point) {
	//	this.point = point;
	//}

	public Polygon getComunaDelimitadaPor() {
		return comunaDelimitadaPor;
	}

	//public void setComunaDelimitadaPor(Polygon comunaDelimitadaPor) {
	//	this.comunaDelimitadaPor = comunaDelimitadaPor;
	//}

}
