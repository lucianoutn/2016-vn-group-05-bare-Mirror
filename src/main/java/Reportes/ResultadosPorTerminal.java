package Reportes;

import java.util.stream.Collectors;

import F5.Busqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ResultadosPorTerminal implements NotificadorDeBusqueda {

	private ArrayList<ReportePorTerminal> reportesPorTerminal = new ArrayList<ReportePorTerminal>();

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
