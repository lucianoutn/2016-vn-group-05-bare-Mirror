package F5;

import java.util.ArrayList;
import java.util.List;

public class Terminal implements INotificarCambioHorario {
	
	private String nombreDeTerminal;
	private RepositorioDePOIs unMapa ;
	private List<NotificadorDeBusqueda> listaObservadores= new ArrayList<NotificadorDeBusqueda>();
	private List<INotificarCambioHorario> procesosBatch =  new ArrayList<INotificarCambioHorario>();	
	
	public Terminal(String nombreTerminal, RepositorioDePOIs map){
		nombreDeTerminal=nombreTerminal;
		unMapa=map;
	}
	
	public List<INotificarCambioHorario> getProcesosBatch() {
		return procesosBatch;
	}

	public void anadirProcesoBatch(INotificarCambioHorario unProceso){
		procesosBatch.add(unProceso);
	}
	
	public void activarAccion(NotificadorDeBusqueda unObservador){
		listaObservadores.add(unObservador);
	}
	
	public void desactivarAccion(NotificadorDeBusqueda unObservador){
		listaObservadores.remove(unObservador);
	}
	
	public void aniadirPoi(PuntoDeInteres unPoi){
		unMapa.anadirPOI(unPoi);
	}
	
	public void modificarPOI(PuntoDeInteres poiAModificar, PuntoDeInteres poiModificado){
		unMapa.eliminarPOI(poiAModificar);
		unMapa.anadirPOI(poiModificado);
	}
	
	public void eliminarPOI(PuntoDeInteres unPoi){
		unMapa.eliminarPOI(unPoi);
	}
	
	public RepositorioDePOIs getUnMapa() {
		return unMapa;
	}

	public List<PuntoDeInteres> buscarEnTerminal(String unaFrase,String user){
		Busqueda unaBusqueda= new Busqueda(new Usuario(user, null),nombreDeTerminal,unaFrase,listaObservadores);
		return unaBusqueda.buscoFrase(unaFrase, unMapa);
	}

	@Override
	public void anteCambioDeHorario(int horario) {
		procesosBatch.forEach(proceso-> proceso.anteCambioDeHorario(horario));
	}
	
	
}
