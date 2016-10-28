package F5;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.uqbar.geodds.Punto;
import org.uqbar.geodds.Polygon;

import F5.Pois.CGP;
import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.LocalComercial;
import F5.Pois.Punto;
import F5.Pois.Servicio;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import InterfacesExternas.CentroDTO;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;
import TestFactory.LocalComercialFactory;

public class MapaTest {
	
	private RepositorioDePOIs map;
	private Punto laPosicionDelBanco;
	private SucursalDeBanco banco, otroBanco;
	private CGP unCGP,otroCGP,anotherCGP;
	private Punto unaPosicion, puntoA, puntoB, puntoC, puntoD;
	private Polygon unaComuna;
	private Servicio unServicio,otroServicio;
	private LocalComercial unLocalComercial;
	
	private List<CGP> CGPs;
	private CentroDTO unCentroDTO;
	private List<CentroDTO> centrosDTO;
	
	
	@Before
	//private PuntoDeInteres poi;
	public void Initialize(){
	ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("005"));
	ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("006"));
	
	map = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
	unCentroDTO = new CentroDTO();
	unCentroDTO.setNroComuna(1);
	unCentroDTO.setDomicilioCGP("Corrientes 57");
	
	centrosDTO = new ArrayList<CentroDTO>();
	centrosDTO.add(unCentroDTO);

	}
	
	@Test
	public void unBancoEstaEnElMapa(){
	laPosicionDelBanco = new Punto(0, 0);
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
	unaPosicion = new Punto(100, 0);
	// creo un poligono de 4 lados simulando la comuna
	puntoA = new Punto(0, 0);
	puntoB = new Punto(10, 0);
	puntoC = new Punto(10, 10);
	puntoD = new Punto(0, 10);
	unaComuna = new Polygon();
	unaComuna.add(puntoA.getPoint());
	unaComuna.add(puntoB.getPoint());
	unaComuna.add(puntoC.getPoint());
	unaComuna.add(puntoD.getPoint());
	otroCGP = new CGP(unaPosicion, new Comuna(1,unaComuna));
	
	laPosicionDelBanco = new Punto(0, 0);
	SucursalDeBanco otroBanco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	
	map.anadirPOI(otroBanco);
	map.anadirPOI(otroCGP);
	
	Assert.assertTrue(map.contiene(otroBanco));
	}
	
	@Test
	public void unCGPEstaEnElMapaConUnCGPyUnBanco(){
	unaPosicion = new Punto(100, 0);
	// creo un poligono de 4 lados simulando la comuna
	puntoA = new Punto(0, 0);
	puntoB = new Punto(10, 0);
	puntoC = new Punto(10, 10);
	puntoD = new Punto(0, 10);
	unaComuna = new Polygon();
	unaComuna.add(puntoA.getPoint());
	unaComuna.add(puntoB.getPoint());
	unaComuna.add(puntoC.getPoint());
	unaComuna.add(puntoD.getPoint());
	otroCGP = new CGP(unaPosicion, new Comuna(1,unaComuna));
	
	laPosicionDelBanco = new Punto(0, 0);
	SucursalDeBanco otroBanco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	
	map.anadirPOI(otroBanco);
	map.anadirPOI(otroCGP);
	
	Assert.assertTrue(map.contiene(otroCGP)); 
	}

	@Test
	public void noEstaUnBancoEnUnMapaVacio(){
	laPosicionDelBanco = new Punto(0, 0);
	SucursalDeBanco banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
	
	Assert.assertFalse(map.contiene(banco));
	}
	
	@Test
	public void noEstaUnBancoEnUnMapa(){
	laPosicionDelBanco = new Punto(0, 0);
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
		
		laPosicionDelBanco = new Punto(10,22);
		banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
		banco.setToleranciaEnCuadras(10);
		map.limpiarPuntosDeInteres();
		map.anadirPOI(banco);
	
		unaPosicion = new Punto(100, 0);
		puntoA = new Punto(0, 0);
		puntoB = new Punto(10, 0);
		puntoC = new Punto(10, 10);
		puntoD = new Punto(0, 10);
		unaComuna = new Polygon();
		unaComuna.add(puntoA.getPoint());
		unaComuna.add(puntoB.getPoint());
		unaComuna.add(puntoC.getPoint());
		unaComuna.add(puntoD.getPoint());
		otroCGP = new CGP(unaPosicion, new Comuna(1,unaComuna));
		unServicio = new Servicio("Inscripcion", new ArrayList<DiaAtencion>());
		otroCGP.anadirServicio(unServicio);
		otroCGP.setCalle("Callao");
		otroCGP.setAltura("1900");
		map.anadirPOI(otroCGP);
		
		unLocalComercial = new LocalComercial("Macowins", "Gaona", "3031", "Ropa", new ArrayList<DiaAtencion>(), unaPosicion);
		map.anadirPOI(unLocalComercial);
		
		unaPosicion = new Punto(10, 22);
		puntoA = new Punto(0, 0);
		puntoB = new Punto(10, 0);
		puntoC = new Punto(10, 10);
		puntoD = new Punto(0, 10);
		unaComuna = new Polygon();
		unaComuna.add(puntoA.getPoint());
		unaComuna.add(puntoB.getPoint());
		unaComuna.add(puntoC.getPoint());
		unaComuna.add(puntoD.getPoint());

		Assert.assertTrue(map.buscaPuntosDeInteresEnSistemaySistemasExternos("Comuna 11","Inscripcion").contains(otroCGP));
		
	}
	
	@Test
	public void buscoUnBancoDentroDelSistemaEnMapaYLoEncuentro(){
		
		laPosicionDelBanco = new Punto(10,22);
		banco = new SucursalDeBanco("Santander", laPosicionDelBanco, new ArrayList<DiaAtencion>());
		banco.setToleranciaEnCuadras(10);
		map.limpiarPuntosDeInteres();
		map.anadirPOI(banco);
		
		unaPosicion = new Punto(100, 0);
		puntoA = new Punto(0, 0);
		puntoB = new Punto(10, 0);
		puntoC = new Punto(10, 10);
		puntoD = new Punto(0, 10);
		unaComuna = new Polygon();
		unaComuna.add(puntoA.getPoint());
		unaComuna.add(puntoB.getPoint());
		unaComuna.add(puntoC.getPoint());
		unaComuna.add(puntoD.getPoint());
		otroCGP = new CGP(unaPosicion, new Comuna(1,unaComuna));
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
			
		unaPosicion = new Punto(100, 0);
		puntoA = new Punto(0, 0);
		puntoB = new Punto(10, 0);
		puntoC = new Punto(10, 10);
		puntoD = new Punto(0, 10);
		unaComuna = new Polygon();
		unaComuna.add(puntoA.getPoint());
		unaComuna.add(puntoB.getPoint());
		unaComuna.add(puntoC.getPoint());
		unaComuna.add(puntoD.getPoint());
		otroCGP = new CGP(unaPosicion, new Comuna(1,unaComuna));
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

