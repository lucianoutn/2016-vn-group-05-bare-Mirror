package F5;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.uqbar.geodds.Polygon;

import F5.Persistencia.PersistidorDeBusqueda;
import F5.Persistencia.PersistidorDePOIs;
import F5.Pois.CGP;
import F5.Pois.Comuna;
import F5.Pois.Punto;
import F5.Pois.PuntoDeInteres;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;

public class PersistidoresTest {

	@Test
	public void prueboPersistidorDeUnaBusqueda(){
		Busqueda unaBusqueda = new Busqueda(new Terminal("terminal random",new RepositorioDePOIs(null, null)),
								new Usuario("pepe",new Comuna(2,new Polygon())),"hola");
		PersistidorDeBusqueda.getInstancia().persistir(unaBusqueda);
		List<Busqueda> busquedas = PersistidorDeBusqueda.getInstancia().traerBusquedas();
		Assert.assertTrue(busquedas.stream().anyMatch(busq->busq.getId_Busqueda() == unaBusqueda.getId_Busqueda()));
	}
	
	@Test
	public void prueboPersistidorDeUnPOI(){
		 CGP unCgp= new CGP(new Punto(5, 5),new Comuna(2,new Polygon()));
		PersistidorDePOIs.getInstancia().persistir(unCgp);
		List<PuntoDeInteres> POIS = PersistidorDePOIs.getInstancia().traerPOIS();
		Assert.assertTrue(POIS.stream().anyMatch(cgps->cgps.getId() == unCgp.getId()));
	}
	
	
}
