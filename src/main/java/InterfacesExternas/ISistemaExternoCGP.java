package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import Kvs.KvsCache;

public abstract class ISistemaExternoCGP {

	
	
	public abstract List<CentroDTO> consultarCgpsDTO(String lugar);
	
	public List<CentroDTO> consultar(String lugar){
	
		List<CentroDTO> cgps = new ArrayList<CentroDTO>();

		String key = "cgps" + lugar;
		String valueCgps = KvsCache.get(key);

		if (valueCgps != null) {
			return cgps;
		}

		cgps.addAll(consultarCgpsDTO(lugar));

		String value = "";
		cgps.stream().forEach(c -> value.concat(c.getDomicilioCGP()));
		KvsCache.save(key, value);

		return cgps;
	}
	
	
	
	
}
