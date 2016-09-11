package F5;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import F5.Pois.PuntoDeInteres;

@Entity
public class PersistenciaDeBusquedas {

	private int cd_Busqueda;
	private int cd_Terminal;
	private int tiempoBusqueda;
	private int cantidadDeResultados;
	private String fraseBuscada;
	private List<PuntoDeInteres> poisEncontrados = new ArrayList<>();
	private Busqueda_CompositeKey busquedaKey;
	
	public void almacenarDatosEnAtributosParaPersistir(DatosDeBusquedaParaPersistir datosDeBusquedaParaPersistir) {
		cd_Busqueda = datosDeBusquedaParaPersistir.getCd_Busqueda();
		cd_Terminal = datosDeBusquedaParaPersistir.getCd_Terminal();
		tiempoBusqueda = datosDeBusquedaParaPersistir.getTiempoBusqueda();
		cantidadDeResultados = datosDeBusquedaParaPersistir.getCantidadDeResultados();
		fraseBuscada = datosDeBusquedaParaPersistir.getFraseBuscada();
		poisEncontrados = datosDeBusquedaParaPersistir.getPoisEncontrados();
	}
	
	public void armaClaveCompuesta(){
		busquedaKey.setBusqueda(cd_Busqueda);
		busquedaKey.setTerminal(cd_Terminal);
	}
	
	@EmbeddedId
	@GeneratedValue
	public Busqueda_CompositeKey claveCompuestaDeBusqueda(){
		return busquedaKey;
	}
	

}
