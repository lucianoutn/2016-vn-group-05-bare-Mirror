package F5;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import F5.Pois.PuntoDeInteres;

@Entity
@Table(name="T_RESULTADO_BUSQUEDA")
public class PersistenciaDeBusquedas {
			
	@Id
	@Column(name="CD_BUSQUEDA")
	private int cd_Busqueda;
	
	@Column(name="CD_TERMINAL")
	private int cd_Terminal;

	@Column(name="TIEMPO_BUSQUEDA")
	private int tiempoBusqueda;

	@Column(name="DC_FRASE_BUSCADA")
	private String fraseBuscada;
	
	@Column(name="QT_RESULTADOS")
	private int cantidadDeResultados;
	//Cuando esten agregados los POIs a la tabla, se va a setear en 1 si encontro algun POI o en 0 si no encontro ninguno.
	//Si hago una Busqueda y no encuentro ningun POI, voy a tener un registro con QT_RESULTADOS=0
	//Si hago una Busqueda y encuentra (por lo menos) un POI, voy a tener cada registro con QT_RESULTADOS=1 y los podre sumarizar
	
	@Transient
	private List<PuntoDeInteres> poisEncontrados = new ArrayList<>();

	
	public void almacenarDatosEnAtributosParaPersistir(DatosDeBusquedaParaPersistir datosDeBusquedaParaPersistir) {
		cd_Busqueda = datosDeBusquedaParaPersistir.getCd_Busqueda();
		cd_Terminal = datosDeBusquedaParaPersistir.getCd_Terminal();
		tiempoBusqueda = datosDeBusquedaParaPersistir.getTiempoBusqueda();
		cantidadDeResultados = datosDeBusquedaParaPersistir.getCantidadDeResultados();
		fraseBuscada = datosDeBusquedaParaPersistir.getFraseBuscada();
		poisEncontrados = datosDeBusquedaParaPersistir.getPoisEncontrados();
	}
		
	public int getCd_Busqueda() {
		return cd_Busqueda;
	}
	
	public int getCd_Terminal() {
		return cd_Terminal;
	}

	public void setCd_Terminal(int cd_Terminal) {
		this.cd_Terminal = cd_Terminal;
	}

	public void setCd_Busqueda(int cd_Busqueda) {
		this.cd_Busqueda = cd_Busqueda;
	}
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	

}
