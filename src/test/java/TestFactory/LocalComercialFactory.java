package TestFactory;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

import F5.DiaAtencion;
import F5.LocalComercial;

public final class LocalComercialFactory {

	public static LocalComercial mimoEsLibreriaPunto100_0DisponibleMiercoles10a20(){
		
		return  new LocalComercial("Mimo","Corrientes","3052","Libreria", DayFactory.miercoles(1000, 2000) , new Point(100, 0));
	}
}
	