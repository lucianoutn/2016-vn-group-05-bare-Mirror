package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import InterfacesExternas.CentroDTO;
import InterfacesExternas.ConsultorCGPMock;
import InterfacesExternas.ServicioDTO;
import junit.framework.Assert;

public class ConsultorCGPMockTest {
	private ConsultorCGPMock consultorCGP;
	private CGP unCGP;
	private List<CGP> CGPs;
	private Polygon unPoligono;
	private CentroDTO unCentroDTO;
	private List<CentroDTO> centrosDTO;
	private Point unPunto;
	private ConsultorCGPMock unConsultorCGP;
	
	@Before
	public void initialize(){
		unConsultorCGP = new ConsultorCGPMock();
		
		unCentroDTO = new CentroDTO();
		unCentroDTO.setNroComuna(1);
		unCentroDTO.setDomicilioCGP("Corrientes 57");
		
		centrosDTO = new ArrayList<CentroDTO>();
		centrosDTO.add(unCentroDTO);
			
		unPunto = new Point(1,1);
		
	}
		
	@Test
	public void unCGPestaEnLaCalleCorrientes(){
		
		CGPs = new ArrayList<CGP>();
		
		CGPs = unConsultorCGP.adaptarCGPs(centrosDTO);
		
		unCGP = CGPs.get(0);
		
		Assert.assertEquals("Corrientes",unCGP.getCalle());
	}
	
	@Test
	public void unCGPestaEnLaAltura57(){
		
		CGPs = new ArrayList<CGP>();
		
		CGPs = unConsultorCGP.adaptarCGPs(centrosDTO);
		
		unCGP = CGPs.get(0);
		
		Assert.assertEquals("57",unCGP.getAltura());
	}
	
	
	
	
	
}
