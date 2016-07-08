package F5;


import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.uqbar.geodds.Point;
import org.junit.Test;
import org.junit.Assert;
import org.uqbar.geodds.Polygon;



public class TerminalTest {

	private SucursalDeBanco unBanco;
	private LocalComercial unLocalComercial;
	private Terminal unaTerminal;
	private Mapa unMapa;
	private Point posicionDelBanco, posicionDelLocalComercial, posicionDelCGP;
	private Polygon comunaDelCGP;
	private CGP unCGP;
	Point puntoADeLaComuna,puntoBDeLaComuna;
	
	
	@Before
	public void initialize(){
		
		unMapa = new Mapa();
		posicionDelBanco = new Point(10, 22);
		unBanco = new SucursalDeBanco("Santander", posicionDelBanco, new ArrayList<DiaAtencion>());
		posicionDelLocalComercial = new Point(22, 10);
		unLocalComercial = new LocalComercial("Shopping", "Honduras", "10", "Ropa", new ArrayList<DiaAtencion>(), posicionDelLocalComercial);
		unaTerminal = new Terminal("Caballito", unMapa);
		posicionDelCGP = new Point(40,53);
		puntoADeLaComuna = new Point(1,1);
		puntoBDeLaComuna = new Point(2,2);
		unCGP = new CGP(posicionDelCGP, comunaDelCGP);
		
	}
	
	
	@Test
	public void seQuitaCorrectamenteUnLocalComercialDelMapaDeLaTerminal(){
  
		unMapa.anadirPOI(unBanco);
		unMapa.anadirPOI(unLocalComercial);
				
		unaTerminal.eliminarPOI(unBanco);
		Assert.assertFalse(unaTerminal.getUnMapa().contiene(unBanco));
	}
	
	@Test
	public void agregoUnCGPALaTerminal(){
  
		unMapa.anadirPOI(unBanco);
		unMapa.anadirPOI(unLocalComercial);
						
		unaTerminal.aniadirPoi(unCGP);
		
		Assert.assertTrue(unaTerminal.getUnMapa().contiene(unCGP));
	}
	
	@Test
	public void cambioLaAlturaDeUnLocalComercial(){
		unaTerminal.aniadirPoi(unBanco);
		unaTerminal.aniadirPoi(unLocalComercial);
		unaTerminal.aniadirPoi(unCGP);
		
		LocalComercial localComercialModificado = new LocalComercial("Shopping", "Honduras", "50", "Ropa", new ArrayList<DiaAtencion>(), posicionDelLocalComercial);
		
		unaTerminal.modificarPOI(unLocalComercial, localComercialModificado);
		
		Assert.assertTrue(unaTerminal.getUnMapa().getPOIs().get(2).getAltura().equals("50"));
	}
	
	
	
}
