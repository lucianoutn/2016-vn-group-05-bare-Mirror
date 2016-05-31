package InterfacesExternas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import F5.SucursalDeBanco;

public class ConsultorBancosMock implements IConsultorBancos {
	// este es el que va a testear contra el ejemplo de respuesta json

	// ATRIBUTOS

	URL url;
	private ObjectMapper objectMapper;
	List<BancosJson> bancos;

	// METODOS

	// contructor de clase
	public ConsultorBancosMock() {
		// this.objectMapper = new ObjectMapper();

	}

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		// TODO aca me comunico con el sistema externo via Json y luego los
		// adapto a mi modelo
		consultarBancos(nombreBanco, unServicio);
		return adaptarBancos(bancos);
	}

	private List<SucursalDeBanco> adaptarBancos(List<BancosJson> bancos) {
		// TODO aca hago la adaptacion de los json a nuestro modelo.
		List<SucursalDeBanco> sucursales = new ArrayList<SucursalDeBanco>();
		
		
		
		return new ArrayList<SucursalDeBanco>();
	}

	private void consultarBancos(String nombreBanco, String unServicio) {
		// TODO aca me comunico con el sistema externo y adapto el Json a
		// bancosJson
		
		//En el mock no se usan los parametros de nombre y servicio xq siempre devuelve lo mismo
		objectMapper = new ObjectMapper();
		// el posta:
		// BancosJson banco= objectMapper.readValue(url, BancosJson.class);

		// el mock:
		File file = new File("src/test/java/F5/ejRespuestaJSON");

		try {
			bancos = Arrays.asList(objectMapper.readValue(file, BancosJson[].class));
		} catch (JsonParseException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}

	}

}
