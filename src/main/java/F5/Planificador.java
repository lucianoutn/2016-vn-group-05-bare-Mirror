package F5;

import java.util.ArrayList;
import java.util.List;

public class Planificador {
	// atributos
	private boolean ejecucionDisponible = true;
	private List<Proceso> procesosPendientesDeEjecucion = new ArrayList<Proceso>();

	// metodos
	public void solicitarEjecucion(Proceso unProceso) { //es solicitado por el metodo ejecutar()de cada proceso
		if (ejecucionDisponible) {
			ejecucionDisponible = false;
			unProceso.ejecutarPosta(); //este hace la ejecucion verdadera del proceso en si
		} else {
			procesosPendientesDeEjecucion.add(unProceso);
		}
	}

	public void liberarEjecucion() {	//es llamado por cada proceso cuando termina
		ejecucionDisponible = true;
		if (procesosPendientesDeEjecucion.size() > 0) {
			Proceso procesoPendiente = procesosPendientesDeEjecucion.remove(0);
			this.solicitarEjecucion(procesoPendiente);
		}

	}

}
