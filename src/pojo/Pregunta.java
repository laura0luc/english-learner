package pojo;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
	
	private String titulo;
	private String instrucciones;
	private int id;
	private String material;
	private List<String> opciones;
	private String correcto;
	
	public Pregunta(){
		opciones= new ArrayList<String>();
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public List<String> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}
	public String getCorrecto() {
		return correcto;
	}
	public void setCorrecto(String correcto) {
		this.correcto = correcto;
	}
	
	public void addOpcion(String opcion){
		opciones.add(opcion);
	}
	

}
