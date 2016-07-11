package F5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import F5.ProcesoSobrePOIS;


public class ProcesoSobreLocalComercialPosta implements ProcesoSobreLocalComercial {

	private String ruta;
	private LocalDate fecha;
	private File archivo;
	private RepositorioDePOIs unMapa;
	private boolean localesComercialesActualizados= false;
	
	public boolean isLocalesComercialesActualizados() {
		return localesComercialesActualizados;
	}
	
	
	public ProcesoSobreLocalComercialPosta(LocalDate horarioDeEjecucion, String rutaArchivo, RepositorioDePOIs map) {
		ruta = rutaArchivo;
		unMapa = map;
		fecha = horarioDeEjecucion;
	}

	public void ejecutar() {
		if( LocalDate.now().equals(fecha))
		this.actualizarLocalComercial();
	}

	public void actualizarLocalComercial() {
		archivo = new File(ruta);
		FileReader lector = null;
		String linea;
		localesComercialesActualizados=true;
		try {
			lector = new FileReader(archivo);
			BufferedReader br = new BufferedReader(lector);
			while ((linea = br.readLine()) != null) {
				int i = 0;
				ArrayList<String> palabrasClaves = new ArrayList<>();
				char c = linea.charAt(i);
				StringBuilder constructorNombre = new StringBuilder();
				StringBuilder constructorPalabra = new StringBuilder();
				while (c != ';') {
					constructorNombre.append(c);
					i++;
				}
				String nombreDelLocal = constructorNombre.toString();
				List<PuntoDeInteres> puntosQueMatchean = unMapa.buscoEnElSistema(nombreDelLocal, null);
				if (!puntosQueMatchean.isEmpty()) {
					while (c != '\0') {
						while (c != ' ') {
							if (c == '\0')
								break;
							constructorPalabra.append(c);
							i++;
						}
						palabrasClaves.add(constructorPalabra.toString());
						if (c == '\0')
							break;
						i++;
					}
					
					puntosQueMatchean.stream().forEach(poi->poi.agregarPalabrasClaves(palabrasClaves));
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
