package agente;

import ia.CalculadorWrapper;
import ia.PairWrapper;

import java.util.Vector;

public class Simulador {
	private CalculadorWrapper calculador;
	private Agente agente;
	
	public Simulador() {
		// TODO Auto-generated constructor stub
		this.calculador = new CalculadorWrapper();

		Vector enemigos = this.calculador.inicializarEnemigo();
		Vector comida   = this.calculador.inicializarComida();
		PairWrapper posicion = this.calculador.getPosicionInicialWrapper();

		this.agente = new Agente();
		// Adivinamos inicialmente que 50 es la energia con la que empieza
		int energia  = 50;
		
		while (true) {
			Percepcion percepcion = new Percepcion(posicion,energia,comida,enemigos);
			
			String accion = this.agente.accion(percepcion);
			
			energia = this.calculador.calcularEnergiaPacMan(accion);
			posicion = this.calcularNuevaPosicion(posicion, accion);
			
			break;
		}
	}

	 
	
	private PairWrapper calcularNuevaPosicion(PairWrapper posicion, String accion) {
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
		
		PairWrapper p = new PairWrapper(x,y);			
		return p;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Simulador s = new Simulador();
	}

}
