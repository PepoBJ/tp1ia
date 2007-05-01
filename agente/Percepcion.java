package agente;

import ia.PairWrapper;

import java.util.Vector;

public class Percepcion {

	private PairWrapper posicionActual;
	private int energia; 
	private int izq;
	private int der;
	private int abajo;
	private int arriba;

	public Percepcion(PairWrapper posicionActual, int energia, Vector comida, Vector enemigos) {
		super();
		this.posicionActual = posicionActual;
		this.energia = energia;
		
		
		this.izq = this.process(posicionActual.x()-1, posicionActual.y(), comida, enemigos);
		this.der = this.process(posicionActual.x()+1, posicionActual.y(), comida, enemigos);
		this.abajo = this.process(posicionActual.x(), posicionActual.y()-1, comida, enemigos);
		this.arriba = this.process(posicionActual.x(), posicionActual.y()+1, comida, enemigos);
	}

	public int process(int x, int y, Vector comida, Vector enemigos) {
		PairWrapper p = new PairWrapper(x,y);
		if (comida.contains(p)) {			
			return 1;
		}
		
		if (enemigos.contains(p)) {
			return 2;
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

	public PairWrapper getPosicionaActual() {
		return posicionActual;
	}



}
