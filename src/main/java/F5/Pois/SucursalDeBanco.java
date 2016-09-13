package F5.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;

@Entity
public class SucursalDeBanco extends PuntoDeInteres {

	@Id
	@GeneratedValue
	private Long id_Banco;
	private String nombre;

	public SucursalDeBanco() {// constructor vacio para persistencia

	}

	public SucursalDeBanco(String unNombre, Point unaPosicion, List<DiaAtencion> diasDeAtencion) {
		nombre = unNombre;
		posicion = unaPosicion;
		this.cargarCoordenadasDePosicion(unaPosicion);
		toleranciaEnCuadras = 5;
		atencionAlPublico = diasDeAtencion;
	}

	public Long getId() {
		return id_Banco;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return nombre.equals(textoLibre);
	}

}