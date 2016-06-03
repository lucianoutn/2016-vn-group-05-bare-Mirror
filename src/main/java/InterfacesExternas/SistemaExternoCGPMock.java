package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

public class SistemaExternoCGPMock implements ISistemaExternoCGP{

	@Override
	public List<CentroDTO> consultarCgpsDTO(String lugar) {
		
		List<CentroDTO> listaDeCentrosDTO = new ArrayList<CentroDTO>();
		
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


/*
int: número de la comuna (ej: 3)
string: zonas que incluye (ej. “Balvanera, San Cristóbal” para la comuna 3)
string: nombre del director 
string: domicilio completo del CGP (ej: Junín 521)
string: teléfono del CGP (4375-0644/45)
lista de “serviciosDTO”: array de servicios que contiene 
string nombre del servicio (ej: Atención ciudadana)
lista de “rangos servicio DTO”: Array con días de servicio que contiene 
int: número de día de la semana (ej: 1 = Lunes, 2 = Martes, etc.)
int: horario desde (9)
int: minutos desde (0)
int: horario hasta (18)
int: minutos hasta (0)

*/