package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import F5.Pois.SucursalDeBanco;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {

	public ModelAndView terminalShow(Request req, Response res) throws Exception{ 
		
		List<SucursalDeBanco> bancos = getBancos();
		
		String user = req.queryParams("user");
		String user2 = req.queryParams(":user");
		Map<String, List<SucursalDeBanco>> model = new HashMap<>();
		
		model.put("bancos", bancos);
		

		return new ModelAndView(model, "terminal-show.hbs");
	}
	
	

	private List<SucursalDeBanco> getBancos() {
		//LUCHO-EMI  ESTO TENDRIA QUE SALIR DE LA BASE DE DATOS
		
		List<SucursalDeBanco> bancos = new ArrayList<SucursalDeBanco>();
		bancos.add( new SucursalDeBanco("HSBC", null, null));
		bancos.add(new SucursalDeBanco("galicia", null, null));
		bancos.add(new SucursalDeBanco("frances", null, null));
		return bancos;
	}
	
}
