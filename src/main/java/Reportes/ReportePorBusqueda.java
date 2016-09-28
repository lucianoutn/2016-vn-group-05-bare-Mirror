package Reportes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class ReportePorBusqueda {
	
	@Id
	@GeneratedValue
	private long numeroDeRenglonDeReporteDeBusqueda;
	private String fraseBuscada;
	private double cantidadDeResultados;
	private double demoraDeBusqueda;
	
	public ReportePorBusqueda(){
		//para que no me rompa hibernate
	}

	public ReportePorBusqueda(String frase, double cantidad, double demora){
		fraseBuscada=frase;
		cantidad= cantidadDeResultados;
		demora= demoraDeBusqueda;
	}

}
