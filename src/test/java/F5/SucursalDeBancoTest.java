package F5;

import TestFactory.BancoFactory;
import TestFactory.PointFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Dias;
import F5.Pois.LocalComercial;
import F5.Pois.SucursalDeBanco;

public class SucursalDeBancoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	@Before
	public void initialize() {

	}

	@Test
	public void cercaBancoSantander() {
		SucursalDeBanco bancoSantander = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertFalse(bancoSantander.estaCerca(PointFactory.LejosBancoSantander()));
	}

	@Test
	public void lejosBancoSantander() {
		SucursalDeBanco bancoSantander = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertFalse(bancoSantander.estaCerca(PointFactory.LejosBancoSantander()));
	}

	@Test
	public void elBancoEstaDisponibleLosMiercolesALas9() {
		SucursalDeBanco otraSucursal = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertTrue(otraSucursal.estaDisponible(Dias.Miercoles, 900, null));
	}

	@Test
	public void elBancoEstaDisponibleLosMiercolesALas18() {
		SucursalDeBanco otraSucursal = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertTrue(otraSucursal.estaDisponible(Dias.Miercoles, 1800, null));
	}

	@Test
	public void elBancoEstaDisponibleLosMiercolesALas18yUnMinuto() {
		SucursalDeBanco otraSucursal = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Miercoles, 1801, null));
	}

	@Test
	public void elBancoNoEstaDisponibleSabados1301() {
		SucursalDeBanco otraSucursal = BancoFactory.BancoSabadoDe10a13();
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Sabado, 1301, null));
	}

	@Test
	public void elBancoNoEstaDisponibleSabados1600() {
		SucursalDeBanco otraSucursal = BancoFactory.BancoSabadoDe10a13();
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Sabado, 1600, null));
	}

	@Test
	public void elBancoNoEstaDisponibleSabados955() {
		SucursalDeBanco otraSucursal = BancoFactory.BancoSabadoDe10a13();
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Sabado, 955, null));
	}

	@Test
	public void encontrarUnBancoSantander() {
		SucursalDeBanco santander = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertTrue(santander.encuentra("Santander"));
	}

	@Test
	public void noEncontrarUnBancoSantander() {
		SucursalDeBanco santander = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertFalse(santander.encuentra("HSBC"));
	}

	@Test
	public void noEncontrarUnBancoSantanderConCaseSensitive() {
		SucursalDeBanco santander = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		Assert.assertFalse(santander.encuentra("santander"));
	}

	@Test
	public void persisteLocalComercial() {
		SucursalDeBanco santander = BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18();
		entityManager().persist(santander);
		Assert.assertEquals(santander, entityManager().find(SucursalDeBanco.class, santander.getId()));

	}
}