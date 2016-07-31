package F5.Reportes;

import java.util.HashMap;
import java.util.List;

import F5.Busqueda;
import F5.Terminal.Terminal;

public abstract class Reporte {

	private RepositorioDeBusquedas repositorioDeBusquedas;
	private int contadorDeBusquedasPorClave = 0;
	private boolean noHayReportes;
	
	public void setRepositorioDeBusquedas(Object repositorioDeBusquedas) {
		this.repositorioDeBusquedas = (RepositorioDeBusquedas) repositorioDeBusquedas;
	}

	public void generarReporte(){
		if (repositorioDeBusquedas.getBusquedasDelSistema().isEmpty()){
			this.setNoHayReportes(true);
		} else {
			this.agregoLasBusquedasAlDiccionario(repositorioDeBusquedas.getBusquedasDelSistema());
			this.setNoHayReportes(false);
		}
	}
	
	private void setNoHayReportes(boolean b) {
		this.noHayReportes = b;
	}

	public void agregoLasBusquedasAlDiccionario(List<Busqueda> busquedasDelSistema) {
		busquedasDelSistema.forEach(busqueda->this.actualizarOInsertarEnReporte(busqueda));
	}

	public abstract void actualizarOInsertarEnReporte(Busqueda busqueda);

	public int getContadorDeBusquedasPorClave() {
		return contadorDeBusquedasPorClave;
	}

	public void setContadorDeBusquedasPorClave(int contadorDeBusquedasPorClave) {
		this.contadorDeBusquedasPorClave = contadorDeBusquedasPorClave;
	}

	public boolean isNoHayReportes() {
		return noHayReportes;
	}
		
}
