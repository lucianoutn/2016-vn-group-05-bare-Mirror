package F5;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class GeneradorDeReporte {
		
	public ArrayList<Busqueda> generarReporte(){
		//buscar todas las busquedas
		List<Busqueda> busquedas = buscarBusquedas();
		ColeccionDeFiltros unaColeccionDeFiltros;
		//Terminar
		
		return busquedas.forEach(busqueda->busqueda.aplicarFiltros(criterios,busqueda));	
		
	}

	private List<Busqueda> buscarBusquedas() {
		return Mapa.getBusquedas();
	}
}
