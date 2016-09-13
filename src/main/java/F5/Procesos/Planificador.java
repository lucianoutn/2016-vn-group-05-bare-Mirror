package F5.Procesos;

import java.util.ArrayList;
import java.util.List;

public class Planificador {
	// atributos
	public boolean ejecucionDisponible = true;
	public List<Proceso> procesosPendientesDeEjecucion = new ArrayList<Proceso>();

	// metodos
	public void solicitarEjecucion(Proceso unProceso) { //es solicitado por el metodo ejecutar()de cada proceso
		if (ejecucionDisponible) {
			ejecucionDisponible = false;
			unProceso.ejecutar(); //este hace la ejecucion verdadera del proceso en si
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
