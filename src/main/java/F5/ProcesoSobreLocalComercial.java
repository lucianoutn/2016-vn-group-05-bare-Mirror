package F5;

import java.time.LocalDate;

import F5.ProcesoSobrePOIS;
//TODO si me dan una mano con manejo de archivos en java, me encargo de este. Emiliano
public class ProcesoSobreLocalComercial extends ProcesoSobrePOIS{
	
	private LocalDate fecha;
	
	public ProcesoSobreLocalComercial(LocalDate horarioDeEjecucion){//falta el archivo
		fecha=horarioDeEjecucion;
	}
	
	public void ejecutar(){
		
		this.actualizarLocalComercial();
	}
	
	
	public void actualizarLocalComercial(){//recibe un txt
		// TODO a partir de un txt que recibe con el formato "NombreDeLocalComercial;AtributosNuevosQueMatcheanConBusqueda los 
		//atributos separados por espacios, le agrega a el/los locales existentes que matcheen con el nombre esa lista de atributos
		//para que matchee cuando se los busca con un poi. Si el nombre del local no está entre los nombres conocidos de locales, no
		//hace nada. Elimina las palabras claves que no pertenezcan a la lista y ya estén en el poi
	}

}
