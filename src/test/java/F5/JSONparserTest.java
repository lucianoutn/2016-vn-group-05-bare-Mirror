package F5;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import InterfacesExternas.BancosJson;

public class JSONparserTest {

	List<BancosJson> bancos;
	String nombreBanco;
	String servicioBanco;
	private ObjectMapper objectMapper;

	@Before
	public void Initialize() {
		nombreBanco = new String("Banco de la Plaza");
		servicioBanco = new String("depósitos");
		objectMapper = new ObjectMapper();

		File file = new File("src/test/java/F5/ejRespuestaJSON");
		try {
			// List<MyClass> myObjects = mapper.readValue(jsonInput, new
			// TypeReference<List<MyClass>>(){});
			bancos = Arrays.asList(objectMapper.readValue(file, BancosJson[].class));
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

	}

	@Test
	public void elparserAnda() {
		Assert.assertFalse(bancos.isEmpty());
	}
	
	@Test
	public void enLaListaTieneQueHaber2ySolo2Bancos() {
		Assert.assertEquals(2, bancos.size());
	}
	
	@Test
	public void elNombreDelPrimerBancoDeLaListaEsCorrecto() {
		Assert.assertArrayEquals(("Banco de la Plaza").toCharArray(), bancos.get(1).getNombre().toCharArray());
	}

}
