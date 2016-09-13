package F5;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import F5.Procesos.ILectorArchivoLocalComercial;
import F5.Procesos.LectorArchivoLocalComercialMock;
import F5.Procesos.Planificador;
import F5.Procesos.ProcesoSobreLocalComercial;
import F5.Terminal.RepositorioDePOIs;

public class ProcesoSobreLocalComercialTest {

	private ProcesoSobreLocalComercial ProcesoLocalAhora;
	private ProcesoSobreLocalComercial ProcesoLocalTarde;
	private Planificador planificador = new Planificador();

	@Before
	public void Initialize() {

		ILectorArchivoLocalComercial lectorMock = new LectorArchivoLocalComercialMock();
		ProcesoLocalAhora = new ProcesoSobreLocalComercial(LocalDate.now(), "/rutaFalsa.txt",
				new RepositorioDePOIs(null, null), 1, planificador, lectorMock);
		ProcesoLocalTarde = new ProcesoSobreLocalComercial(LocalDate.of(2000, 04, 30), "/rutaFalsa.txt",
				new RepositorioDePOIs(null, null), 1, planificador, lectorMock);
	}

	@Test
	public void ProcesoQueSeEjecutaAhora() {
		ProcesoLocalAhora.preEjecutar();
		Assert.assertTrue(ProcesoLocalAhora.isLocalesComercialesActualizados());
	}

	@Test
	public void ProcesoQueSeEjecutaTarde() {
		ProcesoLocalTarde.preEjecutar();
		Assert.assertFalse(ProcesoLocalTarde.isLocalesComercialesActualizados());
	}

}
