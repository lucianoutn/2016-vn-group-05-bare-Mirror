package F5;

import java.util.ArrayList;
import java.util.List;

//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;

public class SucursalDeBanco extends PuntoDeInteres {

	// atributos
	private String nombre;
	
	// metodos
	public SucursalDeBanco(String unNombre, Point unaPosicion, List<DiaAtencion> diasDeAtencion) {
		nombre = unNombre;
		posicion = unaPosicion;
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