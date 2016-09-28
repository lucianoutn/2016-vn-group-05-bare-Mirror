package F5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
//import org.uqbar.geodds.Punto;
import org.junit.Test;
import org.junit.Assert;
import org.uqbar.geodds.Polygon;

import F5.Pois.CGP;
import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.LocalComercial;
import F5.Pois.Punto;
import F5.Pois.SucursalDeBanco;
import F5.Procesos.Planificador;
import F5.Procesos.ProcesoDeBajaPOI;
import F5.Terminal.Reloj;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import InterfacesExternas.BajaPoisRestMock;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class TerminalTest {

	private SucursalDeBanco unBanco;
	private LocalComercial unLocalComercial, otroLocalComercial;
	private Terminal unaTerminal;
	private RepositorioDePOIs unMapa, unRepositorioDePOIs;
	private Punto posicionDelBanco, posicionDelLocalComercial, posicionDelCGP;
	private Polygon comunaDelCGP;
	private CGP unCGP;
	private Punto puntoADeLaComuna, puntoBDeLaComuna;
	private Reloj unReloj;
	private ProcesoDeBajaPOI unProcesoDeBajaDePOI;
	private BajaPoisRestMock bajaDePOIsMock;
	private Planificador planificador = new Planificador();

	@Before
	public void initialize() {

		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());

		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);

		posicionDelBanco = new Punto(10, 22);
		unBanco = new SucursalDeBanco("Santander", posicionDelBanco, new ArrayList<DiaAtencion>());

		posicionDelLocalComercial = new Punto(20, 10);
		otroLocalComercial = new LocalComercial("Naic", "Rivadavia", "10", "Ropa Deportiva",
				new ArrayList<DiaAtencion>(), posicionDelLocalComercial);

		unLocalComercial = new LocalComercial("Shopping", "Honduras", "10", "Ropa", new ArrayList<DiaAtencion>(),
				new Punto(22, 10));
		unaTerminal = new Terminal(22,"Caballito", unMapa);

		posicionDelCGP = new Punto(40, 53);
		puntoADeLaComuna = new Punto(1, 1);
		puntoBDeLaComuna = new Punto(2, 2);
		unCGP = new CGP(posicionDelCGP, new Comuna(1, comunaDelCGP));

		unRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);

		unReloj = new Reloj(100);
		unReloj.setHora(900);
	}

	@Test
	public void seQuitaCorrectamenteUnLocalComercialDelMapaDeLaTerminal() {

		unMapa.anadirPOI(unBanco);
		unMapa.anadirPOI(unLocalComercial);

		unaTerminal.eliminarPOI(unBanco);
		Assert.assertFalse(unaTerminal.getUnMapa().contiene(unBanco));
	}

	@Test
	public void agregoUnCGPALaTerminal() {

		unMapa.anadirPOI(unBanco);
		unMapa.anadirPOI(unLocalComercial);

		unaTerminal.aniadirPoi(unCGP);

		Assert.assertTrue(unaTerminal.getUnMapa().contiene(unCGP));
	}

	@Test
	public void cambioLaAlturaDeUnLocalComercial() {
		unaTerminal.aniadirPoi(unBanco);
		unaTerminal.aniadirPoi(unLocalComercial);
		unaTerminal.aniadirPoi(unCGP);

		LocalComercial localComercialModificado = new LocalComercial("Shopping", "Honduras", "50", "Ropa",
				new ArrayList<DiaAtencion>(), posicionDelLocalComercial);

		unaTerminal.modificarPOI(unLocalComercial, localComercialModificado);

		Assert.assertTrue(unaTerminal.getUnMapa().getPOIs().get(2).getAltura().equals("50"));
	}

	@Test
	public void aumentoLaHoraDelRelojConMetodoAumetarModuloHorarioCorrectamente() {

		unReloj.aumentarModuloHorario();

		Assert.assertEquals(1000, unReloj.getHora());
	}

	@Test
	public void laAccionDelProcesoSeEjecutaDespuesDeAumentarLaHoraDelReloj() {

		bajaDePOIsMock = new BajaPoisRestMock(unMapa);

		unMapa.anadirPOI(unBanco);
		unMapa.anadirPOI(unLocalComercial);

		unRepositorioDePOIs.anadirPOI(unBanco);
		unRepositorioDePOIs.anadirPOI(unLocalComercial);
		unRepositorioDePOIs.anadirPOI(otroLocalComercial);

		unProcesoDeBajaDePOI = new ProcesoDeBajaPOI(unRepositorioDePOIs, bajaDePOIsMock, 1000, planificador);

		unaTerminal.anadirProcesoBatch(unProcesoDeBajaDePOI);

		unReloj.suscribirANotificadorDeCambioHorario(unaTerminal);

		unReloj.aumentarModuloHorario();

		Assert.assertTrue(unProcesoDeBajaDePOI.getRepoDePOIs().getPOIs().contains(otroLocalComercial));
	}

	@Test
	public void laAccionDelProcesoNoSeEjecutaDespuesDeAumentarLaHoraDelReloj() {

		bajaDePOIsMock = new BajaPoisRestMock(unMapa);

		unMapa.anadirPOI(unBanco);
		unMapa.anadirPOI(unLocalComercial);

		unRepositorioDePOIs.anadirPOI(unBanco);
		unRepositorioDePOIs.anadirPOI(unLocalComercial);
		unRepositorioDePOIs.anadirPOI(otroLocalComercial);

		unProcesoDeBajaDePOI = new ProcesoDeBajaPOI(unRepositorioDePOIs, bajaDePOIsMock, 900, planificador);

		unaTerminal.anadirProcesoBatch(unProcesoDeBajaDePOI);

		unReloj.suscribirANotificadorDeCambioHorario(unaTerminal);

		unReloj.aumentarModuloHorario();

		Assert.assertEquals(unRepositorioDePOIs, unProcesoDeBajaDePOI.getRepoDePOIs());
	}

}
