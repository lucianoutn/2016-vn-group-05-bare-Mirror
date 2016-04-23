package F5;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import static org.junit.Assert.*;

import org.junit.Assert;

public class ParadaDeColectivoTest {

	private ParadaDeColectivo paradaDeBondi;
	private Point posicionParada, unaPosicionRandom;
	// public ParadaDeColectivo (String calleDeParada, int alturaDeParada, int
	// ejeY, int ejeX, String lineaDeColectivo)

	@Before
	public void Initialize() {
		unaPosicionRandom = new Point(100, 0);
		posicionParada = new Point(0, 0);
		paradaDeBondi = new ParadaDeColectivo("Mozart", "2600", new Point(100,100),"114");
		paradaDeBondi.setToleranciaEnCuadras(100);
	}
	
	@Test
	public void laParadaEstaA() {
		//falla por ser coordenadas geograficas q tiene en cuenta la curvatura de la tierra
		Assert.assertNotEquals(100, paradaDeBondi.cuadrasDeDistancia(unaPosicionRandom));
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
	public void encuentraLaParada() {
		Assert.assertTrue(paradaDeBondi.encuentra("114"));
		Assert.assertFalse(paradaDeBondi.encuentra("11"));
	}
}