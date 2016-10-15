package F5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Terminal.Terminal;
import F5.Terminal.Usuario;

public class PersistidorDeTerminal  extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private final static PersistidorDeTerminal instancia = new PersistidorDeTerminal();

	private static LocalDate time;
	private  List<Terminal> terminales = new ArrayList<Terminal>();

	public static PersistidorDeTerminal getInstancia() {

		return instancia;
	}
	

	public  void clear(){
		terminales.clear();
	}

	public static void setHora(LocalDate hora) {
		time = hora;// seteas la hora a la que querés que ocurra la acción

	}

	public List<Terminal> traerUsuarios() {
		// traés los usuarios cuando es necesario
		return entityManager().createQuery("from Terminal", Terminal.class).getResultList();
	}

	public void persistir() {// le pegás aca todas las horas y cuando es el
								// horario se persiste.
		if (LocalDate.now() == time)
			terminales.stream().forEach(terminal -> entityManager().persist(terminal));
	}

	public void guardaParaPersistir(Terminal term) {// guarda los objetos
														// en la lista para
														// persitirlos segun
														// evento

		this.terminales.add(term);
	}
	
}
