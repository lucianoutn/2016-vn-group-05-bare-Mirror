package F5;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import InterfacesExternas.CentroDTO;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoCGPMock;
import TestFactory.LocalComercialFactory;

public class MapaTest {
	
	private Mapa map;
	private Point laPosicionDelBanco;
	private SucursalDeBanco banco, otroBanco;
	private CGP unCGP,otroCGP,anotherCGP;
	private Point unaPosicion, puntoA, puntoB, puntoC, puntoD;
	private Polygon unaComuna;
	private Servicio unServicio,otroServicio;
	private LocalComercial unLocalComercial;
	private ConsultorCGP consultorCGP;
	private List<CGP> CGPs;
	private CentroDTO unCentroDTO;
	private List<CentroDTO> centrosDTO;
	private ConsultorCGP unConsultorCGP;
	
	@Before
	//private PuntoDeInteres poi;
	public void Initialize(){
	map = new Mapa();
	
	unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
	
	unCentroDTO = new CentroDTO();
	unCentroDTO.setNroComuna(1);
	unCentroDTO.setDomicilioCGP("Corrientes 57");
	
	centrosDTO = new ArrayList<CentroDTO>();
	centrosDTO.add(unCentroDTO);

	}
	
	@Test
	public void unBancoEstaEnElMapa(){
	laPosicionDelBanco = new Point(0, 0);
	SucursalDeBanco banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	map.anadirPOI(banco);
	Assert.assertTrue(map.contiene(banco));
	}
	
	@Test
	public void unCGPEstaEnElMapa(){
	unCGP = new CGP(null, null);
	
	map.anadirPOI(unCGP);
	Assert.assertTrue(map.contiene(unCGP));
	}

	@Test
	public void unBancoEstaEnElMapaConUnCGPyUnBanco(){
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
	}
	
	@Test
	public void unCGPEstaEnElMapaConUnCGPyUnBanco(){
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
	
	Assert.assertTrue(map.contiene(otroCGP)); 
	}

	@Test
	public void noEstaUnBancoEnUnMapaVacio(){
	laPosicionDelBanco = new Point(0, 0);
	SucursalDeBanco banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	
	Assert.assertFalse(map.contiene(banco));
	}
	
	@Test
	public void noEstaUnBancoEnUnMapa(){
	laPosicionDelBanco = new Point(0, 0);
	SucursalDeBanco banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	map.anadirPOI(LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20());
	
	Assert.assertFalse(map.contiene(banco));
	}
	
	@Test
	public void nullNoEstaEnElMapa(){
		Assert.assertFalse(map.contiene(null));
	}

	@Test
	public void buscoUnCGPDentroDelSistemaEnMapaYLoEncuentro(){
		
		laPosicionDelBanco = new Point(10,22);
		banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
		banco.setToleranciaEnCuadras(10);
		map.limpiarPuntosDeInteres();
		map.anadirPOI(banco);
	
		unaPosicion = new Point(100, 0);
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
		unServicio = new Servicio("Inscripcion", new ArrayList<DiaAtencion>());
		otroCGP.anadirServicio(unServicio);
		otroCGP.setCalle("Callao");
		otroCGP.setAltura("1900");
		map.anadirPOI(otroCGP);
		
		unLocalComercial = new LocalComercial("Macowins", "Gaona", "3031", "Ropa", new ArrayList<DiaAtencion>(), unaPosicion);
		map.anadirPOI(unLocalComercial);
		
		unaPosicion = new Point(10, 22);
		puntoA = new Point(0, 0);
		puntoB = new Point(10, 0);
		puntoC = new Point(10, 10);
		puntoD = new Point(0, 10);
		unaComuna = new Polygon();
		unaComuna.add(puntoA);
		unaComuna.add(puntoB);
		unaComuna.add(puntoC);
		unaComuna.add(puntoD);

		Assert.assertTrue(map.buscaPuntosDeInteresEnSistemaySistemasExternos("Comuna 11","Inscripcion").contains(otroCGP));
		
	}
	
	@Test
	public void buscoUnBancoDentroDelSistemaEnMapaYLoEncuentro(){
		
		laPosicionDelBanco = new Point(10,22);
		banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
		banco.setToleranciaEnCuadras(10);
		map.limpiarPuntosDeInteres();
		map.anadirPOI(banco);
		
		unaPosicion = new Point(100, 0);
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
		unServicio = new Servicio("Inscripcion", new ArrayList<DiaAtencion>());
		otroCGP.anadirServicio(unServicio);
		otroCGP.setCalle("Callao");
		otroCGP.setAltura("1900");
		map.anadirPOI(otroCGP);
		
		unLocalComercial = new LocalComercial("Macowins", "Gaona", "3031", "Ropa", new ArrayList<DiaAtencion>(), unaPosicion);
		map.anadirPOI(unLocalComercial);
		
		
		Assert.assertTrue(map.buscaPuntosDeInteresEnSistemaySistemasExternos("Santander","Recoleccion de basura").contains(banco));
		
	}
	
	
	@Test
	public void buscoUnCGPUbicadoEnJuninFueraDelSistemaYLoEncuentro(){
		
		map.limpiarPuntosDeInteres();
			
		Assert.assertTrue(map.buscaPuntosDeInteresEnSistemaySistemasExternos("Junin","ABL").get(0).getAltura().equals("521"));
		//Con este test vemos que la busqueda pega contra el Mock de Sistema Externo que devuelve CGPs, por el assert contra 521
	}
	
	@Test
	public void buscoUnLocalComercialDentroDelSistemaEnMapaYLoEncuentro(){
		
		map.limpiarPuntosDeInteres();
			
		unaPosicion = new Point(100, 0);
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
		unServicio = new Servicio("Inscripcion", new ArrayList<DiaAtencion>());
		otroCGP.anadirServicio(unServicio);
		otroCGP.setCalle("Junin ");
		otroCGP.setAltura("1900");
		map.anadirPOI(otroCGP);
		
		unLocalComercial = new LocalComercial("Macowins", "Gaona", "3031", "Ropa", new ArrayList<DiaAtencion>(), unaPosicion);
		map.anadirPOI(unLocalComercial);
		

		Assert.assertTrue(map.buscaPuntosDeInteresEnSistemaySistemasExternos("Macowins","Vestimenta").contains(unLocalComercial));
		
	}
	
	@Test
	public void buscoUnBancoLlamadoDeLaPlazaFueraDelSistemaYLoEncuentro(){
		
		map.limpiarPuntosDeInteres();
		
		SucursalDeBanco unBanco = (SucursalDeBanco)map.buscaPuntosDeInteresEnSistemaySistemasExternos("Banco de la Plaza","ABL").get(0);
		
		Assert.assertTrue(unBanco.getNombre().equals("Banco de la Plaza"));

	}
}

