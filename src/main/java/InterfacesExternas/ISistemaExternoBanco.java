package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import Kvs.KvsCache;

public abstract class ISistemaExternoBanco {

	public abstract List<BancosJson> consultarBancos(String nombreBanco, String unServicio);

	public List<BancosJson> consultar(String nombreBanco, String unServicio) {
		List<BancosJson> bancos = new ArrayList<BancosJson>();

		String key = "bancos" + nombreBanco + unServicio;
		String valueBancos = KvsCache.get(key);

		if (valueBancos != null) {
			return bancos;
		}

		bancos.addAll(consultarBancos(nombreBanco, unServicio));

		String value = "";
		bancos.stream().forEach(b -> value.concat(b.getNombre()));
		KvsCache.save(key, value);

		return bancos;

	}

}
