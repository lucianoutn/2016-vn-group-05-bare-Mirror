package F5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Polygon;

import F5.Persistencia.PersistidorDePOIs;
import F5.Persistencia.PersistidorDeReportes;
import F5.Persistencia.PersistidorDeTerminal;
import F5.Persistencia.PersistidorDeUsuarios;
import F5.Pois.Comuna;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import Reportes.ReportePorTerminal;
import Reportes.ResultadosPorTerminal;

public class PersistidoresTest {
	
	@Before
	public void initialize(){
		
		PersistidorDeReportes.getInstancia().clear();
		PersistidorDePOIs.getInstancia().clear();
		PersistidorDeTerminal.getInstancia().clear();
		PersistidorDeUsuarios.getInstancia().clear();
		
	}
	
	@Test
	public void persistoUnReporte(){
		ResultadosPorTerminal unReporte= new ResultadosPorTerminal();
		
		unReporte.notificarBusqueda(new Busqueda(new Terminal("flores",
				new RepositorioDePOIs(null, null)),new Usuario("pepe", new Comuna(0, new Polygon())),
				"nada"));
		
		PersistidorDeReportes.getInstancia().persistir();

		//hay que revisar, el persisitidor no persiste.
		Assert.assertTrue(PersistidorDeReportes.getInstancia().traerNotificadores().size() == 1);
	}

}
