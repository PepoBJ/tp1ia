package agente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import jpl.Atom;
import jpl.Compound;
import jpl.Integer;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class AgenteProlog{
	private Estado estado;
	private List acciones = new LinkedList();
	private Compound sit = new Atom("cero");
	
	public AgenteProlog(Percepcion p) {
		estado = new Estado();
		//acciones.add("0");
		posicionInicial(p);
	}
	
	public AgenteProlog() {
	
	}
	
	public void posicionInicial(Percepcion p){
		
		//LEE TODO LO QUE HAY EN EL ARCHIVO ORIGINAL DE PROLOG
		StringBuffer buffer = new StringBuffer();
		String line;
		FileReader fReader;
		BufferedReader bReader;
		
		try {
			fReader = new FileReader("tp2.pl");
			bReader = new BufferedReader(fReader);
			while ((line = bReader.readLine()) != null){
				buffer.append(line);
				buffer.append("\n");
			}
			bReader.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String posicionAgente = "agente(" + p.getPosicionActual().x() + "," + p.getPosicionActual().y() + "," + "cero).";
		String baseProlog = buffer.toString();
		
		//ESCRIBE EL NUEVO ARCHIVO
		FileWriter fWriter;
		BufferedWriter bWriter;
		
		try {
			fWriter = new FileWriter("temp.pl");
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(posicionAgente);
			bWriter.newLine();
			bWriter.write(baseProlog);
			bWriter.close();
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		/*String percepcionString = this._codificarPercepcion(p);
		this._actualizarPercepcionProlog(percepcionString);
		acciones.add("0");*/
	}
	
	public String accion(Percepcion p){
		this.actualizarEstado(p);
		
		Percepcion pe = this.estado.arreglarPercepcion();
		String percepcionString = this._codificarPercepcion(pe);
		System.out.println("percepcion string: " + percepcionString);
		
		this._actualizarPercepcionProlog(percepcionString);
		
		String accion = this.elegirMejorAccion();
		
		System.out.println(accion);
		
		acciones.add(accion);
		
		
	    if(accion.equals("irArriba"))
	    	accion = "arriba";
	    if(accion.equals("irAbajo"))
	    	accion = "abajo";
	    if(accion.equals("irIzquierda"))
	    	accion = "izquierda";
  	    if(accion.equals("irDerecha"))
   	    	accion = "derecha"; 
  	    if(accion.equals("comer"))
  	    	accion = "comer";
  	    if(accion.equals("pelear"))
  	    	accion = "pelear";
  	    
		this.actualizarEstado2(accion);
		
		estado.imprimirMundo();
		
		return accion;
	}
	
	private String _codificarPercepcion(Percepcion p) {
		String result = "percepcion([";
		result += p.getIzq() + ",";
		result += p.getDer() + ",";
		result += p.getArriba() + ",";
		result += p.getAbajo() + ",";
		result += p.getEnergia();
		result += "]," + this._codificarSituacion();
		result += ").";
		return result;
	}
	
	private String _codificarSituacion(){
		System.out.println("acciones: " + acciones.size());
		if(acciones.size() == 0)
			return "cero";
		
		String result = "";
		String coma;
		for(int i = acciones.size() - 1; i >= 0; i--){
			String accion = (String)acciones.get(i);

				result += "res(" + accion + ",";
		}
		
		result += "cero";
		
		for(int j = 0; j < acciones.size(); j++){
			result += ")";	
		}
		return result;		
	}

	private void actualizarEstado(Percepcion p){
		estado.actualizar(p);
	}
	
	private String elegirMejorAccion(){
		String accion = null;
		Variable X = new Variable("X");
		
	    Query q1 =
	        new Query(
	            "consult",
	            new Term[] {new Atom("temp.pl")}
	        );

	    q1.query();
	    
	    if(!acciones.isEmpty()){
	    	String ultimaAccion = (String)acciones.get(acciones.size() - 1);
	       	sit = new Compound("res", 
	    			new Term[] {new Atom(ultimaAccion), sit});	    	
	    }

	    Compound c =
	        new Compound(
	            "siguiente_accion",
	            new Term[] {X,sit}
	        );
	    
	    Query q = new Query(c);
	    
	    Hashtable solution;
		   
	    if (q.hasSolution()){		    
		    solution = q.oneSolution();	
		    System.out.println( "X = " + solution.get("X"));		    
		    accion = solution.get("X").toString();	    			
	    }else{
	    	System.out.println("error");
			System.exit(1);
	    }	

	    return accion;
	}
	
	private void _actualizarPercepcionProlog(String p) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("temp.pl", true));
			bw.write(p);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {                       
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				
			}
		} 
		
		
	}

		
	private void actualizarEstado2(String accion){		
		this.estado.agregarAccionPos(accion);
		this.estado.actualizarRecorrido(accion);
		
		this.estado.agregarAccionPos(accion);
		
		if(accion.equals("comer")){
			estado.comerAccion();
		}
		
		if(accion.equals("pelear")){
			estado.pelearAccion();
		}
		
		if(accion.equals("arriba")){
			estado.arribaAccion();
		}
		
		if(accion.equals("abajo")){
			estado.abajoAccion();
		}
		
		if(accion.equals("derecha")){
			estado.derechaAccion();
		}
		if(accion.equals("izquierda")){
			estado.izquierdaAccion();
		}
	}

}
