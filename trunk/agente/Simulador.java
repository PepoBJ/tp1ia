package agente;

import java.util.Iterator;
import java.util.Vector;

import calculador.Calculador;
import calculador.Pair;

public class Simulador {
	public int performance;
	private Calculador calculador;
	private Agente agente;
	static int MAX_CICLOS = 80;
	public Simulador() {
		this.calculador = new Calculador();

		Vector enemigos = this.calculador.inicializarEnemigo();
		Vector comida   = this.calculador.inicializarComida();
		Pair posicion = this.calculador.getPosicionInicial();
		
		//this.imprimir_ambiente(enemigos, comida, posicion);
		
		this.agente = new Agente();
		int energia = this.calculador.calcularEnergiaPacMan();
		int ciclo = 0;
		while (ciclo < MAX_CICLOS) {
			
			
			Percepcion percepcion = new Percepcion(posicion,energia,comida,enemigos);
			String accion = this.agente.accion(percepcion);
			
			//System.out.println(" energia:"+energia+" posicion:"+posicion.x()+","+posicion.y());
			//System.out.println(ciclo+") "+percepcion);
			//System.out.println(" accion:"+accion);
			
			if (accion == "terminar") {
				this.performance=this.calculador.getPerformance();
				System.out.print(" performance: "+this.performance);
				break;
			}
			energia = this.calculador.calcularEnergiaPacMan(accion);
			posicion = this.calcularNuevaPosicion(posicion, accion);
			ciclo++;
		}
		
	}

	 
	
	private void imprimir_ambiente(Vector enemigos, Vector comida, Pair posicion) {
		// System.out.println(posicion.x() + " " + posicion.y());
		boolean encontrado;
		for(int j=1; j < 5 ;j++){
			for(int i=1;i < 5;i++){
	
				encontrado = false;
				Pair p = new Pair(j,i);
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
	}



	private Pair calcularNuevaPosicion(Pair posicion, String accion) {
		int x = posicion.x();
		int y = posicion.y();
		
		if (accion == "arriba") {
			x -= 1;
			if (x<1) {				
				x = 4;
			}
		} else if (accion == "abajo") {
			x += 1;
			if (x > 4) {				
				x=1;
			}
		} else if (accion == "izquierda" ) {
			y -= 1;
			if (y<1) {
				y=4;
			}
		} else if (accion == "derecha") {
			y += 1;
			if (y>4){
				y=1;
			}
		}
		
		Pair p = new Pair(x,y);			
		return p;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*String perform="";
		float performNro=0;
		for(int i=0; i<200;i++){
			Simulador s = new Simulador();
			perform += "\n" + s.performance;
			performNro+=s.performance;
		}
		System.out.println(perform);
		
		System.out.print(performNro/200);*/
		Simulador s = new Simulador();
	}

}
