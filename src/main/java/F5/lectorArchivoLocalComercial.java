package F5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class lectorArchivoLocalComercial {

	public void leerArchivo(File archivo, ProcesoSobreLocalComercial unLocal) throws IOException{
		FileReader lector = null;
		String linea;
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
			List<PuntoDeInteres> puntosQueMatchean = unLocal.getUnMapa().buscoEnElSistema(nombreDelLocal, null);
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
				unLocal.setCantidadDeElementosAfectados(puntosQueMatchean.size());
			}
			br.close();
		
	}
	
}
}
