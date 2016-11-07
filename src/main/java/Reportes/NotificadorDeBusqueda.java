package Reportes;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import F5.Busqueda;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class NotificadorDeBusqueda {
	
	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	private long id;
	
	public long getId() {
		return id;
	}

	public void notificarBusqueda(Busqueda unaBusqueda){
		
	}

}
