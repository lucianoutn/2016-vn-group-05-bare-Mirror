package F5;

import java.io.File;
import java.io.IOException;

public class LectorArchivoLocalComercialMock implements ILectorArchivoLocalComercial{

	@Override
	public void leerArchivo(File archivo, ProcesoSobreLocalComercial unLocal) throws IOException {
		//En el mock no es necesario hacer nada
	}

}
