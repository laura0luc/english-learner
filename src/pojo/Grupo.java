package pojo;

import java.io.Serializable;

public class Grupo implements Serializable{
	private String clave;
	private String nombre;
	private int grupo;
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	@Override
	public String toString() {
		return clave+"-"+grupo+" "+nombre;
	}
}
