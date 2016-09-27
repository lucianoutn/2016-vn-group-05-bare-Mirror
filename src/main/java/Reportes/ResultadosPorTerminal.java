package Reportes;

import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import F5.Busqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class ResultadosPorTerminal extends NotificadorDeBusqueda {

	@Id
	@GeneratedValue
	private long numeroDeReporteDeResultadosPorTerminal;
	
	
	
	public long getNumeroDeReporteDeResultadosPorTerminal() {
		return numeroDeReporteDeResultadosPorTerminal;
	}
	@OneToMany
	@JoinColumn(name="numeroDeRenglon")
	List<ReportePorTerminal> reportesPorTerminal = new ArrayList<ReportePorTerminal>();

	public void notificarBusqueda(Busqueda unaBusqueda) {
		if (reportesPorTerminal.stream().anyMatch(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal())))
			reportesPorTerminal.stream().filter(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal()))
					.forEach(x -> x.setCantResultados((double) unaBusqueda.getCantResultados()));

		else
			reportesPorTerminal.add(new ReportePorTerminal(unaBusqueda.getTerminal(), unaBusqueda.getCantResultados()));
	}
	public List<ReportePorTerminal> generarReporte(String unaTerminal){ 
		if(unaTerminal==null)
			return reportesPorTerminal;
		else
			return reportesPorTerminal.stream().filter( (x -> x.getUnaTerminal().equals(unaTerminal))).collect(Collectors.toList());
	}
}
