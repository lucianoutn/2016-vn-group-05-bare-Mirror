package F5.Persistencia;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Terminal.Usuario;

public class PersistidorDeUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private static PersistidorDeUsuarios instancia;


	public static PersistidorDeUsuarios getInstancia() {
		if (instancia == null)
			instancia = new PersistidorDeUsuarios();
		return instancia;
	}

	public List<Usuario> traerUsuarios() {
		// traés los usuarios cuando es necesario
		return entityManager().createQuery("from Usuarios", Usuario.class).getResultList();
	}

	public void persistir(Usuario usuario) {// le pegás aca todas las horas y cuando es el
								// horario se persiste.
	entityManager().persist(usuario);
	}


}
