package gestorAplicacion.Clientes;

import java.util.ArrayList;

public class Cliente {

	private String nombre;
	private int id;
	private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
	private String telefono;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
