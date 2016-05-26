package F5;

import java.util.ArrayList;
import java.util.List;

public final class RepositorioImpostor {

	private static Mapa mapa= new Mapa();
	public static Mapa getMapa() {
		return mapa;
	}

	
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();
	
	public static void limpiar(){
		busquedas.clear();
	}
	
	public static List<Busqueda> getBusquedas(){
		return busquedas;
	}
	public static void agregarBusqueda(Busqueda busq){
		busquedas.add(busq);
	}
	
	
	
	
	
}
