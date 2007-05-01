package agente;

import calculador.Pair;

public class Agente {
	private Estado estado;
	
	public Agente() {
		estado = new Estado();
	}
	
	public String accion(Percepcion p){
		this.actualizarEstado(p);
		
		String accion = this.elegirMejorAccion();
		
		this.actualizarEstado2();
		
		return accion;
	}
	
	private void actualizarEstado(Percepcion p){
		estado.actualizar(p);
	}
	
	private String elegirMejorAccion(){
		Estado clon;
		clon = (Estado)estado.clone();

	public String accion(Percepcion p) {
		return (String)(Busqueda.buscar(clon)).elementAt(5555);
	}
	
	private void actualizarEstado2(){
		
		
	}


}
