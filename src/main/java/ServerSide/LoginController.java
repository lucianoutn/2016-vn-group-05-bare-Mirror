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

public class LoginController implements WithGlobalEntityManager, TransactionalOps{
	
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

	
	


}
