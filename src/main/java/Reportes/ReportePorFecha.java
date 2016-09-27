package Reportes;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class ReportePorFecha {
	
	@Id
	@GeneratedValue
	private long numeroDeRenglonReportePorFecha;
	double cantidadDeBusquedas;
	@Transient//TODO luchito salvame
	LocalTime diaDeLaBusqueda;
	
	public ReportePorFecha(){
		//para que no me rompa hibernate X2
	}
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
