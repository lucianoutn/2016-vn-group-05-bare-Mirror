package F5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Busqueda;
import Reportes.NotificadorDeBusqueda;

public class PersistidorDeBusqueda extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	private static PersistidorDeBusqueda instancia;

	
	public static PersistidorDeBusqueda getInstancia() {
		if (instancia == null)
			instancia = new PersistidorDeBusqueda();
		return instancia;
	}

	
	public List<Busqueda> traerBusquedas() {
		// traés las busquedas cuando es necesario
		return entityManager().createQuery("from Busqueda", Busqueda.class).getResultList();
	}

	public void persistir(Busqueda unaBusqueda) {
		entityManager().persist(unaBusqueda);
		
	//persistis las busquedas una por una
	}


	

}
