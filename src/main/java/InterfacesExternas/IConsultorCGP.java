package InterfacesExternas;

import java.util.List;

import F5.Pois.CGP;

public interface IConsultorCGP extends OrigenDeDatos{

	public List<CGP> cgpUbicadasEn(String lugar);

}
