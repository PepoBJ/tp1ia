package agente;

import java.awt.List;
import java.util.LinkedList;
import java.util.Vector;

public class Busqueda {

	public Busqueda() {
		// TODO Auto-generated constructor stub
	}
	
	static LinkedList buscar(Estado e){
		Nodo nodo = new Nodo(e);
		System.out.println("BBB1");
		return buscar2(nodo);
	}
	
	private static LinkedList buscar2(Nodo n){
		if(objetivo(n.getEstado())){
			System.out.print("Objetivo encontrado");
			return solucion(n);
		}
		
		if(n.getProfundidad() < 8){
			Estado temp;
			Nodo nodo;
			LinkedList v;
			if(! n.getEstado().accionPosRepetida("arriba")){
				temp =(Estado) n.getEstado().clone();
				temp.arriba();
				nodo = new Nodo(temp,"arriba",n);
				v = buscar2(nodo); 
				if(v != null && ! v.isEmpty()){
					return v; 
				}
			}
			
			if(! n.getEstado().accionPosRepetida("abajo")){
				temp =(Estado) n.getEstado().clone();
				temp.abajo();
				nodo = new Nodo(temp,"abajo",n);
				v = buscar2(nodo); 
				if(v != null && ! v.isEmpty()){
					return v; 
				}
			}
			
			if(! n.getEstado().accionPosRepetida("derecha")){
				temp =(Estado) n.getEstado().clone();
				temp.derecha();
				nodo = new Nodo(temp,"derecha",n);
				v = buscar2(nodo); 
				if(v != null && ! v.isEmpty()){
					return v; 
				}
			}
			
			if(! n.getEstado().accionPosRepetida("izquierda")){
				temp =(Estado) n.getEstado().clone();
				temp.izquierda();
				nodo = new Nodo(temp,"izquierda",n);
				v = buscar2(nodo); 
				if(v != null && ! v.isEmpty()){
					return v; 
				}
			}
			
			temp =(Estado) n.getEstado().clone();
			temp.comer();
			nodo = new Nodo(temp,"comer",n);
			v = buscar2(nodo); 
			if(v != null && ! v.isEmpty()){
				return v; 
			}
			
			temp =(Estado) n.getEstado().clone();
			temp.pelear();
			nodo = new Nodo(temp,"pelear",n);
			v = buscar2(nodo); 
			if(v != null && ! v.isEmpty()){
				return v; 
			}			
		}
		//System.out.println("CORTE");
		return null;
		
		
	}
	
	private static boolean objetivo(Estado e){
		return e.todoConocido();
	}
	
	//COMPLETAR
	private static LinkedList solucion(Nodo n){
		LinkedList s = new LinkedList();
		
/*<<<<<<< .mine
		while(n.getPadre() != null){
			
			solucion.addAll(solucion(n.getPadre()));
		}
*/
		while(! n.accion.equals("")){
			s.add(n.accion);
			n=n.getPadre();
		}		
		return s;		

		
	}
	
	static class Nodo{
		Nodo padre;
		Estado estado;
		String accion;
		int profundidad;
		
		public Nodo(Estado estado, String accion, Nodo padre) {
			super();
			this.padre = padre;
			this.estado = estado;
			this.accion = accion;
			this.profundidad = padre.getProfundidad() + 1;

		}
		

		public Nodo(Estado estado) {
			super();
			this.padre = null;
			this.estado = estado;
			this.accion = "";
			this.profundidad = 0;
		}

		public int getProfundidad() {
			return profundidad;
		}		
		
		public String getAccion() {
			return accion;
		}
		public void setAccion(String accion) {
			this.accion = accion;
		}
		public Estado getEstado() {
			return estado;
		}
		public void setEstado(Estado estado) {
			this.estado = estado;
		}
		public Nodo getPadre() {
			return padre;
		}
		public void setPadre(Nodo padre) {
			this.padre = padre;
		}
	}

}
