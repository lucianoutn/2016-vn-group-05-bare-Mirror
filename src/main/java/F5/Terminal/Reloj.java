package F5.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Reloj {
	private int hora;
	private int moduloHorario;
	private List<INotificarCambioHorario> aNotificar = new ArrayList<INotificarCambioHorario>();
	
	public Reloj(int unModuloHorario){
		hora = 100;
		moduloHorario = unModuloHorario;
	}
	
	public int getHora() {
		return hora;
	}
	
	public void suscribirANotificadorDeCambioHorario(INotificarCambioHorario unNotificado){
		aNotificar.add(unNotificado);
	}
	
	public List<INotificarCambioHorario> getaNotificar() {
		return aNotificar;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public void aumentarModuloHorario(){
		hora = hora + moduloHorario;
		notificarCambioHorario();
	}

	private void notificarCambioHorario() {
		aNotificar.forEach(notificado -> notificado.anteCambioDeHorario(hora));	
	}
	
	
	
}
