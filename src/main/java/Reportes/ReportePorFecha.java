package Reportes;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;


@Entity
public class ReportePorFecha {
	
	@Id
	@GeneratedValue
	private long id;

	
	double cantidadDeBusquedas;
	@Convert(converter = LocalDateTimeConverter.class)
	LocalTime diaDeLaBusqueda;
	
	public ReportePorFecha(){

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
