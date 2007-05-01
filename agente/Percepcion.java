package agente;

import ia.PairWrapper;

public class Percepcion {

	private PairWrapper posicionaActual;
	private int energia; 
	private int izq;
	private int der;
	private int abajo;
	private int arriba;

	public Percepcion(PairWrapper posicionaActual, int energia, int izq, int der, int abajo, int arriba) {
		super();
		this.posicionaActual = posicionaActual;
		this.energia = energia;
		this.izq = izq;
		this.der = der;
		this.abajo = abajo;
		this.arriba = arriba;
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
		return posicionaActual;
	}



}
