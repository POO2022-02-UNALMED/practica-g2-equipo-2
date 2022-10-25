package gestorAplicacion.Clientes;

import java.io.Serializable;

public class Animal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String especie;
	protected String nombreMascota;
	protected int edad;
	
	
	
	public Animal(String especie, String nombreMascota, int edad) {
		super();
		this.especie = especie;
		this.nombreMascota = nombreMascota;
		this.edad = edad;
	}
	public String getNombreMascota() {
		return nombreMascota;
	}
	public void setNombre(String nombre) {
		this.nombreMascota = nombre;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int sobrecargoEdad() { //teniendo en cuenta la edad, se genererá un sobrecargo en la factura. Para Animal el sobrecargo será de $1000
		return this.edad*1000; 
	}
	
	
}

