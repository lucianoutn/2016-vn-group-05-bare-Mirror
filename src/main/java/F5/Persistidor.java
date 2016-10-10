package F5;



import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.PuntoDeInteres;

public class Persistidor extends AbstractPersistenceTest implements WithGlobalEntityManager{

	public void persistirUnElemento(Object unObjeto){
			entityManager().persist(unObjeto);
	}
	
	
}
