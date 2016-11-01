package ServerSide;

import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.*;

public class UserController implements WithGlobalEntityManager, TransactionalOps{
	
	//● Una vez autenticado mostrará la pantalla correspondiente para su perfil.
	
	public ModelAndView login(Request req, Response res){
		
		
		Map<String, String> model = new HashMap<>();
		model.put("nombre", "Ezequiel");
		
		
		return new ModelAndView(model, "user/login.hbs");
	}
}
