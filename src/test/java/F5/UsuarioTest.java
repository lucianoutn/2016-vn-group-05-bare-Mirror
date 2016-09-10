package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
		Comuna comuna = new Comuna(1, null);
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
	
	
}
