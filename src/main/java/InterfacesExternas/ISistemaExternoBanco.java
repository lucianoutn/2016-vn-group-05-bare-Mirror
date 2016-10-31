package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import Kvs.KvsCache;

public abstract class ISistemaExternoBanco {

	public String id = "";
	
	public ISistemaExternoBanco(String id){
		this.id = id;
	}
	
	public abstract List<BancosJson> consultarBancos(String nombreBanco, String unServicio);

	public List<BancosJson> consultar(String nombreBanco, String unServicio) {
		List<BancosJson> bancos = new ArrayList<BancosJson>();

		String key = id + '-' +  "bancos" + '-' + nombreBanco + '-' + unServicio;
		
		if(tengoQueIrABancoExterno(bancos, key));
			bancos.addAll(consultarBancos(nombreBanco, unServicio));

		String value = "true";
		//bancos.stream().forEach(b -> value.concat(b.getNombre()));
		KvsCache.save(key, value);

		return bancos;

	}

	private void tengoQueIrABancoExterno(List<BancosJson> bancos, String key) {
		boolean valueBancos = KvsCache.get(key);

		if (valueBancos) {
			return bancos;
		}
	}

}
