package Reportes;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import F5.Busqueda;

//@Entity
//@Inheritance(strategy = JOINED)
public abstract class NotificadorDeBusqueda {

	//@Id
	//@GeneratedValue
	private long id_PadreDeLosReportes;
	
	public  void notificarBusqueda(Busqueda unaBusqueda){
		
	}

}
