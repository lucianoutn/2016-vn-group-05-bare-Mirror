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
	
	public ModelAndView login(Request req, Response res) throws Exception{
		
		
		List<Terminal> terminales = entityManager()
									.createQuery("from Terminal", Terminal.class)
									.getResultList();
			 	
		
		
		Map<String, List<Terminal>> model = new HashMap<>();
		model.put("terminal", terminales);
		
		
		
		return new ModelAndView(model, "user-login.hbs");
	}
	
	public ModelAndView checkLogin(Request req, Response res) throws Exception{
			
	
		List<Usuario> usuarios = getUsuarios(); //esto deberia ser un find pero no esta funcionando
		if (estaLogueadoCorrectamente(usuarios, req)){
			UsuarioLogueado.usuario = usuarios.get(0);
			res.redirect("http://localhost:9000/terminal/show");
		    return null;
		}
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

	public boolean estaLogueadoCorrectamente(List<Usuario> usuarios, Request req) {
		
		return true;
		//NO ESTA LLEGANDO LOS QUERYPARAMS
		/*usuarios.forEach(u-> {
		 
			if (u.getNombre() == req.params("name"))
				usuario = u;
		});
		return usuario != null &&  usuario.getPassword() == req.params("pass"); */ 
		
	}

	public List<Usuario> getUsuarios() {
		//   List<Usuario> usuarios =PersistidorDeUsuarios.getInstancia().traerUsuarios();
		 List<Usuario> usuarios = new ArrayList<Usuario>();
		 Usuario ezequiel = new Usuario("Ezequiel", null, "unaPass");
		 Usuario franco = new Usuario("Franco", null, "passfranco");
		 usuarios.add(ezequiel);
		 usuarios.add(franco);
		 return usuarios;
	}

	
	


}