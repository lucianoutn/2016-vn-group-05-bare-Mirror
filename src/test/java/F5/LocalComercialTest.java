package F5;

import java.util.ArrayList;
import F5.DiaAtencion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class LocalComercialTest {
	
	private LocalComercial unLocalComercial, otroLocalComercial;
	private Point unaPosicion;
	private DiaAtencion unDia, otroDia;
	private ArrayList<DiaAtencion> listaDeDias;

	@Before
	public void Initialize() {
		unaPosicion = new Point(100, 0);
		unLocalComercial = new LocalComercial("Mimo","Corrientes","3052","Libreria",new ArrayList<DiaAtencion>(),unaPosicion);
	}
	
	@Test
	public void elLocalComercialEstaCerca(){
		unLocalComercial.setCuadrasDeCercania(5);
		Assert.assertTrue(unLocalComercial.estaCerca(unaPosicion));
	}

	@Test
	public void elLocalComercialEstaDisponibleUnLunes17hs(){
		unDia = new DiaAtencion(Dias.Martes,10,20);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(unDia);
		otroLocalComercial = new LocalComercial("Mimo","Corrientes","3052","Libreria",listaDeDias,null);
		Assert.assertTrue(unLocalComercial.estaDisponible(Dias.Martes, 17, null));
	}
}