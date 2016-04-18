package F5;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class DispositivoTest {

	private Dispositivo dispositivoTest;
	private Point puntoDispositivo, puntoA, puntoB, puntoC, puntoD;
	private Polygon comunaDelimitadaPor;

	@Before
	public void initialize() {
		dispositivoTest = new Dispositivo();
		puntoDispositivo = new Point(1, 1); // punto donde se encuentra el
											// dispositivo

		// creo un poligono de 4 lados simulando la comuna
		puntoA = new Point(0, 0);
		puntoB = new Point(10, 0);
		puntoC = new Point(10, 10);
		puntoD = new Point(0, 10);
		comunaDelimitadaPor = new Polygon();
		comunaDelimitadaPor.add(puntoA);
		comunaDelimitadaPor.add(puntoB);
		comunaDelimitadaPor.add(puntoC);
		comunaDelimitadaPor.add(puntoD);

		dispositivoTest.setPoint(puntoDispositivo);
		dispositivoTest.setComunaDelimitadaPor(comunaDelimitadaPor);

	}

	@Test // se espera que el dispositvo ese fiscamente dentro del area
			// geografica de la comuna
	public void elDispositivoSeEncuentraDentroDeSuPropiaComuna() {
		Assert.assertTrue(dispositivoTest.getComunaDelimitadaPor().isInside(dispositivoTest.getPoint()));
	}

	@Test
	public void elPuntoSeEncuentraDentroDeLaComuna() {
		Assert.assertTrue(dispositivoTest.getComunaDelimitadaPor().isInside(new Point(5, 5)));
	}
}
