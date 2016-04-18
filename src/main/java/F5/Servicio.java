package F5;

import java.time.LocalDate;
import java.util.Collection;

public class Servicio {
	
	private int horarioApertura;
	private int horarioCierre;
	private Collection<String>diasDeAtencion;
	
	public Servicio(int horA, int horC, String dDA){
		horarioApertura= horA;
		horarioCierre= horC;
		diasDeAtencion.add(dDA);
		
	}
	boolean estaAbierto(LocalDate X){
		
		return false;
	}
}

