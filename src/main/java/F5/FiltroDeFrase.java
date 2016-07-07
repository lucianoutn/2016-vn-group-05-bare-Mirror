package F5;

public class FiltroDeFrase implements CriterioDeFiltro{

	private String unaFrase;
	private int cantidadDeResultados;
	
	@Override
	public boolean cumpleConElFiltro(Object unObjeto) {
		
		if(unaFrase==null)
			return true;
		
		((Busqueda) unObjeto).setFraseBuscada(unaFrase);
		
		cantidadDeResultados = (int) Mapa.cantidadDeMatcheosConPois(unaFrase);
		
		return (cantidadDeResultados>0);
	}

}
