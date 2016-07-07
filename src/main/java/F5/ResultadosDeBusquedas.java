package F5;

import java.util.ArrayList;

public class ResultadosDeBusquedas implements NotificadorDeBusqueda {

	private ArrayList<ResultadoDeBusqueda> reporte=new ArrayList<>();
	
	@Override
	public void notificarBusqueda(Busqueda unaBusqueda) {
		ResultadoDeBusqueda nuevoResultado= new ResultadoDeBusqueda(unaBusqueda.getFraseBuscada(),unaBusqueda.getCantResultados(), unaBusqueda.getTiempoBusqueda());
		reporte.add(nuevoResultado);
		
	}
	public ArrayList<ResultadoDeBusqueda> generarReporte(){
		return reporte;
	}
}
