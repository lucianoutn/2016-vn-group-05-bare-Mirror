package Reportes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class ReportePorBusqueda {
	
	@Id
	@GeneratedValue
	private long id_reporte_por_busqueda;
	
	private String fraseBuscada;
	private double cantidadDeResultados;
	private double demoraDeBusqueda;
	
	public ReportePorBusqueda(){
		//para que no me rompa hibernate
	}

	public ReportePorBusqueda(String frase, double cantidad, double demora){
		fraseBuscada=frase;
		cantidadDeResultados=cantidad ;
		demoraDeBusqueda=demora;
	}

}
