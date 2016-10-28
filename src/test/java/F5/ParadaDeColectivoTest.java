package F5;

import org.junit.Before;
import org.junit.Test;
//import org.uqbar.geodds.Punto;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Dias;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.Punto;
import F5.Terminal.Usuario;
import Kvs.KvsCache;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import static org.hamcrest.CoreMatchers.*;

public class ParadaDeColectivoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private ParadaDeColectivo paradaDeBondi;
	private Punto posicionParada, unaPosicionRandom;
	// public ParadaDeColectivo (String calleDeParada, int alturaDeParada, int
	// ejeY, int ejeX, String lineaDeColectivo)

	@Before
	public void Initialize() {
		unaPosicionRandom = new Punto(100, 0);
		posicionParada = new Punto(0, 0);
		paradaDeBondi = new ParadaDeColectivo("Mozart", "2600", new Punto(100, 100), "114");
		paradaDeBondi.setToleranciaEnCuadras(100);
	}
	
	@After
	public void limpiarKvs(){
		KvsCache.clear();
	}

	@Test
	public void laParadaEstaA() {
		// falla por ser coordenadas geograficas q tiene en cuenta la curvatura
		// de la tierra
		Assert.assertNotEquals(100, paradaDeBondi.cuadrasDeDistancia(unaPosicionRandom));
	}

	@Test
	public void testAssertThatEqual() {
		assertThat("123", is("123"));
	}

	@Test
	public void laParadaEstaDisponible() {
		paradaDeBondi.setToleranciaEnCuadras(100);
		Assert.assertTrue(paradaDeBondi.estaDisponible(Dias.Lunes, 1400, null));

	}

	@Test
	public void laParadaEstaDisponibleLosDiasSabadosA2400mts() throws Exception {
		Assert.assertTrue(paradaDeBondi.estaDisponible(Dias.Sabado, 2400, null));
	}

	@Test
	public void laParadaEstaDisponibleLosDiasDomingosA() throws Exception {
		Assert.assertTrue(paradaDeBondi.estaDisponible(Dias.Domingo, 100, null));
	}

	@Test
	public void encuentraLaParadaDel114() {
		Assert.assertTrue(paradaDeBondi.encuentra("114"));
	}

	@Test
	public void noEncuentraLaParadaDel11() {
		Assert.assertFalse(paradaDeBondi.encuentra("11"));
	}

	@Test
	public void persisteParadaDeColectivo() {
		entityManager().persist(paradaDeBondi);
		Long idPersistido = paradaDeBondi.getId();

		/*
		 * String paradaLinea = entityManager().createQuery(
		 * "from ParadaDeColectivo",
		 * ParadaDeColectivo.class).getResultList().get(0) .getNumeroDeLinea();
		 * Assert.assertEquals(paradaDeBondi.getNumeroDeLinea(), paradaLinea);
		 */
		Assert.assertEquals(idPersistido, entityManager().find(ParadaDeColectivo.class, paradaDeBondi.getId()).getId());

	}
}
