package F5;

import java.util.ArrayList;

import TestFactory.LocalComercialFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.DiaAtencion;
import F5.Pois.Dias;
import F5.Pois.LocalComercial;
import F5.Pois.ParadaDeColectivo;

public class LocalComercialTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private LocalComercial mimo;

	@Before
	public void Initialize() {
		mimo = LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20();

	}

	@Test
	public void mimoEstaCercaDeUnPunto() {

		mimo.setCuadrasDeCercania(5);
		Assert.assertTrue(mimo.estaCerca(new Point(101, 0)));

	}

	@Test
	public void mimoNoEstaCercaDeUnPunto() {

		mimo.setCuadrasDeCercania(5);
		Assert.assertFalse(mimo.estaCerca(new Point(50, 50)));

	}

	@Test
	public void localComercialDisponibleMiercoles11horas() {
		Assert.assertTrue(mimo.estaDisponible(Dias.Miercoles, 1100, null));
	}

	@Test
	public void localComercialNoDisponibleMartes20horas() {

		Assert.assertFalse(mimo.estaDisponible(Dias.Martes, 2000, null));
	}

	@Test
	public void localComercialNoDisponibleSabado15horas() {

		Assert.assertTrue(mimo.estaDisponible(Dias.Miercoles, 1100, null));

	}

	@Test
	public void persisteLocalComercial() {
		entityManager().persist(mimo);
		
		LocalComercial mimoPersistido = entityManager().find(LocalComercial.class, mimo.getId());
		
		Assert.assertEquals(mimo.getAltura(), mimoPersistido.getAltura());
		Assert.assertEquals(mimo.getCalle(), mimoPersistido.getCalle());
		Assert.assertEquals(mimo.getId(), mimoPersistido.getId());
		Assert.assertEquals(mimo.getPalabrasClaves().size(), mimoPersistido.getPalabrasClaves().size());
		
		

	}

}
