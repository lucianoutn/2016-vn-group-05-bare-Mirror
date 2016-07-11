package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class ProcesoAccionesSobreUsuarioTest {

	
	private Usuario unUsuario;
	private Usuario otroUsuario;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private AccionEjemplo unaAccion;
	private List<AccionPostBusqueda> acciones = new ArrayList<AccionPostBusqueda>();
	
	private ProcesoSobreUsuario unProcesoSobreUsuario;
	
	private Busqueda unaBusqueda;
	
	private RepositorioDePOIs unMapa;
		
	
	@Before
	public void initialize(){
		unUsuario = new Usuario("Juan", new Comuna(1, null));
		otroUsuario = new Usuario("Pedro", new Comuna(2, null));
		usuarios.add(unUsuario);
		usuarios.add(otroUsuario);
		
		unaAccion = new AccionEjemplo();
		acciones.add(unaAccion);
		
		unaBusqueda = new Busqueda(unUsuario, "Boedo", "Hola", null);
		
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
	}
	
	@Test
	public void seAgregaUnaAccionQueImprimeCorrectamente(){
		unProcesoSobreUsuario = new ProcesoSobreUsuario(usuarios, acciones, null);
		unProcesoSobreUsuario.ejecutar();
		Assert.assertEquals(1,unUsuario.getAccionesRealizables().size());
	}
	
	@Test
	public void seRealizaUnaBusquedaYSeEjecutanLasAccionesDelUsuario(){
		unUsuario.agregarAccion(unaAccion);
		unaBusqueda.buscoFrase("Junin", unMapa);
		Assert.assertTrue(unaAccion.isAccionEjecutada());
	}
	
	@Test
	public void filtraUnUsuarioEnLaComunaUno(){
		
		RepositorioDeUsuarios.usuarios = new ArrayList<Usuario>();
		RepositorioDeUsuarios.addUsuario(unUsuario);
		RepositorioDeUsuarios.addUsuario(otroUsuario);
		ProcesoSobreUsuario unProceso = new ProcesoSobreUsuario(new Comuna(1, null));
		Assert.assertTrue(unProceso.getUsuarios().size() == 1);
	}
	
	@Test
	public void dosUsuariosEnElProcesoQueSeCreaParaTodosLosUsuariosDelSistema(){
		
		RepositorioDeUsuarios.usuarios = new ArrayList<Usuario>();
		RepositorioDeUsuarios.addUsuario(unUsuario);
		RepositorioDeUsuarios.addUsuario(otroUsuario);
		ProcesoSobreUsuario unProceso = new ProcesoSobreUsuario();
		Assert.assertTrue(unProceso.getUsuarios().size() == 2);
	}
	

}
