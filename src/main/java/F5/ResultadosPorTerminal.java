package F5;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ResultadosPorTerminal implements NotificadorDeBusqueda {

	private ArrayList<ResultadoPorTerminal> reporte = new ArrayList<ResultadoPorTerminal>();

	public void notificarBusqueda(Busqueda unaBusqueda) {
		if (reporte.stream().anyMatch(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal())))
			reporte.stream().filter(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal()))
					.forEach(x -> x.setCantResultados((double) unaBusqueda.getCantResultados()));

		else
			reporte.add(new ResultadoPorTerminal(unaBusqueda.getTerminal(), unaBusqueda.getCantResultados()));
	}
	public List<ResultadoPorTerminal> generarReporte(String unaTerminal){ 
		if(unaTerminal==null)
			return reporte;
		else
			return reporte.stream().filter( (x -> x.getUnaTerminal().equals(unaTerminal))).collect(Collectors.toList());
	}
}
