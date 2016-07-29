package F5;

import java.io.File;
import java.io.IOException;

public interface ILectorArchivoLocalComercial {
	public void leerArchivo(File archivo, ProcesoSobreLocalComercial unLocal) throws IOException;
}
