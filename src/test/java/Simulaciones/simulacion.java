package Simulaciones;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import F5.CGP;
import F5.Mapa;
import F5.ParadaDeColectivo;
import F5.PuntoDeInteres;
import F5.RepositorioImpostor;
import F5.SGP;
import InterfacesExternas.ConsultorCGP;
import Mocks.MockConsultorBanco;
import TestFactory.BancoFactory;
import TestFactory.LocalComercialFactory;
import TestFactory.PointFactory;

public class simulacion {

	private RepositorioImpostor repositorio = new RepositorioImpostor();
	private SGP sistema = new SGP();
	
	@Test
	public void correrSimulacion() {
		inicializarRepositorio();
		inicializarSistema();
		usarDipositivo();
	}	

	private void inicializarSistema() {
		sistema.agregarConsultorBanco(new MockConsultorBanco());
	}


	private void usarDipositivo() {
		actualizarRepositorio(); // cada vez que se inicia el repositorio traigo todos los CGP
	}


	private void actualizarRepositorio() {
		List<PuntoDeInteres> cpgsNuevas= sistema.dameNuevasCgp();
		for (PuntoDeInteres cgp : cpgsNuevas) {
			repositorio.getMapa().anadirPOI((CGP)cgp);
		}	
	}


	private void inicializarRepositorio() {
		repositorio.getMapa().anadirPOI(BancoFactory.BancoHSBC());
		repositorio.getMapa().anadirPOI(BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18());
		repositorio.getMapa().anadirPOI(BancoFactory.BancoSabadoDe10a13());
		repositorio.getMapa().anadirPOI(LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20());
		repositorio.getMapa().anadirPOI(new ParadaDeColectivo("Medrano", "1022", PointFactory.CercaBancoSantander(), "160"));
		
		Point unaPosicion = new Point(100, 0);
		Point puntoA = new Point(0, 0);
		Point puntoB = new Point(10, 0);
		Point puntoC = new Point(10, 10);
		Point puntoD = new Point(0, 10);
		Polygon unaComuna = new Polygon();
		unaComuna.add(puntoA);
		unaComuna.add(puntoB);
		unaComuna.add(puntoC);
		unaComuna.add(puntoD);
		
		repositorio.getMapa().anadirPOI(new CGP(unaPosicion, unaComuna));
	}
}
