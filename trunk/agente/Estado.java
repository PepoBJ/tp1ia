package agente;

import java.util.Iterator;
import java.util.LinkedList;

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
		
		if(mundoPercibido.getCeldaAt(x + 1, y) == -1)
			mundoPercibido.actualizarCelda(x + 1,y,p.getAbajo());
		if(mundoPercibido.getCeldaAt(x - 1, y) == -1)
			mundoPercibido.actualizarCelda(x - 1,y,p.getArriba());
		if(mundoPercibido.getCeldaAt(x, y + 1) == -1)
			mundoPercibido.actualizarCelda(x,y + 1,p.getDer());
		if(mundoPercibido.getCeldaAt(x, y - 1) == -1)
			mundoPercibido.actualizarCelda(x,y - 1,p.getIzq());

		
	}
	
	/**
	 * Agrega la accion ejecutada y la posicion actual a  
	 * la secuencia de acciones
	 * @param accion accion ejecutada.
	 */

	public void agregarAccionPos(String accion) {
		LinkedList accionPos = new LinkedList();
		accionPos.add( new Pair2(this.posicionActual.x(),this.posicionActual.y()) );
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
		int arribaY = posicionActual.y();
		int arribaX = posicionActual.x() - 1;
		int arribaValor = mundoPercibido.getCeldaAt(arribaX, arribaY);
		if(arribaValor == -1) arribaValor = 0;
		
		int abajoY = posicionActual.y();
		int abajoX = posicionActual.x() + 1;
		int abajoValor = mundoPercibido.getCeldaAt(abajoX, abajoY);
		if(abajoValor == -1) abajoValor = 0;		
		
		int derechaX = posicionActual.x();
		int derechaY = posicionActual.y() + 1;
		int derechaValor = mundoPercibido.getCeldaAt(derechaX, derechaY);
		if(derechaValor == -1) derechaValor = 0;		
	
		
		int izquierdaX = posicionActual.x();
		int izquierdaY = posicionActual.y() - 1;
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


	public void abajo() {
		int temp = posicionActual.x() + 1;
		if (temp == 5) {temp = 1;}
				
		agregarAccionPos("abajo");		
		posicionActual.setX(temp);		
		posicionesAdyacentes();
	}
	
	public void abajoAccion() {	
		agregarAccionPos("abajo");		
	}

	public void arriba() {
		int temp = posicionActual.x() - 1;
		if(temp == 0) temp = 4;
				
		agregarAccionPos("arriba");
		posicionActual.setX(temp);
		posicionesAdyacentes();
	}

	public void arribaAccion() {
		agregarAccionPos("arriba");
	}	
	
	public void derecha() {
		int temp = posicionActual.y() + 1;
		if(temp == 5) temp = 1;
		
		agregarAccionPos("derecha");
		posicionActual.setY(temp);
		posicionesAdyacentes();
	}

	public void derechaAccion() {
		agregarAccionPos("derecha");
	}

	
	public void izquierda() {
		int temp = posicionActual.y() - 1;
		if(temp == 0) temp = 4;
		
		agregarAccionPos("izquierda");
		posicionActual.setY(temp);
		posicionesAdyacentes();
	}
	
	public void izquierdaAccion() {
		agregarAccionPos("izquierda");
	}	

	public void comer() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
		this.energiaActual += 10;
		agregarAccionPos("comer");
	}
	
	public void comerAccion() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
		agregarAccionPos("comer");
	}

	public void pelear() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
		this.energiaActual -= 10;
		agregarAccionPos("pelear");
	}

	public void pelearAccion() {
		mundoPercibido.actualizarCelda(posicionActual.x(), posicionActual.y(), 0);
		agregarAccionPos("pelear");
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
			Pair2 p = (Pair2)l.get(0);
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
		
		for(int i = secuenciaAccionPos.size()-1; i >=0; i--){
			LinkedList l = (LinkedList) secuenciaAccionPos.get(i);
			String a = (String) l.get(1);

			if(a.equals("pelear")  || a.equals("comer")){
				continue; 
			}
			
			if(a.equals(accion))
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	public int cantidadEjecuciones(String accion){
		int cant = 0;
		for(int i = secuenciaAccionPos.size()-1; i >=0; i--){
			LinkedList l = (LinkedList) secuenciaAccionPos.get(i);
			String a = (String) l.get(1);
			
			if(a.equals(accion))
				cant ++;
		}
		
		return cant;
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
		               + "energia: " + energiaActual  + "\n" 
		               + "posicion: " + posicionActual.x()  + posicionActual.y() + "\n"         
		               + mundoPercibido.toString(posicionActual) + accion;
		
	}

	/*Este metodo sirve para que no vuelva a aparecer comida o enemigos
	 * si ya acabamos de comer o pelear
	 * */
	public Percepcion arreglarPercepcion() {	
		int x = posicionActual.x();
		int y = posicionActual.y();
		
		int izq = mundoPercibido.getCeldaAt(x, y - 1);
		int der = mundoPercibido.getCeldaAt(x, y + 1);
		int arriba = mundoPercibido.getCeldaAt(x - 1, y);
		int abajo = mundoPercibido.getCeldaAt(x + 1, y);
		
		Percepcion result = new Percepcion(izq, der, arriba, abajo, energiaActual);
		return result;
	}

	public void imprimirMundo() {
		System.out.println(mundoPercibido.toString(posicionActual));
	}
	


	
}
