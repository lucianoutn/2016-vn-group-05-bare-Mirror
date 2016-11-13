package ServerSide;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Busqueda;
import F5.Pois.PuntoDeInteres;
import F5.Terminal.Terminal;
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorBusqueda;
import Reportes.ReportePorFecha;
import Reportes.ReportePorTerminal;
import Reportes.ResultadosDeBusquedas;
import Reportes.ResultadosPorTerminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{

	public List<ReportePorFecha> busquedasPorFecha = new ArrayList<ReportePorFecha>();
	public List<ReportePorTerminal> busquedasPorTerminal = new ArrayList<ReportePorTerminal>();
	public List<ReportePorBusqueda> reportesPorBusqueda = new ArrayList<ReportePorBusqueda>();
	

	public ModelAndView terminalShow(Request req, Response res) throws Exception{ 
		if(Logueado.usuario == null){
			res.redirect("http://localhost:9000/user/login");
		    return null;
		}
		
		
		List<PuntoDeInteres> pois = getBancos();
		
		String user = req.queryParams("user");
		String criterio = req.queryParams("criterio");
		if(criterio != null && !criterio.isEmpty()){
			pois = buscar(criterio);
		}
		
		String usuarioLogueado = "";
		if (Logueado.usuario != null)
			usuarioLogueado = Logueado.usuario.getNombre();
		
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("todosLosPois", pois);
		model.put("busquedasPorFecha", getBusquedasPorFecha());
		model.put("busquedasPorTerminal", getBusquedasPorTerminal());
		model.put("busquedasPorBusqueda", getBusquedasPorBusqueda());
		model.put("todasLasBusquedas", getBusquedas());
		model.put("usuario", usuarioLogueado);

		return new ModelAndView(model, "terminal-show.hbs");
	}
	
	
	private Object getBusquedas() {
		List<Object> busquedas = new ArrayList<Object>();
		
		busquedas.addAll(getBusquedasPorFecha());
		busquedas.addAll(getBusquedasPorTerminal());
		busquedas.addAll(getBusquedasPorBusqueda());
		
		return busquedas;
	}
	
	
	private void eliminarPoi(long id){
		
		Logueado.terminal.getUnMapa().eliminarPOIporID(id);
		
	}
	
	
	private List<ReportePorBusqueda> getBusquedasPorBusqueda() {
		return reportesPorBusqueda;
		//List<ReportePorBusqueda> reportes = new ArrayList<ReportePorBusqueda>();
		//withTransaction(()->
		//PersistidorDeReportes.getInstancia().traerResultadosDeBusquedas().
			//				stream().forEach(reportero-> reportes.addAll(reportero.generarReporte())));
		/*double demora = 41;
		double demora2 = 1;
		double demora3 = 11;
		ReportePorBusqueda reporte = new ReportePorBusqueda("colectivo 5", 2, demora);
		ReportePorBusqueda reporte2= new ReportePorBusqueda("CGP Saavedra", 0, demora2);
		ReportePorBusqueda reporte3 = new ReportePorBusqueda("Banco Galicia", 1, demora3);
		reportes.add(reporte);
		reportes.add(reporte2);
		reportes.add(reporte3);
		*/
		//return reportes;
	}


	private List<ReportePorTerminal>  getBusquedasPorTerminal() {
		return busquedasPorTerminal;
		

	}


	public List<ReportePorFecha> getBusquedasPorFecha() {
		Random randomGenerator = new Random();
	
		busquedasPorFecha.forEach(b -> b.setCantidadDeBusquedas(randomGenerator.nextInt(10)));
		busquedasPorFecha.forEach(b -> b.setDiaDeLaBusqueda(LocalTime.now()));
		
		return busquedasPorFecha; 
	}



	private List<PuntoDeInteres> buscar(String criterio) {
	
		
		
		BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
		ResultadosPorTerminal resultadosPorTerminal = new ResultadosPorTerminal();
		ResultadosDeBusquedas resultadosPorBusqueda = new ResultadosDeBusquedas();
		List<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<NotificadorDeBusqueda>();
		listaDeUnReportero.add(reporteroDeBusquedas);
		listaDeUnReportero.add(resultadosPorTerminal);
		listaDeUnReportero.add(resultadosPorBusqueda);
		
		Logueado.terminal.setListaObservadores(listaDeUnReportero);
		
		
		Busqueda unaBusqueda= new Busqueda(Logueado.terminal, Logueado.usuario,criterio);
		unaBusqueda.setListaObservers(listaDeUnReportero);
		List<PuntoDeInteres> poisEncontrados= unaBusqueda.buscoFrase(criterio, Logueado.terminal.getUnMapa());
		
		
		
		busquedasPorFecha.addAll(reporteroDeBusquedas.reportesPorFecha);
		busquedasPorTerminal.addAll(resultadosPorTerminal.getReportesPorTerminal());
		reportesPorBusqueda.addAll(resultadosPorBusqueda.getReporte());
		return ((List<PuntoDeInteres>) poisEncontrados);	
		
	}



	private List<PuntoDeInteres> getBancos() {
		return Logueado.terminal.getUnMapa().getPOIs();
	}
	
	
	public ModelAndView terminalNew(Request req, Response res) throws Exception{ 
		if(Logueado.usuario == null){
			res.redirect("http://localhost:9000/user/login");
		    return null;
		}
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("usuario", Logueado.usuario.getNombre());
		return new ModelAndView(null, "terminal-new.hbs");
	}
	
	public ModelAndView crear(Request req, Response res) throws Exception{ 
		
		String nombreDeTerminal = req.queryParams("nombreDeTerminal");
		
		Terminal terminalNueva = new Terminal(nombreDeTerminal, Logueado.terminal.getUnMapa());
	   
		withTransaction(() -> {
			entityManager().persist(terminalNueva);
	    });

	    res.redirect("/administrador/pois/show");
		return null;
		
		
	}
	
}
