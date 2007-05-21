package agente;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Busqueda {
	static Stack nodosExpandirPila;
	static LinkedList nodosExpandirCola;
	static PriorityQueue nodosExpandirColaPrioridad;
	static String recorrido = "";

	public Busqueda() {

	}

	/**
	 * Busqueda en profundidad utilizando una PILA.
	 * Utiliza las precondiciones
	 * para saber si un nodo debe ser expandido o no
	 * @param e Estado actual del mundo del Pacman.
	 */
	public static LinkedList buscarProfundidad(Estado e){
		recorrido =  "";
		nodosExpandirPila = new Stack();
		Nodo nodoInicial = new Nodo(e);
		nodosExpandirPila.push(nodoInicial);

		Nodo ste;
		Nodo tempNode;
		Estado temp;
		while(!nodosExpandirPila.empty()){

			ste = (Nodo)nodosExpandirPila.pop(); 
			if(objetivo(ste.getEstado())){
				System.out.println("Objetivo encontrado");

				imprimirArbol(nodoInicial,0, ste);
				return solucion(ste);
			}

			if(ste.getProfundidad() > 30){
				continue;
			}

			if(precondicion("arriba", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.arriba();
				nodosExpandirPila.push(tempNode=new Nodo(temp,"arriba",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("abajo", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.abajo();
				nodosExpandirPila.push(tempNode=new Nodo(temp,"abajo",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("derecha", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.derecha();
				nodosExpandirPila.push(tempNode=new Nodo(temp,"derecha",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("izquierda", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.izquierda();
				nodosExpandirPila.push(tempNode=new Nodo(temp,"izquierda",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("comer", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.comer();
				nodosExpandirPila.push(tempNode=new Nodo(temp,"comer",ste));
				ste.addHijos(tempNode);
			}


			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirPila.push(tempNode=new Nodo(temp,"pelear",ste));
				ste.addHijos(tempNode);
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
		recorrido =  "";
		nodosExpandirCola = new LinkedList();
		Nodo nodoInicial = new Nodo(e);
		nodosExpandirCola.addLast(nodoInicial);

		Nodo ste;
		Nodo tempNode;
		Estado temp;

		while(!nodosExpandirCola.isEmpty()){

			ste = (Nodo)nodosExpandirCola.removeFirst();
			


			if(objetivo(ste.getEstado())){
				//System.out.println("Objetivo encontrado");
				//imprimirArbol(nodoInicial,1,ste);

				return solucion(ste);
			}

			if(precondicion("comer", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.comer();
				nodosExpandirCola.addLast(tempNode=new Nodo(temp,"comer",ste));
				ste.addHijos(tempNode);

			}

			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirCola.addLast(tempNode=new Nodo(temp,"pelear",ste));
				ste.addHijos(tempNode);
			}			

			if(precondicion("arriba", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.arriba();
				nodosExpandirCola.addLast(tempNode=new Nodo(temp,"arriba",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("abajo", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.abajo();
				nodosExpandirCola.addLast(tempNode=new Nodo(temp,"abajo",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("derecha", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.derecha();
				nodosExpandirCola.addLast(tempNode=new Nodo(temp,"derecha",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("izquierda", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.izquierda();
				nodosExpandirCola.addLast(tempNode=new Nodo(temp,"izquierda",ste));
				ste.addHijos(tempNode);
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
		Nodo tempNode;
		nodosExpandirColaPrioridad = new PriorityQueue();
		Nodo nodoInicial = new Nodo(e);
		nodosExpandirColaPrioridad.add(nodoInicial);
		
		PriorityQueue pq = nodosExpandirColaPrioridad;

		Nodo ste;
		Estado temp;
		while(!nodosExpandirColaPrioridad.isEmpty()){

			ste = (Nodo)nodosExpandirColaPrioridad.remove(); 

			if(objetivo(ste.getEstado())){
				imprimirArbol(nodoInicial,1,ste);
				return solucion(ste);
			}

			if(precondicion("comer", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.comer();
				nodosExpandirColaPrioridad.add(tempNode=new Nodo(temp,"comer",ste));
				ste.addHijos(tempNode);

			}

			if(precondicion("pelear", ste)){
				temp =(Estado) ste.getEstado().clone();
				temp.pelear();
				nodosExpandirColaPrioridad.add(tempNode=new Nodo(temp,"pelear",ste));
				ste.addHijos(tempNode);
			}			

			if(precondicion("arriba", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.arriba();
				nodosExpandirColaPrioridad.add(tempNode=new Nodo(temp,"arriba",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("abajo", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.abajo();
				nodosExpandirColaPrioridad.add(tempNode=new Nodo(temp,"abajo",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("derecha", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.derecha();
				nodosExpandirColaPrioridad.add(tempNode=new Nodo(temp,"derecha",ste));
				ste.addHijos(tempNode);
			}

			if(precondicion("izquierda", ste)){
				temp = (Estado) ste.getEstado().clone();
				temp.izquierda();
				nodosExpandirColaPrioridad.add(tempNode=new Nodo(temp,"izquierda",ste));
				ste.addHijos(tempNode);
			}

		}

		return new LinkedList();
	}	

	/**
	 * Averigua si el estado es un estado objetivo
	 * @param e Estado actual en el arbol de busqueda.
	 */	
	private static boolean objetivo(Estado e){
		return e.todoComidaConsumidaEnemigosMuertos();
	}

	/*Imprime una representacion en String
	 * del proceso de busqueda
	 * */
	private static void imprimirArbol(Nodo n,int tab, Nodo f) {
		List hijos = n.getListHijos();
		Iterator i = hijos.iterator();
		for(int x=0;x<tab;x++)
			System.out.print("-*-");
		System.out.print(n.getAccion() + " " + n.costo);
		if (n == f) System.out.print("  NODO FINAL");
		System.out.println();
		while(i.hasNext()){
			n = (Nodo)i.next();
			imprimirArbol(n,tab + 1, f);
		}

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
		}

		if(a == "arriba"){
			if(e.accionPosRepetida("arriba"))
				return false;			
			if(e.accionRepetida("arriba",1))
				return false;
			if(e.ultimaMovimiento("abajo"))
				return false;
		}

		if(a == "abajo"){
			if(e.accionPosRepetida("abajo"))
				return false;
			if(e.accionRepetida("abajo",1))
				return false;
			if(e.ultimaMovimiento("arriba"))
				return false;
		}

		if(a == "derecha"){
			if(e.accionPosRepetida("derecha"))
				return false;
			if(e.accionRepetida("derecha",1))
				return false;		
			if(e.ultimaMovimiento("izquierda"))
				return false;
		}

		if(a == "izquierda"){
			if(e.accionPosRepetida("izquierda"))
				return false;
			if(e.accionRepetida("izquierda",1))
				return false;			
			if(e.ultimaMovimiento("derecha"))
				return false;
		}

		return true;


	}



	private static LinkedList solucion(Nodo n){
		//System.out.println("COSTO " + n.costo);
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
		LinkedList Hijos;

		public Nodo(Estado estado, String accion, Nodo padre) {
			super();
			this.padre = padre;
			this.estado = estado;
			this.accion = accion;
			this.Hijos= new LinkedList();
			this.profundidad = padre.getProfundidad() + 1;
			this.costo = padre.getCosto() + this.costoAccion(accion, estado);
		}

		public Nodo(Estado estado) {
			super();
			this.padre = null;
			this.estado = estado;
			this.accion = "";
			this.Hijos= new LinkedList();
			this.profundidad = 0;
			this.costo = 0;

		}

		public void addHijos(Nodo h)
		{
			if (h!=null)
				Hijos.add(h);
		}
		public List getListHijos()
		{
			return Hijos;

		}
		private int costoAccion(String accion, Estado e) {
			if(accion == "comer")
				return 1;

			if(accion == "pelear" && e.getEnergiaActual() > 5)
				return 2;
			if(accion == "pelear" && e.getEnergiaActual() <= 5)
				return 500;

			if(padre.getAccion() == "comer" )
				return 1;
			if(padre.getAccion() == "pelear" )
				return 2;
			
			return 20000;

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

		public int getCosto() {
			return costo;
		}

		public void setCosto(int costo) {
			this.costo = costo;
		}
	}

}
