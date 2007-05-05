package agente;

import java.util.*;

public class Busqueda {
	static Stack nodosExpandirPila;

	public Busqueda() {

	}

	/**
	 * Busqueda en profundidad con recursion
	 * determinada accion
	 * @param e Estado actual del mundo del Pacman.
	 */
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

	/**
	 * Busqueda en profundidad utilizando una PILA
	 * determinada accion. Utiliza las precondiciones
	 * para saber si un nodo debe ser expandido o no
	 * @param e Estado actual del mundo del Pacman.
	 */
	public static LinkedList buscarPila(Estado e){
		nodosExpandirPila = new Stack();
		Nodo nodoInicial = new Nodo(e);
		System.out.println("BBB1");
		nodosExpandirPila.push(nodoInicial);

		Nodo ste;
		Estado temp;
		while(!nodosExpandirPila.empty()){

			ste = (Nodo)nodosExpandirPila.pop(); 

			if(objetivo(ste.getEstado())){
				System.out.print("Objetivo encontrado");
				return solucion(ste);
			}

			if(ste.getProfundidad() > 7){
				continue;
			}

			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirPila.push(new Nodo(temp,"pelear",ste));
			}			

			if(precondicion("arriba", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.arriba();
				nodosExpandirPila.push(new Nodo(temp,"arriba",ste));
			}

			if(precondicion("abajo", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.abajo();
				nodosExpandirPila.push(new Nodo(temp,"abajo",ste));
			}

			if(precondicion("derecha", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.derecha();
				nodosExpandirPila.push(new Nodo(temp,"derecha",ste));
			}

			if(precondicion("izquierda", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.izquierda();
				nodosExpandirPila.push(new Nodo(temp,"izquierda",ste));
			}

			if(precondicion("comer", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.comer();
				nodosExpandirPila.push(new Nodo(temp,"comer",ste));
			}
			

		}

		return new LinkedList();
	}
	
	/**
	 * Averigua si se cumplen las precondiciones para una
	 * determinada accion
	 * @param a La precondicion se aplica sobre la accion a.
	 * @param n El nodo actual en el arbol de busqueda.
	 */
	private static boolean precondicion(String a, Nodo n){
		Estado e = n.getEstado();
		if(a == "comer"){
			if(e.accionPosRepetida("comer"))
				return false;
			if(e.accionRepetida("comer",1))
				return false;
			if(!e.hayComida())
				return false;
		}

		if(a == "pelear"){
			return false;			
		}
			
		if(a == "arriba"){
			if(e.accionPosRepetida("arriba"))
				return false;			
			if(e.accionRepetida("arriba",2))
				return false;
			if(e.ultimaAccion("abajo"))
				return false;
		}
			
		if(a == "abajo"){
			if(e.accionPosRepetida("abajo"))
				return false;
			if(e.accionRepetida("abajo",2))
				return false;
			if(e.ultimaAccion("arriba"))
				return false;			
		}
			
		if(a == "derecha"){
			if(e.accionPosRepetida("derecha"))
				return false;
			if(e.accionRepetida("derecha",2))
				return false;		
			if(e.ultimaAccion("izquierda"))
				return false;			
		}

		if(a == "izquierda"){
			if(e.accionPosRepetida("izquierda"))
				return false;
			if(e.accionRepetida("izquierda",2))
				return false;			
			if(e.ultimaAccion("derecha"))
				return false;			
			
		}
		
		return true;
			
			
	}
	
	/**
	 * Averigua si el estado es un estado objetivo
	 * @param e Estado actual en el arbol de busqueda.
	 */	
	private static boolean objetivo(Estado e){
		return e.todoConocido();
	}

	private static LinkedList solucion(Nodo n){
		LinkedList s = new LinkedList();

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
