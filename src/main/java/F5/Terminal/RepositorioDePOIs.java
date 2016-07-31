package F5.Terminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import F5.Busqueda;
import F5.Pois.PuntoDeInteres;
import InterfacesExternas.ConsultorBancos;

import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.IConsultorCGP;
import InterfacesExternas.ISistemaExternoCGP;
import InterfacesExternas.OrigenDeDatos;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class RepositorioDePOIs {

	// atributos
	private List<PuntoDeInteres> puntosDeInteres = new ArrayList<>();
	
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();
	private ConsultorBancos consultorDeBancos;
	private ConsultorCGP consultorCGP;
	
	public ConsultorBancos getConsultorDeBancos() {
		return consultorDeBancos;
	}
	
	public ConsultorCGP getConsultorCGP() {
		return consultorCGP;
	}
	
	public RepositorioDePOIs(ConsultorBancos c_bancos, ConsultorCGP c_cgp){
		consultorCGP= c_cgp;
		consultorDeBancos = c_bancos;
	}
	
	
	public void anadirPOI(PuntoDeInteres poi) {
		puntosDeInteres.add(poi);
	}
	
	public List<PuntoDeInteres> getPOIs(){
		return puntosDeInteres;
	}
	
	public void limpiarPuntosDeInteres() {
		puntosDeInteres.clear();
	}

	public List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}	
	
	public void setPuntosDeInteres(List<PuntoDeInteres> pois) {
		this.puntosDeInteres.addAll(pois);
	}

	public void eliminarPOI(PuntoDeInteres poi) {
		puntosDeInteres.remove(poi);
	}

	public boolean contiene(PuntoDeInteres unPoi) {
		return (puntosDeInteres.stream().anyMatch(x -> x == unPoi));
	}

	
	public double cantidadDeMatcheosConPois(String unaFrase) {
		return puntosDeInteres.stream().filter(x -> x.encuentra(unaFrase)).count();
	}

	public List<Busqueda> getBusquedas() {
		return busquedas;
	}

	public void agregarBusqueda(Busqueda busq) {
		busquedas.add(busq);
	}

	public List<PuntoDeInteres> buscaPuntosDeInteresEnSistemaySistemasExternos(String nombre, String servicio) {

		List<PuntoDeInteres> poisSistema = new ArrayList<PuntoDeInteres>();																		
		List<PuntoDeInteres> poisSistemasExternos = new ArrayList<PuntoDeInteres>();																						// Externo
		List<PuntoDeInteres> poisEncontrados = new ArrayList<PuntoDeInteres>(); 
		
		poisSistema = buscoEnElSistema(nombre, servicio);
		addAllIfNotNull(poisEncontrados, poisSistema);

		poisSistemasExternos = buscoEnSistemasExternos(nombre, servicio);
		addAllIfNotNull(poisEncontrados, poisSistemasExternos);

		return poisEncontrados;
	}

	public List<PuntoDeInteres> buscoEnElSistema(String nombre, String servicio) {

		if (nombre == null)
			return puntosDeInteres.stream().filter((unPOI -> unPOI.encuentra(servicio))).collect(Collectors.toList());
		if (servicio == null)
			return puntosDeInteres.stream().filter((unPOI -> unPOI.encuentra(nombre))).collect(Collectors.toList());

		return puntosDeInteres.stream().filter((unPOI -> unPOI.encuentra(servicio) || unPOI.encuentra(nombre)))
				.collect(Collectors.toList());
	}

	public List<PuntoDeInteres> buscoEnSistemasExternos(String nombre, String servicio) {
		List<PuntoDeInteres> poisExternos = new ArrayList<PuntoDeInteres>();
		
		addAllIfNotNull(poisExternos,consultorCGP.buscaPuntosDeInteresENCGP(nombre));
 		addAllIfNotNull(poisExternos,consultorDeBancos.bancosQueCumplenCon(nombre, servicio));

		return poisExternos;
	}

	public List<PuntoDeInteres> addAllIfNotNull(List<PuntoDeInteres> list, Collection<? extends PuntoDeInteres> c) {
		if (c != null) {
			list.addAll(c);
		}
		return list;
	}

}
