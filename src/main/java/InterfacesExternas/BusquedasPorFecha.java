package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import F5.Busqueda;
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorFecha;

public class BusquedasPorFecha extends NotificadorDeBusqueda {
	public  List<ReportePorFecha> reporte = new ArrayList<ReportePorFecha>();

	public void notificarBusqueda(Busqueda unaBusqueda){
		if(reporte.stream().anyMatch(x-> x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))){
			reporte.stream().filter(x->x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))
							.forEach(x-> x.setCantidadDeBusquedas(x.getCantidadDeBusquedas()+1));
		}
		else
			reporte.add(new ReportePorFecha(unaBusqueda.getFecha()));
	}

	public List<ReportePorFecha> generarReporte(){
		return reporte;
	}
}
