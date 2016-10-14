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

	private final static PersistidorDePOIs instancia = new PersistidorDePOIs();

	private LocalDate time;
	private List<PuntoDeInteres> POIS = new ArrayList<PuntoDeInteres>();// la
																		// lista
																		// de
																		// pois
																		// a
																		// persistir

	public static PersistidorDePOIs getInstancia() {

		return instancia;
	}

	public void setHora(LocalDate hora) {
		time = hora;// seteas la hora a la que querés que ocurra la acción

	}

	public List<PuntoDeInteres> traerPOIS() {
		// traés los pois cuando es necesario
		return entityManager().createQuery("from PuntoDeInteres", PuntoDeInteres.class).getResultList();
	}

	public void persistir() {// le pegás aca todas las horas y cuando es el
								// horario se persiste.
		if (LocalDate.now() == time)
			POIS.stream().forEach(poi -> entityManager().persist(poi));
	}

	public void guardaParaPersistir(PuntoDeInteres POI) {// guarda los objetos
															// en la lista para
															// persitirlos segun
															// evento

		this.POIS.add(POI);
	}
}
