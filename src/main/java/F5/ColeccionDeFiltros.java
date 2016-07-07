package F5;

import java.util.List;
import java.util.ArrayList;

public class ColeccionDeFiltros {

	List<CriterioDeFiltro> criteriosDeFiltro = new ArrayList<>();
	
	public void agregaCriterio(CriterioDeFiltro unCriterio) {
		criteriosDeFiltro.add(unCriterio);
	}
		
	public void aplicaCriterios(List<CriterioDeFiltro> coleccionDeCriterios, Busqueda unaBusqueda){
		coleccionDeCriterios.forEach(criterio->criterio.cumpleConElFiltro(unaBusqueda));
	}
	
}