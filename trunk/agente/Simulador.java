package agente;

import java.util.Iterator;
import java.util.Vector;

import calculador.Calculador;
import calculador.Pair;

public class Simulador {
	private Calculador calculador;
	private Agente agente;
	static int MAX_CICLOS = 80;
	public Simulador() {
		this.calculador = new Calculador();

		Vector enemigos = this.calculador.inicializarEnemigo();
		Vector comida   = this.calculador.inicializarComida();
		Pair posicion = this.calculador.getPosicionInicial();

		boolean encontrado;
		for(int j=4; j >0 ;j--){
			for(int i=1;i<5;i++){
	
				encontrado = false;
				Pair p = new Pair(i,j);
				Iterator t = comida.iterator();		
				while (t.hasNext()) {
					Pair p2 = (Pair) t.next();
					if (p2.x() == p.x() && p2.y() == p.y()) {
						encontrado = true;
						System.out.print(" 1");			
					}
				}		
				
				t = enemigos.iterator();		
				while (t.hasNext()) {
					Pair p2 = (Pair) t.next();
					if (p2.x() == p.x() && p2.y() == p.y()) {
						encontrado = true;
						System.out.print(" 2");				
					}
				}
				if(p.equal(posicion)){
					encontrado = true;
					System.out.print(" P");
				}
				if(!encontrado)
					System.out.print(" 0");
			}
			System.out.println();
		}
		
		this.agente = new Agente();
		// Adivinamos inicialmente que 50 es la energia con la que empieza
		//int energia  = 50;
		int energia = this.calculador.calcularEnergiaPacMan();
		int ciclo = 0;
		while (ciclo < MAX_CICLOS) {
			System.out.println(" energia:"+energia+" posicion:"+posicion.x()+","+posicion.y());
			
			Percepcion percepcion = new Percepcion(posicion,energia,comida,enemigos);
			System.out.println(ciclo+") "+percepcion);
			
			String accion = this.agente.accion(percepcion);
			
			System.out.println(" accion:"+accion);
			if (accion == "terminar") {
				System.out.print(" performance: "+this.calculador.getPerformance());
				break;
			}
			energia = this.calculador.calcularEnergiaPacMan(accion);
			posicion = this.calcularNuevaPosicion(posicion, accion);
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
