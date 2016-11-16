package ServerSide;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Busqueda;
import F5.Persistencia.PersistidorDePOIs;
import F5.Persistencia.PersistidorDeTerminal;
import F5.Pois.Punto;
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
		String idPoiAEliminar = req.queryParams("eliminar");
		String idPoiAModificar = req.queryParams("editarID");
		String terminal  = req.queryParams("terminal");
		
		if(terminal != null && !terminal.isEmpty() && !busquedasPorTerminal.isEmpty() ){
			
			busquedasPorTerminal = busquedasPorTerminal.stream()
					.filter(bus-> bus.getUnaTerminal().getNombreDeTerminal().equals(terminal)).
					collect(Collectors.toList());
			
		}
		
		
		if(idPoiAModificar != null && !idPoiAModificar.isEmpty() ){
			String nombre = req.queryParams("nombrePoi");
			String call = req.queryParams("CallePoi");
			String lat = req.queryParams("latitudPoi");
			String longi = req.queryParams("longitudPoi");
			if(lat != null && !lat.isEmpty() && longi != null && !longi.isEmpty())
				this.editarPoi(Long.parseLong(idPoiAModificar), nombre, call, 
						new Punto( Long.parseLong(lat),Long.parseLong(longi)));
			else
				this.editarPoi(Long.parseLong(idPoiAModificar), nombre, call, null);
			
		}
		
		if(criterio != null && !criterio.isEmpty()){
			pois = buscar(criterio);
		}
		
		if(idPoiAEliminar != null && !idPoiAEliminar.isEmpty()){
			
			this.eliminarPoi(Long.parseLong(idPoiAEliminar));
			
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
	
	
	
	
	
	private List<ReportePorBusqueda> getBusquedasPorBusqueda() {
		return reportesPorBusqueda;
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
	

	private void eliminarPoi(long id) {

		Logueado.terminal.getUnMapa().eliminarPOIporID(id);

	}
	
	private void editarPoi(long id, String nombre, String calle, Punto ubicacion){
		Logueado.terminal.getUnMapa().getPuntosDeInteres()
				.stream().filter(poi-> poi.getId().equals(id))
				.forEach( p-> p.editarPoi(nombre,calle,ubicacion));
		withTransaction(()->{
		PersistidorDeTerminal.getInstancia().persistir(Logueado.terminal);});
		
	}

	
}
