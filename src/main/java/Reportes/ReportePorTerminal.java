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

import F5.Terminal.Terminal;

@Entity
public class ReportePorTerminal {
	
	@Id
	@GeneratedValue
	private long id;
	
	public long getNumeroDeRenglonDeReportePorTerminal() {
		return id;
	}
	
	@Transient
	private Terminal unaTerminal;
	
	@ElementCollection
	@CollectionTable(name="cantidadDeResultados", joinColumns=@JoinColumn(name="resultado_id"))
	private List<Double> cantResultados;
		
	public Terminal getUnaTerminal() {
		return unaTerminal;
	}

	public void setUnaTerminal(Terminal unaTerminal) {
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
	}

	public ReportePorTerminal(Terminal terminal,double i){
		unaTerminal= terminal;
		cantResultados= new ArrayList<Double>();
		cantResultados.add(i);
	}	
}
