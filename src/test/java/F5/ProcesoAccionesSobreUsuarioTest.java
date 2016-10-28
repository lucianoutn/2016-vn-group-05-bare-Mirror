package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Polygon;

import F5.Pois.Comuna;
import F5.Procesos.ObtenerUsuariosAProcesar;
import F5.Procesos.Planificador;
import F5.Procesos.ProcesoSobreUsuario;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
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
	private ObtenerUsuariosAProcesar obtenerUsuarios;
	private ProcesoSobreUsuario unProcesoSobreUsuario;
	private Busqueda unaBusqueda;
	private RepositorioDePOIs unMapa;
	private Planificador planificador = new Planificador();
	private Terminal unaTerminal;

	@Before
	public void initialize() {
		unUsuario = new Usuario("Juan", new Comuna(1, (Polygon)null));
		otroUsuario = new Usuario("Pedro", new Comuna(2, (Polygon)null));
		usuarios.add(unUsuario);
		usuarios.add(otroUsuario);

		unaAccion = new AccionEjemplo();
		acciones.add(unaAccion);
		
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("007"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("008"));

		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
		unaTerminal = new Terminal("Boedo",unMapa);

		unaBusqueda = new Busqueda(unaTerminal,unUsuario,"Hola");


		obtenerUsuarios = new ObtenerUsuariosAProcesar();

	}

	@Test
	public void seAgregaUnaAccionQueImprimeCorrectamente() {
		unProcesoSobreUsuario = new ProcesoSobreUsuario(usuarios, acciones, null, 1, planificador);
		unProcesoSobreUsuario.preEjecutar();
		Assert.assertTrue(unUsuario.getAccionesRealizables().contains(unaAccion));
	}

	@Test
	public void seRealizaUnaBusquedaYSeEjecutanLasAccionesDelUsuario() {
		unUsuario.agregarAccion(unaAccion);
		unaBusqueda.buscoFrase("Junin", unMapa);
		Assert.assertTrue(unaAccion.isAccionEjecutada());
	}

	@Test
	public void filtraUnUsuarioEnLaComunaUno() {

		RepositorioDeUsuarios.usuarios = new ArrayList<Usuario>();
		RepositorioDeUsuarios.addUsuario(unUsuario);
		RepositorioDeUsuarios.addUsuario(otroUsuario);
		ProcesoSobreUsuario unProceso = new ProcesoSobreUsuario(obtenerUsuarios.usuariosEnComuna(new Comuna(1,(Polygon) null)),
				null, null, 900, planificador);
		Assert.assertTrue(unProceso.getUsuarios().contains(unUsuario));
	}
	
	//@Test
	public void otroUsuarioNoEsParteDelProcesoPorEstarEnLaComunaDos() {

		RepositorioDeUsuarios.usuarios = new ArrayList<Usuario>();
		RepositorioDeUsuarios.addUsuario(unUsuario);
		RepositorioDeUsuarios.addUsuario(otroUsuario);
		ProcesoSobreUsuario unProceso = new ProcesoSobreUsuario(obtenerUsuarios.usuariosEnComuna(new Comuna(1,(Polygon) null)),
				null, null, 900, planificador);
		Assert.assertFalse(unProceso.getUsuarios().contains(otroUsuario));
	}

	@Test
	public void dosUsuariosEnElProcesoQueSeCreaParaTodosLosUsuariosDelSistema() {

		RepositorioDeUsuarios.usuarios = new ArrayList<Usuario>();
		RepositorioDeUsuarios.addUsuario(unUsuario);
		RepositorioDeUsuarios.addUsuario(otroUsuario);
		ProcesoSobreUsuario unProceso = new ProcesoSobreUsuario(obtenerUsuarios.usuariosEnSistema(), null, null, 900,
				planificador);
		Assert.assertEquals(RepositorioDeUsuarios.getUsuarios(),unProceso.getUsuarios());
	}

}
