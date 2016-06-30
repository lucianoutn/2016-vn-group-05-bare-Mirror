package F5;

import java.time.LocalTime;

public class BusquedaPorFecha {
	double cantidadDeBusquedas;
	LocalTime diaDeLaBusqueda;
	
	public BusquedaPorFecha(LocalTime diaBusqueda){
		diaDeLaBusqueda = diaBusqueda;
	}
	public double getCantidadDeBusquedas() {
		return cantidadDeBusquedas;
	}
	public void setCantidadDeBusquedas(double cantidadDeBusquedas) {
		this.cantidadDeBusquedas = cantidadDeBusquedas;
	}
	public LocalTime getDiaDeLaBusqueda() {
		return diaDeLaBusqueda;
	}
	public void setDiaDeLaBusqueda(LocalTime diaDeLaBusqueda) {
		this.diaDeLaBusqueda = diaDeLaBusqueda;
	}
	
	

}
