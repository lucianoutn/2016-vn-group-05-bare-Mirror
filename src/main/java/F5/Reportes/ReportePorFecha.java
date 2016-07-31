package F5.Reportes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import F5.Busqueda;


public class ReportePorFecha extends Reporte{
	
	private Map<LocalDate, Integer> busquedasPorFecha;
	private LocalDate fechaDeBusqueda;
	
	
	public ReportePorFecha(RepositorioDeBusquedas unRepositorioDeBusquedas){
		this.setRepositorioDeBusquedas(unRepositorioDeBusquedas);
		busquedasPorFecha = new HashMap<LocalDate, Integer>();
	}
	
	public void actualizarOInsertarEnReporte(Busqueda busqueda) {
		fechaDeBusqueda = busqueda.getFecha().toLocalDate();
		if (!busquedasPorFecha.containsKey(fechaDeBusqueda)){
			busquedasPorFecha.put(fechaDeBusqueda, 1);
		} else {
			this.setContadorDeBusquedasPorClave(busquedasPorFecha.get(fechaDeBusqueda)+1);
			busquedasPorFecha.put(fechaDeBusqueda, this.getContadorDeBusquedasPorClave());
		}
	}
	
	public int numeroDeBusquedasPara(LocalDate unaFecha){
		return busquedasPorFecha.get(unaFecha);
	}
}
