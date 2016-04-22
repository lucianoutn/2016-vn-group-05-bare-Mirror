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
		unaSucursal = new SucursalDeBanco("Santander" , laPosicionDelBanco,  new ArrayList<DiaAtencion>());
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
	
	@Test
	public void elBancoNoEstaDisponibleLosMiercoles() {
		unDia = new DiaAtencion(Dias.Martes,10,18);
		otroDia = new DiaAtencion(Dias.Sabado,10,13);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(unDia);
		listaDeDias.add(otroDia);
		otraSucursal = new SucursalDeBanco("Santander" , laPosicionDelBanco,  listaDeDias);
		Assert.assertFalse(otraSucursal.estaDisponible(Dias.Miercoles, 11, null));
	}
	
	@Test
	public void elBancoEstaDisponibleLosMartesALas18() {
		unDia = new DiaAtencion(Dias.Martes,10,18);
		otroDia = new DiaAtencion(Dias.Sabado,10,13);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(unDia);
		listaDeDias.add(otroDia);
		otraSucursal = new SucursalDeBanco("Santander" , laPosicionDelBanco,  listaDeDias);
		Assert.assertTrue(otraSucursal.estaDisponible(Dias.Martes, 18, null));
	}
	
	@Test
	public void encontrarUnBancoSantander(){
		Assert.assertTrue(unaSucursal.encuentra("Santander"));	
		}
}