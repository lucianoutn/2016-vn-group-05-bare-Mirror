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
		Usuario usuario = new Usuario("Ezequiel", new Comuna(0, null));
		
		entityManager().persist(usuario);
		
		List<Usuario> usuariosDb = entityManager()
									.createQuery("from Usuario", Usuario.class)
									.getResultList();
		String nombreDb = usuariosDb.get(0).getNombre(); 
		Assert.assertEquals(nombreDb, usuario.getNombre());
				
				//List<Jugador> jugadores = entityManager().
			     // createQuery("from Jugador", Jugador.class).
			     // getResultList();
		
	}
	
	
}
