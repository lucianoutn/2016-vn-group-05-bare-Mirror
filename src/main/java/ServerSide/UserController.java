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

public class UserController implements WithGlobalEntityManager, TransactionalOps{
	
	//● Una vez autenticado mostrará la pantalla correspondiente para su perfil.
	
	public ModelAndView login(Request req, Response res) throws Exception{
		
		
		Map<String, List<Object>> model = new HashMap<>();
		
		//esto tiene qe salir del bootstrap cuando almacena en la base
		List<Object> pois = new ArrayList<Object>(); 
		pois.add(new SucursalDeBanco("HSBC", null, null));
		pois.add(new SucursalDeBanco("galicia", null, null));
		pois.add(new SucursalDeBanco("frances", null, null));
		
		model.put("bancos", pois);
		
		Object terminales = entityManager()
									.createQuery("from Terminal", Terminal.class)
									.getResultList();
		
		List<Object> a = (List<Object>) terminales;
		
		model.put("terminal", a);
		
		
		
		return new ModelAndView(model, "user-login.hbs");
	}
}
