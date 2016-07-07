package F5;

public class FiltroDeTerminal implements CriterioDeFiltro{

	private String unaTerminal;
	
	@Override
	public boolean cumpleConElFiltro(Object unObjeto) {
		
		if(unObjeto==null)
			return false;
		
		return ((Busqueda) unObjeto).getTerminal().matches(unaTerminal);
	}
	

}
