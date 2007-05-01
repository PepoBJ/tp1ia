package agente;

import java.util.Vector;

import calculador.Calculador;
import calculador.Pair;

public class Simulador {
	private Calculador calculador;
	private Agente agente;
	
	public Simulador() {
		// TODO Auto-generated constructor stub
		this.calculador = new Calculador();

		Vector enemigos = this.calculador.inicializarEnemigo();
		Vector comida   = this.calculador.inicializarComida();
		Pair posicion = this.calculador.getPosicionInicial();

		this.agente = new Agente();
		// Adivinamos inicialmente que 50 es la energia con la que empieza
		int energia  = 50;
		
		while (true) {
			Percepcion percepcion = new Percepcion(posicion,energia,comida,enemigos);
			System.out.print(percepcion);
			String accion = this.agente.accion(percepcion);
			
			energia = this.calculador.calcularEnergiaPacMan(accion);
			posicion = this.calcularNuevaPosicion(posicion, accion);
			
			break;
		}
	}

	 
	
	private Pair calcularNuevaPosicion(Pair posicion, String accion) {
		int x = posicion.x();
		int y = posicion.y();
		
		if (accion == "arriba") {			
			y += 1; 
			
		} else if (accion == "abajo") {
			y -= 1;
		} else if (accion == "izquierda" ) {
			x -= 1;
		} else if (accion == "derecha") {
			x += 1;
		}
		
		Pair p = new Pair(x,y);			
		return p;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Simulador s = new Simulador();
	}

}
