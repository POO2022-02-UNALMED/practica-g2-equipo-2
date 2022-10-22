package uiMain;

import java.util.ArrayList;
import java.util.Map;
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
		System.out.println("\nEl cliente ha sido registrado");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("\nEl Medico ha sido registrado");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("\nLa mascota ha sido registrada");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void agendarTurno() {
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		String cedula="";
		boolean valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cedula del cliente al que se le asignara la cita: ");
			cedula = entrada.nextLine();
			if(Cliente.validarCedula(cedula)) {
				valido=true;
			}else {
				System.out.print("La cedula no existe en el sistema, por favor ingrese una valida\n\n");
			}
			
		}
		System.out.println("\nLista de mascotas de este cliente");
		System.out.print(Cliente.obtenerMascotasCliente(cedula));
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
		System.out.println("\nLista de medicos de este tipo");
		System.out.print(Medico.obtenerMedicos(tipoMed));
		System.out.print("\n");
		String cedulaDoctor="";
		valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cedula del medico con el que quiere asignar la cita: ");
			cedulaDoctor = entrada.nextLine();
			if(Medico.validarCedula(cedulaDoctor)) {
				valido=true;
			}else {
				System.out.print("La cedula no existe en el sistema, por favor ingrese una valida\n\n");
			}
			
		}
		System.out.print("Ingrese la fecha para agendar el turno (dd-mm-aaaa): ");
		String fecha = entrada.nextLine();
		Medico.mapaMedico.get(cedulaDoctor).crearFecha(fecha);
		if(Cliente.mapaClientes.get(cedula).isFrecuente()) {
			System.out.println("\nEl cliente ingresado es un cliente frecuente");
			System.out.println("\nLista de turnos recomendados para este cliente");
			Map<Integer, Integer> mayores = Cliente.mapaClientes.get(cedula).obtenerTurnosFrecuentes();
			
			for (Map.Entry<Integer, Integer> entry : mayores.entrySet()) {
	    		if(Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].isDisponibilidad()) {
	    				
	    			if(Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].getHoraInicio()<13) {
	    				System.out.print("Turno "+(entry.getKey())+": "+Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].getHoraInicio()+":00 AM\n");
					}else {
						System.out.print("Turno "+(entry.getKey())+": "+Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].getHoraInicio()+":00 PM\n");
					}
	    		}
		    }
			
			System.out.print("¿Desea ver la lista completa con los turnos disponibles? (S/N): ");
			String desc = entrada.nextLine();
			if(desc.equals("S")) {
				System.out.println("\nLista de turnos disponibles con este doctor");
				System.out.print(Medico.mapaMedico.get(cedulaDoctor).obtenerTurnosDisponibles(fecha));
			}
			
		}else {
			System.out.println("\nLista de turnos disponibles con este doctor");
			System.out.print(Medico.mapaMedico.get(cedulaDoctor).obtenerTurnosDisponibles(fecha));
		}
		System.out.print("Ingrese el numero del turno seleccionado: ");
		int turno = entrada.nextInt();
		entrada.nextLine();
		Medico.mapaMedico.get(cedulaDoctor).asignarTurno(fecha, turno-1, cedula, opc-1);
		System.out.println("\nEl turno ha sido registrado");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
