package agente;

import java.util.Vector;

public class MundoPercibido {
	Vector posiciones;
	public MundoPercibido() {
		posiciones = new Vector();
		Vector celdas = new Vector();
		for(int i = 0; i < 4; i++){
			celdas.add(new Integer(-1));
		}
		
		for (int i = 0; i < 4; i++){
			posiciones.add(celdas.clone());	
		}
		
		
	}

	public void actualizarCelda(int x, int y, int valor) {
		((Vector)posiciones.elementAt(x)).setElementAt(new Integer(valor), y);
		
	}
	
	public Vector getPosiciones(){
		return posiciones;
	}
	
	public static void main(String[] args) {
		MundoPercibido a = new MundoPercibido();
		System.out.print(a.getPosiciones());
		
	}

}
