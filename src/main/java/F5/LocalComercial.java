package F5;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class LocalComercial extends PuntoDeInteres {

	private String rubro;
	private String nombre;
	
	public LocalComercial(String unNombre, String unaCalle, String unaAltura, String unRubro,
			List<DiaAtencion> diasDeAtencion, Point unaPosicion) {
		nombre = unNombre;
		calle = unaCalle;
		altura = unaAltura;
		posicion = unaPosicion;
		rubro = unRubro;
		atencionAlPublico = diasDeAtencion;
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
		this.toleranciaEnCuadras = cuadrasDeCercania;
	}

}
