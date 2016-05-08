package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import F5.SucursalDeBanco;

public class ConsultorBancosJson implements IConsultorBancos{

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		//TODO aca me comunico con el sistema externo via Json y luego los adapto a mi modelo
		List<bancosJson> bancos = consultarBancos(nombreBanco, unServicio);
		return adaptarBancos(bancos);
	}

	private List<SucursalDeBanco> adaptarBancos(List<bancosJson> bancos) {
		// TODO aca hago la adaptacion de los json a nuestro modelo.
		// Dependiendo el framework de json que usemos tal vez no sea necesaria la clase bancosJson
		return new ArrayList<SucursalDeBanco>();
	}

	private List<bancosJson> consultarBancos(String nombreBanco, String unServicio) {
		// TODO aca me comunico con el sistema externo y adapto el Json a bancosJson. devuelvo ARRAY LIST
		// Dependiendo el framework de json que usemos tal vez no sea necesaria la clase bancosJson
		return new ArrayList<bancosJson>();
	}

}
