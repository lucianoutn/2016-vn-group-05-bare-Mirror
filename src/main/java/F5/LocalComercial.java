package F5;

import java.time.LocalDate;
import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class LocalComercial implements PuntoDeInteres {

	private String calle;

	private String altura;
	private String nombre;
	private int cuadrasDeCercania;
	private Point posicion;

	public LocalComercial(String unNombre) {
		nombre = unNombre;
	}

	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		// TODO Auto-generated method stub
		return false;
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
		return encuentraCalle(textoLibre)  || encuentraNombre(textoLibre);

	}

	
	private boolean encuentraCalle(String textoLibre) {
		return getCalle().equals(textoLibre);
	}

	private boolean encuentraNombre(String textoLibre) {
		return getNombre().equals(textoLibre);

	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public String getNombre() {
		return nombre;
	}

}
