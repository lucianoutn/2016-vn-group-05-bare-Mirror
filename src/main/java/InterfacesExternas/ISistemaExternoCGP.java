package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import Kvs.KvsCache;

public abstract class ISistemaExternoCGP {

	public String id = "";
	
	public ISistemaExternoCGP(String id){
		this.id = id;
	}
	
	public abstract List<CentroDTO> consultarCgpsDTO(String lugar);
	
	public List<CentroDTO> consultar(String lugar){
	
		List<CentroDTO> cgps = new ArrayList<CentroDTO>();

		String key = id + "cgps" + lugar;
		boolean valueCgps = KvsCache.get(key);

		if (valueCgps) {
			return cgps;
		}

		cgps.addAll(consultarCgpsDTO(lugar));

		String value = "true"; 
		
		KvsCache.save(key, value);

		return cgps;
	}
	
	
	
	
}
