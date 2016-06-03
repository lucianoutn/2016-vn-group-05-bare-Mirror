package F5;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import InterfacesExternas.ConsultorBancosJsonPosta;




public class ConsultorBancosPostaTest {

	private ConsultorBancosJsonPosta consultor;
	private String nombreBanco;
	private String servicioBanco;
	private List<SucursalDeBanco> sucursales;

	@Before
	public void Initialize() {
		nombreBanco = new String("BancodelaPlaza");
		servicioBanco = new String("depositos");
		consultor = new ConsultorBancosJsonPosta();
		
	}

	@Test
	public void laListaDeBancosNoEstaVaciaLuegoDeParsearElEjempo(){
		sucursales = consultor.bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertNotNull(sucursales);
		
	}
	
	@Test
	public void enLaListaTieneQueHaber2ySolo2Bancos() {
		sucursales = consultor.bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertEquals(2, sucursales.size());
	}

	@Test
	public void elNombreDelPrimerBancoDeLaListaEsCorrecto() {
		sucursales = consultor.bancosQueCumplenCon(nombreBanco, servicioBanco);
		Assert.assertArrayEquals(("Banco de la Plaza").toCharArray(), sucursales.get(0).getNombre().toCharArray());
	}
}
