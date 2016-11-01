package F5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.PuntoDeInteres;
import Reportes.NotificadorDeBusqueda;

public class PersistidorDeReportes extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private final static PersistidorDeReportes instancia = new PersistidorDeReportes();


	public static PersistidorDeReportes getInstancia() {

		return instancia;
	}

	
	public List<NotificadorDeBusqueda> traerNotificadores() {
		// traés los notificadores cuando es necesario
		return entityManager().createQuery("from NotificadorDeBusqueda", NotificadorDeBusqueda.class).getResultList();
	}
	

	
	public void persistir(NotificadorDeBusqueda notificador) {
		entityManager().persist(notificador);
		
	//persistis los notificadores uno por uno
	}

	
	
}
