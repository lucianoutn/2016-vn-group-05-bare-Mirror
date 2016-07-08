package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import InterfacesExternas.CentroDTO;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.ServicioDTO;
import InterfacesExternas.SistemaExternoCGPMock;
import junit.framework.Assert;

public class ConsultorCGPMockTest {
	private ConsultorCGP consultorCGP;
	private CGP unCGP;
	private List<CGP> CGPs;
	private Polygon unPoligono;
	private CentroDTO unCentroDTO;
	private List<CentroDTO> centrosDTO;
	private Point unPunto;
	private ConsultorCGP unConsultorCGP;
	
	@Before
	public void initialize(){
		unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		
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
		
		CGPs = unConsultorCGP.cgpAdapter.adaptarCGPs(centrosDTO);
		
		unCGP = CGPs.get(0);
		
		Assert.assertEquals("Corrientes",unCGP.getCalle());
	}
	
	@Test
	public void unCGPestaEnLaAltura57(){
		
		CGPs = new ArrayList<CGP>();
		
		CGPs = unConsultorCGP.cgpAdapter.adaptarCGPs(centrosDTO);
		
		unCGP = CGPs.get(0);
		
		Assert.assertEquals("57",unCGP.getAltura());
	}
	
	@Test
	public void buscoEnElMockUnCgpDeLaCalleJunin(){
		
		List<CGP> cgps = unConsultorCGP.cgpUbicadasEn("Junin");
		CGP cgpEncontrada = cgps.get(0);
		
		
		Assert.assertEquals("Junin",cgpEncontrada.getCalle());
	}
	
	@Test
	public void buscoEnElMockUnCgpEnLaAltura521DeLaCalleJunin(){
		
		List<CGP> cgps = unConsultorCGP.cgpUbicadasEn("Junin");
		CGP cgpEncontrada = cgps.get(0);
		
		Assert.assertEquals("521",cgpEncontrada.getAltura());
	}
	
	@Test
	public void buscoEnElMockUnCgpDeLaCalleJuninEstaDisponibleElMartesALas12(){
		
		List<CGP> cgps = unConsultorCGP.cgpUbicadasEn("Junin");
		CGP cgpEncontrada = cgps.get(0);
		
		Assert.assertTrue(cgpEncontrada.estaDisponible(Dias.Martes,1200));
	}
	
	@Test
	public void buscoEnElMockUnCgpDeLaCalleJuninNoEstaDisponibleElLunesALas12(){
		
		List<CGP> cgps = unConsultorCGP.cgpUbicadasEn("Junin");
		CGP cgpEncontrada = cgps.get(0);
		
		Assert.assertFalse(cgpEncontrada.estaDisponible(Dias.Lunes,1200));
	}
}
