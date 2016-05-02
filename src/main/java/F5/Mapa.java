package F5;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
	private List<PuntoDeInteres> puntosDeInteres = new ArrayList<>();
	
	public void anadirPOI(PuntoDeInteres poi) {
	puntosDeInteres.add(poi);
	}
	
	public boolean contiene(PuntoDeInteres unPoi){
	return (puntosDeInteres.stream().anyMatch(x->x == unPoi));
	}
}
