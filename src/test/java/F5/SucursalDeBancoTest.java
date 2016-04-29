package F5;

import java.util.ArrayList;
import F5.DiaAtencion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class SucursalDeBancoTest {

	private SucursalDeBanco unaSucursal, otraSucursal;
	private Point laPosicionDelBanco;
	private Point unPuntoCerca;
	private Point unPuntoLejos;
	private DiaAtencion unDia, otroDia;
	private ArrayList<DiaAtencion> listaDeDias;

	@Before
	public void initialize() {
		laPosicionDelBanco = new Point(0, 0);
		unaSucursal = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
		unPuntoCerca = new Point(0, 4);
		unPuntoLejos = new Point(0, 6);
	}

	@Test
	public void distanciaDelBanco() {
		Assert.assertTrue(unaSucursal.estaCerca(unPuntoCerca));
		Assert.assertFalse(unaSucursal.estaCerca(unPuntoLejos));
	}

	@Test
	public void elBancoEstaDisponibleLosMiercolesSoloDe9a18() {
		unDia = new DiaAtencion(Dias.Miercoles, 900, 1800);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(unDia);
		otraSucursal = new SucursalDeBanco("Santander", laPosicionDelBanco, listaDeDias);
		Assert.assertTrue(otraSucursal.estaDisponible(Dias.Miercoles, 900, null));
		Assert.assertTrue(otraSucursal.estaDisponible(Dias.Miercoles, 1800, null));
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Miercoles, 1801, null));

	}

	@Test
	public void elBancoNoEstaDisponibleSabadosLuegoDeLas13() {
		otroDia = new DiaAtencion(Dias.Sabado, 1000, 1300);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(otroDia);
		otraSucursal = new SucursalDeBanco("Santander", laPosicionDelBanco, listaDeDias);
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Sabado, 1301, null));
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Sabado, 1600, null));
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Sabado, 1800, null));
	}

	@Test
	public void encontrarUnBancoSantander() {
		Assert.assertTrue(unaSucursal.encuentra("Santander"));
		Assert.assertFalse(unaSucursal.encuentra("santander"));
		Assert.assertFalse(unaSucursal.encuentra("HSBC"));
	}
}