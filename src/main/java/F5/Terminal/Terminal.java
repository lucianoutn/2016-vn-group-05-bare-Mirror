package F5.Terminal;

import java.util.ArrayList;
import java.util.List;

import F5.Busqueda;
import F5.Pois.PuntoDeInteres;
import F5.Procesos.NotificadorDeBusqueda;
import F5.Reportes.RepositorioDeBusquedas;
import Mocks.NotificadorDeAdministradorMock;

public class Terminal implements INotificarCambioHorario {
	
	private String nombreDeTerminal;
	private RepositorioDePOIs unMapa ;
	private List<INotificarCambioHorario> procesosBatch =  new ArrayList<INotificarCambioHorario>();
	private NotificadorDeBusqueda unNotificadorDeBusqueda;
	private RepositorioDeBusquedas repositorioDeBusquedas;
	
	public void setUnNotificadorDeBusqueda(NotificadorDeBusqueda unNotificadorDeBusqueda) {
		this.unNotificadorDeBusqueda = unNotificadorDeBusqueda;
	}

	public Terminal(String nombreTerminal, RepositorioDePOIs map, RepositorioDeBusquedas unRepositorioDeBusquedas){
		nombreDeTerminal=nombreTerminal;
		unMapa=map;
		repositorioDeBusquedas = unRepositorioDeBusquedas;
	}
	
	public List<INotificarCambioHorario> getProcesosBatch() {
		return procesosBatch;
	}

	public void anadirProcesoBatch(INotificarCambioHorario unProceso){
		procesosBatch.add(unProceso);
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
		Busqueda unaBusqueda= new Busqueda(user,this,unaFrase, repositorioDeBusquedas, unNotificadorDeBusqueda);
		return unaBusqueda.buscoFrase(unaFrase, unMapa);	
	};

	@Override
	public void anteCambioDeHorario(int horario) {
		procesosBatch.forEach(proceso-> proceso.anteCambioDeHorario(horario));
	}

	
}
