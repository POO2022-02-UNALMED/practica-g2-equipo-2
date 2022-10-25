package gestorAplicacion.Clientes;

import java.io.Serializable;

public class Mascota extends Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String raza;
	private String sexo;
	private int peso;
	private Cliente duenno;
	private int id;
	public static int cantidadMascotas = 0;
	
	public Mascota(String nombreMascota, String especie, String raza, String sexo, int edad, int peso, Cliente duenno) {
		super(especie, nombreMascota, edad);
		this.raza = raza;
		this.setSexo(sexo);
		this.peso = peso;
		this.duenno = duenno;
		this.id = cantidadMascotas;
		cantidadMascotas++;
		//mapaMascotas.put(this.id, this);
	}
	
	public Mascota(String nombreMascota, String especie, String raza, String sexo, int edad, int peso) {
		this(nombreMascota, especie, raza, sexo, edad, peso, Cliente.mapaClientes.get("0000"));
		cantidadMascotas++;
		Cliente.mascotas.get("0000").add(this);
	}
	
	
	
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public Cliente getDuenno() {
		return duenno;
	}
	public void setDuenno(Cliente duenno) {
		this.duenno = duenno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String toString() {
		return "nombre: " + nombreMascota + "\n" + "especie: " + especie + "\n" + "raza: " + raza + "\n" + "edad: " + edad + "\n" + "peso: " + peso+ "\n" + "dueño:\n" + duenno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int sobrecargoEdad() {
		return this.edad*500;
	}
	
	public String saludarDuenno() {
		return "Soy callejero, yo no tengo dueño";
	}
	public String saludarDuenno(Cliente cliente) {
		return "Mi dueño es " + cliente.getNombre();
	}
	
}
