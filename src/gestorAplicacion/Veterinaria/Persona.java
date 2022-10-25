package gestorAplicacion.Veterinaria;

import java.io.Serializable;

public abstract class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String cedula;
	protected String telefono;
	
	public Persona(String nombre, String cedula, String telefono) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public abstract String saludar();
	
	public String presentarse() {
		return "La persona "+this.nombre + " fue registrada";
	}

}

