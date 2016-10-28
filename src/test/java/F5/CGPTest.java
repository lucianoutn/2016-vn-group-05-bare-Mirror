package F5;

import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import F5.Pois.CGP;
import F5.Pois.Comuna;
import F5.Pois.DiaAtencion;
import F5.Pois.Dias;
import F5.Pois.LocalComercial;
import F5.Pois.Punto;
import F5.Pois.Servicio;
import Kvs.KvsCache;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.uqbar.geodds.Punto;

public class CGPTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private CGP unCGP;
	private Punto unaPosicion, puntoA, puntoB, puntoC, puntoD;
	private DiaAtencion unDia;
	private Polygon unaComuna;
	private Servicio unServicio;
	private List<DiaAtencion> diasDeAtencion = new ArrayList<>();

	@Before
	public void Initialize() {
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
		unCGP = new CGP(unaPosicion, new Comuna(1, unaComuna));

	}

	
	@After
	public void limpiarKvs(){
		KvsCache.clear();
	}
	@Test
	public void elCGPEstaDisponibleUnLunesALaManiana() {

		unDia = new DiaAtencion(Dias.Lunes, 1000, 2000);
		diasDeAtencion.add(unDia);
		unServicio = new Servicio("rentas", diasDeAtencion);
		unCGP.anadirServicio(unServicio);

		Assert.assertTrue(unCGP.getServicios().stream().anyMatch(s -> s.getNombre().equals("rentas")));
	}

	@Test
	public void elCGPRentasEstaDisponibleUnLunesALaManiana() {

		unDia = new DiaAtencion(Dias.Lunes, 1000, 2000);
		diasDeAtencion.add(unDia);
		unServicio = new Servicio("rentas", diasDeAtencion);
		unCGP.anadirServicio(unServicio);
		Assert.assertTrue(unCGP.estaDisponible(Dias.Lunes, 1500, unServicio));
	}

	@Test
	public void elCPGEncuentraARentas() {

		unServicio = new Servicio("rentas", diasDeAtencion);
		unCGP.anadirServicio(unServicio);
		Assert.assertTrue(unCGP.encuentra("rentas"));
	}

	@Test
	public void persisteCGP() {
		entityManager().persist(unCGP);
		Long idPersistido = unCGP.getId();
		Assert.assertEquals(idPersistido, entityManager().find(CGP.class, unCGP.getId()).getId());
		entityManager().remove(unCGP);
	}

}