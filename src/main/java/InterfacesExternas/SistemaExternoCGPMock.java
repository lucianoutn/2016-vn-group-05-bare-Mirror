package InterfacesExternas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import F5.Pois.PuntoDeInteres;

public class SistemaExternoCGPMock extends ISistemaExternoCGP{

	
	private List<CentroDTO> listaDeCentrosDTO = new ArrayList<>();
	@Override
	public List<CentroDTO> consultarCgpsDTO(String lugar) {
		
		List<CentroDTO> listaDeCentrosDTO = new ArrayList<CentroDTO>();
		if(lugar ==null)
			return listaDeCentrosDTO;
		
		
		
		CentroDTO unCentroDTO = new CentroDTO();
		unCentroDTO.setNroComuna(3);
		unCentroDTO.setNombreDirector("Pedro");
		unCentroDTO.setDomicilioCGP("Junin 521");
		unCentroDTO.setTelefono("4375-0644/45");
		
		List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
		
		ServicioDTO unServicioDTO = new ServicioDTO();
		unServicioDTO.setNombre("Atencion Ciudadana");
	
		RangoServicioDTO rangoDeServicios = new RangoServicioDTO();
		rangoDeServicios.setDia(0);
		rangoDeServicios.setHorarioDesde(9);
		rangoDeServicios.setMinutoDesde(00);
		rangoDeServicios.setHorarioHasta(18);
		rangoDeServicios.setMinutoHasta(30);
		
		unServicioDTO.getRangos().add(rangoDeServicios);
		serviciosDTO.add(unServicioDTO);
		unCentroDTO.setServiciosDTO(serviciosDTO);
		listaDeCentrosDTO.add(unCentroDTO);
		
		return listaDeCentrosDTO;
	}
}
