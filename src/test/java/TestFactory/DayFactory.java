package TestFactory;

import F5.Dias;

import java.util.ArrayList;
import java.util.List;

import F5.DiaAtencion;

public final class DayFactory {

	public static DiaAtencion miercolesDe9a18(){
		return new DiaAtencion(Dias.Miercoles, 900, 1800);
	}

	public static List<DiaAtencion> sabado(int horaInicial, int horaCierre) {
		List<DiaAtencion> dias = new ArrayList<DiaAtencion>();
		DiaAtencion sabado = new DiaAtencion(Dias.Sabado, horaInicial, horaCierre);
		dias.add(sabado);
		return dias;
	}
	
	public static List<DiaAtencion> miercoles(int horaInicial, int horaCierre) {
		List<DiaAtencion> dias = new ArrayList<DiaAtencion>();
		DiaAtencion sabado = new DiaAtencion(Dias.Sabado, horaInicial, horaCierre);
		dias.add(sabado);
		return dias;
	}
	
	
	
}
