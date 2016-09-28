package TestFactory;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import F5.Pois.DiaAtencion;
import F5.Pois.Dias;
import F5.Pois.Punto;
import F5.Pois.SucursalDeBanco;

public final class BancoFactory {

	public static SucursalDeBanco BancoSantanderEnOrigenYMiercolesDe9A18() {

		Punto origen = PointFactory.PuntoOrigen();
		List<DiaAtencion> dias = DayFactory.miercoles(900, 1800);
		SucursalDeBanco unaSucursal = new SucursalDeBanco("Santander", origen, dias);
		return unaSucursal;
	}

	public static SucursalDeBanco BancoHSBC() {
		return new SucursalDeBanco("HSBC", null, null);
	}

	public static SucursalDeBanco BancoSabadoDe10a13() {

		return new SucursalDeBanco(null, null, DayFactory.sabado(10, 13));
	}

}
