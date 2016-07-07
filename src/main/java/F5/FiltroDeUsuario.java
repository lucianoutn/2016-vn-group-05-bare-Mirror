package F5;

public abstract class FiltroDeUsuario implements CriterioDeFiltro{
	
	private String usuario;
	
	public boolean cumpleConElFiltro(Object unObjeto) {
		
		if(unObjeto==null)
			return true;
		
		return ((Busqueda) unObjeto).getUsuario().matches(usuario);

	}

}
