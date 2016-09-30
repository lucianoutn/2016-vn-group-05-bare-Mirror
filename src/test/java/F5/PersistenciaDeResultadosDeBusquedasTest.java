package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Comuna;
import F5.Terminal.Usuario;
import Reportes.NotificadorDeBusqueda;
import Reportes.ResultadosDeBusquedas;


public class PersistenciaDeResultadosDeBusquedasTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private ResultadosDeBusquedas unReporte;
	private ArrayList<NotificadorDeBusqueda> observadores;

	@Before
	public void initialize() {
		unReporte = new ResultadosDeBusquedas();
		observadores = new ArrayList<>();
		observadores.add(unReporte);
	}
	
	@Test
	public void guardoUnReporteVacio(){
		entityManager().persist(unReporte);
		List<ResultadosDeBusquedas> copiaDelReporte = entityManager()
				.createQuery("from ResultadosDeBusquedas", ResultadosDeBusquedas.class)
				.getResultList();
		Assert.assertTrue(copiaDelReporte.stream().anyMatch(repo->repo.getId() == unReporte.getId()));
		
	}
	
	@Test
	public void hagoUnaBusquedaYGuardoSuReporte() {
		Busqueda unaBusqueda = new Busqueda(2, new Usuario("pepe", new Comuna()),"flores" , "", observadores );
		entityManager().persist(unReporte);
		List<ResultadosDeBusquedas> copiaDelReporte = entityManager()
				.createQuery("from ResultadosDeBusquedas", ResultadosDeBusquedas.class)
				.getResultList();
		Assert.assertTrue(copiaDelReporte.stream().anyMatch(repo->repo.getId() == unReporte.getId()));
		
		
	}
}
