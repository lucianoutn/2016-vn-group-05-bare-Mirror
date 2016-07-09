package InterfacesExternas;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SistemaExternoBanco implements ISistemaExternoBanco {

	private URL url;
	private ObjectMapper objectMapper;
	
	
	@Override
	public List<BancosJson> consultarBancos(String nombreBanco, String unServicio) {
		// aca me comunico con el sistema externo y adapto el Json a
				// bancosJson
				
				try {
					url = new URL("http://private-96b476-ddsutn.apiary-mock.com/banks?banco=" + nombreBanco + "&servicio=" + unServicio);
					//url = new URL("http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio");
				} catch (MalformedURLException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
				objectMapper = new ObjectMapper();
				
				try {
					return Arrays.asList(objectMapper.readValue(url, BancosJson[].class));
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
