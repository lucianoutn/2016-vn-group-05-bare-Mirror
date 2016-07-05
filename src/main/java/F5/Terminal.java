package F5;

public class Terminal {
	
	private Mapa unMapa = new Mapa();
	
	public void aniadirPoi(PuntoDeInteres unPoi){
		unMapa.anadirPOI(unPoi);
	}
	
	public void darDeBaja(PuntoDeInteres unPoi){
		unMapa.eliminarPOI(unPoi);
	}
	
	public void modificarPOI(PuntoDeInteres poiAModificar, PuntoDeInteres poiModificado){
		unMapa.eliminarPOI(poiAModificar);
		unMapa.anadirPOI(poiModificado);
	}
	

}
