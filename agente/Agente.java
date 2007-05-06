package agente;

import java.util.LinkedList;

public class Agente {
	private Estado estado;
	
	public Agente() {
		estado = new Estado();
	}
	
	public String accion(Percepcion p){
		this.actualizarEstado(p);
		String accion = this.elegirMejorAccion();
		this.actualizarEstado2(accion);
		return accion;
	}
	
	private void actualizarEstado(Percepcion p){
		estado.actualizar(p);
	}
	
	private String elegirMejorAccion(){
		Estado clon;
		clon = (Estado)estado.clone();
		
		LinkedList sol = Busqueda.buscarProfundidad(clon);
		if (sol != null && ! sol.isEmpty()) {
			String accion = (String)sol.getLast();
			this.actualizarEstado2(accion);
				
			return accion;
		} else {
			return "terminar";
		}
	}
	
	private void actualizarEstado2(String accion){
		this.estado.agregarAccionPos(accion);
		if(accion == "comer"){
			estado.comer();
		}
		if(accion == "pelear"){
			estado.pelear();
		}
	}


}
