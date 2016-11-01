package Reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import F5.Busqueda;
import F5.Persistencia.PersistidorDeReportes;

@Entity

public class ResultadosDeBusquedas extends NotificadorDeBusqueda {

	@OneToMany
	@JoinColumn(name = "numeroDeRenglon")
	private List<ReportePorBusqueda> reporte = new ArrayList<ReportePorBusqueda>();

	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		ReportePorBusqueda nuevoResultado = new ReportePorBusqueda(unaBusqueda.getFraseBuscada(),
				unaBusqueda.getCantResultados(), unaBusqueda.getTiempoBusqueda());
		reporte.add(nuevoResultado);
		// una opción es que vaya acá
	}

	public List<ReportePorBusqueda> generarReporte() {
		return reporte;
	}
}
