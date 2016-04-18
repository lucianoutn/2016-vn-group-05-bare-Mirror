package F5;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PuntoGeoTest {

	private PuntoGeo puntoTest;

	@Before
	public void initialize() {
		puntoTest = new PuntoGeo(20, 30);
	}

	@Test
	public void elPuntoTieneValoresCorrectos() {
		Assert.assertEquals(20, puntoTest.getX());
		Assert.assertEquals(30, puntoTest.getY());
	}

}
