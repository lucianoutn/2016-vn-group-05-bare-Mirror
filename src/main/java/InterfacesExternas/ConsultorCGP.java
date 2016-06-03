package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import F5.CGP;

public class ConsultorCGP implements IConsultorCGP {

	public CentroDTOAdapter cgpAdapter = new CentroDTOAdapter();
	
	
	@Override
	public List<CGP> cgpUbicadasEn(String lugar) {
		//aca me comunico con el sistema externo y devuelvo una lista de CentroDTO 
		List<CentroDTO> centros = consultarCgps(lugar);
		return adaptarCentros(centros);
		
	}

	private List<CGP> adaptarCentros(List<CentroDTO> centros) {
		// aca adapto todos los centros al modelo de mi sistema
		return null;
	}

	private List<CentroDTO> consultarCgps(String lugar) {
		// aca me conecto con el otro sistema y devuelvo un ARRAY LIST de CentroDTO
		return null;
	}

}
