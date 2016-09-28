package Reportes;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import F5.Busqueda;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class NotificadorDeBusqueda {

	@Id
	@GeneratedValue
	private long id_PadreDeLosReportes;
	
	public long getId(){
		return id_PadreDeLosReportes;
	}
	
	public  void notificarBusqueda(Busqueda unaBusqueda){
		
	}

}
