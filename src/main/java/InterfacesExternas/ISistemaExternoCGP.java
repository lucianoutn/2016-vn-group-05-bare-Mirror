package InterfacesExternas;

import java.util.List;

public interface ISistemaExternoCGP {

	public SistemaExternoCGPMock sistema = new SistemaExternoCGPMock();
	
	public List<CentroDTO> consultarCgpsDTO(String lugar);
}
