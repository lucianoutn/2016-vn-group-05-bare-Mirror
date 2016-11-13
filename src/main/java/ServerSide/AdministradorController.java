package ServerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Pois.CGP;
import F5.Pois.DiaAtencion;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.Punto;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.Terminal;
import TestFactoryF5.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public ModelAndView administradorPoisShow(Request req, Response res) {
		if (Logueado.usuario == null) {
			res.redirect("http://localhost:9000/user/login");
			return null;
		}
		List<PuntoDeInteres> pois = getPois();
		List<Terminal> terminales = getTerminales();
		String usuarioLogueado = Logueado.usuario.getNombre();

		Map<String, Object> model = new HashMap<>();

		model.put("todosLosPois", pois);
		model.put("terminales", terminales);
		model.put("usuario", usuarioLogueado);

		return new ModelAndView(model, "administrador-pois-show.hbs");
	}

	private void eliminarPoi(long id) {

		Logueado.terminal.getUnMapa().eliminarPOIporID(id);

	}
	
	private void editarPoi(long id, String nombre, String calle, Punto ubicacion){
		Logueado.terminal.getUnMapa().getPuntosDeInteres()
				.stream().filter(poi-> poi.getId().equals(id))
				.forEach( p-> p.editarPoi(nombre,calle,ubicacion));
	}

	private List<Terminal> getTerminales() {
		return entityManager().createQuery("from Terminal", Terminal.class).getResultList();

	}

	private List<PuntoDeInteres> getPois() {
		return Logueado.terminal.getUnMapa().getPOIs();

	}
}
