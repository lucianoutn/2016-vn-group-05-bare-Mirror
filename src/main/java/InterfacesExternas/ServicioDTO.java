package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {

	private String nombre;
	private List<RangoServicioDTO> servicios = new ArrayList<RangoServicioDTO>();
	
	public ServicioDTO(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
