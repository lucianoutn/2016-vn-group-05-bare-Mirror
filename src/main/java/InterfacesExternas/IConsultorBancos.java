package InterfacesExternas;

import java.util.List;

public interface IConsultorBancos {

	public List<bancosJson> getBancosQueCumplenCon(String nombreBranco, String unServicio);
	
	
}
