package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import F5.CGP;

public class ConsultorCGP implements IConsultorCGP {

	@Override
	public List<CGP> cgpUbicadasEn(String lugar) {
		//TODO aca me comunico con el sistema externo y devuelvo una lista de CentroDTO 
		List<CentroDTO> centros = consultarCgps(lugar);
		return adaptarCentros(centros);
		
	}

	private List<CGP> adaptarCentros(List<CentroDTO> centros) {
		// TODO aca adapto todos los centros al modelo de mi sistema
		return null;
	}

	private List<CentroDTO> consultarCgps(String lugar) {
		// TODO aca me conecto con el otro sistema y devuelvo un ARRAY LIST de CentroDTO
		return null;
	}

}
