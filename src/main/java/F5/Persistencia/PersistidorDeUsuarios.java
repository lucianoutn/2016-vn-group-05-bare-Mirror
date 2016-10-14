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

	private final static PersistidorDeUsuarios instancia = new PersistidorDeUsuarios();

	private LocalDate time;
	private List<Usuario> Usuarios = new ArrayList<Usuario>();// la
																// lista
																// de
																// usuarios
																// a
																// persistir

	public static PersistidorDeUsuarios getInstancia() {

		return instancia;
	}

	public void setHora(LocalDate hora) {
		time = hora;// seteas la hora a la que querés que ocurra la acción

	}

	public List<Usuario> traerUsuarios() {
		// traés los usuarios cuando es necesario
		return entityManager().createQuery("from Usuarios", Usuario.class).getResultList();
	}

	public void persistir() {// le pegás aca todas las horas y cuando es el
								// horario se persiste.
		if (LocalDate.now() == time)
			Usuarios.stream().forEach(usuario -> entityManager().persist(usuario));
	}

	public void guardaParaPersistir(Usuario usuario) {// guarda los objetos
														// en la lista para
														// persitirlos segun
														// evento

		this.Usuarios.add(usuario);
	}
}
