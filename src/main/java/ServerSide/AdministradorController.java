package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import F5.Pois.SucursalDeBanco;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController {

	public ModelAndView administradorPoisShow(Request req, Response res) {
		List<SucursalDeBanco> bancos = getBancos();
		
		String usuarioLogueado = "";
		if (UsuarioLogueado.usuario != null)
			usuarioLogueado = UsuarioLogueado.usuario.getNombre().concat(" ");
		
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("bancos", bancos);
		model.put("usuario", usuarioLogueado);

		return new ModelAndView(model, "administrador-pois-show.hbs");
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
