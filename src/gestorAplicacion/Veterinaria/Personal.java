package gestorAplicacion.Veterinaria;

import java.io.Serializable;

public class Personal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String cedula;
	protected String telefono;
	
	public Personal(String nombre, String cedula, String telefono) {
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

}
