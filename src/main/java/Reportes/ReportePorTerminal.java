package Reportes;

import java.util.ArrayList;
import java.util.List;

public class ReportePorTerminal {
	private String unaTerminal;
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

	public ReportePorTerminal(String terminal,double i){
		unaTerminal= terminal;
		cantResultados= new ArrayList<Double>();
		cantResultados.add(i);
	}
	
	
	

}
