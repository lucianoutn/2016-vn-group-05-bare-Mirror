package F5.Terminal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import F5.Busqueda;
import F5.Pois.PuntoDeInteres;
import Reportes.NotificadorDeBusqueda;

@Entity
@Table(name="terminales")
public class Terminal extends INotificarCambioHorario {
	
	public Terminal(){
		//constructor vacio
	}
	
	@Id
	@GeneratedValue
	private int id_Terminal;
	
	private String nombreDeTerminal;
	
	public String getNombreDeTerminal() {
		return nombreDeTerminal;
	}

	@Transient
	private RepositorioDePOIs unMapa;
	
	@ManyToMany
	@JoinTable(name="Terminal_Notificadores",joinColumns=@JoinColumn(name="id_Terminal"),inverseJoinColumns=@JoinColumn(name="id"))
	private List<NotificadorDeBusqueda> listaObservadores= new ArrayList<NotificadorDeBusqueda>();
	
	public void setListaObservadores(List<NotificadorDeBusqueda> listaObservadores) {
		this.listaObservadores = listaObservadores;
	}

	public List<NotificadorDeBusqueda> getListaObservadores() {
		return listaObservadores;
	}

	@Transient
	private List<INotificarCambioHorario> procesosBatch =  new ArrayList<INotificarCambioHorario>();	
	
	public Terminal(String nombreTerminal,RepositorioDePOIs map){
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
		Busqueda unaBusqueda= new Busqueda(this,user,unaFrase);
		return unaBusqueda.buscoFrase(unaFrase, unMapa);
	}

	@Override
	public void anteCambioDeHorario(int horario) {
		procesosBatch.forEach(proceso-> proceso.anteCambioDeHorario(horario));
	}

	
}
