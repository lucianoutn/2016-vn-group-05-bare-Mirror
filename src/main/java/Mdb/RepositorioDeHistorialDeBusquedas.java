package Mdb;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import F5.Busqueda;
import Mdb.DocumentBuilder;

public class RepositorioDeHistorialDeBusquedas {

	private MongoDatabase db;

	public RepositorioDeHistorialDeBusquedas(MongoDatabase db) {
		this.db = db;
	}

	public MongoDatabase getRepositorio() {
		return db;
	}

	public void guardarBusqueda(Busqueda unaBusqueda) {
		db.getCollection("Busquedas").insertOne(conversorBusqueda(unaBusqueda));
	}

	private Document conversorBusqueda(Busqueda unaBusqueda) {
		return new DocumentBuilder().with("Frase buscada", unaBusqueda.getFraseBuscada())
				.with("cantidad resultados", unaBusqueda.getCantResultados()).with("el día", unaBusqueda.getFecha())
				.with("que demoro", unaBusqueda.getTiempoBusqueda())
				.with("realizada por", unaBusqueda.getUsuario().getNombre())
				.with("realizada en", unaBusqueda.getTerminal().getNombreDeTerminal())
				.with("con pois encontrados", unaBusqueda.getPoisEncontrados()).build();

	}
}
