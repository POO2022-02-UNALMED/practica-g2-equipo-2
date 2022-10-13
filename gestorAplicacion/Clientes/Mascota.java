package Clientes;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mascota {

	private String nombre;
	private String especie;
	private String raza;
	private int edad;
	private int peso;
	private Cliente duenno;
	private int id;
	public static Map<Integer, Mascota> mapaMascotas = new HashMap<>();
	public static int cantidadMascotas = 0;
	
	public Mascota(String nombre, String especie, String raza, int edad, int peso, Cliente duenno) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.edad = edad;
		this.peso = peso;
		this.duenno = duenno;
		this.id = cantidadMascotas;
		cantidadMascotas++;
		mapaMascotas.put(this.id, this);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public static Mascota registrarMascota() {
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese el nombre de la mascota:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la especie:");
		String especie = entrada.nextLine();
		System.out.print("Ingrese la raza:");
		String raza = entrada.nextLine();
		System.out.print("Ingrese la edad:");
		int edad = entrada.nextInt();
		System.out.print("Ingrese el peso:");
		int peso = entrada.nextInt();
		String cedula="";
		entrada.nextLine();
		boolean valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cedula del dueño de la mascota: ");
			cedula = entrada.nextLine();
			if(Cliente.mapaClientes.containsKey(cedula)) {
				valido=true;
			}else {
				System.out.print("La cedula no existe en el sistema, por favor ingrese una valida\n\n");
			}
			
		}
		Mascota mascota1 = new Mascota(nombre,especie,raza,edad,peso,Cliente.mapaClientes.get(cedula));
		return mascota1;
	}
	
	public String toString() {
		return "nombre: " + nombre + "\n" + "especie: " + especie + "\n" + "raza: " + raza + "\n" + "edad: " + edad + "\n" + "peso: " + peso+ "\n" + "dueño:\n" + duenno;
	}
	
}
