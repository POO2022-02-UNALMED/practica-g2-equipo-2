package uiMain;

import java.util.ArrayList;
import java.lang.Thread;
import java.util.Scanner;
import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import gestorAplicacion.Veterinaria.Medico;
import gestorAplicacion.Veterinaria.tipoMedico;

public class Interaccion {
	
	public static void registrarCliente(){
		
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
	}
	
	public static void registrarMedico(){
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese el nombre del medico:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la cedula del medico:");
		String cedula = entrada.nextLine();
		System.out.print("Ingrese el telefono del medico:");
		String telefono = entrada.nextLine();
		System.out.print("Ingrese el cargo del medico (general/especialista):");
		String cargo = entrada.nextLine();
		tipoMedico tipoMed;
		if(cargo.equals("general")) {
			tipoMed = tipoMedico.General;
		}else {
			tipoMed = tipoMedico.Especialista;
		}
		Medico medico1 = new Medico(nombre, cedula, telefono, tipoMed);
		Medico.mapaMedico.put(cedula, medico1);
	}
	
	public static void registrarMascota() {
		
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
		          
		}
		System.out.print("\n");
		System.out.print("Ingrese la mascota para la cual quiere asignar el turno: ");
		int opc = entrada.nextInt();
		entrada.nextLine();
		System.out.print("Ingrese el tipo de medico para agendar el turno (general/especialista): ");
		String cargo = entrada.nextLine();
		tipoMedico tipoMed;
		if(cargo.equals("general")) {
			tipoMed = tipoMedico.General;
		}else {
			tipoMed = tipoMedico.Especialista;
		}
		System.out.println("\nLista de doctores de este tipo");
		Medico.mapaMedico.forEach((k,v) -> {if(v.getTipoMed().equals(tipoMed)){System.out.println(v.getNombre()+" - Cedula: "+v.getCedula());}});
		System.out.print("\n");
		String cedulaDoctor="";
		valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cedula del doctor con el que quiere asignar la cita: ");
			cedulaDoctor = entrada.nextLine();
			if(Medico.mapaMedico.containsKey(cedulaDoctor)) {
				valido=true;
			}else {
				System.out.print("La cedula no existe en el sistema, por favor ingrese una valida\n\n");
			}
			
		}
		System.out.print("Ingrese la fecha para agendar el turno (dd-mm-aaaa): ");
		String fecha = entrada.nextLine();
		if(!Medico.mapaMedico.get(cedulaDoctor).agenda.containsKey(fecha)) {
			Medico.mapaMedico.get(cedulaDoctor).crearFecha(fecha);
		}
		System.out.println("\nLista de turnos disponibles con este doctor");
		for(int i = 0; i<24; i++) {
			if(Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[i].isDisponibilidad()) {
				if(Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[i].getHoraInicio()<13) {
					System.out.println("Turno "+(i+1)+": "+Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[i].getHoraInicio()+":00 AM");
				}else {
					System.out.println("Turno "+(i+1)+": "+Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[i].getHoraInicio()+":00 PM");
				}
			}	
		}
		System.out.print("Ingrese el numero del turno seleccionado: ");
		int turno = entrada.nextInt();
		entrada.nextLine();
		Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[turno-1].setDisponibilidad(false);
		Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[turno-1].setCliente(Cliente.mapaClientes.get(cedula));
		Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[turno-1].setMascota(Cliente.mascotas.get(cedula).get(opc-1));
		
		System.out.println("\nEl turno ha sido registrado");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
