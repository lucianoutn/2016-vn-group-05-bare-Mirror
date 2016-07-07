package F5;

import java.time.LocalTime;

public class FiltroDeFecha implements CriterioDeFiltro{

	private LocalTime unaFecha;
	
	@Override
	public boolean cumpleConElFiltro(Object unObjeto) {

		return ((Busqueda) unObjeto).getFecha().equals(unaFecha);

	}
}
