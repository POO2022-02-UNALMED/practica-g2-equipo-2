package gestorAplicacion.Clientes;

import java.io.Serializable;

public class Mascota extends Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String nombreMascota;
	private String especie;
	private String raza;
	private String sexo;
	private int edad;
	private int peso;
	private Cliente duenno;
	private int id;
	public static int cantidadMascotas = 0;

	public Mascota(){
		
	}
	
	public Mascota(String nombreMascota, String especie, String raza, String sexo, int edad, int peso, Cliente duenno) {
		super();
		this.nombreMascota = nombreMascota;
		this.especie = especie;
		this.raza = raza;
		this.setSexo(sexo);
		this.edad = edad;
		this.peso = peso;
		this.duenno = duenno;
		this.id = cantidadMascotas;
		cantidadMascotas++;
		//mapaMascotas.put(this.id, this);
	}
	
	
	public String getNombreMascota() {
		return nombreMascota;
	}
	public void setNombre(String nombre) {
		this.nombreMascota = nombre;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
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


	protected String hablar() {
		if(this.especie.equals("perro")) {
			return "Ladrar";
		}else {
			return "Maullar";
		}
	}
	
}
