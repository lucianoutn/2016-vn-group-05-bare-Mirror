package F5;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import F5.Pois.SucursalDeBanco;
import F5.Terminal.RepositorioDePOIs;
import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;


public class ConsultorBancosPostaTest {

	private String nombreBanco;
	private String servicioBanco;
	private List<SucursalDeBanco> sucursales;
	private RepositorioDePOIs unMapa;

	@Before
	public void Initialize() {
		nombreBanco = new String("Banco de la Plaza");
		servicioBanco = new String("depositos");
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("003"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("004"));
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
	}

	@Test
	public void laListaDeBancosNoEstaVaciaLuegoDeParsearElEjempo(){
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertNotNull(sucursales);
		
	}
	
	@Test
	public void enLaListaTieneQueHaber2ySolo2Bancos() {
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals(2, sucursales.size());
		//@Alan: Aca el size no me parece mal usado. La otra opcion serian 
		//dos asserts, pero nos dijeron que no es una buena practica
	}

	@Test
	public void elNombreDelPrimerBancoDeLaListaEsCorrecto() {
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals("Banco de la Plaza", sucursales.get(0).getNombre());
	}
}