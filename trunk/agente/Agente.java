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
		
		LinkedList sol = Busqueda.buscarCosto(clon);
		if (sol != null && ! sol.isEmpty()) {
			String accion = (String)sol.getLast();
			if(accion == "comer"){
				estado.comer();
			}
				
			return accion;
		} else {
			return "terminar";
		}
	}
	
	private void actualizarEstado2(String accion){
		this.estado.agregarAccionPos(accion);
	}


}
