package F5;

import java.time.LocalTime;

public class ReportePorFecha {
	double cantidadDeBusquedas;
	LocalTime diaDeLaBusqueda;
	
	public ReportePorFecha(LocalTime diaBusqueda){
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
