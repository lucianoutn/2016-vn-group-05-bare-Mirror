package InterfacesExternas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

public class ConsultorBancos implements IConsultorBancos {
	
	
	
	private List<BancosJson> bancos = new ArrayList<BancosJson>();
	private ISistemaExternoBanco sistemaExterno;
	public ConsultorBancos(ISistemaExternoBanco sistema){
		sistemaExterno = sistema;
	}

	@Override
	public List<SucursalDeBanco> bancosQueCumplenCon(String nombreBanco, String unServicio) {
		// aca me comunico con el sistema externo via Json y luego los
		// adapto a mi modelo
		List<BancosJson> bancosJson = sistemaExterno.consultarBancos(nombreBanco, unServicio);
		List<SucursalDeBanco> bancosAdaptados = adaptarBancos(bancosJson);
		List<SucursalDeBanco> bancos = filtrarBancosQueCumplenCon(bancosAdaptados, nombreBanco);
		return bancos;
	}

	private List<SucursalDeBanco> filtrarBancosQueCumplenCon(List<SucursalDeBanco> bancos, String nombreBanco){
		
		List<SucursalDeBanco> bancosFiltrados = bancos.stream().filter(unBanco->unBanco.encuentra(nombreBanco)).collect(Collectors.toList());;

		return bancosFiltrados; 
	}

	private List<SucursalDeBanco> adaptarBancos(List<BancosJson> bancosJson) {
		// aca hago la adaptacion de los json a nuestro modelo.
		List<SucursalDeBanco> sucursales = new ArrayList<SucursalDeBanco>();

		bancosJson.forEach(banco -> {
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
	
}
