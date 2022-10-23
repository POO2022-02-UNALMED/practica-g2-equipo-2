package uiMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Thread;
import java.util.Scanner;
import java.util.Map.Entry;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import gestorAplicacion.Veterinaria.Factura;
import gestorAplicacion.Veterinaria.Inventario;
import gestorAplicacion.Veterinaria.Medicamento;
import gestorAplicacion.Veterinaria.Medico;
import gestorAplicacion.Veterinaria.tipoMedico;
import gestorAplicacion.Veterinaria.TurnoContab;;

public class Interaccion {
	
	public static void registrarCliente(){
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese el nombre del cliente:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la cédula del cliente:");
		String cedula = entrada.nextLine();
		System.out.print("Ingrese el teléfono del cliente:");
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
		System.out.print("Ingrese el nombre del médico:");
		String nombre = entrada.nextLine();
		System.out.print("Ingrese la cedula del médico:");
		String cedula = entrada.nextLine();
		System.out.print("Ingrese el teléfono del médico:");
		String telefono = entrada.nextLine();
		System.out.print("Ingrese el cargo del médico (general/especialista):");
		String cargo = entrada.nextLine();
		tipoMedico tipoMed;
		if(cargo.equals("general")) {
			tipoMed = tipoMedico.General;
		}else {
			tipoMed = tipoMedico.Especialista;
		}
		Medico medico1 = new Medico(nombre, cedula, telefono, tipoMed);
		Medico.mapaMedico.put(cedula, medico1);
		System.out.println("\nEl médico ha sido registrado");
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
			
			System.out.print("Ingrese la cédula del dueño de la mascota: ");
			cedula = entrada.nextLine();
			if(Cliente.mapaClientes.containsKey(cedula)) {
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
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
			
			System.out.print("Ingrese la cédula del cliente al que se le asignará la cita: ");
			cedula = entrada.nextLine();
			if(Cliente.validarCedula(cedula)) {
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			}
			
		}
		System.out.println("\nLista de mascotas de este cliente");
		System.out.print(Cliente.obtenerMascotasCliente(cedula));
		System.out.print("\n");
		System.out.print("Ingrese la mascota para la cual quiere asignar el turno: ");
		int opc = entrada.nextInt();
		entrada.nextLine();
		System.out.print("Ingrese el tipo de médico para agendar el turno (general/especialista): ");
		String cargo = entrada.nextLine();
		tipoMedico tipoMed;
		if(cargo.equals("general")) {
			tipoMed = tipoMedico.General;
		}else {
			tipoMed = tipoMedico.Especialista;
		}
		System.out.println("\nLista de médicos de este tipo");
		System.out.print(Medico.obtenerMedicos(tipoMed));
		System.out.print("\n");
		String cedulaDoctor="";
		valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cédula del médico con el que quiere asignar la cita: ");
			cedulaDoctor = entrada.nextLine();
			if(Medico.validarCedula(cedulaDoctor)) {
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
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
	public static void generarFactura() {
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		String cedula = "";
		boolean valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cédula del cliente al que se le generará la factura: ");
			cedula = entrada.nextLine();
			if(Cliente.validarCedula(cedula)) {
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			}
			
		}
		String cedulaDoctor = "";
		boolean valido2=false;
		while(valido2==false) {
			
			System.out.print("Ingrese la cedula del médico que atendió el turno: ");
			cedulaDoctor = entrada.nextLine();
			 if(Medico.validarCedula(cedulaDoctor)) {
				 valido2=true;
			 }else {
				 System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			 }
			
		}
		System.out.print("Listado disponible de Medicamentos:\n");
		for (int i=0;i<Inventario.getMedicamentos().size();i++) {
			System.out.print ((i+1) + ". " + Inventario.getMedicamentos().get(i).getNombre()+"\n");     
		}
		System.out.print("Seleccione el número de Medicamento que desea ordenar:");
		int nombreMedicamento = entrada.nextInt();
		entrada.nextLine();
		System.out.print("Ingrese la cantidad (en unidades) del medicamento: ");
        short cantidadMedicamento = entrada.nextShort();
		entrada.nextLine();
		System.out.print("Listado de turnos pendientes por pagar:\n");
		for (int i=0;i<Cliente.mapaClientes.get(cedula).turnosPendientes.size();i++) {
			System.out.print ((i+1) + ". " + Cliente.mapaClientes.get(cedula).turnosPendientes.get(i).getHoraInicio()+":00"+" en la fecha:"+Cliente.mapaClientes.get(cedula).turnosPendientes.get(i).getFecha()+"\n");     
		}
		System.out.print("Seleccione el número de turno que desea pagar:");
		int turnoAPagar = entrada.nextInt();
		entrada.nextLine();
		/* 
		for(String ced: Medico.mapaMedico.keySet()) { //KeySet*
			System.out.println(ced);
		}
		System.out.println(Medico.mapaMedico.get("67890"));
		System.out.println(Cliente.mapaClientes.get(cedula));
		System.out.println(Inventario.getMedicamentos().get(nombreMedicamento-1));
		System.out.println(cantidadMedicamento);
		System.out.println(Cliente.mapaClientes.get(cedula).turnosPendientes.get(turnoAPagar-1) );
		*/
		Factura factura = new Factura(Medico.mapaMedico.get(cedulaDoctor), Cliente.mapaClientes.get(cedula), Inventario.getMedicamentos().get(nombreMedicamento-1), cantidadMedicamento,Cliente.mapaClientes.get(cedula).turnosPendientes.get(turnoAPagar-1) );
		System.out.println("\nEl cliente: " + Cliente.mapaClientes.get(cedula).getNombre()+ ", debe pagar un total de: $" + factura.calculoTotalFactura());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void estadoCaja() {
		System.out.println("En caja debería haber "+ TurnoContab.TotalDiario());

	}

	public static void mostrarDeudaMedicos() {
		HashMap<String, Integer> deudaMedicos = TurnoContab.TotalMedico(); 
		for(Entry<String, Integer> e: deudaMedicos.entrySet() ){
			System.out.println("Al doctor" + e.getKey()+ " se le adeuda "+e.getValue());
		}

	}


	public static void mostrarInventario() {
		System.out.println("INVENTARIO\n");
		ArrayList<Medicamento> medicamentos= Inventario.getMedicamentos();
		for(Medicamento producto:medicamentos){
			System.out.println(producto.getNombre()+ " "+ producto.getCantidad());
			if (producto.getCantidad()<10){
				System.out.println("Bajas unidades de " + producto.getNombre());
			}
		}
	}


	public static void cambiarTurno() {
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		System.out.println("1. Nuevo turno contable sin retiro de la caja");
		System.out.println("2 Nuevo turno contable con retiro de la caja");
		int eleccion = entrada.nextInt();
		entrada.nextLine();
		if (eleccion==1)
		{
			TurnoContab.setInicial(TurnoContab.calcularCaja(0));
			TurnoContab.facturas.clear();
			TurnoContab.setTotalmedicosturno(0);
		}
		else{
			System.out.println("Ingrese la cantidad que desea retirar");
			int retirar = entrada.nextInt();
			entrada.nextLine();
			TurnoContab.TotalMedico();
			TurnoContab.setInicial(TurnoContab.calcularCaja(retirar));
			TurnoContab.facturas.clear();
			TurnoContab.setTotalmedicosturno(0);
		}

	}
	public static void informeTotal() {
		estadoCaja();
		mostrarDeudaMedicos();
		mostrarInventario();

	}



	public static void Caja(){
		int opcc=100;
	
		while(opcc != 0) {
			System.out.println("Administrador Veterinaria\n");
			System.out.println("1. Mostrar dinero en caja");
			System.out.println("2. Mostrar deuda medicos");
			System.out.println("3. Mostrar inventario");
			System.out.println("4. Cambiar turno");
			System.out.println("5. Informe Total");
			System.out.println("0. Salir\n");

			System.out.print("Digite una opcion: ");
			
			@SuppressWarnings("resource")
			Scanner entrada=new Scanner(System.in);
			opcc = entrada.nextInt();
			entrada.nextLine();
			
			if(opcc == 1) {
				Interaccion.estadoCaja();
			}
			if(opcc == 2) {
				Interaccion.mostrarDeudaMedicos();
			}
			if(opcc == 3) {
				Interaccion.mostrarInventario();
			}
			if(opcc == 4) {
				Interaccion.cambiarTurno();
			}
			if(opcc == 5) {
				Interaccion.cambiarTurno();
			}
			}
			if(opcc == 0) {
				System.out.println("Salir");
			}
			System.out.println("\n\n"); 
		}
}