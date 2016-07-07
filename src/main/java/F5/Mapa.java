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

	public List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}
	
	
	public void setPuntosDeInteres(List<PuntoDeInteres> parada101) {
		Mapa.puntosDeInteres = parada101;
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
	
	public double cantidadDeMatcheosConPois(String unaFrase){
		return puntosDeInteres.stream().filter(x -> x.encuentra(unaFrase)).count();
	}
	
	public List<Busqueda> getBusquedas() {
		return busquedas;
	}

	public  void agregarBusqueda(Busqueda busq) {
		busquedas.add(busq);
	}
	
	public List<PuntoDeInteres> buscaPuntosDeInteresEnSistemaySistemasExternos(String parametroBusqueda1, String parametroBusqueda2){
		
		List<PuntoDeInteres> poisEncontrados = new ArrayList<PuntoDeInteres>();
		List<PuntoDeInteres> poisSistema = new ArrayList<PuntoDeInteres>();
		
		if(parametroBusqueda1!=null || parametroBusqueda2!=null)
		//poisSistema = buscoEnElSistema(parametroBusqueda1,parametroBusqueda2);
		addAllIfNotNull(poisSistema,buscoEnElSistema(parametroBusqueda1,parametroBusqueda2)); //TODO Contrato debil
		
		//List<PuntoDeInteres> poisSistemasExternos = buscoEnSistemasExternos(parametroBusqueda1,parametroBusqueda2);
		
		poisEncontrados.addAll(poisSistema);
		//poisEncontrados.addAll(poisSistemasExternos);
				
		return poisEncontrados;
	}
	
	
	
	public List<PuntoDeInteres> buscoEnElSistema(String nombre, String servicio){
		
		return puntosDeInteres.stream().filter(	(unPOI-> unPOI.encuentra(nombre)	|| 
												unPOI.encuentra(servicio)))
												.collect(Collectors.toList());
	}		
		
	public List<PuntoDeInteres> buscoEnSistemasExternos(String parametroBusqueda1,String parametroBusqueda2){
		List<PuntoDeInteres> poisExternos = new ArrayList<PuntoDeInteres>();
		poisExternos.addAll(ConsultorCGP.buscaPuntosDeInteresENCGP(parametroBusqueda1));
		//poisExternos.addAll(ConsultorBancosJsonPosta.buscaPuntosDeInteresEnBanco(parametroBusqueda1,parametroBusqueda2));
		
		return poisExternos;
	}
	
	public List<PuntoDeInteres> addAllIfNotNull(List<PuntoDeInteres> list, Collection<? extends PuntoDeInteres> c) {
	    if (c != null) {
	        list.addAll(c);
	    }
	    return list;
	}

}
