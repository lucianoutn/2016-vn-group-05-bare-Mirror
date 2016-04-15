package model;

import java.time.LocalDate;
import java.util.Collection;
import model.PuntoDeInteres;
import model.Sucursal;

public class SucursalDeBanco implements PuntoDeInteres {

	private String calle;
	private int altura;
	private int x;//Asumimos que las coordenadas son cuadras
	private int y;
	private String unNombreDeBanco;
	private Collection<Sucursal> sucursales;
	private Sucursal unaSucursal;
	
	/* El POI de una Sucursal de Banco se encuentra cerca si, desde el punto actual esta a 5 cuadras */
	@Override
	public boolean estaCerca(int x, int y) {
		int distancia = Math.abs(x-this.x) + Math.abs(y-this.y);
		return (distancia < 5);
	}
	
	public boolean estaDisponible(LocalDate horaActual){
		 unaSucursal = null;
		if (sucursales.contains(unaSucursal) && unaSucursal.estaAbierto(horaActual))
			return true;

		return false;
	}

	@Override
	public boolean encuentra(String unNombreDeSucursal) {
		return (unNombreDeBanco == unNombreDeSucursal);
	}

	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		// TODO Auto-generated method stub
		return false;
	}


}