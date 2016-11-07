package ServerSide;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JSpinner.DateEditor;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Persistencia.PersistidorDeBusqueda;
import F5.Persistencia.PersistidorDeReportes;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import Reportes.BusquedasPorFecha;
import Reportes.NotificadorDeBusqueda;
import Reportes.ReportePorBusqueda;
import Reportes.ReportePorFecha;
import Reportes.ReportePorTerminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{

	public List<ReportePorFecha> busquedasPorFecha;
	public List<ReportePorTerminal> busquedasPorTerminal;
	public List<ReportePorBusqueda> reportesPorBusqueda;
	

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
		Object porFecha = getBusquedasPorFecha();
		if(porFecha != null){
			busquedas.addAll((List<Object>)(porFecha));
		}
			
		busquedas.addAll(getBusquedasPorTerminal());
		busquedas.addAll(getBusquedasPorBusqueda());
		
		return busquedas;
	}


	private List<ReportePorBusqueda> getBusquedasPorBusqueda() {
		List<ReportePorBusqueda> reportes = new ArrayList<ReportePorBusqueda>();
		withTransaction(()->
		PersistidorDeReportes.getInstancia().traerResultadosDeBusquedas().
							stream().forEach(reportero-> reportes.addAll(reportero.generarReporte())));
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
		return reportes;
	}


	private List<ReportePorTerminal>  getBusquedasPorTerminal() {
		List<ReportePorTerminal> reportes = new ArrayList<ReportePorTerminal>();
		withTransaction(()->
		PersistidorDeReportes.getInstancia().traerResultadosPorTerminal().
						stream().forEach(reportero-> reportes.addAll(reportero.generarReporte(null))));
		// FALTA ACLARAR QUE TERMINAL EN PARTICULAR TIENE QUE GENERARSE EL REPORTE
		/*
		ReportePorTerminal reporte = new ReportePorTerminal();
		double cant =2;
		reporte.setCantResultados(cant);
		reporte.setUnaTerminal(new Terminal("Abasto", null));
		reportes.add(reporte);		*/
		return reportes;

	}


	public List<ReportePorFecha> getBusquedasPorFecha() {
		if (busquedasPorFecha != null ){
			busquedasPorFecha.get(0).setCantidadDeBusquedas(5);
			busquedasPorFecha.get(0).setDiaDeLaBusqueda(LocalTime.now());
		}
				
		return busquedasPorFecha; //ESTO TIENE QUE SALIR de la base de datos segun las busquedas
	}



	private List<PuntoDeInteres> buscar(String criterio) {
		//ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("009"));
		//ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("010"));

		
		
		RepositorioDePOIs unMapa = new RepositorioDePOIs(null, null);
		unMapa.anadirPOI(new SucursalDeBanco("HSBC", null, null));
		unMapa.anadirPOI(new SucursalDeBanco("galicia", null, null));
		unMapa.anadirPOI(new SucursalDeBanco("frances", null, null));
		
		Terminal unaTerminal = new Terminal("Caballito", unMapa);
		
		BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
		List<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<NotificadorDeBusqueda>();
		listaDeUnReportero.add(reporteroDeBusquedas);
		unaTerminal.setListaObservadores(listaDeUnReportero);
		
		List<PuntoDeInteres> poisEncontrados =  unaTerminal.buscarEnTerminal(criterio, Logueado.usuario);
		reporteroDeBusquedas.reportesPorFecha.forEach(r -> persist(r));
		
		busquedasPorFecha = reporteroDeBusquedas.reportesPorFecha;
		return ((List<PuntoDeInteres>) poisEncontrados);
		
		
		
	}



	private List<PuntoDeInteres> getBancos() {
		return Logueado.terminal.getUnMapa().getPOIs();
	}
	
}
