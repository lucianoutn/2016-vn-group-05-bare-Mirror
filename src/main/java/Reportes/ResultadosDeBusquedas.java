package Reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import F5.Busqueda;

@Entity
public class ResultadosDeBusquedas extends NotificadorDeBusqueda {
	
	
	@OneToMany
	@JoinColumn(name="numeroDeRenglon")
	private List<ReportePorBusqueda> reporte=new ArrayList<ReportePorBusqueda>();
	
	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		ReportePorBusqueda nuevoResultado= new ReportePorBusqueda(unaBusqueda.getFraseBuscada(),unaBusqueda.getCantResultados(), unaBusqueda.getTiempoBusqueda());
		reporte.add(nuevoResultado);
		
	}
	public List<ReportePorBusqueda> generarReporte(){
		return reporte;
	}
}
