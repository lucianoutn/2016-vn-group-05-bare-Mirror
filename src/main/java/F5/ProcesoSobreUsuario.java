package F5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import F5.ProcesoSobrePOIS;

public class ProcesoSobreUsuario implements ProcesoSobrePOIS {
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<AccionPostBusqueda> accionesParaAgregar = new ArrayList<AccionPostBusqueda>();
	private List<AccionPostBusqueda> accionesParaQuitar = new ArrayList<AccionPostBusqueda>();
	
	public ProcesoSobreUsuario(List<Usuario> algunosUsuarios, List<AccionPostBusqueda> accionesAAgregar, List<AccionPostBusqueda> accionesAQuitar){
		usuarios=algunosUsuarios;
		accionesParaAgregar=accionesAAgregar;
		accionesParaQuitar=accionesAQuitar;
	}
	
	public ProcesoSobreUsuario(){
		usuarios=RepositorioDeUsuarios.getUsuarios();
	}
	
	public ProcesoSobreUsuario(Comuna comuna){
		usuarios=				RepositorioDeUsuarios
								.getUsuarios().stream()
								.filter(usuario-> usuario.getComuna().getNroComuna() == comuna.getNroComuna())
								.collect(Collectors.toList());;
	}
	public void ejecutar(){
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
