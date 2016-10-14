package F5;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.INITIALIZE;
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
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorTerminal;
import Reportes.ResultadosPorTerminal;

public class PersistenciaDeResultadosPorTerminalTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	private ResultadosPorTerminal unReporte;
	private ArrayList<NotificadorDeBusqueda> observadores;
	private Terminal unaTerminal;
	private RepositorioDePOIs unMapa;
	
	@Before
	public void initialize(){
		unReporte = new ResultadosPorTerminal();
		observadores = new ArrayList<>();
		observadores.add(unReporte);
		
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		unaTerminal = new Terminal("flores", unMapa);
		unaTerminal.setListaObservadores(observadores);
	}
	
	@Test
	public void almacenoUnRenglonDelReporte(){
		ReportePorTerminal unRenglon = new ReportePorTerminal("Flores",20);
		entityManager().persist(unRenglon);
		List<ReportePorTerminal> copiaDelRenglon = entityManager()
				.createQuery("from ReportePorTerminal", ReportePorTerminal.class)
				.getResultList();
		Assert.assertTrue(copiaDelRenglon.stream().anyMatch(repo->repo.getNumeroDeRenglonDeReportePorTerminal() == unRenglon.getNumeroDeRenglonDeReportePorTerminal()));

	}
	
	@Test
	public void guardoUnReporteVacio(){
		entityManager().persist(unReporte);
		List<ResultadosPorTerminal> copiaDelReporte = entityManager()
				.createQuery("from ResultadosPorTerminal", ResultadosPorTerminal.class)
				.getResultList();
		Assert.assertTrue(copiaDelReporte.stream().anyMatch(repo->repo.getId() == unReporte.getId()));
		
	}
	
	@Test
	public void hagoUnaBusquedaYGuardoSuReporte() {
		Busqueda unaBusqueda = new Busqueda(unaTerminal, new Usuario("pepe", new Comuna()),"");
		entityManager().persist(unReporte);
		List<ResultadosPorTerminal> copiaDelReporte = entityManager()
				.createQuery("from ResultadosPorTerminal", ResultadosPorTerminal.class)
				.getResultList();
		Assert.assertTrue(copiaDelReporte.stream().anyMatch(repo->repo.getId() == unReporte.getId()));
		
		
	}

}
