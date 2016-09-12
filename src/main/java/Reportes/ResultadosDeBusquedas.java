package Reportes;

import java.util.ArrayList;

import F5.Busqueda;

public class ResultadosDeBusquedas implements NotificadorDeBusqueda {

	private ArrayList<ReportePorBusqueda> reporte=new ArrayList<>();
	
	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		ReportePorBusqueda nuevoResultado= new ReportePorBusqueda(unaBusqueda.getFraseBuscada(),unaBusqueda.getCantResultados(), unaBusqueda.getTiempoBusqueda());
		reporte.add(nuevoResultado);
		
	}
	public ArrayList<ReportePorBusqueda> generarReporte(){
		return reporte;
	}
}
