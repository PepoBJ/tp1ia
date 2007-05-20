package agente;

import java.util.Iterator;
import java.util.Vector;

import calculador.Pair;

public class Percepcion {

	private Pair posicionActual;
	
	/*Valores en el ambiente en las distintas posiciones*/
	private int energia; 
	private int izq;
	private int der;
	private int abajo;
	private int arriba;

	public Percepcion(Pair posicionActual, int energia, Vector comida, Vector enemigos) {
		super();
		this.posicionActual = posicionActual;
		this.energia = energia;
		
		
		this.izq = this.process(posicionActual.x()-1, posicionActual.y(), comida, enemigos);
		this.der = this.process(posicionActual.x()+1, posicionActual.y(), comida, enemigos);
		this.abajo = this.process(posicionActual.x(), posicionActual.y()-1, comida, enemigos);
		this.arriba = this.process(posicionActual.x(), posicionActual.y()+1, comida, enemigos);
		//this.arriba = this.process(posicionActual.x(), posicionActual.y()-1, comida, enemigos);
		//this.abajo = this.process(posicionActual.x(), posicionActual.y()+1, comida, enemigos);
	}

	public int process(int x, int y, Vector comida, Vector enemigos) {
		if (x < 1) {
			x = 4;
		}
		
		if (x > 4) {
			x = 1;
		}
		
		if (y < 1){
			y = 4;
		}
			
		if (y > 4) {
			y = 1;
		}

		Pair p = new Pair(x,y);		
		
		Iterator i = comida.iterator();		
		while (i.hasNext()) {
			Pair p2 = (Pair) i.next();
			if (p2.x() == p.x() && p2.y() == p.y()) {
				return 1;				
			}
		}		
		
		i = enemigos.iterator();		
		while (i.hasNext()) {
			Pair p2 = (Pair) i.next();
			if (p2.x() == p.x() && p2.y() == p.y()) {
				return 2;				
			}
		}				
		
		return 0;
	}
	
	public int getAbajo() {
		return abajo;
	}

	public int getArriba() {
		return arriba;
	}

	public int getDer() {
		return der;
	}

	public int getEnergia() {
		return energia;
	}

	public int getIzq() {
		return izq;
	}

	public Pair getPosicionActual() {
		return posicionActual;
	}

	public String toString() {
		return new Integer(this.izq).toString() + " " +
			new Integer(this.der).toString() + " " +
			new Integer(this.arriba).toString() + " " +
			new Integer(this.abajo).toString() ;
	
	}

}
