package ServerSide;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.eclipse.xtend.lib.macro.declaration.EnumerationTypeDeclaration;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Busqueda;
import F5.Persistencia.PersistidorDeTerminal;
import F5.Pois.CGP;
import F5.Pois.DiaAtencion;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.Punto;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.Terminal;
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorBusqueda;
import Reportes.ReportePorFecha;
import Reportes.ReportePorTerminal;
import Reportes.ResultadosDeBusquedas;
import Reportes.ResultadosPorTerminal;
import TestFactoryF5.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public List<ReportePorFecha> busquedasPorFecha = new ArrayList<ReportePorFecha>();
	public List<ReportePorTerminal> busquedasPorTerminal = new ArrayList<ReportePorTerminal>();
	public List<ReportePorBusqueda> reportesPorBusqueda = new ArrayList<ReportePorBusqueda>();
	

	public ModelAndView administradorPoisShow(Request req, Response res) {
		if (Logueado.usuario == null) {
			res.redirect("http://localhost:9000/user/login");
			return null;
		}
		List<PuntoDeInteres> pois = getPois();
		List<Terminal> terminales = getTerminales();
		String usuarioLogueado = Logueado.usuario.getNombre();
		
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
		

		Map<String, Object> model = new HashMap<>();

		model.put("todosLosPois", pois);
		model.put("terminales", terminales);
		model.put("usuario", usuarioLogueado);
		model.put("busquedasPorFecha", getBusquedasPorFecha());
		model.put("busquedasPorTerminal", getBusquedasPorTerminal());
		model.put("busquedasPorBusqueda", getBusquedasPorBusqueda());
		model.put("todasLasBusquedas", getBusquedas());

		return new ModelAndView(model, "administrador-pois-show.hbs");
	}
	
	private Object getBusquedas() {
		List<Object> busquedas = new ArrayList<Object>();
		
		busquedas.addAll(getBusquedasPorFecha());
		busquedas.addAll(getBusquedasPorTerminal());
		busquedas.addAll(getBusquedasPorBusqueda());
		
		return busquedas;
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

	private List<Terminal> getTerminales() {
		return entityManager().createQuery("from Terminal", Terminal.class).getResultList();

	}

	private List<PuntoDeInteres> getPois() {
		return Logueado.terminal.getUnMapa().getPOIs();

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



}
