package agente;

import calculador.Pair;

class Pair2{
	private int x = 0;
	private int y = 0;
	public Pair2(Pair p){
		this.x = p.x();
		this.y = p.y();
	}
	
	public Pair2(Pair2 p){
		this.x = p.x();
		this.y = p.y();
	}

	public Pair2(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int y() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
		
}