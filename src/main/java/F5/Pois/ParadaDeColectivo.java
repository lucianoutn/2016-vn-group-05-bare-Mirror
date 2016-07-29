package F5.Pois;


import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends PuntoDeInteres {

	private String numeroDeLinea;

	public String getNumeroDeLinea() {
		return numeroDeLinea;
	}

	public ParadaDeColectivo(String calleDeParada, String alturaDeParada, Point unaPosicion, String lineaDeColectivo) {
		calle = calleDeParada;
		altura = alturaDeParada;
		posicion = unaPosicion;
		numeroDeLinea = lineaDeColectivo;
		toleranciaEnCuadras = 1;
	}

	@Override
	public boolean estaDisponible(Dias unDia, int hora , Servicio valorX) {
		return true;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return encuentraNumeroDeLinea(textoLibre) || encuentraCalle(textoLibre);
	}

	private boolean encuentraNumeroDeLinea(String textoLibre) {
		return getNumeroDeLinea().equals(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return getCalle().equals(textoLibre);
	}

}
