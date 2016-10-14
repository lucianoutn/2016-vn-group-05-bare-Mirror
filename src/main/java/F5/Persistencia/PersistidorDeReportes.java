package F5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.PuntoDeInteres;
import Reportes.NotificadorDeBusqueda;

public class PersistidorDeReportes extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private final static PersistidorDeReportes instancia = new PersistidorDeReportes();

	private LocalDate time;
	private List<NotificadorDeBusqueda> notificadores = new ArrayList<NotificadorDeBusqueda>();
	
	public void setHora(LocalDate hora) {
		time = hora;// seteas la hora a la que querés que ocurra la acción

	}
	
	public static PersistidorDeReportes getInstancia() {

		return instancia;
	}

	
	public List<NotificadorDeBusqueda> traerNotificadores() {
		// traés los notificadores cuando es necesario
		return entityManager().createQuery("from NotificadorDeBusqueda", NotificadorDeBusqueda.class).getResultList();
	}

	public void persistir() {
		if (LocalDate.now() == time)
			notificadores.stream().forEach(notificador -> entityManager().persist(notificador));
		
	//persistis los notificadores uno por uno
	}

	public void guardaParaPersistir(NotificadorDeBusqueda notificador) {

		this.notificadores.add(notificador);
	}
	
	
	
}
