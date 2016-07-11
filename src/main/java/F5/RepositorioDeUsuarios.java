package F5;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeUsuarios {
	
	public static List<Usuario> usuarios = new ArrayList<Usuario>();

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public static void addUsuario(Usuario unUsuario){
		usuarios.add(unUsuario);
	}
}
