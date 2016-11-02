package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import TestFactory.BancoFactory;
import spark.*;

public class ConsultaDePoisController implements WithGlobalEntityManager, TransactionalOps{
	
	//● Una vez autenticado mostrará la pantalla correspondiente para su perfil.
	
	public ModelAndView login(Request req, Response res) throws Exception{
		
		
		List<Terminal> terminales = entityManager()
									.createQuery("from Terminal", Terminal.class)
									.getResultList();
			 	
		
		
		Map<String, List<Terminal>> model = new HashMap<>();
		model.put("terminal", terminales);
		
		
		
		return new ModelAndView(model, "user-login.hbs");
	}
	
	public ModelAndView checkLogin(Request req, Response res) throws Exception{
		
		if(1==1){ //aca valida si el usuario va a la terminal
			res.redirect("http://localhost:9000/administrador/pois/show");
		    return null;
		}
		if(1==1){ //aca valida si el usuario va a la terminal
			res.redirect("http://localhost:9000/terminal/show");
		    return null;
		}
		return null;
	}

	
	public ModelAndView administradorPoisShow(Request req, Response res) {
		List<SucursalDeBanco> bancos = getBancos();
		
		
		Map<String, List<SucursalDeBanco>> model = new HashMap<>();
		
		model.put("bancos", bancos);
		

		return new ModelAndView(model, "administrador-pois-show.hbs");
	}

	public ModelAndView terminalShow(Request req, Response res) throws Exception{ 
		
		List<SucursalDeBanco> bancos = getBancos();
		
	
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
