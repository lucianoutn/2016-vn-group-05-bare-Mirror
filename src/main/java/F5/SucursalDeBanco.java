package F5;

import java.time.LocalDate;
import java.util.Collection;
//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class SucursalDeBanco implements PuntoDeInteres {

	// atributos
	private String calle;
	private int altura;
	private Point posicion;
	private String unNombreDeBanco;
	private Collection<SucursalDeBanco> sucursales;
	private LocalDate horarioInicial;
	private LocalDate horarioCierre;
	private Collection<String> diasDeAtencion;

	// metodos

	/*
	 * El POI de una Sucursal de Banco se encuentra cerca si, desde el punto
	 * actual esta a 5 cuadras
	 */
	@Override
	public boolean estaCerca(Point deOtroPoint) {

		int distancia = (int) Math.abs(posicion.distance(deOtroPoint) / 100);
		return (distancia < 5);
	}

	// private int Partidax;
	// private int Partiday;
	// arrayList<int> puntoDePartida = new arrayList<int>();
	// puntoDePartida.add(Partidax); //Determinar como obtener la posicion
	// actual
	// puntoDePartida.add(Partiday);
	// arrayList<int> puntoDeLlegada = new arrayList<int>();
	// puntoDePartida.add(Llegadax); //Determinar como obtener la posicion
	// actual
	// puntoDePartida.add(Llegaday);
	// public boolean estaCerca(arrayList<int> puntoDePartida, arrayList<int>
	// puntoDeLlegada){
	// }

	public SucursalDeBanco(Point suPosicion) {
		posicion= suPosicion;
	}

	public boolean estaDisponible(LocalDate horaActual) {
		SucursalDeBanco unaSucursal = null;
		if (sucursales.contains(unaSucursal) && unaSucursal.estaAbierto(horaActual))
			return true;
		return false;
	}

	public boolean estaAbierto(LocalDate horaActual) {
		if (horaActual.isAfter(horarioInicial) && horaActual.isBefore(horarioCierre)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean encuentra(String unNombreDeSucursal) {
		return unNombreDeBanco.equals(unNombreDeSucursal);
	}

	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		// TODO Auto-generated method stub
		return false;
	}

	public Point getPosicion() {
		return posicion;
	}

	//public void setPosicion(Point posicion) {
//		this.posicion = posicion;
	//}
}