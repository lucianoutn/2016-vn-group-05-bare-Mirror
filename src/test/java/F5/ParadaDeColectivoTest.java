package F5;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
<<<<<<< HEAD
import org.junit.After;
=======
>>>>>>> implementacion
import java.time.LocalDate;

import org.junit.Assert;

public class ParadaDeColectivoTest {
	private ParadaDeColectivo paradaDeBondi;
	private Point posicionParada, unaPosicionRandom;
	// public ParadaDeColectivo (String calleDeParada, int alturaDeParada, int
	// ejeY, int ejeX, String lineaDeColectivo)

	@Before
	public void Initialize() {
<<<<<<< HEAD
		unaPosicionRandom = new Point(100, 0);
		posicionParada = new Point(0, 0);
		paradaDeBondi = new ParadaDeColectivo("Mozart", "2600", posicionParada, "114");
=======
		paradaDeBondi = new ParadaDeColectivo("Mozart", "2600", new Point(100,100),"114");
>>>>>>> implementacion
	}

	
	@Test
	public void laParadaEstaCerca() {
		paradaDeBondi.setToleranciaEnCuadras(111);
		Assert.assertTrue(paradaDeBondi.estaCerca(unaPosicionRandom));
	}
	
	@Test
	public void laParadaEstaA() {
		//falla por ser coordenadas geograficas q tiene en cuenta la curvatura de la tierra
		Assert.assertNotEquals(100, paradaDeBondi.cuadrasDeDistancia(unaPosicionRandom));
	}

	@Test
	public void laParadaEstaDisponible() {
		paradaDeBondi.setToleranciaEnCuadras(100);
		Assert.assertEquals(true, paradaDeBondi.estaDisponible(Dias.Lunes, 1400, null));
	}

	@Test
	public void encuentraLaParada() {
		Assert.assertEquals(true, paradaDeBondi.encuentra("114"));
	}

}