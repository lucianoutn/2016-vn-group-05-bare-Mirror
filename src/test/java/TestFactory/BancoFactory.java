package TestFactory;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

import F5.DiaAtencion;
import F5.Dias;
import F5.SucursalDeBanco;

public final class BancoFactory {

	public static SucursalDeBanco BancoSantanderEnOrigenYMiercolesDe9A18(){
	
		
		Point origen = PointFactory.PuntoOrigen();
		SucursalDeBanco unaSucursal = new SucursalDeBanco("Santander", origen, new  ArrayList<DiaAtencion>());
		return unaSucursal;
	}

	public static SucursalDeBanco BancoHSBC(){
		return new SucursalDeBanco("HSBC", null, null);
	}
	
	public static SucursalDeBanco BancoSabadoDe10a13() {
		
		return new SucursalDeBanco(null, null, DayFactory.sabado(10,13));
	}
	
	
	
}
