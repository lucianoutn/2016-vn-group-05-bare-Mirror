package F5;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Collection;


public class Sucursal {
	
	private ChronoLocalDate horarioInicial;
	private ChronoLocalDate horarioCierre;
	private Collection<String>diasDeAtencion;
	
	public Sucursal(ChronoLocalDate horaInicio, ChronoLocalDate horaCierre, String dias){
		horarioInicial= horaInicio;
		horarioCierre=horaCierre;
		diasDeAtencion.add(dias);
		
	}
	public boolean estaAbierto(LocalDate X){
		// TODO REIMPLEMENTAR
		if(X.isAfter(horarioInicial) && X.isBefore(horarioCierre))
			{
				return true;
			}
		else
			{
			return false;
			}
	}
	

}

