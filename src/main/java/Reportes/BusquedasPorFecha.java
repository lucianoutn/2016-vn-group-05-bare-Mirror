package Reportes;

import java.util.ArrayList;
import java.util.List;

import F5.Busqueda;

public class BusquedasPorFecha implements NotificadorDeBusqueda {
	public  List<ReportePorFecha> reportesPorFecha = new ArrayList<ReportePorFecha>();

	public void notificarBusqueda(Busqueda unaBusqueda){
		if(reportesPorFecha.stream().anyMatch(x-> x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))){
			reportesPorFecha.stream().filter(x->x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))
							.forEach(x-> x.setCantidadDeBusquedas(x.getCantidadDeBusquedas()+1));
		}
		else
			reportesPorFecha.add(new ReportePorFecha(unaBusqueda.getFecha()));
	}

	public List<ReportePorFecha> generarReporte(){
		return reportesPorFecha;
	}
}
