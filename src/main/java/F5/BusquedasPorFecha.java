package F5;

import java.util.ArrayList;

public class BusquedasPorFecha implements Observers {
	public  ArrayList<BusquedaPorFecha> reporte = new ArrayList<BusquedaPorFecha>();

	public void notificarBusqueda(Busqueda unaBusqueda){
		if(reporte.stream().anyMatch(x-> x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha()))){
			reporte.stream().filter(x->x.getDiaDeLaBusqueda().equals(unaBusqueda.getFecha())).forEach(x-> x.setCantidadDeBusquedas(x.getCantidadDeBusquedas()+1));
		}
		else
			reporte.add(new BusquedaPorFecha(unaBusqueda.getFecha()));
	}

	public ArrayList<BusquedaPorFecha> generarReporte(){
		return reporte;
	}
}
