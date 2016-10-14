package F5;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class Persistidor extends AbstractPersistenceTest implements WithGlobalEntityManager{

	public void persistirUnElemento(Object unObjeto){
			entityManager().persist(unObjeto);
	}
	
	
}
