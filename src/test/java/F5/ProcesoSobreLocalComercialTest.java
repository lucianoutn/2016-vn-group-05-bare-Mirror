package F5;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcesoSobreLocalComercialTest {
	
	private ProcesoSobreLocalComercialClaseAbst ProcesoLocalAhora;
	private ProcesoSobreLocalComercialClaseAbst ProcesoLocalTarde;
	
	@Before
	public void Initialize(){
		
		
		ProcesoLocalAhora = new ProcesoSobreLocalComercialMock(LocalDate.now(), "/rutaFalsa.txt", new RepositorioDePOIs(null, null),1);
		ProcesoLocalTarde = new ProcesoSobreLocalComercialMock(LocalDate.of(2000, 04,30), "/rutaFalsa.txt", new RepositorioDePOIs(null, null),1 );
		
	}
	
	@Test
	public void ProcesoQueSeEjecutaAhora(){
		ProcesoLocalAhora.ejecutar();
		Assert.assertTrue(ProcesoLocalAhora.isLocalesComercialesActualizados());
	}
	
	@Test
	public void ProcesoQueSeEjecutaTarde(){
		ProcesoLocalTarde.ejecutar();
		Assert.assertFalse(ProcesoLocalTarde.isLocalesComercialesActualizados());
	}
	
	
}
