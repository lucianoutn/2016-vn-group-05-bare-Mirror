package InterfacesExternas;

import java.util.ArrayList;
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

	// METODOS

	// contructor de clase
	public ConsultorBancosMock() {
		// this.objectMapper = new ObjectMapper();

	}

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		// TODO aca me comunico con el sistema externo via Json y luego los
		// adapto a mi modelo
		List<BancosJson> bancos = consultarBancos(nombreBanco, unServicio);
		return adaptarBancos(bancos);
	}

	private List<SucursalDeBanco> adaptarBancos(List<BancosJson> bancos) {
		// TODO aca hago la adaptacion de los json a nuestro modelo.
		// Dependiendo el framework de json que usemos tal vez no sea necesaria
		// la clase bancosJson
		return new ArrayList<SucursalDeBanco>();
	}

	private List<BancosJson> consultarBancos(String nombreBanco, String unServicio) {
		// TODO aca me comunico con el sistema externo y adapto el Json a
		// bancosJson. devuelvo ARRAY LIST
		// Dependiendo el framework de json que usemos tal vez no sea necesaria
		// la clase bancosJson

		// BancosJson banco = new BancosJson();
		ObjectMapper objectMapper = new ObjectMapper();
		// el posta:
		// BancosJson banco= objectMapper.readValue(url, BancosJson.class);

		// el mock:

		File file = new File("ejRespuestaJSON");

		try {
			BancosJson banco = objectMapper.readValue(file, BancosJson.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<BancosJson>();
	}

}
