package F5.Pois;

import javax.persistence.*;

import org.uqbar.geodds.Point;

import F5.Persistencia.PersistidorDePOIs;

@Entity
@Table(name = "Paradas")
public class ParadaDeColectivo extends PuntoDeInteres {

	private String numeroDeLinea;

	public String getNumeroDeLinea() {
		return numeroDeLinea;
	}

	public ParadaDeColectivo() {// constructor vacio para persistencia

	}

	public ParadaDeColectivo(String calleDeParada, String alturaDeParada, Punto unaPosicion, String lineaDeColectivo) {
		calle = calleDeParada;
		altura = alturaDeParada;
		posicion = unaPosicion;
		this.cargarCoordenadasDePosicion(unaPosicion);
		numeroDeLinea = lineaDeColectivo;
		toleranciaEnCuadras = 1;
	}

	@Override
	public boolean estaDisponible(Dias unDia, int hora, Servicio valorX) {
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
