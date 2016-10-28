package F5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
//import org.uqbar.geodds.Punto;

import F5.Pois.DiaAtencion;
import F5.Pois.LocalComercial;
import F5.Pois.Punto;
import F5.Pois.SucursalDeBanco;
import F5.Procesos.EstadosDelProceso;
import F5.Procesos.MandarMailPorError;
import F5.Procesos.Planificador;
import F5.Procesos.ProcesoDeBajaPOI;
import F5.Procesos.ReintentarProcesoPorError;
import F5.Terminal.RepositorioDePOIs;
import InterfacesExternas.BajaPoisRestMock;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Reportes.NotificadorDeAdministrador;
import junit.framework.Assert;

public class ManejoDeResultadosyErroresTest {

	private SucursalDeBanco unaSucursalDeBanco;
	private LocalComercial unLocalComercial, otroLocalComercial;
	private ConsultorCGP unConsultorCGP;
	private ConsultorBancos consultorBanco;
	private RepositorioDePOIs unRepositorioDePOIs, otroRepositorioDePOIs;
	private BajaPoisRestMock mockRESTBajaPOIs;
	private ProcesoDeBajaPOI procesoDeBajaPOI;
	private MandarMailPorError manejoDeError_EnviarMail;
	private NotificadorDeAdministrador unNotificadorDeAdmin;
	private ReintentarProcesoPorError manejoDeError_Reintento;
	private Planificador planificador = new Planificador();

	@Before
	public void initialize() {

		unaSucursalDeBanco = new SucursalDeBanco("Rio", new Punto(10, 10), new ArrayList<DiaAtencion>());
		unLocalComercial = new LocalComercial("Macowins", "Pedernera", "10", "Ropa", new ArrayList<DiaAtencion>(),
				new Punto(10, 10));
		otroLocalComercial = new LocalComercial("Naic", "Rivadavia", "10", "Ropa Deportiva",
				new ArrayList<DiaAtencion>(), new Punto(20, 10));

		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("015"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("016"));
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
	public void elEstadoInicialDelProcesoDeBajaEsSinIniciar() {
		Assert.assertEquals(EstadosDelProceso.SinIniciar, procesoDeBajaPOI.getEstado());
	}

	@Test
	public void elEstadoDelProcesoAlEjecutarEsEjecutando() {
		procesoDeBajaPOI.preEjecutar();
		Assert.assertEquals(EstadosDelProceso.Ejecutando, procesoDeBajaPOI.getEstado());
	}

	@Test
	public void alFallarUnProcesoSeEnviaUnMail() {

		unNotificadorDeAdmin = new NotificadorDeAdministrador();
		manejoDeError_EnviarMail = new MandarMailPorError(unNotificadorDeAdmin);

		procesoDeBajaPOI.pasarAFinalizadoConError();
		procesoDeBajaPOI.setManejadorDeError(manejoDeError_EnviarMail);

		manejoDeError_EnviarMail.manejarError(procesoDeBajaPOI);

		Assert.assertTrue(manejoDeError_EnviarMail.isMailEnviado());
	}

	@Test
	public void alFallarUnProcesoSeVuelveAEjecutarTresVeces() {

		manejoDeError_Reintento = new ReintentarProcesoPorError(3, false, null);

		procesoDeBajaPOI.pasarAFinalizadoConError();
		procesoDeBajaPOI.setManejadorDeError(manejoDeError_Reintento);

		manejoDeError_Reintento.manejarError(procesoDeBajaPOI);

		Assert.assertEquals(3, manejoDeError_Reintento.getContadorDeIntentos());
	}

	@Test
	public void alFallarUnProcesoSeVuelveAEjecutarTresVecesYAlFallarEnviaUnMail() {

		unNotificadorDeAdmin = new NotificadorDeAdministrador();
		manejoDeError_Reintento = new ReintentarProcesoPorError(3, true, unNotificadorDeAdmin);

		procesoDeBajaPOI.pasarAFinalizadoConError();
		procesoDeBajaPOI.setManejadorDeError(manejoDeError_Reintento);

		manejoDeError_Reintento.manejarError(procesoDeBajaPOI);

		Assert.assertTrue(manejoDeError_Reintento.isMailEnviado());
	}

	@Test
	public void cantidadDeElementosAfectadosDelProcesoEsIgualADos() {
		procesoDeBajaPOI.preEjecutar();
		Assert.assertEquals(2, procesoDeBajaPOI.getCantidadDeElementosAfectados());
	}

	//@Test
	public void laHoraDelProcesoEsLaActual() {
		procesoDeBajaPOI.preEjecutar();
		Assert.assertEquals(LocalDateTime.now(), procesoDeBajaPOI.getHoraDeEjecucion());
	}

}
