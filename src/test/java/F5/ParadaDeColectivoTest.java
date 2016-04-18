package F5;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.time.LocalDate;

import org.junit.Assert;
//import F5.ParadaDeColectivo;

public class ParadaDeColectivoTest {
	private ParadaDeColectivo paradaDeBondi;
	
	//public ParadaDeColectivo (String calleDeParada, int alturaDeParada, int ejeY, int ejeX, String lineaDeColectivo)
	
	@Before
	public void Initialize() {
		paradaDeBondi = new ParadaDeColectivo("Mozart",2600,100,100,"114");
	}
	
	@Test
	public void laParadaEstaCerca() {
		paradaDeBondi.setToleranciaEnCuadras(100);
		Assert.assertEquals(true, paradaDeBondi.estaCerca(70,50));
	}
	
	@Test
	public void laParadaEstaDisponible() {
		paradaDeBondi.setToleranciaEnCuadras(100);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(true, paradaDeBondi.estaDisponible(today));
	}
	
	@Test
	public void encuentraLaParada() {
		Assert.assertEquals(true, paradaDeBondi.encuentra("114"));
	}
	
}