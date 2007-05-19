package agente;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Busqueda {
	static Stack nodosExpandirPila;
	static LinkedList nodosExpandirCola;
	static PriorityQueue nodosExpandirColaPrioridad;
	public Busqueda() {

	}

	/**
	 * Busqueda en profundidad utilizando una PILA.
	 * Utiliza las precondiciones
	 * para saber si un nodo debe ser expandido o no
	 * @param e Estado actual del mundo del Pacman.
	 */
	public static LinkedList buscarProfundidad(Estado e){
		nodosExpandirPila = new Stack();
		Nodo nodoInicial = new Nodo(e);
		nodosExpandirPila.push(nodoInicial);

		Nodo ste;
		Estado temp;
		while(!nodosExpandirPila.empty()){

			ste = (Nodo)nodosExpandirPila.pop(); 

			if(objetivo(ste.getEstado())){
				System.out.print("Objetivo encontrado");
				return solucion(ste);
			}

			if(ste.getProfundidad() > 30){
				continue;
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
			

			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirPila.push(new Nodo(temp,"pelear",ste));
			}			

		}

		return new LinkedList();
	}
	
	/**
	 * Busqueda en amplitud utilizando un COLA (LinkedList).
	 * Utiliza las precondiciones
	 * para saber si un nodo debe ser expandido o no
	 * @param e Estado actual del mundo del Pacman.
	 */
	public static LinkedList buscarAnchura(Estado e){
		nodosExpandirCola = new LinkedList();
		Nodo nodoInicial = new Nodo(e);
		nodosExpandirCola.addLast(nodoInicial);

		Nodo ste;
		Estado temp;
		while(!nodosExpandirCola.isEmpty()){

			ste = (Nodo)nodosExpandirCola.removeFirst(); 

			if(objetivo(ste.getEstado())){
				System.out.print("Objetivo encontrado");
				return solucion(ste);
			}
			
			if(precondicion("comer", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.comer();
				nodosExpandirCola.addLast(new Nodo(temp,"comer",ste));
			}
			
			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirCola.addLast(new Nodo(temp,"pelear",ste));
			}			

			if(precondicion("arriba", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.arriba();
				nodosExpandirCola.addLast(new Nodo(temp,"arriba",ste));
			}

			if(precondicion("abajo", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.abajo();
				nodosExpandirCola.addLast(new Nodo(temp,"abajo",ste));
			}

			if(precondicion("derecha", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.derecha();
				nodosExpandirCola.addLast(new Nodo(temp,"derecha",ste));
			}

			if(precondicion("izquierda", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.izquierda();
				nodosExpandirCola.addLast(new Nodo(temp,"izquierda",ste));
			}

		}

		return new LinkedList();
	}
	
	/**
	 * Busqueda en costo uniforme se utiliza una
	 * COLA DE PRIORIDAD. Utiliza las precondiciones
	 * para saber si un nodo debe ser expandido o no
	 * @param e Estado actual del mundo del Pacman.
	 */
	public static LinkedList buscarCosto(Estado e){
		nodosExpandirColaPrioridad = new PriorityQueue();
		Nodo nodoInicial = new Nodo(e);
		nodosExpandirColaPrioridad.add(nodoInicial);

		Nodo ste;
		Estado temp;
		while(!nodosExpandirColaPrioridad.isEmpty()){

			ste = (Nodo)nodosExpandirColaPrioridad.remove(); 

			if(objetivo(ste.getEstado())){
				System.out.print("Objetivo encontrado");
				return solucion(ste);
			}

			if(precondicion("comer", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.comer();
				nodosExpandirColaPrioridad.add(new Nodo(temp,"comer",ste));
			}
			
			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirColaPrioridad.add(new Nodo(temp,"pelear",ste));
			}			

			if(precondicion("arriba", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.arriba();
				nodosExpandirColaPrioridad.add(new Nodo(temp,"arriba",ste));
			}

			if(precondicion("abajo", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.abajo();
				nodosExpandirColaPrioridad.add(new Nodo(temp,"abajo",ste));
			}

			if(precondicion("derecha", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.derecha();
				nodosExpandirColaPrioridad.add(new Nodo(temp,"derecha",ste));
			}

			if(precondicion("izquierda", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.izquierda();
				nodosExpandirColaPrioridad.add(new Nodo(temp,"izquierda",ste));
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
			if(!e.hayComida())
				return false;
		}

		if(a == "pelear"){
			if (!e.hayEnemigo()) {
				return false;
			}
			/*if(e.getEnergiaActual() < 5){
				return false;
			}*/
		}
			
		if(a == "arriba"){
			if(e.accionPosRepetida("arriba"))
				return false;			
			if(e.accionRepetida("arriba",2))
				return false;
			if(e.ultimaAccion("abajo"))
				return false;
			if(e.hayComida())
				return false;
		}
			
		if(a == "abajo"){
			if(e.accionPosRepetida("abajo"))
				return false;
			if(e.accionRepetida("abajo",2))
				return false;
			if(e.ultimaAccion("arriba"))
				return false;
			if(e.hayComida())
				return false;
		}
			
		if(a == "derecha"){
			if(e.accionPosRepetida("derecha"))
				return false;
			if(e.accionRepetida("derecha",2))
				return false;		
			if(e.ultimaAccion("izquierda"))
				return false;
			if(e.hayComida())
				return false;
		}

		if(a == "izquierda"){
			if(e.accionPosRepetida("izquierda"))
				return false;
			if(e.accionRepetida("izquierda",2))
				return false;			
			if(e.ultimaAccion("derecha"))
				return false;
			if(e.hayComida())
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
		System.out.println("COSTO " + n.costo);
		LinkedList s = new LinkedList();

		while(! n.accion.equals("")){
			s.add(n.accion);
			n=n.getPadre();
		}		
		return s;		


	}

	static class Nodo implements Comparable{
		Nodo padre;
		Estado estado;
		String accion;
		int profundidad;
		int costo;

		public Nodo(Estado estado, String accion, Nodo padre) {
			super();
			this.padre = padre;
			this.estado = estado;
			this.accion = accion;
			this.profundidad = padre.getProfundidad() + 1;
			this.costo = this.costoAccion(accion, estado) + profundidad;
		}


		private int costoAccion(String accion, Estado e) {
			if(accion == "comer")
				return 1;
			
			if(accion == "pelear")
				return 20;
			
			if(e.hayComida())
				return 5;
			
			return 15;
			
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
		
		public int compareTo(Object n){
			if(this.costo == ((Nodo)n).costo)
				return 0;
			
			if(this.costo > ((Nodo)n).costo)
				return 1;
				
			return -1;
			
		}
	}

}
