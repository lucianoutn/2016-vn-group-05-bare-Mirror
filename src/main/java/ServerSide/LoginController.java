package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Persistencia.PersistidorDeUsuarios;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import TestFactory.BancoFactory;
import spark.*;

public class LoginController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	//● Una vez autenticado mostrará la pantalla correspondiente para su perfil.
	
	public  Usuario usuario;
	public boolean encontro = false;
	public ModelAndView login(Request req, Response res) throws Exception{
		
		String mensajeError = "";
		String error = req.queryParams("error");
		if(error != null && error.equals("validation")){
			mensajeError = "Ingreso mal el usuario o la contraseña";
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("mensajeError", mensajeError);
		
		
		
		return new ModelAndView(model, "user-login.hbs");
	}
	
	public ModelAndView checkLogin(Request req, Response res) throws Exception{
		
		Logueado.terminal = entityManager().createQuery("from Terminal", Terminal.class).getResultList().get(0);
		
		
		String error = req.queryParams("error");
		String nombreUsuario = req.params("name");
		String password = req.params("password");
		if(nombreUsuario == null){
			nombreUsuario = "";
		}
			
		if(password == null){
			password = "";
		}
			
		//nombreUsuario = "Ezequiel";
		//password = "unaPass";
		//nombreUsuario = "Franco";
		//password = "passfranco";
		
		List<Usuario> usuarios = getUsuarios(); //esto deberia ser un find pero no esta funcionando
		
		if (estaLogueadoCorrectamente(usuarios, nombreUsuario, password)){
			
			
		  
			if(Logueado.usuario.isAdmin()){ //aca valida si el usuario va a la terminal
				res.redirect("http://localhost:9000/administrador/pois/show");
				return null;
			}else{
				res.redirect("http://localhost:9000/terminal/show");
			    return null;
			}
		}
		res.redirect("http://localhost:9000/user/login?error=validation");
	    return null;
		
	}

	public boolean estaLogueadoCorrectamente(List<Usuario> usuarios, String usuario, String password) {
		
		Logueado.usuario = usuarios.get(3); //Ezequiel
		//Logueado.usuario = usuarios.get(1); //Franco
		
			
		//return true;
		//NO ESTA LLEGANDO LOS QUERYPARAMS
		
		usuarios.forEach(u-> {
		 
			if (u.getNombre().equals(usuario) && u.getPassword().equals(password));{
				encontro = true;
			}
				
		});
		return encontro;
	
		
	}

	public List<Usuario> getUsuarios() {
		//   List<Usuario> usuarios =PersistidorDeUsuarios.getInstancia().traerUsuarios();
		 List<Usuario> usuarios = new ArrayList<Usuario>();
		 Usuario ezequiel = new Usuario("Ezequiel", null, "unaPass");
		 Usuario franco = new Usuario("Franco", null, "passfranco");
		 Usuario emiliano = new Usuario("Emiliano", null, "1234");
		 Usuario lucho = new Usuario("lucho", null, "1234");
		 usuarios.add(ezequiel);
		 usuarios.add(franco);
		 usuarios.add(emiliano);
		 usuarios.add(lucho);
		 return usuarios;
	}

	
	


}
