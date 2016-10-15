package InterfacesExternas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Kvs.KvsCache;

public class SistemaExternoBancoMock implements ISistemaExternoBanco {

	
	private ObjectMapper objectMapper;
	
	
	@Override
	public List<BancosJson> consultarBancos(String nombreBanco, String unServicio) {
				String key = "bancos-nombreBanco-unServicio";
		 				
 				String valueBancos = KvsCache.get(key);
 				
 				if (valueBancos != null || valueBancos != ""){
 					return new ArrayList<BancosJson>();
 				}
 					 
				objectMapper = new ObjectMapper();
				// el posta:
				// BancosJson banco= objectMapper.readValue(url, BancosJson.class);

				// el mock:
				File file = new File("src/test/java/F5/ejRespuestaJSON");

				try {
					List<BancosJson> bancos = Arrays.asList(objectMapper.readValue(file, BancosJson[].class));
 					String value = "";
 					bancos.stream().forEach(b -> value.concat(b.getNombre()));				
 					KvsCache.save(key, value);
					return bancos;
				} catch (JsonParseException e) {
					// Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (JsonMappingException e) {
					// Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					// Auto-generated catch block
					e.printStackTrace();
					return null;
				}

		
	}

}
