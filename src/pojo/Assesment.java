package pojo;

import java.util.ArrayList;
import java.util.List;

//Plain old java object
public class Assesment {
	private String grupo;
	private boolean activo;
	private String titulo;
	private String instrucciones;
	private List<Pregunta> preguntas;

	
	public Assesment(){
		preguntas= new ArrayList<Pregunta>();
	}
	
	public String getGrupo() {
		return grupo;
		
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public void addPreguntas(Pregunta pregunta){
		preguntas.add(pregunta);
	}
	

}

