package agente;

import java.util.Vector;

import calculador.Calculador;
import calculador.Pair;

public class Simulador {
	private Calculador calculador;
	private Agente agente;
	static int MAX_CICLOS = 10;
	public Simulador() {
		// TODO Auto-generated constructor stub
		this.calculador = new Calculador();

		Vector enemigos = this.calculador.inicializarEnemigo();
		Vector comida   = this.calculador.inicializarComida();
		Pair posicion = this.calculador.getPosicionInicial();

		this.agente = new Agente();
		// Adivinamos inicialmente que 50 es la energia con la que empieza
		//int energia  = 50;
		int energia = this.calculador.calcularEnergiaPacMan("");
		int ciclo = 0;
		while (ciclo < MAX_CICLOS) {
			Percepcion percepcion = new Percepcion(posicion,energia,comida,enemigos);
			System.out.print(ciclo+") "+percepcion+" ");
			String accion = this.agente.accion(percepcion);
			if (accion == "terminar") {				
				break;
			}			
			energia = this.calculador.calcularEnergiaPacMan(accion);
			
			posicion = this.calcularNuevaPosicion(posicion, accion);
			
			System.out.println("energia:"+energia+" posicion:"+posicion.x()+","+posicion.y());
			ciclo++;
		}
	}

	 
	
	private Pair calcularNuevaPosicion(Pair posicion, String accion) {
		int x = posicion.x();
		int y = posicion.y();
		
		if (accion == "arriba") {
			y += 1;
			if (y>4) {				
				y = 1;
			}
		} else if (accion == "abajo") {
			y -= 1;
			if (y<1) {				
				y=4;
			}
		} else if (accion == "izquierda" ) {
			x -= 1;
			if (x<1) {
				x=4;
			}
		} else if (accion == "derecha") {
			x += 1;
			if (x>4){
				x=1;
			}
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
