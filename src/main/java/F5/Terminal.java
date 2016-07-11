package F5;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
	
	private String nombreDeTerminal;
	private RepositorioDePOIs unMapa ;
	private ArrayList<NotificadorDeBusqueda> listaObservadores= new ArrayList<>();
	
	
	
	public Terminal(String nombreTerminal, RepositorioDePOIs map){
		nombreDeTerminal=nombreTerminal;
		unMapa=map;
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
		Busqueda unaBusqueda= new Busqueda(new Usuario(user),nombreDeTerminal,unaFrase,listaObservadores);
		return unaBusqueda.buscoFrase(unaFrase, unMapa);
	}
}
