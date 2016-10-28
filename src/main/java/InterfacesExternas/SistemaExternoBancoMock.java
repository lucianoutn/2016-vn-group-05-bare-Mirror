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

public class SistemaExternoBancoMock extends ISistemaExternoBanco {

	
	public SistemaExternoBancoMock(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	private ObjectMapper objectMapper;
	
	
	@Override
	public List<BancosJson> consultarBancos(String nombreBanco, String unServicio) {
				
		 				
 				
 					 
				objectMapper = new ObjectMapper();
				// el posta:
				// BancosJson banco= objectMapper.readValue(url, BancosJson.class);

				// el mock:
				File file = new File("src/test/java/F5/ejRespuestaJSON");

				try {
					return Arrays.asList(objectMapper.readValue(file, BancosJson[].class));
 					
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
