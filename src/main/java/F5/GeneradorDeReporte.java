package F5;

import java.util.List;
import java.time.LocalTime;
public class GeneradorDeReporte {
		
	public long generarReporte(String unUsuario, String unaTerminal, LocalTime unaFecha, String unaFrase){
		//buscar todas las busquedas
		List<Busqueda> busquedas = buscarBusquedas();
		
		return busquedas.stream()
					 .filter(b-> b.realizadaEn(unaTerminal))
					 .filter(b-> b.realizadaEnLaFecha(unaFecha))
					 .filter(b-> b.realizadaPor(unUsuario))
					 .filter(b-> b.buscoFrase(unaFrase))
					 .count()
					 ;		
		
	}

	private List<Busqueda> buscarBusquedas() {
		return Mapa.getBusquedas();
	}
}
