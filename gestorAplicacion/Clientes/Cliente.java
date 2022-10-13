package Clientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Cliente {

	private String nombre;
	private String cedula;
	private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
	private String telefono;
	public static Map<String, Cliente> mapaClientes = new HashMap<>();
	public static int cantidadClientes = 0;
	
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
	public ArrayList<Mascota> getMascotas() {
		return mascotas;
	}
	public void setMascotas(ArrayList<Mascota> mascotas) {
		this.mascotas = mascotas;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public static Cliente registrarCliente(){
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese el nombre del cliente:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la cedula del cliente:");
		String cedula = entrada.nextLine();
		System.out.print("Ingrese el telefono del cliente:");
		String telefono = entrada.nextLine();
		Cliente cliente1 = new Cliente(nombre, cedula, telefono);
		mapaClientes.put(cedula, cliente1);
		return cliente1;
	}
	
	public String toString() {
		return "nombre: " + nombre + "\n" + "cedula: " + cedula + "\n" + "telefono: " + telefono;
	}
	
}
