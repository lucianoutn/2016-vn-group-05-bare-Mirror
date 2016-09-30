package Reportes;

import java.util.ArrayList;
import java.util.List;

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

@Entity	
public class BusquedasPorFecha extends NotificadorDeBusqueda {

	@OneToMany
	@JoinColumn(name="id_reporte_por_fecha")
	public List<ReportePorFecha> reportesPorFecha = new ArrayList<ReportePorFecha>();

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
