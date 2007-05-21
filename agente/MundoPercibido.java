package agente;

import java.util.Vector;

import calculador.Pair;

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
		x-=1;
		y-=1;
		Integer t = (Integer)((Vector)posiciones.elementAt(x)).elementAt(y);
		return t.intValue();
	}
	
	public Vector getPosiciones(){
		return posiciones;
	}
	
	public String toString(Pair2 p){
		String result = "";
		String c;
		for(int i = 1; i < 5; i++){
			for(int j= 1; j < 5; j++){
				c = new Integer(getCeldaAt(i, j)).toString();
				if(p.x() == i && p.y() == j){
					c = " P";
				}else if(getCeldaAt(i, j) != -1)
					c = " " + new Integer(getCeldaAt(i, j)).toString();
				result += c + " ";
			}
			result+="\n";
			
		}
		return result;
	}

	/**
	 * Se utiliza este metodo en lugar de clone()  
	 * porque clone() no clona bien :)
	 */
	public Vector clonarPosiciones(){
		Vector result = new Vector();
		Vector temp;
		for(int i=0; i < 4; i++){
			temp = (Vector)((Vector)posiciones.elementAt(i)).clone();
			result.add(temp);
		}
		
		return result;
	}
	
	
	public Object clone() {
		MundoPercibido m = new MundoPercibido();
		m.posiciones = this.clonarPosiciones();
		return m;		
	}
	

}
