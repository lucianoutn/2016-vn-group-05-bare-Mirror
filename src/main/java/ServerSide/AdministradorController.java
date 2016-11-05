package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import F5.Pois.CGP;
import F5.Pois.DiaAtencion;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.Punto;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import TestFactoryF5.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController {

	public ModelAndView administradorPoisShow(Request req, Response res) {
		List<PuntoDeInteres> pois = getPois();
		
		String usuarioLogueado = "";
		if (Logueado.usuario != null)
			usuarioLogueado = Logueado.usuario.getNombre().concat(" ");
		
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("todosLosPois", pois);
		model.put("usuario", usuarioLogueado);

		return new ModelAndView(model, "administrador-pois-show.hbs");
	}
	
	
	private List<PuntoDeInteres> getPois() {
		//LUCHO-EMI  ESTO TIENE QE SALIR DEL MAPA DE LAS TERMINALES
		
		
			return  Logueado.terminal.getUnMapa().getPOIs();
 		
	}
}
