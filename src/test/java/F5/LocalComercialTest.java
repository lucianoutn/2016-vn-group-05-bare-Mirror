package F5;

import java.util.ArrayList;

import TestFactory.LocalComercialFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import F5.Pois.DiaAtencion;
import F5.Pois.Dias;
import F5.Pois.LocalComercial;

public class LocalComercialTest {
	
	
	
	@Before
	public void Initialize() {
		
	}
	
	@Test
	public void mimoEstaCercaDeUnPunto(){
		
		LocalComercial mimo = LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20();
		mimo.setCuadrasDeCercania(5);
		Assert.assertTrue(mimo.estaCerca(new Point(101,0)));
		
	}
	@Test
	public void mimoNoEstaCercaDeUnPunto(){
		
		LocalComercial mimo = LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20();
		mimo.setCuadrasDeCercania(5);
		Assert.assertFalse(mimo.estaCerca(new Point(50,50)));
		
	}
	

	@Test
	public void localComercialDisponibleMiercoles11horas(){
		LocalComercial mimo = LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20();
		Assert.assertTrue(mimo.estaDisponible(Dias.Miercoles,1100,null));
	}
	
	@Test
	public void localComercialNoDisponibleMartes20horas(){
		
		LocalComercial mimo = LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20();
		Assert.assertFalse(mimo.estaDisponible(Dias.Martes,2000,null));
	}
	
	@Test
	public void localComercialNoDisponibleSabado15horas(){
		
		LocalComercial mimo = LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20();
		Assert.assertTrue(mimo.estaDisponible(Dias.Miercoles,1100,null));
		
	}
	
}
