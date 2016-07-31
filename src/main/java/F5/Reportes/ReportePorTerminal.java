package F5.Reportes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import F5.Busqueda;
import F5.Terminal.Terminal;

public class ReportePorTerminal extends Reporte{

	private Map<Terminal, Integer> busquedasPorTerminal;
	private Terminal terminal;
	
	public ReportePorTerminal(RepositorioDeBusquedas unRepositorioDeBusquedas){
		this.setRepositorioDeBusquedas(unRepositorioDeBusquedas);
		busquedasPorTerminal = new HashMap<Terminal, Integer>();
	}
		
	public void actualizarOInsertarEnReporte(Busqueda busqueda) {
		terminal = busqueda.getTerminal();
		if(!busquedasPorTerminal.containsKey(terminal)){
			busquedasPorTerminal.put(terminal, 1);
		} else {
			this.setContadorDeBusquedasPorClave(busquedasPorTerminal.get(terminal)+1);
			busquedasPorTerminal.put(terminal, this.getContadorDeBusquedasPorClave());
		}
		
	}

	public int numeroDeBusquedasPara(Terminal unaTerminal) {
		return busquedasPorTerminal.get(unaTerminal);
	}
	
	
}
