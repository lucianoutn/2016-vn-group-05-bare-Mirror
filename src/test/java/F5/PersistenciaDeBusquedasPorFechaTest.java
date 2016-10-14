package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Comuna;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;
import Reportes.ResultadosDeBusquedas;

public class PersistenciaDeBusquedasPorFechaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	private BusquedasPorFecha unReporte;
	private ArrayList<NotificadorDeBusqueda> observadores;
	private Terminal unaTerminal;
	private RepositorioDePOIs unMapa;
	
	@Before
	public void initialize() {
		unReporte = new BusquedasPorFecha();
		observadores = new ArrayList<>();
		observadores.add(unReporte);
		
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		unaTerminal = new Terminal("flores", unMapa);
		unaTerminal.setListaObservadores(observadores);
	}
	
	@Test
	public void guardoUnReporteVacio(){
		entityManager().persist(unReporte);
		List<BusquedasPorFecha> copiaDelReporte = entityManager()
				.createQuery("from BusquedasPorFecha", BusquedasPorFecha.class)
				.getResultList();
		Assert.assertTrue(copiaDelReporte.stream().anyMatch(repo->repo.getId() == unReporte.getId()));
		
	}
	
	@Test
	public void hagoUnaBusquedaYGuardoSuReporte() {
		Busqueda unaBusqueda = new Busqueda(unaTerminal, new Usuario("pepe", new Comuna()),"");
		entityManager().persist(unReporte);
		List<BusquedasPorFecha> copiaDelReporte = entityManager()
				.createQuery("from BusquedasPorFecha", BusquedasPorFecha.class)
				.getResultList();
		Assert.assertTrue(copiaDelReporte.stream().anyMatch(repo->repo.getId() == unReporte.getId()));
	}
}
