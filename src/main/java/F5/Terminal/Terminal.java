package F5.Terminal;

import java.util.ArrayList;
import java.util.List;

import F5.Busqueda;
import F5.Pois.PuntoDeInteres;
import Reportes.NotificadorDeBusqueda;

public class Terminal implements INotificarCambioHorario {
	
	private int cd_Terminal;
	private int cd_Busqueda = 1; //Lo iniciamos en 1 para que no haya cd_Busqueda con codigo 0
	private String nombreDeTerminal;
	private RepositorioDePOIs unMapa ;
	private List<NotificadorDeBusqueda> listaObservadores= new ArrayList<NotificadorDeBusqueda>();
	private List<INotificarCambioHorario> procesosBatch =  new ArrayList<INotificarCambioHorario>();	
	
	public Terminal(int id_Terminal,String nombreTerminal,RepositorioDePOIs map){
		cd_Terminal = id_Terminal;
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

	public List<PuntoDeInteres> buscarEnTerminal(String unaFrase, Usuario user){
		Busqueda unaBusqueda= new Busqueda(cd_Busqueda, cd_Terminal, user,nombreDeTerminal,unaFrase,listaObservadores);
		this.incrementar_cd_Busqueda();	//Cada vez que haga una busqueda, la asigna una clave natural mas alta
		return unaBusqueda.buscoFrase(unaFrase, unMapa);
	}

	private void incrementar_cd_Busqueda() {
		cd_Busqueda++;
	}

	@Override
	public void anteCambioDeHorario(int horario) {
		procesosBatch.forEach(proceso-> proceso.anteCambioDeHorario(horario));
	}

	
}
