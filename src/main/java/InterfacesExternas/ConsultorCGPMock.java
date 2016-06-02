package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import F5.CGP;
import TestFactory.PointFactory;

public class ConsultorCGPMock implements IConsultorCGP {
	
private List<CGP> CGPs = new ArrayList<CGP>();	
	
@Override
public List<CGP> cgpUbicadasEn(String lugar) {
// TODO Auto-generated method stub
	
	return null;
}

public List<CGP> adaptarCGPs(List<CentroDTO> centrosDTO){
	
	centrosDTO.forEach(centroDTO->{CGPs.add(adaptarCentro(centroDTO));});
	
	return CGPs;
}
	
CGP adaptarCentro(CentroDTO unCentro){
	
	CGP unCGP = new CGP(PointFactory.PuntoOrigen(),new Polygon());
	Polygon unaComuna = new Polygon();
	String unaCalle = new String();
	String unaAltura = new String();
	
	unaComuna = adaptarComuna(unCentro);
	unCGP.setComuna(unaComuna);
	
	unaCalle = adaptarCalle(unCentro);
	unCGP.setCalle(unaCalle);
	
	unaAltura = adaptarAltura(unCentro);
	unCGP.setAltura(unaAltura);

	return unCGP;
}

private String adaptarAltura(CentroDTO unCentro) {
	
	String unDomicilio = unCentro.getDomicilioCGP();
	
	String domicilio[] = unDomicilio.split(" ",2);
	
	return domicilio[1];
	
}

private String adaptarCalle(CentroDTO unCentro) {
	
	String unDomicilio = unCentro.getDomicilioCGP();
	
	String domicilio[] = unDomicilio.split(" ", 2);
	
	return domicilio[0];
	
}

private Polygon adaptarComuna(CentroDTO unCentro) {
	//TODO falta el metodo buscarComuna
	int nroDeComuna; 
	
	Polygon unaComuna = new Polygon();
	nroDeComuna = unCentro.getNroComuna();
	unaComuna = buscarComuna(nroDeComuna);
	
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
