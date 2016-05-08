package F5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import InterfacesExternas.IConsultorBancos;
import Mocks.MockConsultorBanco;
import TestFactory.LocalComercialFactory;

public class SGPTest {

	
	@Test
	public void flatMapBancoDebeDevolver6ElementosEnLaLista(){
		
		SGP sgp = new SGP();
		ArrayList<IConsultorBancos> mockBancos = new ArrayList<IConsultorBancos>();	
		mockBancos.add(new MockConsultorBanco());
		mockBancos.add(new MockConsultorBanco());
		mockBancos.add(new MockConsultorBanco());
		sgp.consultoresBancos = mockBancos;
		
		
		List<SucursalDeBanco> bancos = sgp.bancosConServicio(null, null);
		Assert.assertTrue(bancos.size() == 6); 
	}
}
