package Mocks;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;

import F5.CGP;
import InterfacesExternas.IConsultorCGP;
import TestFactory.PointFactory;

public class MockConsutorCGP implements IConsultorCGP {

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



}
