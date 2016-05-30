package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {

	private int nroComuna;
	private List<String> zonas = new ArrayList<String>();
	private String nombreDirector;
	private String domicilioCGP;
	private String telefono;
	private List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();	
	
	public CentroDTO(){}

	public int getNroComuna() {
		return nroComuna;
	}

	public void setNroComuna(int nroComuna) {
		this.nroComuna = nroComuna;
	}

	public String getDomicilioCGP() {
		return domicilioCGP;
	}

	public void setDomicilioCGP(String domicilioCGP) {
		this.domicilioCGP = domicilioCGP;
	}

	public List<ServicioDTO> getServiciosDTO() {
		return serviciosDTO;
	}

	public void setServiciosDTO(List<ServicioDTO> serviciosDTO) {
		this.serviciosDTO = serviciosDTO;
	}
	
	
	 
	
	
	 
	
	

	
	
}
