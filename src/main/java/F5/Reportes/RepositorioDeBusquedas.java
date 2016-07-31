package F5.Reportes;

import java.util.ArrayList;
import java.util.List;

import F5.Busqueda;

public class RepositorioDeBusquedas {

	private static List<Busqueda> busquedasDelSistema = new ArrayList<Busqueda>();

	public List<Busqueda> getBusquedasDelSistema() {
		return busquedasDelSistema;
	}

	public void agregarBusqueda(Busqueda busqueda) {
		busquedasDelSistema.add(busqueda);
	}	
	
	public void limpiarBusquedas(){
		busquedasDelSistema.clear();
	}
}
