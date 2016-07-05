package F5;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BusquedasPorFechaTest {
	public BusquedasPorFecha reporteroDeBusquedas= new BusquedasPorFecha();
	public ArrayList<NotificadorDeBusqueda> listaDeUnReportero = new ArrayList<>();

	@Test
	public void generarReporteDeUnaBusqueda(){
		listaDeUnReportero.add(reporteroDeBusquedas);
		Busqueda unaBusq = new Busqueda("pedro","flores", "101",listaDeUnReportero);
		
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
		Busqueda segundaBusqueda= new Busqueda("carlos", "recoleta", "101", listaDeUnReportero);
		
		Assert.assertEquals(2, reporteroDeBusquedas.generarReporte().size());
	}
	
}
