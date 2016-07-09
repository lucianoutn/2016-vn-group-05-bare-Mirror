package InterfacesExternas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SistemaExternoBancoMock implements ISistemaExternoBanco {

	
	private ObjectMapper objectMapper;
	
	
	@Override
	public List<BancosJson> consultarBancos(String nombreBanco, String unServicio) {
		// aca me comunico con el sistema externo y adapto el Json a
				// bancosJson

			
				objectMapper = new ObjectMapper();
				// el posta:
				// BancosJson banco= objectMapper.readValue(url, BancosJson.class);

				// el mock:
				File file = new File("src/test/java/F5/ejRespuestaJSON");

				try {
					return  Arrays.asList(objectMapper.readValue(file, BancosJson[].class));
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
