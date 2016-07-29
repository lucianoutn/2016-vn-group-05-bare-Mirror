package F5;

import java.util.ArrayList;

import org.junit.Before;
import junit.framework.Assert;
import org.junit.Test;
import org.uqbar.geodds.Point;

import F5.Pois.DiaAtencion;
import F5.Pois.SucursalDeBanco;
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
		
		unaSucursalDeBanco = new SucursalDeBanco("Rio", new Point(10, 10), new ArrayList<DiaAtencion>());
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		unRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		otroRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		otroRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		mockRESTBajaPOIs = new BajaPoisRestMock(unRepositorioDePOIs);

	}

	@Test
	public void AlHaberDosProcesosConcurrentesSoloDebeEjecutar1() {
		// dos procesos a la misma hora con el planificador asignado
		procesoDeBajaPOIA = new ProcesoDeBajaPOI(otroRepositorioDePOIs, mockRESTBajaPOIs, 1, planificador);
		procesoDeBajaPOIB = new ProcesoDeBajaPOI(otroRepositorioDePOIs, mockRESTBajaPOIs, 1, planificador);

		// orden de ejecutar a ambos
		procesoDeBajaPOIA.ejecutar();
		procesoDeBajaPOIB.ejecutar();
		
		Assert.assertEquals("Ejecutando", procesoDeBajaPOIA.getEstado().toString());
		//Assert.assertEquals("EnEspera", procesoDeBajaPOIB.getEstado().toString());
		//Assert.assertFalse(planificador.ejecucionDisponible); //no esta disponible
		//Assert.assertEquals(1, planificador.procesosPendientesDeEjecucion.size()); //tiene 1 en lista de pendientes
		
	}

}
