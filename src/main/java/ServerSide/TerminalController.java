package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {

	public ModelAndView terminalShow(Request req, Response res) throws Exception{ 
		
		List<PuntoDeInteres> bancos = getBancos();
		
		String user = req.queryParams("user");
		String criterio = req.queryParams("criterio");
		if(criterio != null && !criterio.isEmpty()){
			bancos = buscar(criterio);
		}
		
		
		Map<String, List<PuntoDeInteres>> model = new HashMap<>();
		
		model.put("bancos", bancos);
		

		return new ModelAndView(model, "terminal-show.hbs");
	}
	
	

	private List<PuntoDeInteres> buscar(String criterio) {
		//ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("009"));
		//ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("010"));

		RepositorioDePOIs unMapa = new RepositorioDePOIs(null, null);
		unMapa.anadirPOI(new SucursalDeBanco("HSBC", null, null));
		unMapa.anadirPOI(new SucursalDeBanco("galicia", null, null));
		unMapa.anadirPOI(new SucursalDeBanco("frances", null, null));
		
		Terminal unaTerminal = new Terminal("Caballito", unMapa);
		
		List<PuntoDeInteres> poisEncontrados =  unaTerminal.buscarEnTerminal(criterio, new Usuario("Eze", null));
		return ((List<PuntoDeInteres>) poisEncontrados);
		
		
		
	}



	private List<PuntoDeInteres> getBancos() {
		//LUCHO-EMI  ESTO TENDRIA QUE SALIR DE LA BASE DE DATOS
		
		List<PuntoDeInteres> bancos = new ArrayList<PuntoDeInteres>();
		bancos.add( new SucursalDeBanco("HSBC", null, null));
		bancos.add(new SucursalDeBanco("galicia", null, null));
		bancos.add(new SucursalDeBanco("frances", null, null));
		return bancos;
	}
	
}
