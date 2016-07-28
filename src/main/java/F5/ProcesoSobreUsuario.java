package F5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import F5.Proceso;

public class ProcesoSobreUsuario extends Proceso {
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<AccionPostBusqueda> accionesParaAgregar = new ArrayList<AccionPostBusqueda>();
	private List<AccionPostBusqueda> accionesParaQuitar = new ArrayList<AccionPostBusqueda>();
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public ProcesoSobreUsuario(List<Usuario> algunosUsuarios, List<AccionPostBusqueda> accionesAAgregar, List<AccionPostBusqueda> accionesAQuitar, int horaPlanificacion){
		usuarios=algunosUsuarios;
		accionesParaAgregar=accionesAAgregar;
		accionesParaQuitar=accionesAQuitar;
		this.setHorarioPlanificacion(horaPlanificacion);
		this.inicializarEstado();
	}
	
	public ProcesoSobreUsuario(){
		usuarios=RepositorioDeUsuarios.getUsuarios();
		this.inicializarEstado();
	}
	
	public ProcesoSobreUsuario(Comuna comuna){
		usuarios=				RepositorioDeUsuarios
								.getUsuarios().stream()
								.filter(usuario-> usuario.getComuna().getNroComuna() == comuna.getNroComuna())
								.collect(Collectors.toList());
		this.inicializarEstado();
	}
	
	public void hacerOperacionesDeCadaProceso(){
		this.setCantidadDeElementosAfectados(usuarios.size());
		this.agregarAccionesAUsuario(accionesParaAgregar);
		this.quitarAccionesAUsuario(accionesParaQuitar);
	}
	
	public void agregarAccionesAUsuario(List<AccionPostBusqueda> acciones){
		usuarios.forEach(usuario->usuario.agregarAcciones(acciones));
	}
	
	public void quitarAccionesAUsuario(List<AccionPostBusqueda> acciones){
		usuarios.forEach(usuario->usuario.quitarAcciones(acciones));
	}

}
