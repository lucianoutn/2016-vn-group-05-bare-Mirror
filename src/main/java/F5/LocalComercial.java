package F5;

import java.util.ArrayList;
import org.uqbar.geodds.Point;

public class LocalComercial implements PuntoDeInteres {

	private String rubro;
	private String calle;
	private String altura;
	private String nombre;
	private int cuadrasDeCercania;
	private Point posicion;

	private ArrayList<DiaAtencion> atencionAlPublico;

	public LocalComercial(String unNombre, String unaCalle, String unaAltura, String unRubro,
			ArrayList<DiaAtencion> diasDeAtencion, Point unaPosicion) {
		nombre = unNombre;
		calle = unaCalle;
		altura = unaAltura;
		posicion = unaPosicion;
		rubro = unRubro;
		atencionAlPublico = diasDeAtencion;
	}

	@Override
	public boolean estaDisponible(Dias unDia, int hora, Servicio unServicio) {
		return atencionAlPublico.stream().anyMatch(d -> d.getDia().equals(unDia) && d.estaAbierto(hora));
	}

	@Override
	public boolean estaCerca(Point point) {
		return cuadrasDeDistancia(point) <= cuadrasDeCercania;
	}

	private int cuadrasDeDistancia(Point point) {
		return (int) Math.round(posicion.distance(point) / 100);
		// Calculo la distancia entre los puntos y la divido por 100 asumiendo
		// que cada cuadra es de 100m y lo redondeo
	}

	public boolean encuentra(String textoLibre) {
		return encuentraCalle(textoLibre) || encuentraNombre(textoLibre) || encuentraRubro(textoLibre);

	}

	private boolean encuentraRubro(String textoLibre) {
		return rubro.equals(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}

	private boolean encuentraNombre(String textoLibre) {
		return nombre.equals(textoLibre);

	}

	public void setCuadrasDeCercania(int cuadrasDeCercania) {
		this.cuadrasDeCercania = cuadrasDeCercania;
	}

}
