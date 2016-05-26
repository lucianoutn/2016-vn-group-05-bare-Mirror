package F5;

import java.util.ArrayList;
import java.util.List;

public final class RepositorioImpostor {

	
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();
	
	public static void limpiarLista(){
		busquedas.clear();
	}
	
	public static List<Busqueda> getBusquedas(){
		return busquedas;
	}
	public static void setBusquedas(Busqueda busq){
		busquedas.add(busq);
	}
}
