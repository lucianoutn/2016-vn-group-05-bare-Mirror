package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {

	private String nombre;
	private List<RangoServicioDTO> rangos = new ArrayList<RangoServicioDTO>();
	
	public List<RangoServicioDTO> getRangos() {
		return rangos;
	}

	public ServicioDTO(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
