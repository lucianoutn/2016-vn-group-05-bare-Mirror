package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import F5.SucursalDeBanco;

public class ConsultorBancosJsonPosta implements IConsultorBancos {
	// lo renombre como "POSTA" xq este es el que se va a comunicar con la URL
	// del servicio REST y va a traer el json de respuesta del banco "posta"

	// ATRIBUTOS

	URL url;
	private ObjectMapper objectMapper;

	// METODOS

	// contructor de clase
	public ConsultorBancosJsonPosta() {
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
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			url = new URL("http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BancosJson banco = objectMapper.readValue(url, BancosJson.class);
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
