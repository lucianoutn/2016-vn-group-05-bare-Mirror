package F5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RepositorioImpostorTest {
	
	private Busqueda busqueda = new Busqueda();
	
	@Before
	public void Initialize() {
		
	RepositorioImpostor.limpiarLista();//sin esto, queda basura de la busqueda anterior (En realidad no es basura)	
	}
	
	
	@Test
	public void listaVaciaTest(){
		Assert.assertEquals(0, RepositorioImpostor.getBusquedas().size());
	}
	
	@Test
	public void listaConUnaBusqueda(){
		busqueda.unaBusqueda("Emiliano", "flores", "algo");
		Assert.assertEquals(1,RepositorioImpostor.getBusquedas().size());
	}
	
	@Test
	public void tresBusquedas(){
		busqueda.unaBusqueda("Emiliano", "flores", "algo");
		busqueda.unaBusqueda("Lucas", "floresta", "cgp");
		busqueda.unaBusqueda("Richard", "Belgrano", "76");
		Assert.assertEquals(3,RepositorioImpostor.getBusquedas().size());
	}
}
