package agente;

import java.util.*;


import calculador.Pair;

public class Estado {

	private int energiaActual;
	private int energiaAnterior;
	private MundoPercibido mundoPercibido;
	private ExperienciaEnergetica experienciaEnergetica;
	private Pair posicionActual;
	private LinkedList secuenciaAccionPos;
	
	public Estado() {
		mundoPercibido = new MundoPercibido();
		experienciaEnergetica = new ExperienciaEnergetica();
		secuenciaAccionPos = new LinkedList();		
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

	public Pair getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Pair posicionActual) {
		this.posicionActual = posicionActual;
	}

	public void actualizar(Percepcion p) {
		posicionActual = p.getPosicionaActual();
		energiaAnterior = energiaActual;
		energiaActual = p.getEnergia();
		
		int x = posicionActual.x();
		int y = posicionActual.y();
		mundoPercibido.actualizarCelda(x,y - 1,p.getAbajo());
		mundoPercibido.actualizarCelda(x,y + 1,p.getArriba());
		mundoPercibido.actualizarCelda(x + 1,y,p.getDer());
		mundoPercibido.actualizarCelda(x - 1,y,p.getIzq());

		System.out.println(mundoPercibido.toString(posicionActual));
	}
	
	public void agregarAccionPos(String accion) {
		LinkedList accionPos = new LinkedList();
		accionPos.add( new Pair(this.posicionActual.x(),this.posicionActual.y()) );
		accionPos.add( accion );
		
		secuenciaAccionPos.add(accionPos);		
	}
	
	public boolean todoConocido() {
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				if(mundoPercibido.getCeldaAt(i, j) == -1)
					return false;
			}
		}
		return true;
	}
	
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
	
	//	setY() setea Columnas no Posiciones!!!!
	public void arriba() {
		int temp = posicionActual.y() + 1;
		if (temp == 5) {temp = 1;}
		temp-=1;
		
		agregarAccionPos("arriba");		
		posicionActual.setY(temp);		
		posicionesAdyacentes();

		//System.out.println("arriba");
		//System.out.println(mundoPercibido.toString(posicionActual));
	}

	public void abajo() {
		int temp = posicionActual.y() - 1;
		if(temp == 0) temp = 4;
		temp-=1;
		
		agregarAccionPos("abajo");
		posicionActual.setY(temp);
		posicionesAdyacentes();
		//System.out.println("abajo");
		//System.out.println(mundoPercibido.toString(posicionActual));		
		
	}

	public void derecha() {
		int temp = posicionActual.x() + 1;
		if(temp == 5) temp = 1;
		temp-=1;

		agregarAccionPos("derecha");
		posicionActual.setX(temp);
		posicionesAdyacentes();
		//System.out.println("derecha");
		//System.out.println(mundoPercibido.toString(posicionActual));		
	}

	public void izquierda() {
		int temp = posicionActual.x() - 1;
		if(temp == 0) temp = 4;
		temp-=1;

		agregarAccionPos("izquierda");
		posicionActual.setX(temp);
		posicionesAdyacentes();
		//System.out.println("izquierda");
		//System.out.println(mundoPercibido.toString(posicionActual));		
	}

	public void comer() {
		// TODO Auto-generated method stub
		
	}

	public void pelear() {
		// TODO Auto-generated method stub
		
	}
	
	public Object clone(){
		Estado e = new Estado();
		e.energiaActual = this.energiaActual;
		e.energiaAnterior = this.energiaAnterior;
		e.mundoPercibido = (MundoPercibido) this.mundoPercibido.clone();
		e.experienciaEnergetica = (ExperienciaEnergetica) this.experienciaEnergetica.clone();
		e.posicionActual = new Pair(this.posicionActual.x(),this.posicionActual.y());
		e.secuenciaAccionPos = (LinkedList)secuenciaAccionPos.clone();
		return e;
	}

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

}
