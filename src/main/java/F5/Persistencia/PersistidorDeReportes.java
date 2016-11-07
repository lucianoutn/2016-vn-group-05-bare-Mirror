package F5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.PuntoDeInteres;
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorBusqueda;
import Reportes.ResultadosDeBusquedas;
import Reportes.ResultadosPorTerminal;

public class PersistidorDeReportes extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private static PersistidorDeReportes instancia;


	public static PersistidorDeReportes getInstancia() {
		if (instancia == null)
			instancia = new PersistidorDeReportes();
		return instancia;
	}

	
	public List<NotificadorDeBusqueda> traerNotificadores() {
		// traés los notificadores cuando es necesario
		return entityManager().createQuery("from NotificadorDeBusqueda", NotificadorDeBusqueda.class).getResultList();
	}
	
	public List<ResultadosDeBusquedas> traerResultadosDeBusquedas(){
		return entityManager().createQuery("from ResultadosDeBusquedas", ResultadosDeBusquedas.class).getResultList();
		
	}
	
	public List<ResultadosPorTerminal> traerResultadosPorTerminal(){
		return entityManager().createQuery("from ResultadosPorTerminal", ResultadosPorTerminal.class).getResultList();

		
	}
	
	
	public void persistir(NotificadorDeBusqueda notificador) {
		entityManager().persist(notificador);
		
	//persistis los notificadores uno por uno
	}

	
	
}
