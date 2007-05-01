package agente;

import java.util.Vector;

public class Busqueda {

	public Busqueda() {
		// TODO Auto-generated constructor stub
	}
	
	static Vector buscar(Estado e){
		Nodo nodo = new Nodo(e);
		
		return buscar2(nodo);
	}
	
	private static Vector buscar2(Nodo n){
		if(objetivo(n.getEstado())){
			return solucion(n);
		}
		
		if(n.getProfundidad() < 10){
			Estado temp =(Estado) n.getEstado().clone();
			temp.arriba();
			Nodo nodo = new Nodo(temp,"arriba",n);
			Vector v = buscar2(nodo); 
			if(v != null){
				return v; 
			}
			
			temp =(Estado) n.getEstado().clone();
			temp.abajo();
			nodo = new Nodo(temp,"abajo",n);
			v = buscar2(nodo); 
			if(v != null){
				return v; 
			}

			temp =(Estado) n.getEstado().clone();
			temp.derecha();
			nodo = new Nodo(temp,"derecha",n);
			v = buscar2(nodo); 
			if(v != null){
				return v; 
			}
			
			temp =(Estado) n.getEstado().clone();
			temp.izquierda();
			nodo = new Nodo(temp,"izquierda",n);
			v = buscar2(nodo); 
			if(v != null){
				return v; 
			}
			
			temp =(Estado) n.getEstado().clone();
			temp.comer();
			nodo = new Nodo(temp,"comer",n);
			v = buscar2(nodo); 
			if(v != null){
				return v; 
			}
			
			temp =(Estado) n.getEstado().clone();
			temp.pelear();
			nodo = new Nodo(temp,"pelear",n);
			v = buscar2(nodo); 
			if(v != null){
				return v; 
			}			
		}

		return null;
		
		
	}
	
	private static boolean objetivo(Estado e){
		return e.todoConocido();
	}
	
	private static Vector solucion(Nodo n){
		return new Vector();
		
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
			// TODO Auto-generated method stub
			return 0;
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
