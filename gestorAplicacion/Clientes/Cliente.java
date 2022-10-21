package gestorAplicacion.Clientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.Veterinaria.Personal;


public class Cliente extends Personal{

	public static Map<String, Cliente> mapaClientes = new HashMap<>();
	public static int cantidadClientes = 0;
	public static Map<String, ArrayList<Mascota>> mascotas = new HashMap<>();
	
	public Cliente(String nombre, String cedula, String telefono) {
		super(nombre, cedula, telefono);
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
	
	public static boolean validarCedula(String cedula) {
		return Cliente.mapaClientes.containsKey(cedula);
	}
	
	public static String obtenerMascotasCliente(String cedula) {
		String cadena = "";
		for (int i=0;i<Cliente.mascotas.get(cedula).size();i++) {
			cadena = cadena + (i+1) + ". " + Cliente.mascotas.get(cedula).get(i).getNombre()+"\n";      
		}
		return cadena;
	}
	
	public String toString() {
		return "nombre: " + nombre + "\n" + "cedula: " + cedula + "\n" + "telefono: " + telefono;
	}
	
}
