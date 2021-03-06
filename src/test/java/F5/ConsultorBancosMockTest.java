package F5;

import java.util.List;

import org.junit.After;
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
import Kvs.KvsCache;

public class ConsultorBancosMockTest {


	private String nombreBanco;
	private String servicioBanco;
	private List<SucursalDeBanco> sucursales;
	private RepositorioDePOIs unMapa;

	@Before
	public void Initialize() {
		nombreBanco = new String("Banco de la Plaza");
		servicioBanco = new String("depósitos");
		
		
		ConsultorCGP unConsultorCGP = new ConsultorCGP(new SistemaExternoCGPMock("022"));
		ConsultorBancos consultorBanco = new ConsultorBancos(new SistemaExternoBancoMock("023"));
		
		unMapa = new RepositorioDePOIs(consultorBanco, unConsultorCGP);
		
	}
	
	@After
	public void cleanKvs(){
		
		KvsCache.clear();
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
	
	
	@Test
	public void TresVecesNoBuscoEnElSistemaExterno() {
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals(2, sucursales.size());
		
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals(1, KvsCache.trues);
		
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals(2, KvsCache.trues);
		
		sucursales = unMapa.getConsultorDeBancos().bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals(3, KvsCache.trues);

	}

}
