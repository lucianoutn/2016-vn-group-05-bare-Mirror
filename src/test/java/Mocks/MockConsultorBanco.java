package Mocks;

import java.util.ArrayList;
import java.util.List;



import F5.SucursalDeBanco;
import InterfacesExternas.IConsultorBancos;
import TestFactory.BancoFactory;

public class MockConsultorBanco implements IConsultorBancos {

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		
		List<SucursalDeBanco> bancos = new ArrayList<SucursalDeBanco>();
				
		bancos.add(BancoFactory.BancoHSBC());
		bancos.add(BancoFactory.BancoSabadoDe10a13());
		
		
		return bancos;
		
	}

}
