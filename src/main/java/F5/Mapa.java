package F5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import InterfacesExternas.ConsultorBancosJsonPosta;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.OrigenDeDatos;

public class Mapa {

	// atributos
	private static List<PuntoDeInteres> puntosDeInteres = new ArrayList<>();
	private List<OrigenDeDatos> origenesDeDatos = new ArrayList<>();
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();

	// metodos
	
	public void anadirPOI(PuntoDeInteres poi) {
		puntosDeInteres.add(poi);
	}
	
	public void limpiarPuntosDeInteres(){
		puntosDeInteres.clear();
	}

	public static List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}

	public void setPuntosDeInteres(List<PuntoDeInteres> puntosDeInteres) {
		this.puntosDeInteres = puntosDeInteres;
	}

	public void eliminarPOI(PuntoDeInteres poi) {
		puntosDeInteres.remove(poi);
	}

	public boolean contiene(PuntoDeInteres unPoi) {
		return (puntosDeInteres.stream().anyMatch(x -> x == unPoi));
	}

	public void aniadirOrigenDeDatos(OrigenDeDatos origen) {
		origenesDeDatos.add(origen);
	}

	public static void limpiar() {
		busquedas.clear();
	}
	
	public static double cantidadDeMatcheosConPois(String unaFrase){
		return puntosDeInteres.stream().filter(x -> x.encuentra(unaFrase)).count();
	}
	
	public static List<Busqueda> getBusquedas() {
		return busquedas;
	}

	public static void agregarBusqueda(Busqueda busq) {
		busquedas.add(busq);
	}
	
	public static List<PuntoDeInteres> buscaPuntosDeInteresEnSistemaySistemasExternos(String parametroBusqueda1, String parametroBusqueda2){
		
		List<PuntoDeInteres> poisEncontrados = new ArrayList<PuntoDeInteres>();
		List<PuntoDeInteres> poisSistema = new ArrayList<PuntoDeInteres>();
		
		if(parametroBusqueda1!=null || parametroBusqueda2!=null)
		//poisSistema = buscoEnElSistema(parametroBusqueda1,parametroBusqueda2);
		addAllIfNotNull(poisSistema,buscoEnElSistema(parametroBusqueda1,parametroBusqueda2));
		
		//List<PuntoDeInteres> poisSistemasExternos = buscoEnSistemasExternos(parametroBusqueda1,parametroBusqueda2);
		
		poisEncontrados.addAll(poisSistema);
		//poisEncontrados.addAll(poisSistemasExternos);
				
		return poisEncontrados;
	}
	
	
	
	public static List<PuntoDeInteres> buscoEnElSistema(String nombre, String servicio){
		
		return puntosDeInteres.stream().filter(	(unPOI-> unPOI.encuentra(nombre)	|| 
												unPOI.encuentra(servicio)))
												.collect(Collectors.toList());
	}		
		
	public static List<PuntoDeInteres> buscoEnSistemasExternos(String parametroBusqueda1,String parametroBusqueda2){
		List<PuntoDeInteres> poisExternos = new ArrayList<PuntoDeInteres>();
		poisExternos.addAll(ConsultorCGP.buscaPuntosDeInteresENCGP(parametroBusqueda1));
		//poisExternos.addAll(ConsultorBancosJsonPosta.buscaPuntosDeInteresEnBanco(parametroBusqueda1,parametroBusqueda2));
		
		return poisExternos;
	}
	
	public static List<PuntoDeInteres> addAllIfNotNull(List<PuntoDeInteres> list, Collection<? extends PuntoDeInteres> c) {
	    if (c != null) {
	        list.addAll(c);
	    }
	    return list;
	}
}
