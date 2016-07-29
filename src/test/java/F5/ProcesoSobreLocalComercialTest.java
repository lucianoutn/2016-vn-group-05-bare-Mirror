package F5;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		ProcesoLocalAhora.ejecutar();
		Assert.assertTrue(ProcesoLocalAhora.isLocalesComercialesActualizados());
	}

	@Test
	public void ProcesoQueSeEjecutaTarde() {
		ProcesoLocalTarde.ejecutar();
		Assert.assertFalse(ProcesoLocalTarde.isLocalesComercialesActualizados());
	}

}
