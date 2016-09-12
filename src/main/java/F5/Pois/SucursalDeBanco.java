package F5.Pois;

import java.util.ArrayList;
import java.util.List;

//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;

public class SucursalDeBanco extends PuntoDeInteres {


	private String nombre;

	public SucursalDeBanco(String unNombre, Point unaPosicion, List<DiaAtencion> diasDeAtencion) {
		nombre = unNombre;
		posicion = unaPosicion;
		this.cargarCoordenadasDePosicion(unaPosicion);
		toleranciaEnCuadras = 5;
		atencionAlPublico = diasDeAtencion;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return nombre.equals(textoLibre);
	}

	

}