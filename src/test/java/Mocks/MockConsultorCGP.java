package Mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import F5.CGP;
import F5.Servicio;
import F5.SucursalDeBanco;
import InterfacesExternas.CentroDTO;
import InterfacesExternas.IConsultorCGP;
import InterfacesExternas.ServicioDTO;
import TestFactory.PointFactory;

public class MockConsultorCGP implements IConsultorCGP {

	@Override
	public List<CGP> cgpUbicadasEn(String lugar) {
		List<CGP> cgps = new ArrayList<CGP>();
		
		CGP unCgp = new CGP(PointFactory.PuntoMuyGrande(), new Polygon());
		unCgp.setCalle(lugar);
		cgps.add(unCgp);
		
		CGP otroCgp = new CGP(PointFactory.LejosBancoSantander(), new Polygon());
		unCgp.setCalle(lugar);
		cgps.add(otroCgp);
		
		return cgps;
	}

	private List<CGP> adaptarCentro(CentroDTO unCentro) {
		List<CGP> cgps = new ArrayList<CGP>();
		List<Servicio> servicios = new ArrayList<Servicio>();
		
		//Creo la instancia de CGP que voy a completar con los datos que vienen del sistema externo
		//y agregar a la lista de CGPs
		CGP unCGP = new CGP(PointFactory.PuntoOrigen(),new Polygon());
		
		//Mapeo la Comuna. Actualmente Hardcodeado. Ver el metodo para mas detalle
		mapearComuna(unCentro,unCGP);
		
		//En este caso, di por hecho que los domicilios son del tipo LETRAS--NUMEROS,
		//donde las letras son el nombre de la calle y los numeros la altura
		adaptaCalle(unCentro,unCGP);
		
		adaptaAltura(unCentro,unCGP);
		
		adaptaServicioDTOaServicioCGP(unCentro.getServiciosDTO(),unCGP);
		
		/*
		Lista de “serviciosDTO”: array de servicios que contiene 
		string nombre del servicio (ej: Atención ciudadana)
		lista de “rangos servicio DTO”: Array con días de servicio que contiene 
		int: número de día de la semana (ej: 1 = Lunes, 2 = Martes, etc.)
		int: horario desde (9)
		int: minutos desde (0)
		int: horario hasta (18)
		int: minutos hasta (0)
		*/

		
		return cgps;
						
		
	}

	private void adaptaAltura(CentroDTO unCentro, CGP unCGP) {
		unCGP.setAltura(this.obtenerAlturaDeDomicilio(unCentro.getDomicilioCGP()));
	}

	private void adaptaCalle(CentroDTO unCentro, CGP unCGP) {
		unCGP.setCalle(this.obtenerCalleDeDomicilio(unCentro.getDomicilioCGP()));
	}

	private void mapearComuna(CentroDTO unCentro, CGP unCGP) {
		unCGP.setComuna(this.buscarComuna(unCentro.getNroComuna()));
	}

	private void adaptaServicioDTOaServicioCGP(List<ServicioDTO> serviciosDTO, CGP unCGP) {
		serviciosDTO.stream().
			map(unServicioDTO->adaptaAServicioCGP(unServicioDTO));
	}

	private Servicio adaptaAServicioCGP(ServicioDTO unServicioDTO) {
		Servicio unServicioCGP = null;
		
		String nombreServicioDTO = obtieneNombre(unServicioDTO);
		//int horaDeApertura = unServicioDTO.obtieneHoraDeApertura;
		//SEGUIR DESDE ACA
		return unServicioCGP;
	}

	private String obtieneNombre(ServicioDTO unServicioDTO) {
		return unServicioDTO.getNombre();
	}

	private Polygon buscarComuna(int nroComuna) {
		// TODO Mas adelante, desarrollar el metodo que recorre la lista de comunas y las reconoce por numero de comuna
		Point unPuntoHardcodeado = new Point(0,0);
		Polygon unPolygonHardcodeado = new Polygon();
		unPolygonHardcodeado.add(unPuntoHardcodeado);
		return unPolygonHardcodeado;
	}
	
	private String obtenerCalleDeDomicilio(String domicilioCGP) {
		String unDomicilio[] = domicilioCGP.split("\\d");
		return unDomicilio[0];	
	}
	
	private String obtenerAlturaDeDomicilio(String domicilioCGP) {
		String unDomicilio[] = domicilioCGP.split("\\d");
		return unDomicilio[1];	
	}

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		// TODO Auto-generated method stub
		return null;
	}

}
