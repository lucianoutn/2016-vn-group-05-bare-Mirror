package Reportes;

import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import F5.Busqueda;

import F5.Terminal.Terminal;
import F5.Persistencia.PersistidorDeReportes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity

public class ResultadosPorTerminal extends NotificadorDeBusqueda {

	@OneToMany
	@JoinColumn(name = "numeroDeRenglon")
	List<ReportePorTerminal> reportesPorTerminal = new ArrayList<ReportePorTerminal>();

	public List<ReportePorTerminal> getReportesPorTerminal() {
		return reportesPorTerminal;
	}

	public void setReportesPorTerminal(List<ReportePorTerminal> reportesPorTerminal) {
		this.reportesPorTerminal = reportesPorTerminal;
	}

	public ResultadosPorTerminal() {
	}

	public void notificarBusqueda(Busqueda unaBusqueda) {
		if (reportesPorTerminal.stream().anyMatch(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal())))
			reportesPorTerminal.stream().filter(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal()))
					.forEach(x -> x.setCantResultados((double) unaBusqueda.getCantResultados()));

		else
			reportesPorTerminal.add(new ReportePorTerminal(unaBusqueda.getTerminal(), unaBusqueda.getCantResultados()));
	
		//PersistidorDeReportes.getInstancia().persist(this);
	}
	

	public List<ReportePorTerminal> generarReporte(Terminal unaTerminal){ 
		if(unaTerminal==null)
			return reportesPorTerminal;
		else
			return reportesPorTerminal.stream().filter((x -> x.getUnaTerminal().equals(unaTerminal)))
					.collect(Collectors.toList());
	}
}
