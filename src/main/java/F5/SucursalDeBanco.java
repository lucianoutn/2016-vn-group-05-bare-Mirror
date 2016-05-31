package F5;

import java.util.ArrayList;
import java.util.List;

//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;

public class SucursalDeBanco implements PuntoDeInteres {

	// atributos
	private String calle;
	private int altura;
	private Point posicion;
	private String nombre;
	private int toleranciaEnCuadras;

	private List<DiaAtencion> atencionAlPublico;

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
	public boolean estaDisponible(Dias unDia, int hora, Servicio unServicio) {
		return atencionAlPublico.stream().anyMatch(d -> d.getDia().equals(unDia) && d.estaAbierto(hora));
	}

	@Override
	public boolean estaCerca(Point point) {
		return cuadrasDeDistancia(point) <= toleranciaEnCuadras;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		return nombre.equals(textoLibre);
	}

	public Point getPosicion() {
		return posicion;
	}

	private int cuadrasDeDistancia(Point point) {
		return (int) Math.round(posicion.distance(point) / 100);

	}

}