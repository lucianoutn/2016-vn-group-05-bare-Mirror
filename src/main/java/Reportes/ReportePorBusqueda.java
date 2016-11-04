package Reportes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class ReportePorBusqueda {
	
	@Id
	@GeneratedValue
	private long id;
	
	public String fraseBuscada;
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public double cantidadDeResultados;
	public double getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public void setDemoraDeBusqueda(double demoraDeBusqueda) {
		this.demoraDeBusqueda = demoraDeBusqueda;
	}

	public double demoraDeBusqueda;
	
	public ReportePorBusqueda(){
		//para que no me rompa hibernate
	}

	public ReportePorBusqueda(String frase, double cantidad, double demora){
		fraseBuscada=frase;
		cantidadDeResultados=cantidad ;
		demoraDeBusqueda=demora;
	}

}
