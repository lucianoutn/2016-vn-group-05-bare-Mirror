package TestFactory;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

import F5.Pois.DiaAtencion;
import F5.Pois.LocalComercial;
import F5.Pois.Punto;

public final class LocalComercialFactory {

	public static LocalComercial mimoEsLibreriaPunto100_0DisponibleMiercoles10a20(){
		
		return  new LocalComercial("Mimo","Corrientes","3052","Libreria", DayFactory.miercoles(1000, 2000) , new Punto(100, 0));
	}
}
	