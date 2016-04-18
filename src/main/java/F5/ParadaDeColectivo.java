package F5;

import java.time.LocalDate;
import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo implements PuntoDeInteres {

	// Declaraciones -- Inicio
	private String calle;
	private String altura;

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getCalle() {
		return calle;
	}

	public Point getPosicion() {
		return posicion;
	}

	private int toleranciaEnCuadras;
	private Point posicion;
	private String numeroDeLinea;
	// Declaraciones -- Fin

	public String getNumeroDeLinea() {
		return numeroDeLinea;
	}

	public void setNumeroDeLinea(String numeroDeLinea) {
		this.numeroDeLinea = numeroDeLinea;
	}

	// Getters y Setters -- Inicio
	public int getToleranciaEnCuadras() {
		return toleranciaEnCuadras;
	}

	public void setToleranciaEnCuadras(int toleranciaEnCuadras) {
		this.toleranciaEnCuadras = toleranciaEnCuadras;
	}
	// Getters y Setters -- Fin

	public ParadaDeColectivo(String calleDeParada, String alturaDeParada, Point unaPosicion, String lineaDeColectivo) {
		calle = calleDeParada;
		altura = alturaDeParada;
		posicion = unaPosicion;
		numeroDeLinea = lineaDeColectivo;
	}

	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		return true;
	}

	@Override
	public boolean estaCerca(Point point) {
		return cuadrasDeDistancia(point) <= toleranciaEnCuadras;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return encuentraNumeroDeLinea(textoLibre) || encuentraCalle(textoLibre);
	}

	private boolean encuentraNumeroDeLinea(String textoLibre) {
		return getNumeroDeLinea().equals(textoLibre);
	}

	private int cuadrasDeDistancia(Point point) {
		return (int) Math.round(posicion.distance(point) / 100);
		// Calculo la distancia entre los puntos y la divido por 100 asumiendo
		// que cada cuadra es de 100m y lo redondeo
	}

	private boolean encuentraCalle(String textoLibre) {
		return getCalle().equals(textoLibre);
	}

}
