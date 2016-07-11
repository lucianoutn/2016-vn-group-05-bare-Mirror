package F5;

import java.time.LocalDate;

//import F5.RepositorioImpostor;//esto había que modificarlo para la entrega 3, pero creo que de acá sacaríamos los pois a modificar
import F5.RepositorioDePOIs;//emi: usamos el mapa de repo ahora? lucho


public interface ProcesoSobrePOIS extends INotificarCambioHorario{
	
	public void ejecutar();
	

}
