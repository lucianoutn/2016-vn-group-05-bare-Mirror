package F5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Terminal.Terminal;
import F5.Terminal.Usuario;

public class PersistidorDeTerminal  extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private static PersistidorDeTerminal instancia;

	public static PersistidorDeTerminal getInstancia() {
		if (instancia == null)
			instancia = new PersistidorDeTerminal();
		return instancia;
	}
	

	public List<Terminal> traerUsuarios() {
		// traés los usuarios cuando es necesario
		return entityManager().createQuery("from Terminal", Terminal.class).getResultList();
	}

	public void persistir(Terminal terminal) {// le pegás aca todas las horas y cuando es el
								// horario se persiste.
		entityManager().persist(terminal);
	}

}
