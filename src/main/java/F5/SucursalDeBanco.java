package F5;


import java.util.ArrayList;

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
	private ArrayList<SucursalDeBanco> sucursales;

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
=======
	private String nombre;
	private String calle;
	private int altura;
	private Point posicion;
	private int toleranciaEnCuadras;
	
	private ArrayList<DiaAtencion> atencionAlPublico;
	
	
	//
	public SucursalDeBanco(String unNombre, ArrayList<DiaAtencion> diasDeAtencion){
		nombre = unNombre;
		toleranciaEnCuadras = 5;
		atencionAlPublico = diasDeAtencion;
		
	} 

	
	@Override
	public boolean estaDisponible(Dias unDia, int hora , Servicio valorX) {
		
		return atencionAlPublico.stream().anyMatch(d-> d.equals(unDia) && d.estaAbierto(hora));
>>>>>>> implementacion
	}

	@Override
	public boolean estaCerca(Point point) {
		return cuadrasDeDistancia(point) <= toleranciaEnCuadras;
	}
	
	@Override
	public boolean encuentra(String textoLibre) {
		return nombre.equals(textoLibre);
	}

<<<<<<< HEAD
	public Point getPosicion() {
		return posicion;
=======
	private int cuadrasDeDistancia(Point point) {
		return (int) Math.round(posicion.distance(point) / 100);
>>>>>>> implementacion
	}

	//public void setPosicion(Point posicion) {
//		this.posicion = posicion;
	//}
}