package F5;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import java.time.LocalDate;

import org.junit.Assert;
//import F5.ParadaDeColectivo;

public class ParadaDeColectivoTest {
	private ParadaDeColectivo paradaDeBondi;
	
	//public ParadaDeColectivo (String calleDeParada, int alturaDeParada, int ejeY, int ejeX, String lineaDeColectivo)
	
	@Before
	public void Initialize() {
		paradaDeBondi = new ParadaDeColectivo("Mozart", "2600", new Point(100,100),"114");
	}
	
	@Test
	public void laParadaEstaCerca() {
		paradaDeBondi.setToleranciaEnCuadras(100);
		Assert.assertEquals(true, paradaDeBondi.estaCerca(null));
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