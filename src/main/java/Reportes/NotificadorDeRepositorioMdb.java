package Reportes;

import com.mongodb.client.MongoDatabase;

import F5.Busqueda;
import Mdb.RepositorioDeHistorialDeBusquedas;

public class NotificadorDeRepositorioMdb extends NotificadorDeBusqueda{
	
	RepositorioDeHistorialDeBusquedas unRepo;
	
	public NotificadorDeRepositorioMdb(MongoDatabase db){
		unRepo= new RepositorioDeHistorialDeBusquedas(db);
	}
	
	public void notificarBusqueda(Busqueda unaBusqueda){
		unRepo.guardarBusqueda(unaBusqueda);
	}
	
}
