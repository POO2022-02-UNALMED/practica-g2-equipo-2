package uiMain;

import java.util.ArrayList;
import java.util.Scanner;
import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import gestorAplicacion.Veterinaria.Turno;
import gestorAplicacion.Veterinaria.Personal;

public class Interaccion {
	
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
		Cliente.mapaClientes.put(cedula, cliente1);
		Cliente.mascotas.put(cedula, new ArrayList<Mascota>());
		return cliente1;
	}
	
	public static Personal registrarPersonal(){
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese el nombre del personal:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la cedula del personal:");
		String cedula = entrada.nextLine();
		System.out.print("Ingrese el telefono del personal:");
		String telefono = entrada.nextLine();
		System.out.print("Ingrese el cargo del personal (general/especialista):");
		String cargo = entrada.nextLine();
		Personal personal1 = new Personal(nombre, cedula, telefono, cargo);
		Personal.mapaPersonal.put(cedula, personal1);
		return personal1;
	}
	
	public static Mascota registrarMascota() {
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese el nombre de la mascota:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la especie (perro/gato):");
		String especie = entrada.nextLine();
		System.out.print("Ingrese la raza:");
		String raza = entrada.nextLine();
		System.out.print("Ingrese la edad (años):");
		int edad = entrada.nextInt();
		System.out.print("Ingrese el peso (kg):");
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
		Cliente.mascotas.get(cedula).add(mascota1);
		return mascota1;
	}

	public static void agendarTurno() {
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		String cedula="";
		boolean valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cedula del cliente al que se le asignara la cita: ");
			cedula = entrada.nextLine();
			if(Cliente.mapaClientes.containsKey(cedula)) {
				valido=true;
			}else {
				System.out.print("La cedula no existe en el sistema, por favor ingrese una valida\n\n");
			}
			
		}
		System.out.println("\nLista de mascotas de este cliente");
		for (int i=0;i<Cliente.mascotas.get(cedula).size();i++) {
		      
		      System.out.println((i+1) + ". " + Cliente.mascotas.get(cedula).get(i).getNombre());
		      System.out.print("\n");
		     
		}
		System.out.print("Ingrese la mascota para la cual quiere asignar el turno");
		int opc = entrada.nextInt();
		entrada.nextLine();
		System.out.print("Ingrese el tipo de medico para agendar el turno (general/especialista): ");
		String cargo = entrada.nextLine();
		
		Personal.mapaPersonal.forEach((k,v) -> {if(v.getCargo().equals(cargo)){System.out.println(v.getNombre()+" - Cedula: "+v.getCedula());}});
		
		
	}
}
