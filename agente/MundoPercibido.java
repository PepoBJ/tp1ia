package agente;

import java.util.Vector;

public class MundoPercibido {
	Vector posiciones;
	public MundoPercibido() {
		posiciones = new Vector();
		Vector celdas = new Vector();
		for(int i = 1; i < 5; i++){
			celdas.add(new Integer(-1));
		}
		
		for (int i = 1; i < 5; i++){
			posiciones.add(celdas.clone());	
		}
		
		
	}

	public Object clone() {
		MundoPercibido m = new MundoPercibido();
		m.posiciones = (Vector) this.posiciones.clone();
		return m;		
	}
	
	public void actualizarCelda(int x, int y, int valor) {
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
		x -= 1;
		y -= 1;
		((Vector)posiciones.elementAt(x)).setElementAt(new Integer(valor), y);
		
	}
	
	public int getCeldaAt(int x, int y){
		x-=1;
		y-=1;
		Integer t = (Integer)((Vector)posiciones.elementAt(x)).elementAt(y);
		return t.intValue();
	}
	
	public Vector getPosiciones(){
		return posiciones;
	}
	
	public static void main(String[] args) {
		MundoPercibido a = new MundoPercibido();
		
	}

}
