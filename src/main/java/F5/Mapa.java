package F5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import InterfacesExternas.ConsultorBancosMock;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.IConsultorCGP;
import InterfacesExternas.ISistemaExternoCGP;
import InterfacesExternas.OrigenDeDatos;
import InterfacesExternas.SistemaExternoCGPMock;

public class Mapa {

	// atributos
	private static List<PuntoDeInteres> puntosDeInteres = new ArrayList<>();
	private List<OrigenDeDatos> origenesDeDatos = new ArrayList<>();
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();
	private ConsultorBancosMock consultorDeBancos = new ConsultorBancosMock();
	private SistemaExternoCGPMock mockSistemaExternoCGP = new SistemaExternoCGPMock();
	private ConsultorCGP consultorCGP = new ConsultorCGP(mockSistemaExternoCGP);

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
	
	public List<PuntoDeInteres> buscaPuntosDeInteresEnSistemaySistemasExternos(String nombre, String servicio){
		
		List<PuntoDeInteres> poisSistema = new ArrayList<PuntoDeInteres>();	//Tiene los POIs encontrados en el Sistema
		List<PuntoDeInteres> poisSistemasExternos = new ArrayList<PuntoDeInteres>(); //Tiene los POIs encontrados en el Sistema Externo
		List<PuntoDeInteres> poisEncontrados = new ArrayList<PuntoDeInteres>(); //Tiene la union entre poisSistema y poisSistemasExternos
		
		poisSistema = buscoEnElSistema(nombre, servicio);
		addAllIfNotNull(poisEncontrados,poisSistema);
		
		poisSistemasExternos = buscoEnSistemasExternos(nombre,servicio);
		addAllIfNotNull(poisEncontrados,poisSistemasExternos);
				
		return poisEncontrados;
	}
	
	
	
	public List<PuntoDeInteres> buscoEnElSistema(String nombre, String servicio){
		
		if (nombre==null)
			return puntosDeInteres.stream().filter(	(unPOI-> unPOI.encuentra(servicio)))
												.collect(Collectors.toList());
		if (servicio==null)
			return puntosDeInteres.stream().filter(	(unPOI-> unPOI.encuentra(nombre)))
					.collect(Collectors.toList());
		
		return puntosDeInteres.stream().filter(	(unPOI-> unPOI.encuentra(servicio) || unPOI.encuentra(nombre)))
				.collect(Collectors.toList());
	}		
		
	public List<PuntoDeInteres> buscoEnSistemasExternos(String nombre,String servicio){
		List<PuntoDeInteres> poisExternos = new ArrayList<PuntoDeInteres>();
		if(nombre==null){
			addAllIfNotNull(poisExternos,consultorDeBancos.bancosQueCumplenCon(null, servicio));
		} else if (servicio==null){
			addAllIfNotNull(poisExternos,consultorCGP.buscaPuntosDeInteresENCGP(nombre));
			addAllIfNotNull(poisExternos,consultorDeBancos.bancosQueCumplenCon(nombre, null));
		} else {
		addAllIfNotNull(poisExternos,consultorCGP.buscaPuntosDeInteresENCGP(nombre));
		addAllIfNotNull(poisExternos,consultorDeBancos.bancosQueCumplenCon(nombre, servicio));
		}
		return poisExternos;
	}
	
	public List<PuntoDeInteres> addAllIfNotNull(List<PuntoDeInteres> list, Collection<? extends PuntoDeInteres> c) {
	    if (c != null) {
	        list.addAll(c);
	    }
	    return list;
	}

}
