package F5;

import java.nio.charset.UnmappableCharacterException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.INITIALIZE;


public class BusquedasPorFechaTest {
	public BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
	public ArrayList<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<>();
	public Mapa unMapa;
	
	@Before
	public void Initialize(){
		
		unMapa = new Mapa();
		
	}

	@Test
	public void generarReporteDeUnaBusqueda(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		
		Assert.assertEquals(1, reporteroDeBusquedas.generarReporte().size());
		
	}
	
	@Test
	public void generarReporteVacio(){
		Assert.assertEquals(0, reporteroDeBusquedas.generarReporte().size());
	}
	
	@Test
	public void generarReporteDeDosBusquedas(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",listaDeUnReportero);
		unaBusq.buscoFrase("101", unMapa);
		Busqueda segundaBusqueda= new Busqueda("carlos", "recoleta", "101", listaDeUnReportero);
		segundaBusqueda.buscoFrase("101", unMapa);
		
		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
	}
	
}
