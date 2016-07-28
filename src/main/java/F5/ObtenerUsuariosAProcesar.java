package F5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObtenerUsuariosAProcesar {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public List<Usuario> unUsuario(Usuario unUsuario){
		usuarios.add(unUsuario);
		return usuarios;
	}
	
	public List<Usuario> usuariosEnComuna(Comuna unaComuna){
		usuarios = 					RepositorioDeUsuarios
									.getUsuarios().stream()
									.filter(usuario-> usuario.getComuna().getNroComuna() == unaComuna.getNroComuna())
									.collect(Collectors.toList());
		return usuarios;
	}
	
	public List<Usuario> usuariosEnSistema(){
		usuarios = RepositorioDeUsuarios.getUsuarios();
		return usuarios;
	}

}

