package InterfacesExternas;

import java.util.List;

import F5.SucursalDeBanco;

public interface IConsultorBancos {

	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio);
	
	
}