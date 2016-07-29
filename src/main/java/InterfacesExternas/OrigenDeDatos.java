package InterfacesExternas;

import java.util.List;

import F5.Pois.SucursalDeBanco;

public interface OrigenDeDatos {

	List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio);
}
