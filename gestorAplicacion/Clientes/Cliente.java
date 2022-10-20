package gestorAplicacion.Clientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Cliente {

	private String nombre;
	private String cedula;
	private String telefono;
	public static Map<String, Cliente> mapaClientes = new HashMap<>();
	public static int cantidadClientes = 0;
	public static Map<String, ArrayList<Mascota>> mascotas = new HashMap<>();
	
	public Cliente(String nombre, String cedula, String telefono) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		cantidadClientes++;
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
	public void setCedula(String id) {
		this.cedula = id;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	public String toString() {
		return "nombre: " + nombre + "\n" + "cedula: " + cedula + "\n" + "telefono: " + telefono;
	}
	
}
