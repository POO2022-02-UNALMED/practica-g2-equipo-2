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
		this.registroHoras.put(1,0);
		this.registroHoras.put(2,0);
		this.registroHoras.put(3,0);
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
	
	public static String obtenerMascotasCliente(String cedula) {
		String cadena = "";
		if(Cliente.mascotas.get(cedula)==null || Cliente.mascotas.get(cedula).size()==0) {
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

	public void registrarHora(int hora) {
		int aumento = this.registroHoras.get(hora) + 1;
		this.registroHoras.put(hora, aumento);
	}
	public int sumaRegistros() {
		int suma = 0;
		for(int i=1; i<25; i++) {
			suma = suma + this.registroHoras.get(i);
		}
		return suma;
	}
	
	public Map<Integer, Integer> obtenerTurnosFrecuentes() {
		
		Map<Integer, Integer> result = this.registroHoras.entrySet().stream()
                .sorted(Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		Map<Integer, Integer> mayores = new HashMap<>();
		int i=0;
		for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
    		if(i<2) {
    			mayores.put(entry.getKey(), entry.getValue());
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
