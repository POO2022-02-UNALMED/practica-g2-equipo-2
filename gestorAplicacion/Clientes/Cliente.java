package gestorAplicacion.Clientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.Veterinaria.Personal;


public class Cliente extends Personal{

	private boolean frecuente = false;
	private int registroHoras[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
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
	public boolean isFrecuente() {
		return frecuente;
	}

	public void setFrecuente(boolean frecuente) {
		this.frecuente = frecuente;
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

	public void registrarHora(int hora) {
		this.registroHoras[hora]++;
	}
	public int sumaRegistros() {
		int suma = 0;
		for(int i=0; i<24; i++) {
			suma = suma + this.registroHoras[i];
		}
		return suma;
	}
	
}
