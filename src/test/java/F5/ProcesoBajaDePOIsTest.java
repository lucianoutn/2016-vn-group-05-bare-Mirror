package F5;

import java.awt.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import F5.Pois.DiaAtencion;
import F5.Pois.LocalComercial;
import F5.Pois.SucursalDeBanco;
import F5.Procesos.Planificador;
import F5.Procesos.ProcesoDeBajaPOI;
import F5.Terminal.RepositorioDePOIs;
import InterfacesExternas.BajaPoisRestMock;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import junit.framework.Assert;

public class ProcesoBajaDePOIsTest {

	private RepositorioDePOIs unRepositorioDePOIs;
	private RepositorioDePOIs otroRepositorioDePOIs;
	private SucursalDeBanco unaSucursalDeBanco;
	private LocalComercial unLocalComercial;
	private LocalComercial otroLocalComercial;
	private BajaPoisRestMock mockRESTBajaPOIs;
	private ProcesoDeBajaPOI procesoDeBajaPOI;
	private Planificador planificador = new Planificador();

	@Before
	public void initialize() {

		unaSucursalDeBanco = new SucursalDeBanco("Rio", new Point(10, 10), new ArrayList<DiaAtencion>());
		unLocalComercial = new LocalComercial("Macowins", "Pedernera", "10", "Ropa", new ArrayList<DiaAtencion>(),
				new Point(10, 10));
		otroLocalComercial = new LocalComercial("Naic", "Rivadavia", "10", "Ropa Deportiva",
				new ArrayList<DiaAtencion>(), new Point(20, 10));

		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		unRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		unRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		unRepositorioDePOIs.anadirPOI(unLocalComercial);

		otroRepositorioDePOIs = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		otroRepositorioDePOIs.anadirPOI(unaSucursalDeBanco);
		otroRepositorioDePOIs.anadirPOI(unLocalComercial);
		otroRepositorioDePOIs.anadirPOI(otroLocalComercial);

		mockRESTBajaPOIs = new BajaPoisRestMock(unRepositorioDePOIs);

		procesoDeBajaPOI = new ProcesoDeBajaPOI(otroRepositorioDePOIs, mockRESTBajaPOIs, 1, planificador);

	}

	@Test
	public void elMockDelRESTtieneDosPOIsADarDeBaja() {
		procesoDeBajaPOI.ejecutar();
		Assert.assertEquals(unRepositorioDePOIs.getPOIs(), procesoDeBajaPOI.getPuntosDeInteresParaBajas());
	}

	@Test
	public void doyDeBajaLosPOIsQueMeDevuelveElMockDelRESTyQuedaUnSoloPOI() {
		procesoDeBajaPOI.ejecutar();
		Assert.assertTrue(procesoDeBajaPOI.getRepoDePOIs().getPOIs().contains(otroLocalComercial));
	}
}
