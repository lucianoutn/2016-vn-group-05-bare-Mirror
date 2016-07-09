package F5;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import InterfacesExternas.ConsultorBancos;
import InterfacesExternas.ConsultorCGP;
import InterfacesExternas.SistemaExternoBancoMock;
import InterfacesExternas.SistemaExternoCGPMock;


public class ConsultorBancosPostaTest {

	private String nombreBanco;
	private String servicioBanco;
	private List<SucursalDeBanco> sucursales;
	private Mapa unMapa;

	@Before
	public void Initialize() {
		nombreBanco = new String("BancodelaPlaza");
		servicioBanco = new String("depositos");
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock());
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock());
		
		unMapa = new Mapa(consultorBanco, unConsultorCGP);
		
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
	}

	@Test
	public void elNombreDelPrimerBancoDeLaListaEsCorrecto() {
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertArrayEquals(("Banco de la Plaza").toCharArray(), sucursales.get(0).getNombre().toCharArray());
	}
}