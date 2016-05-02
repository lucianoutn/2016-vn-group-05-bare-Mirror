package F5;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class MapaTest {
	
	private Mapa map;
	private Point laPosicionDelBanco;
	private SucursalDeBanco banco, otroBanco;
	
	private CGP unCGP,otroCGP;
	private Point unaPosicion, puntoA, puntoB, puntoC, puntoD;
	private Polygon unaComuna;
	
	@Before
	//private PuntoDeInteres poi;
	public void Initialize(){
	map = new Mapa();
	
	}
	
	@Test
	public void unBancoEstaEnLaListaDePOIDelMapa(){
	laPosicionDelBanco = new Point(0, 0);
	SucursalDeBanco banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	map.anadirPOI(banco);
	Assert.assertTrue(map.contiene(banco));
	}
	
	@Test
	public void unCGPEstaEnLaListaDePOIDelMapa(){
	unaPosicion = new Point(100, 0);
	// creo un poligono de 4 lados simulando la comuna
	puntoA = new Point(0, 0);
	puntoB = new Point(10, 0);
	puntoC = new Point(10, 10);
	puntoD = new Point(0, 10);
	unaComuna = new Polygon();
	unaComuna.add(puntoA);
	unaComuna.add(puntoB);
	unaComuna.add(puntoC);
	unaComuna.add(puntoD);
	unCGP = new CGP(unaPosicion, unaComuna);
	
	map.anadirPOI(unCGP);
	Assert.assertTrue(map.contiene(unCGP));
	}

	@Test
	public void unBancoYUnCGPEstanEnLaListaDePOIDelMapa(){
	unaPosicion = new Point(100, 0);
	// creo un poligono de 4 lados simulando la comuna
	puntoA = new Point(0, 0);
	puntoB = new Point(10, 0);
	puntoC = new Point(10, 10);
	puntoD = new Point(0, 10);
	unaComuna = new Polygon();
	unaComuna.add(puntoA);
	unaComuna.add(puntoB);
	unaComuna.add(puntoC);
	unaComuna.add(puntoD);
	otroCGP = new CGP(unaPosicion, unaComuna);
	
	laPosicionDelBanco = new Point(0, 0);
	SucursalDeBanco otroBanco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	
	map.anadirPOI(otroBanco);
	map.anadirPOI(otroCGP);
	
	Assert.assertTrue(map.contiene(otroBanco));
	Assert.assertTrue(map.contiene(otroCGP)); 
	}
}

