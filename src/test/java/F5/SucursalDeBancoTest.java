package F5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class SucursalDeBancoTest {

	private SucursalDeBanco unaSucursal;
	private Point laPosicionDelBanco;
	private Point unPuntoCerca;
	private Point unPuntoLejos;

	@Before
	public void initialize() {
		laPosicionDelBanco = new Point(0, 0);
		unaSucursal = new SucursalDeBanco(laPosicionDelBanco);
		unPuntoCerca = new Point(0, 4);
		unPuntoLejos = new Point(0, 6);
	}

	@Test
	public void elBancoEstaCercaDe() {
		Assert.assertTrue(unaSucursal.estaCerca(unPuntoCerca));
	}

	@Test
	public void elBancoEstaLejosDe() {
		Assert.assertFalse(unaSucursal.estaCerca(unPuntoLejos));
	}
}
