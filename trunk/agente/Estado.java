package agente;

import calculador.Pair;

public class Estado {

	private int energiaActual;
	private int energiaAnterior;
	private MundoPercibido mundoPercibido;
	private ExperienciaEnergetica experienciaEnergetica;
	private Pair posicionActual;
	
	public Estado() {
		mundoPercibido = new MundoPercibido();
		experienciaEnergetica = new ExperienciaEnergetica();	
		
	}

	public int getEnergiaActual() {
		return energiaActual;
	}

	public void setEnergiaActual(int energiaActual) {
		this.energiaActual = energiaActual;
	}

	public int getEnergiaAnterior() {
		return energiaAnterior;
	}

	public void setEnergiaAnterior(int energiaAnterior) {
		this.energiaAnterior = energiaAnterior;
	}

	public ExperienciaEnergetica getExperienciaEnergetica() {
		return experienciaEnergetica;
	}

	public void setExperienciaEnergetica(ExperienciaEnergetica experienciaEnergetica) {
		this.experienciaEnergetica = experienciaEnergetica;
	}

	public MundoPercibido getMundoPercibido() {
		return mundoPercibido;
	}

	public void setMundoPercibido(MundoPercibido mundoPercibido) {
		this.mundoPercibido = mundoPercibido;
	}

	public Pair getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Pair posicionActual) {
		this.posicionActual = posicionActual;
	}

	public void actualizar(Percepcion p) {
		posicionActual = p.getPosicionaActual();
		energiaAnterior = energiaActual;
		energiaActual = p.getEnergia();
		
		int x = posicionActual.x();
		int y = posicionActual.y();
		mundoPercibido.actualizarCelda(x,y - 1,p.getAbajo());
		mundoPercibido.actualizarCelda(x,y + 1,p.getArriba());
		mundoPercibido.actualizarCelda(x + 1,y,p.getDer());
		mundoPercibido.actualizarCelda(x - 1,y,p.getIzq());

	}
	
	public Object clone(){
		Estado e = new Estado();
		e.energiaActual = this.energiaActual;
		e.energiaAnterior = this.energiaAnterior;
		e.mundoPercibido = (MundoPercibido) this.mundoPercibido.clone();
		e.experienciaEnergetica = (ExperienciaEnergetica) this.experienciaEnergetica.clone();
		e.posicionActual = new Pair(this.posicionActual.x(),this.posicionActual.y());
		return e;
	}
	
	
	
	public boolean todoConocido() {
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				if(mundoPercibido.getCeldaAt(i, j) == -1)
					return false;
			}
		}
		return true;
	}
	
	private void posicionesAdyacentes(){
		int arribaY = posicionActual.y() + 1;
		int arribaX = posicionActual.x();
		
		int abajoY = posicionActual.y() - 1;
		int abajoX = posicionActual.x();
		
		int derechaX = posicionActual.x() + 1;
		int derechaY = posicionActual.y();
	
		
		int izquierdaX = posicionActual.x() - 1;
		int izquierdaY = posicionActual.y();
	
		
		mundoPercibido.actualizarCelda(arribaX, arribaY, 0);
		mundoPercibido.actualizarCelda(abajoX, abajoY, 0);
		mundoPercibido.actualizarCelda(derechaX, derechaY, 0);
		mundoPercibido.actualizarCelda(izquierdaX, izquierdaY, 0);
		
	}

	public void arriba() {
		int temp = posicionActual.y() + 1;
		if (temp == 5) {
			temp = 1;
		}

		posicionActual.setY(temp);
		posicionesAdyacentes();
	}

	public void abajo() {
		int temp = posicionActual.y() - 1;
		if(temp == 0) temp = 4;

		posicionActual.setY(temp);
		posicionesAdyacentes();
	}

	public void derecha() {
		int temp = posicionActual.x() + 1;
		if(temp == 5) temp = 1;

		posicionActual.setX(temp);

		posicionesAdyacentes();
	}

	public void izquierda() {
		int temp = posicionActual.x() - 1;
		if(temp == 0) temp = 4;

		posicionActual.setX(temp);

		posicionesAdyacentes();
		
	}

	public void comer() {
		// TODO Auto-generated method stub
		
	}

	public void pelear() {
		// TODO Auto-generated method stub
		
	}

}
