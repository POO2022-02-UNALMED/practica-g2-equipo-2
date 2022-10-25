package gestorAplicacion.Clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import gestorAplicacion.Veterinaria.Persona;
import gestorAplicacion.Veterinaria.Turno;


public class Cliente extends Persona implements Comunicacion, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private boolean frecuente = false;
	public ArrayList<Turno> turnosPendientes = new ArrayList<Turno>();
	public Map<Integer, Integer> registroHoras = new HashMap<>();
	public static HashMap<String, Cliente> mapaClientes = new HashMap<>();
	public static int cantidadClientes = 0;
	public static Map<String, ArrayList<Mascota>> mascotas = new HashMap<>();
	
	public Cliente(String nombre, String cedula, String telefono) {
		super(nombre, cedula, telefono);
		cantidadClientes++;
		this.registroHoras.put(1,0); //agrega al hashmap registrohoras la clave correspondiente a cada turno
		this.registroHoras.put(2,0);
		this.registroHoras.put(3,0); //el valor inicia en 0 e ira aumentando cada que un cliente pida un turno en cada horario
		this.registroHoras.put(4,0);
		this.registroHoras.put(5,0);
		this.registroHoras.put(6,0);
		this.registroHoras.put(7,0);
		this.registroHoras.put(8,0);
		this.registroHoras.put(9,0);
		this.registroHoras.put(10,0);
		this.registroHoras.put(11,0);
		this.registroHoras.put(12,0);
		this.registroHoras.put(13,0);
		this.registroHoras.put(14,0);
		this.registroHoras.put(15,0);
		this.registroHoras.put(16,0);
		this.registroHoras.put(17,0);
		this.registroHoras.put(18,0);
		this.registroHoras.put(19,0);
		this.registroHoras.put(20,0);
		this.registroHoras.put(21,0);
		this.registroHoras.put(22,0);
		this.registroHoras.put(23,0);
		this.registroHoras.put(24,0);
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
	
	public static String obtenerMascotasCliente(String cedula) { //este metodo recibe la cedula de un cliente y acumula en cadena la lista de sus mascotas
		String cadena = "";
		if(Cliente.mascotas.get(cedula)==null || Cliente.mascotas.get(cedula).size()==0) { //valida si el cliente si posee mascotas registradas
			return "Este cliente no tiene mascotas registradas";
		}
		for (int i=0;i<Cliente.mascotas.get(cedula).size();i++) {
			cadena = cadena + (i+1) + ". " + Cliente.mascotas.get(cedula).get(i).getNombreMascota()+"\n";      
		}
		return cadena;
	}
	
	public String toString() {
		return "nombre: " + nombre + "\n" + "cedula: " + cedula + "\n" + "telefono: " + telefono;
	}

	public void registrarHora(int hora) {  //cuando el cliente pide un turno, se invoca este metodo y se le pasa la hora del turno que se pidio
		int aumento = this.registroHoras.get(hora) + 1;
		this.registroHoras.put(hora, aumento); // aunmenta en uno el registro correspodiente a la hora del turno para llevar la cuenta de cuantas veces ha pedido turno en dicha hora
	}
	public int sumaRegistros() { //suma todo los registros del cliente para saber cuantos turno ha pedido en total
		int suma = 0;
		for(int i=1; i<25; i++) {
			suma = suma + this.registroHoras.get(i);
		}
		return suma;
	}
	
	public Map<Integer, Integer> obtenerTurnosFrecuentes() { //devuelve un mapa con los turno que mas frecuenta el usuario
		
		Map<Integer, Integer> result = this.registroHoras.entrySet().stream()
                .sorted(Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); //ordena el hashmap de registro hora segun sus valores de manera descendente para que las hras mas frecuentas queden en las primera posiciones
		Map<Integer, Integer> mayores = new HashMap<>();
		int i=0;
		for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
    		if(i<2) {
    			mayores.put(entry.getKey(), entry.getValue()); //crea un nuevo mapa solo con los registros de las horas mas frecuentadas
    			i++;
    		}
	    }
		
		return mayores;
	}



	@Override
	public String saludar() {
		return "Hola soy cliente";
	}
	public String presentarse() {
		return "El cliente " + this.nombre + " fue registrado";
	}



	@Override
	public String llamar() {
		return "Llamo desde mi telefono " + this.telefono;
	}
	
	
	
}

