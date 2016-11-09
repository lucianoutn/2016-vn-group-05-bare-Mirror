package ServerSide;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Pois.CGP;
import F5.Pois.Comuna;
import F5.Pois.ParadaDeColectivo;
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
import Reportes.ResultadosPorTerminal;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init(){
		SucursalDeBanco santander = new SucursalDeBanco("Santander", null, null);
		santander.setCalle("Boedo");
		santander.setAltura("1500");
	
		
		SucursalDeBanco hsbc = new SucursalDeBanco("HSBC", null, null);
		hsbc.setAltura("1231");
		hsbc.setCalle("Rivadavia");
	
		
		SucursalDeBanco galicia = new SucursalDeBanco("galicia", null, null);
		galicia.setAltura("3116");
		galicia.setCalle("Gaona");
	
		
		SucursalDeBanco frances = new SucursalDeBanco("frances", null, null);
		frances.setAltura("121");
		frances.setCalle("Independencia");
	
		
		ParadaDeColectivo bondi = new ParadaDeColectivo();
		bondi.setAltura("412");
		bondi.setCalle("9 de Julio");
	
		bondi.setNumeroDeLinea("8");
		
		CGP cgp = new CGP();
		cgp.setAltura("512");
		cgp.setCalle("Caseros");
	
		cgp.setNombrePoi("CGP CASEROS");
		
		withTransaction(() ->{
			
			
			
			persist(hsbc);
			persist(galicia);
			persist(frances);
			persist(bondi);
			persist(santander);
			persist(bondi);
			persist(cgp);
			
			
			Comuna comuna = new Comuna(1, (Polygon)null);
			persist(comuna);
			Usuario ezequiel = new Usuario("Ezequiel", comuna, "unaPass", true);
			persist(ezequiel);
			Usuario franco = new Usuario("Franco", comuna, "passfranco", false);
			persist(franco);
			
			ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("009"));
			ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("010"));

			RepositorioDePOIs unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
			unMapa.anadirPOI(hsbc);
			unMapa.anadirPOI(galicia);
			unMapa.anadirPOI(frances);
			unMapa.anadirPOI(santander);
			unMapa.anadirPOI(bondi);
			unMapa.anadirPOI(cgp);
			
			
			persist(unMapa);
			BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
			ResultadosPorTerminal reporteroDeTerminal = new ResultadosPorTerminal();
			List<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<NotificadorDeBusqueda>();
			listaDeUnReportero.add(reporteroDeBusquedas);
			listaDeUnReportero.add(reporteroDeTerminal);
			persist(reporteroDeBusquedas);
			persist(reporteroDeTerminal);
			
			Terminal unaTerminal = new Terminal("Caballito", unMapa);
			unaTerminal.setListaObservadores(listaDeUnReportero);
			Terminal boedo = new Terminal("Boedo", unMapa);
			boedo.setListaObservadores(listaDeUnReportero);
			Terminal palermo = new Terminal("Palermo", unMapa);
			boedo.setListaObservadores(listaDeUnReportero);
			persist(unaTerminal);
			persist(boedo);
			persist(palermo);
		});
	}
}