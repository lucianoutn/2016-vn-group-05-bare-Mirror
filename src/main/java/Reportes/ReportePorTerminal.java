package Reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ReportePorTerminal")
public class ReportePorTerminal {
	@Id
	@GeneratedValue
	private long numeroDeRenglonDeReportePorTerminal;
	public long getNumeroDeRenglonDeReportePorTerminal() {
		return numeroDeRenglonDeReportePorTerminal;
	}

	private String unaTerminal;
	@ElementCollection
	@CollectionTable(name="cantidadDeResultados", joinColumns=@JoinColumn(name="resultado_id"))
	private List<Double> cantResultados;
		
	public String getUnaTerminal() {
		return unaTerminal;
	}

	public void setUnaTerminal(String unaTerminal) {
		this.unaTerminal = unaTerminal;
	}

	public List<Double> getCantResultados() {
		return cantResultados;
	}

	public void setCantResultados(Double cantResultados) {
		this.cantResultados.add (cantResultados);
	}
	
	public ReportePorTerminal(){
		cantResultados= new ArrayList<Double>();
		//para que no me rompa hibernate X3
	}

	public ReportePorTerminal(String terminal,double i){
		unaTerminal= terminal;
		cantResultados= new ArrayList<Double>();
		cantResultados.add(i);
	}
	
	
	

}
