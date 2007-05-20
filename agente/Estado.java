package agente;

import java.util.*;


import calculador.Pair;

public class Estado {
	private Percepcion ultimaPercepcion;
	private int energiaActual;
	private int energiaAnterior;
	private MundoPercibido mundoPercibido;
	private ExperienciaEnergetica experienciaEnergetica;
	private Pair2 posicionActual;
	private LinkedList secuenciaAccionPos;
	private String recorrido;
	
	public Estado() {
		mundoPercibido = new MundoPercibido();
		experienciaEnergetica = new ExperienciaEnergetica();
		secuenciaAccionPos = new LinkedList();
		recorrido = "";
	}

	public void actualizar(Percepcion p) {
		ultimaPercepcion = p;
		posicionActual = new Pair2(p.getPosicionActual());
		energiaAnterior = energiaActual;
		energiaActual = p.getEnergia();
		
		int x = posicionActual.x();
		int y = posicionActual.y();
		
		if(mundoPercibido.getCeldaAt(x, y - 1) == -1)
			mundoPercibido.actualizarCelda(x,y - 1,p.getAbajo());
		if(mundoPercibido.getCeldaAt(x, y + 1) == -1)
			mundoPercibido.actualizarCelda(x,y + 1,p.getArriba());
		if(mundoPercibido.getCeldaAt(x + 1, y) == -1)
			mundoPercibido.actualizarCelda(x + 1,y,p.getDer());
		if(mundoPercibido.getCeldaAt(x - 1, y) == -1)
			mundoPercibido.actualizarCelda(x - 1,y,p.getIzq());

		
	}
	
	/**
	 * Agrega la accion ejecutada y la posicion actual a  
	 * la secuencia de acciones
	 * @param accion accion ejecutada.
	 */

	public void agregarAccionPos(String accion) {
		LinkedList accionPos = new LinkedList();
		accionPos.add( new Pair(this.posicionActual.x(),this.posicionActual.y()) );
		accionPos.add( accion );
		
		secuenciaAccionPos.add(accionPos);		
	}

	/**
	 * Determina si todo el mundo del Pacman es  
	 * conocido. O sea que no haya ningun -1
	 */

