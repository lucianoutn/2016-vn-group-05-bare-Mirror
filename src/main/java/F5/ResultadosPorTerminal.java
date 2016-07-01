package F5;

import java.util.ArrayList;

public class ResultadosPorTerminal implements Observers {

	private  ArrayList<ResultadoPorTerminal> reporte = new ArrayList<ResultadoPorTerminal>();

	public void notificarBusqueda(Busqueda unaBusqueda) {
		if (reporte.stream().anyMatch(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal())))
			reporte.stream().filter(x -> x.getUnaTerminal().equals(unaBusqueda.getTerminal()))
					.forEach(x -> x.setCantResultados((double) unaBusqueda.getCantResultados()));
		
		else
			reporte.add(new ResultadoPorTerminal(unaBusqueda.getTerminal(),unaBusqueda.getCantResultados()));
	}
	public ArrayList<ResultadoPorTerminal> generarReporte(){
		return reporte;
	}
}
