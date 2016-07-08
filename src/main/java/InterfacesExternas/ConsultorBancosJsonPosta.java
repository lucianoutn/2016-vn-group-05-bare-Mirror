package InterfacesExternas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import F5.DiaAtencion;
import F5.SucursalDeBanco;

public class ConsultorBancosJsonPosta implements IConsultorBancos {
	// lo renombre como "POSTA" xq este es el que se va a comunicar con la URL
	// del servicio REST y va a traer el json de respuesta del banco "posta"

	// ATRIBUTOS
	private static URL url;
	private static ObjectMapper objectMapper;
	private static List<BancosJson> bancos;

	// METODOS

	// contructor de clase
	public ConsultorBancosJsonPosta() {
		// this.objectMapper = new ObjectMapper();

	}

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		// aca me comunico con el sistema externo via Json y luego los
		// adapto a mi modelo
		consultarBancos(nombreBanco, unServicio);
		return adaptarBancos();
	}

	private List<SucursalDeBanco> adaptarBancos() {
		// aca hago la adaptacion de los json a nuestro modelo.
		List<SucursalDeBanco> sucursales = new ArrayList<SucursalDeBanco>();
		this.bancos.forEach(banco -> {
			String nombre = banco.getNombre();
			double x = banco.getX();
			double y = banco.getY();
			Point pos = new Point(x, y);
			List<DiaAtencion> diaDeAtencion = new ArrayList<DiaAtencion>();
			SucursalDeBanco sucursal = new SucursalDeBanco(nombre, pos, diaDeAtencion);
			sucursales.add(sucursal);
		});
		return sucursales;
	}

	private void consultarBancos(String nombreBanco, String unServicio) {
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
			bancos = Arrays.asList(objectMapper.readValue(url, BancosJson[].class));
		} catch (JsonParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
