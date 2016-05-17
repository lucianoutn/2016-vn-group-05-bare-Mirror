package F5;

import java.util.ArrayList;
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
		
		
		// TODO hacer un test para cuando pide:
		// 1) todos los parametros correctos
		// 2) para un parametro null 
		// 3) para dos parametros null
		// 4) para tres parametros  null
		// 5) para todos los parametros null
	}

	private List<Busqueda> buscarBusquedas() {
		return RepositorioImpostor.getBusquedas();
	}
}
