package InterfacesExternas;

import java.util.List;

import F5.Pois.SucursalDeBanco;

public interface IConsultorBancos extends OrigenDeDatos {

	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio); 
	
	
}