	public boolean todoConocido() {
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				if(mundoPercibido.getCeldaAt(i, j) == -1)
					return false;
			}
		}
		return true;
	}

	/**
	 * Determina si todos los enemigos del Pacman estan   
	 * muertos. O sea que no haya ningun -1 y 2
	 */
	
	public boolean todoEnemigosMuertos() {
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				if(mundoPercibido.getCeldaAt(i, j) == -1 ||
				   mundoPercibido.getCeldaAt(i, j) == 2	
				)
					return false;
			}
		}
		return true;	
	}
	
	public boolean todoComidaConsumida() {
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				if(mundoPercibido.getCeldaAt(i, j) == -1 ||
				   mundoPercibido.getCeldaAt(i, j) == 1 
				)
					return false;
			}
		}
		return true;	
	}
	
	public boolean todoComidaConsumidaEnemigosMuertos() {
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				if(mundoPercibido.getCeldaAt(i, j) == -1 ||
				   mundoPercibido.getCeldaAt(i, j) == 1  ||
				   mundoPercibido.getCeldaAt(i, j) == 2	
				)
					return false;
			}
		}
		return true;	
	}
	
	/**
	 * Setea todas las posiciones adyacentes a las del Pacman  
	 * en 0 a efectos de la simulacion
	 */
	private void posicionesAdyacentes(){
		int arribaY = posicionActual.y() + 1;
		int arribaX = posicionActual.x();
		int arribaValor = mundoPercibido.getCeldaAt(arribaX, arribaY);
		if(arribaValor == -1) arribaValor = 0;
		
		int abajoY = posicionActual.y() - 1;
		int abajoX = posicionActual.x();
		int abajoValor = mundoPercibido.getCeldaAt(abajoX, abajoY);
		if(abajoValor == -1) abajoValor = 0;		
		
		int derechaX = posicionActual.x() + 1;
		int derechaY = posicionActual.y();
		int derechaValor = mundoPercibido.getCeldaAt(derechaX, derechaY);
		if(derechaValor == -1) derechaValor = 0;		
	
		
		int izquierdaX = posicionActual.x() - 1;
		int izquierdaY = posicionActual.y();
		int izquierdaValor = mundoPercibido.getCeldaAt(izquierdaX, izquierdaY);
		if(izquierdaValor == -1) izquierdaValor = 0;		
		
		mundoPercibido.actualizarCelda(arribaX, arribaY, arribaValor);
		mundoPercibido.actualizarCelda(abajoX, abajoY, abajoValor);
		mundoPercibido.actualizarCelda(derechaX, derechaY, derechaValor);
		mundoPercibido.actualizarCelda(izquierdaX, izquierdaY, izquierdaValor);
	}
	
	/**
	 * Los Stes metodos solo son utilizados para la simulacion
	 */

	public void arriba() {
		int temp = posicionActual.y() + 1;
		if (temp == 5) {temp = 1;}
				
		agregarAccionPos("arriba");		
		posicionActual.setY(temp);		
		posicionesAdyacentes();
	}

	public void abajo() {
		int temp = posicionActual.y() - 1;
		if(temp == 0) temp = 4;
				
		agregarAccionPos("abajo");
		posicionActual.setY(temp);
		posicionesAdyacentes();
	}

	public void derecha() {
		int temp = posicionActual.x() + 1;
		if(temp == 5) temp = 1;
		
		agregarAccionPos("derecha");
		posicionActual.setX(temp);
		posicionesAdyacentes();
	}

	public void izquierda() {
		int temp = posicionActual.x() - 1;
		if(temp == 0) temp = 4;
		
		agregarAccionPos("izquierda");
		posicionActual.setX(temp);
		posicionesAdyacentes();
	}

	public void comer() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
		this.energiaActual += 10;
	}
	
	public void comerAccion() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
	}

	public void pelear() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
		this.energiaActual -= 10;
	}

	public void pelearAccion() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
	}
	
	/**
	 * Determina si ya se realizo accion estando  
	 * el Pacman en la Posicion Actual
	 * @param accion accion repetida.
	 */
	public boolean accionPosRepetida(String accion) {
		Iterator i = secuenciaAccionPos.iterator();
		while(i.hasNext()){
			LinkedList l = (LinkedList)i.next();
			Pair p = (Pair)l.get(0);
			String a = (String)l.get(1);
			if(p.x() == posicionActual.x() && 
			   p.y() == posicionActual.y() && 
			   a == accion){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determina si una accion se repitio cant veces 
	 * al final de la secuencia de acciones
	 * @param accion accion repetida.
	 * @param cant cantidad de veces.
	 */
	public boolean accionRepetida(String accion, int cant) {
		int tamSec = secuenciaAccionPos.size();
		if(tamSec < cant) return false;
		
		LinkedList l;
		for( int i = 0; i < cant; i++){
			l = (LinkedList) secuenciaAccionPos.get(tamSec - i -1);
			String a = (String)l.get(1);
			if(!a.equals(accion)) return false;
		}
		return true;
	}

	/**
	 * Se fija si la ultima movimiento llevado a
	 * cabo es accion (arriba, abajo, derecha, izquierda).
	 * @param accion es accion la ultima accion?.
	 */
	public boolean ultimaMovimiento(String accion){
		if(secuenciaAccionPos.isEmpty())
			return false;
		
		LinkedList l = (LinkedList) secuenciaAccionPos.getLast();
		String a = (String) l.get(1); 
		if(a.equals("pelear")  || a.equals("comer")){
			l = (LinkedList) secuenciaAccionPos.getLast();
			a = (String) l.get(1); 
		}
		if(a.equals(accion))
			return true;
		return false;
	}

	/**
	 * Se fija si hay comida en la celda en la que esta
	 * parado el Pacman
	 */
	public boolean hayComida(){
		if(mundoPercibido.getCeldaAt(posicionActual.x(), posicionActual.y()) == 1)
			return true;
		return false;
	}

	/**
	 * Se fija si hay enemigo en la celda en la que esta
	 * parado el Pacman
	 */
	public boolean hayEnemigo(){
		if(mundoPercibido.getCeldaAt(posicionActual.x(), posicionActual.y()) == 2)
			return true;
		return false;
	}	

	public int getEnergiaActual() {
		return energiaActual;
	}

	public void setEnergiaActual(int energiaActual) {
		this.energiaActual = energiaActual;
	}

	public int getEnergiaAnterior() {
		return energiaAnterior;
	}

	public void setEnergiaAnterior(int energiaAnterior) {
		this.energiaAnterior = energiaAnterior;
	}

	public ExperienciaEnergetica getExperienciaEnergetica() {
		return experienciaEnergetica;
	}

	public void setExperienciaEnergetica(ExperienciaEnergetica experienciaEnergetica) {
		this.experienciaEnergetica = experienciaEnergetica;
	}

	public MundoPercibido getMundoPercibido() {
		return mundoPercibido;
	}

	public void setMundoPercibido(MundoPercibido mundoPercibido) {
		this.mundoPercibido = mundoPercibido;
	}

	public Pair2 getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Pair posicionActual) {
		this.posicionActual = new Pair2(posicionActual);
	}	
	
	public Object clone(){
		Estado e = new Estado();
		e.energiaActual = this.energiaActual;
		e.energiaAnterior = this.energiaAnterior;
		e.mundoPercibido = (MundoPercibido) this.mundoPercibido.clone();
		e.experienciaEnergetica = (ExperienciaEnergetica) this.experienciaEnergetica.clone();
		e.posicionActual = new Pair2(this.posicionActual);
		e.secuenciaAccionPos = (LinkedList)secuenciaAccionPos.clone();
		return e;
	}

	public Object getRecorrido() {
		// TODO Auto-generated method stub
		return recorrido;
	}

	public void actualizarRecorrido(String accion) {
		this.recorrido += "\n"  
					   + " " + ultimaPercepcion.toString() + "\n" 
		               + "energia: " + energiaActual  + "\n" + mundoPercibido.toString(posicionActual) + accion;
		
	}
	


	
}
