package F5;

import java.util.ArrayList;
import F5.DiaAtencion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class LocalComercialTest {
	
	private LocalComercial unLocalComercial, otroLocalComercial;
	private Point unaPosicion, otraPosicion;
	private DiaAtencion unDia;
	private ArrayList<DiaAtencion> listaDeDias;

	@Before
	public void Initialize() {
		unaPosicion = new Point(100, 0);
		unLocalComercial = new LocalComercial("Mimo","Corrientes","3052","Libreria",new ArrayList<DiaAtencion>(),unaPosicion);
	}
	
	@Test
	public void elLocalComercialEstaCerca(){
		otraPosicion = new Point(50,50);
		unLocalComercial.setCuadrasDeCercania(5);
		Assert.assertTrue(unLocalComercial.estaCerca(unaPosicion));
		Assert.assertFalse(unLocalComercial.estaCerca(otraPosicion));
	}

	@Test
	public void elLocalComercialEstaDisponibleUnMartes(){
		unDia = new DiaAtencion(Dias.Martes,1000,2000);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(unDia);
		otroLocalComercial = new LocalComercial("Mimo","Corrientes","3052","Libreria",listaDeDias,unaPosicion);
		Assert.assertTrue(otroLocalComercial.estaDisponible(Dias.Martes,2000,null));
		Assert.assertTrue(otroLocalComercial.estaDisponible(Dias.Martes,1000,null));
		Assert.assertFalse(otroLocalComercial.estaDisponible(Dias.Sabado,1500,null));
	}
	
	public void elLocalComercialNoEstaDisponibleUnSabado15hs(){
		
		unDia = new DiaAtencion(Dias.Martes,1000,2000);
		listaDeDias = new ArrayList<DiaAtencion>();
		listaDeDias.add(unDia);
		otroLocalComercial = new LocalComercial("Mimo","Corrientes","3052","Libreria",listaDeDias,unaPosicion);
		Assert.assertFalse(otroLocalComercial.estaDisponible(Dias.Sabado,1500,null));
		
	}
}
