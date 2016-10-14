package F5.Persistencia;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.PuntoDeInteres;


//Dejo este lucho como ejemplo. Habría que volarlo a este cuando tengamos los demás.

public class Persistidor extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private final static Persistidor instancia = new Persistidor();

	
	public static Persistidor getInstancia(){
		
		return instancia;
	}
	
	public void persistirUnElemento(Object unObjeto) {
		entityManager().persist(unObjeto);
	}

}
