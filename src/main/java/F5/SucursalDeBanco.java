package F5;


import java.util.ArrayList;

//import F5.PuntoDeInteres;
//import F5.Sucursal;

import org.uqbar.geodds.Point;

public class SucursalDeBanco implements PuntoDeInteres {

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
	}

	@Override
	public boolean estaCerca(Point point) {
		return cuadrasDeDistancia(point) <= toleranciaEnCuadras;
	}
	
	@Override
	public boolean encuentra(String textoLibre) {
		return nombre.equals(textoLibre);
	}

	private int cuadrasDeDistancia(Point point) {
		return (int) Math.round(posicion.distance(point) / 100);
	}

}