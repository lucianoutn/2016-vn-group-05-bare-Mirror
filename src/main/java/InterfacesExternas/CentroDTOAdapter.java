package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import F5.CGP;
import F5.DiaAtencion;
import F5.Dias;
import F5.Servicio;
import TestFactory.PointFactory;

public class CentroDTOAdapter  {
	

public List<CGP> adaptarCGPs(List<CentroDTO> centrosDTO){
	List<CGP> cgps = new ArrayList<CGP>();		
	centrosDTO.stream().forEach(centro-> cgps.add(adaptarCentro(centro)));
	
	return cgps;
}
	
CGP adaptarCentro(CentroDTO unCentro){
	
	
	Point posicionCGP = adaptarPosicion(unCentro.getDomicilioCGP());
	Polygon unaComuna = adaptarComuna(unCentro.getNroComuna());
	String unaCalle = adaptarCalle(unCentro.getDomicilioCGP());
	String unaAltura = adaptarAltura(unCentro.getDomicilioCGP());
	List<Servicio> servicios = adaptarServicios(unCentro.getServiciosDTO());
	
	CGP unCGP = new CGP(posicionCGP, unaComuna);
	unCGP.setCalle(unaCalle);
	unCGP.setComuna(unaComuna);
	unCGP.setAltura(unaAltura);
	unCGP.setServicios(servicios);
	
	return unCGP;
}

private List<Servicio> adaptarServicios(List<ServicioDTO> serviciosDTO) {
	ArrayList<Servicio> servicios = new ArrayList<Servicio>();
	
	serviciosDTO.stream().forEach(servDto-> servicios.add(adaptarServicioDTO(servDto)));
	
	return servicios;
}

private Servicio adaptarServicioDTO(ServicioDTO servDto) {
	
	String nombre = servDto.getNombre();
	
	List<DiaAtencion> diasAtencion = new ArrayList<DiaAtencion>();
	
	servDto.getRangos().stream().limit((servDto.getRangos().size())).forEach(rango-> diasAtencion.add(adaptarRangoDTO(rango)));

	return new Servicio(nombre, diasAtencion);
	
}

private DiaAtencion adaptarRangoDTO(RangoServicioDTO rango) {
	Dias unDia = Dias.values()[rango.getDia()+1];
	int horaApertura = (100 *rango.getHorarioDesde()) + rango.getMinutoDesde();
	int horaCierre = (100 * rango.getHorarioHasta()) + rango.getMinutoHasta();
	return new DiaAtencion(unDia, horaApertura, horaCierre);
	
}

private Point adaptarPosicion(String domicilioCGP) {

	Point ubicacionGeografica = buscarUbicacionGeografica(domicilioCGP);
	return ubicacionGeografica;
}

private Point buscarUbicacionGeografica(String domicilioCGP) {
	//TODO implementar el metodo que busque dado un domicilio la ubicacion en el mapa que tiene. 
	return PointFactory.PuntoOrigen();
}

private String adaptarAltura(String unDomicilio) {
	
	String domicilio[] = unDomicilio.split(" ",2);
	
	return domicilio[1];
	
}

private String adaptarCalle(String unDomicilio) {
	
	String domicilio[] = unDomicilio.split(" ", 2);
	return domicilio[0];
	
}

private Polygon adaptarComuna(int nroComuna) {
	
	Polygon unaComuna = buscarComuna(nroComuna);
	return unaComuna;
}

private Polygon buscarComuna(int nroDeComuna) {
	// TODO Mas adelante, desarrollar el metodo que recorre la lista de comunas y las reconoce por numero de comuna
	Point unPuntoHardcodeado = new Point(0,0);
	Polygon unPolygonHardcodeado = new Polygon();
	
	unPolygonHardcodeado.add(unPuntoHardcodeado);
	
	return unPolygonHardcodeado;
	}


}
