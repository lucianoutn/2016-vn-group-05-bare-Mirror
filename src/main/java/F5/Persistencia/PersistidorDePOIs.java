package F5.Persistencia;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.PuntoDeInteres;
import Reportes.BusquedasPorFecha;

public class PersistidorDePOIs extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private static PersistidorDePOIs instancia;
	
	public static PersistidorDePOIs getInstancia() {
		if (instancia == null)
			instancia = new PersistidorDePOIs();
		
		return instancia;
	}


	public List<PuntoDeInteres> traerPOIS() {
		// traés los pois cuando es necesario
		return entityManager().createQuery("from PuntoDeInteres", PuntoDeInteres.class).getResultList();
	}

	public void persistir(PuntoDeInteres POI) {// le pegás aca todas las horas y cuando es el
								// horario se persiste.
		entityManager().persist(POI);
	}


}
