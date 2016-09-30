package Reportes;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
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
	private long id_reporte_general;
	
	public long getId(){
		return id_reporte_general;
	}
	
	public void notificarBusqueda(Busqueda unaBusqueda){
		
	}

}
