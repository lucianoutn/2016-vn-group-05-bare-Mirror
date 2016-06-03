package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import F5.CGP;

public class ConsultorCGP implements IConsultorCGP {

	public CentroDTOAdapter cgpAdapter = new CentroDTOAdapter();
	
	public ISistemaExternoCGP sistemaExterno;
	
	public ConsultorCGP(ISistemaExternoCGP sistema){
		sistemaExterno = sistema;
	}
	
	@Override
	public List<CGP> cgpUbicadasEn(String lugar) {
		//aca me comunico con el sistema externo trayendo una lista de CentroDTO. Luego los adapto y devuelvo
		List<CentroDTO> centros = consultarCgps(lugar);
		return adaptarCentros(centros);
		
	}

	private List<CGP> adaptarCentros(List<CentroDTO> centros) {
		return cgpAdapter.adaptarCGPs(centros);
	}

	private List<CentroDTO> consultarCgps(String lugar) {
		return sistemaExterno.consultarCgpsDTO(lugar); 
	}

}
