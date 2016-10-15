package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import F5.Pois.CGP;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;

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
	
	public List<PuntoDeInteres> buscaPuntosDeInteresENCGP(String lugar) {
		List<CentroDTO> centrosDTO = consultarCgps(lugar);
		List<CGP> cgps = adaptarCentros(centrosDTO);
		return cgps.stream().filter(unCGP->unCGP.encuentra(lugar)).collect(Collectors.toList());
	}

	private List<CGP> adaptarCentros(List<CentroDTO> centros) {
		return cgpAdapter.adaptarCGPs(centros);
	}

	private List<CentroDTO> consultarCgps(String lugar) {
		return sistemaExterno.consultar(lugar); 
	}




}
