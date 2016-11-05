package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import F5.Pois.DiaAtencion;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.Punto;
import F5.Pois.SucursalDeBanco;
import TestFactoryF5.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController {

	public ModelAndView administradorPoisShow(Request req, Response res) {
		List<Object> bancos = getPois();
		
		String usuarioLogueado = "";
		if (UsuarioLogueado.usuario != null)
			usuarioLogueado = UsuarioLogueado.usuario.getNombre().concat(" ");
		
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("todosLosPois", bancos);
		model.put("usuario", usuarioLogueado);

		return new ModelAndView(model, "administrador-pois-show.hbs");
	}
	
	
	private List<Object> getPois() {
		//LUCHO-EMI  ESTO TIENE QE SALIR DEL MAPA DE LAS TERMINALES
		
		List<Object> pois = new ArrayList<Object>();
		SucursalDeBanco santander = new SucursalDeBanco("Santander", null, null);
		santander.setCalle("Boedo");
		santander.setAltura("1500");
		santander.setId_Poi(112l);
		
		SucursalDeBanco hsbc = new SucursalDeBanco("HSBC", null, null);
		hsbc.setAltura("1231");
		hsbc.setCalle("Rivadavia");
		hsbc.setId_Poi(212l);
		SucursalDeBanco galicia = new SucursalDeBanco("galicia", null, null);
		galicia.setAltura("3116");
		galicia.setCalle("Gaona");
		galicia.setId_Poi(331l);
		SucursalDeBanco frances = new SucursalDeBanco("frances", null, null);
		frances.setAltura("121");
		frances.setCalle("Independencia");
		frances.setId_Poi(401l);
		ParadaDeColectivo bondi = new ParadaDeColectivo();
		bondi.setAltura("412");
		bondi.setCalle("9 de Julio");
		bondi.setId_Poi(731l);
		
		
		pois.add(hsbc);
		pois.add(santander);
		pois.add(galicia);
		pois.add(frances);
		return pois;
	}
}
