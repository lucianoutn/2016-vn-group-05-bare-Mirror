package ServerSide;

import java.util.ArrayList;

import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import F5.Pois.Comuna;
import F5.Pois.ParadaDeColectivo;
import F5.Pois.PuntoDeInteres;
import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init(){
		withTransaction(() ->{
			SucursalDeBanco hsbc = new SucursalDeBanco("HSBC", null, null);
			persist(hsbc);
			persist(new SucursalDeBanco("galicia", null, null));
			persist(new SucursalDeBanco("frances", null, null));
			//persist(new ParadaDeColectivo("Mozart", "2500", null, "101"));
			
			Comuna comuna = new Comuna(1, (Polygon)null);
			persist(comuna);
			Usuario ezequiel = new Usuario("Ezequiel", comuna);
			persist(ezequiel);
			Usuario franco = new Usuario("Franco", comuna);
			persist(franco);
			
			ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("009"));
			ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("010"));

			RepositorioDePOIs unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
			unMapa.anadirPOI(hsbc);
			Terminal unaTerminal = new Terminal("Caballito", unMapa);
			
			persist(unaTerminal);
			
		});
	}
}