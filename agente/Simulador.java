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
			// TODO: 0,0,0,0 debería ser las celdas adjacentes basadas en 
			// enemigos o comida...
			Percepcion percepcion = new Percepcion(posicion,energia,0,0,0,0);
			
			String accion = this.agente.accion(percepcion)
			
			energia = this.calculador.calcularEnergiaPacMan(accion);
			posicion = this.calcularNuevaPosicion(posicion, accion);
		}
	}

	 
	
	private PairWrapper calcularNuevaPosicion(PairWrapper posicion, String accion) {
		int x = posicion.x();
		int y = posicion.y();
		
		switch (accion) {
		case "arriba":
			y += 1; 
			break;
		case "abajo":
			y -= 1; 
			break;
		case "izquierda":
			x -= 1; 
			break;
		case "derecha":
			x += 1; 
			break;
		default:
			break;
		}
		PairWrapper p = new PairWrapper(x,y);			
		return p;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

}
