package InterfacesExternas;

import java.util.List;

public interface ISistemaExternoBanco {

	public List<BancosJson> consultarBancos(String nombreBanco, String unServicio);
}
