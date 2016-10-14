package F5.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;

import F5.Persistencia.PersistidorDePOIs;

@Entity
@Table(name = "Bancos")
public class SucursalDeBanco extends PuntoDeInteres {

	private String nombre;

	public SucursalDeBanco() {// constructor vacio para persistencia

	}

	public SucursalDeBanco(String unNombre, Punto unaPosicion, List<DiaAtencion> diasDeAtencion) {
		nombre = unNombre;
		posicion = unaPosicion;
		this.cargarCoordenadasDePosicion(unaPosicion);
		toleranciaEnCuadras = 5;
		atencionAlPublico = diasDeAtencion;
		PersistidorDePOIs.getInstancia().guardaParaPersistir(this);
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return nombre.equals(textoLibre);
	}

}