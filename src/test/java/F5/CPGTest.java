package F5;

import org.uqbar.geodds.Polygon;

import F5.Pois.CGP;
import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.Dias;
import F5.Pois.Servicio;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
public class CPGTest {

	private CGP unCGP;
	private Point unaPosicion, puntoA, puntoB, puntoC, puntoD;
	private DiaAtencion unDia;
	private Polygon unaComuna;
	private Servicio unServicio;
	private List<DiaAtencion> diasDeAtencion = new ArrayList<>();

	@Before
	public void Initialize() {
		unaPosicion = new Point(100, 0);
		// creo un poligono de 4 lados simulando la comuna
		puntoA = new Point(0, 0);
		puntoB = new Point(10, 0);
		puntoC = new Point(10, 10);
		puntoD = new Point(0, 10);
		unaComuna = new Polygon();
		unaComuna.add(puntoA);
		unaComuna.add(puntoB);
		unaComuna.add(puntoC);
		unaComuna.add(puntoD);
		unCGP = new CGP(unaPosicion, new Comuna(1,unaComuna));

	}

	@Test
	public void elCGPEstaDisponibleUnLunesALaManiana() {

		unDia = new DiaAtencion(Dias.Lunes, 1000, 2000);
		diasDeAtencion.add(unDia);
		unServicio = new Servicio("rentas", diasDeAtencion);
		unCGP.anadirServicio(unServicio);
		
		Assert.assertTrue(unCGP.getServicios().stream().anyMatch(s->s.getNombre().equals("rentas")));
		//Assert.assertThat("rentas", anyIn(unCGP.getServicios()));

	}

	@Test
	public void elCGPRentasEstaDisponibleUnLunesALaManiana() {

		unDia = new DiaAtencion(Dias.Lunes, 1000, 2000);
		diasDeAtencion.add(unDia);
		unServicio = new Servicio("rentas", diasDeAtencion);
		unCGP.anadirServicio(unServicio);
		Assert.assertTrue(unCGP.estaDisponible(Dias.Lunes, 1500, unServicio));

	}

	@Test
	public void elCPGEncuentraARentas() {

		unServicio = new Servicio("rentas", diasDeAtencion);
		unCGP.anadirServicio(unServicio);
		Assert.assertTrue(unCGP.encuentra("rentas"));
	}
	
	
}