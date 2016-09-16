package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.Comuna;
import F5.Terminal.Usuario;


public class UsuarioTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Before
	public void Initialize(){
		
	}
	
	@Test 
	public void almacenoUsuarioEnBaseDeDatos(){
		Comuna comuna = new Comuna(1, (Polygon)null);
		entityManager().persist(comuna);
		Usuario ezequiel = new Usuario("Ezequiel", comuna);
		
		entityManager().persist(ezequiel);
		
		List<Usuario> usuariosDb = entityManager()
									.createQuery("from Usuario", Usuario.class)
									.getResultList();
		String ezequielDb = usuariosDb.get(0).getNombre(); 
		Comuna comunaDb = usuariosDb.get(0).getComuna(); 
		Assert.assertEquals(ezequielDb, ezequiel.getNombre());
		Assert.assertEquals(comunaDb.getNroComuna(), comuna.getNroComuna());
		
		//Comuna comuna2 = new Comuna(2, null);
		//entityManager().persist(comuna2);
		
		Usuario franco = new Usuario("Franco", comuna);
		entityManager().persist(franco);
		
		usuariosDb = entityManager()
				.createQuery("from Usuario", Usuario.class)
				.getResultList();
		
		String francoDb = usuariosDb.get(1).getNombre();
		Assert.assertEquals(francoDb, franco.getNombre());
		
			
	}
	
	public void almacenoUsuarioConComuna(){
		Point puntoA = new Point(0, 0);
		Point puntoB = new Point(10, 0);
		Point puntoC = new Point(10, 10);
		Point puntoD = new Point(0, 10);
		List<Point> points = new ArrayList<Point>();
		points.add(puntoA);
		points.add(puntoB);
		points.add(puntoC);
		points.add(puntoD);
		Comuna comuna = new Comuna(1, points);
	
		entityManager().persist(comuna);
		Usuario ezequiel = new Usuario("Ezequiel", comuna);
		
		entityManager().persist(ezequiel);
		
		List<Usuario> usuariosDb = entityManager()
									.createQuery("from Usuario", Usuario.class)
									.getResultList();
		String ezequielDb = usuariosDb.get(0).getNombre(); 
		Comuna comunaDb = usuariosDb.get(0).getComuna(); 
		Assert.assertEquals(ezequielDb, ezequiel.getNombre());
		Assert.assertEquals(comunaDb.getNroComuna(), comuna.getNroComuna());
		Assert.assertEquals(comunaDb.getPuntos().size(), 4);
		
		
			
	}
	
	
}
