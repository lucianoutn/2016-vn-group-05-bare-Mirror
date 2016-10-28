package F5;

import java.util.ArrayList;

import org.junit.Before;
import junit.framework.Assert;
import org.junit.Test;
import org.uqbar.geodds.Point;

import F5.Pois.DiaAtencion;
import F5.Pois.Punto;
import F5.Pois.SucursalDeBanco;
import F5.Procesos.EstadosDelProceso;
import F5.Procesos.Planificador;
import F5.Procesos.ProcesoDeBajaPOI;
import F5.Terminal.RepositorioDePOIs;
import InterfacesExternas.BajaPoisRestMock;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class ConcurrenciaDeProcesosTest {

	private ProcesoDeBajaPOI procesoDeBajaPOIA;
	private ProcesoDeBajaPOI procesoDeBajaPOIB;
	private Planificador planificador = new Planificador();
	private BajaPoisRestMock mockRESTBajaPOIs;
	private RepositorioDePOIs unRepositorioDePOIs;
	private RepositorioDePOIs otroRepositorioDePOIs;
	private SucursalDeBanco unaSucursalDeBanco;

	@Before
	public void initialize() {

		unaSucursalDeBanco = new SucursalDeBanco("Rio", new Punto(10, 10), new ArrayList<DiaAtencion>());
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("024"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("025"));

		unRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		unRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		otroRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		otroRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		mockRESTBajaPOIs = new BajaPoisRestMock(unRepositorioDePOIs);

		procesoDeBajaPOIA = new ProcesoDeBajaPOI(otroRepositorioDePOIs, mockRESTBajaPOIs, 1, planificador);
		procesoDeBajaPOIB = new ProcesoDeBajaPOI(otroRepositorioDePOIs, mockRESTBajaPOIs, 1, planificador);

		procesoDeBajaPOIA.preEjecutar();
		procesoDeBajaPOIB.preEjecutar();

	}

	@Test
	public void ProcesoAyProcesoBSeEjecutanALaMismaHoraYSeEjecutaElProcesoAyElBTieneUnaEspera() {
		Assert.assertEquals(EstadosDelProceso.Ejecutando, procesoDeBajaPOIA.getEstado());
		Assert.assertTrue(procesoDeBajaPOIB.gettuvoEspera());

	}

}
