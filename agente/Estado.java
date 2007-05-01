package agente;

import calculador.Pair;

public class Estado {


	
	private Pair posicion ;
	
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
		mundoPercibido.actualizarCelda(x,y + 1,p.getAbajo());
		mundoPercibido.actualizarCelda(x,y - 1,p.getArriba());
		mundoPercibido.actualizarCelda(x + 1,y,p.getDer());
		mundoPercibido.actualizarCelda(x - 1,y,p.getIzq());

	}
	
	public Object clone(){
		return new Estado();
	}

	public boolean todoConocido() {
		return false;
	}

	public void arriba() {
		// TODO Auto-generated method stub
		
	}

	public void abajo() {
		// TODO Auto-generated method stub
		
	}

	public void derecha() {
		// TODO Auto-generated method stub
		
	}

	public void izquierda() {
		// TODO Auto-generated method stub
		
	}

	public void comer() {
		// TODO Auto-generated method stub
		
	}

	public void pelear() {
		// TODO Auto-generated method stub
		
	}

}
