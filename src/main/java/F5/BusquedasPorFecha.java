package F5;

import java.util.ArrayList;
import java.util.List;

public class BusquedasPorFecha implements NotificadorDeBusqueda {
	public  List<BusquedaPorFecha> reporte = new ArrayList<BusquedaPorFecha>();

	public void notificarBusqueda(Busqueda unaBusqueda){
		if(reporte.stream().anyMatch(x-> x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))){
			reporte.stream().filter(x->x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))
							.forEach(x-> x.setCantidadDeBusquedas(x.getCantidadDeBusquedas()+1));
		}
		else
			reporte.add(new BusquedaPorFecha(unaBusqueda.getFecha()));
	}

	public List<BusquedaPorFecha> generarReporte(){
		return reporte;
	}
}
