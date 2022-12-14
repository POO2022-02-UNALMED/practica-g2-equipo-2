package uiMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Thread;
import java.util.Scanner;
import java.util.Map.Entry;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import gestorAplicacion.Veterinaria.Diagnostico;
import gestorAplicacion.Veterinaria.Factura;
import gestorAplicacion.Veterinaria.Inventario;
import gestorAplicacion.Veterinaria.Medicamento;
import gestorAplicacion.Veterinaria.Medico;
import gestorAplicacion.Veterinaria.Persona;
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
		Persona persona1 = cliente1;
		System.out.println("\n"+persona1.presentarse());
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
		System.out.print("Ingrese la cédula del médico:");
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
		Persona persona1 = medico1;
		System.out.println("\n"+persona1.presentarse());
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
		System.out.print("Ingrese el sexo:");
		String sexo = entrada.nextLine();
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
		Mascota mascota1 = new Mascota(nombre,especie,raza,sexo,edad,peso,Cliente.mapaClientes.get(cedula));
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
		while(valido==false) { // ciclo para leer la cedula del cliente, solo permite continuar al ingresar una cedula valida
			
			System.out.print("Ingrese la cédula del cliente al que se le asignará la cita: ");
			cedula = entrada.nextLine();
			if(Cliente.validarCedula(cedula)) {
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			}
			
		}
		
		
		if(!Cliente.obtenerMascotasCliente(cedula).equals("Este cliente no tiene mascotas registradas")) {
			System.out.println("\nLista de mascotas de este cliente");
			System.out.print(Cliente.obtenerMascotasCliente(cedula));  //se obtienen e imprimen las mascotas de cliente
			System.out.print("\n");
			
		System.out.print("Ingrese el número de la lista de la mascota para la cual quiere asignar el turno: ");
		int opc = entrada.nextInt();
		entrada.nextLine();
		System.out.print("Ingrese el tipo de médico para agendar el turno (general/especialista): ");
		String cargo = entrada.nextLine();
		tipoMedico tipoMed;
		if(cargo.equals("general")) {
			tipoMed = tipoMedico.General;  //se traduce lo ingresado por el usuario para manejar las constantes del enum
		}else {
			tipoMed = tipoMedico.Especialista;
		}
		System.out.println("\nLista de médicos de este tipo");
		System.out.print(Medico.obtenerMedicos(tipoMed));  //imprime los medicos cuyo atributo tipomed concuerde con lo que ingreso el usuario
		System.out.print("\n");
		String cedulaDoctor="";
		valido=false;
		while(valido==false) { // ciclo para leer la cedula del medico, solo permite continuar al ingresar una cedula valida
			
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
		if(Cliente.mapaClientes.get(cedula).isFrecuente()) {  //se bifurca el codigo para realizar acciones diferentes dependiendo de si el cliente es frecuente
			System.out.println("\nEl cliente ingresado es un cliente frecuente");
			System.out.println("\nLista de turnos recomendados para este cliente");
			Map<Integer, Integer> mayores = Cliente.mapaClientes.get(cedula).obtenerTurnosFrecuentes();
			
			for (Map.Entry<Integer, Integer> entry : mayores.entrySet()) {
	    		if(Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].isDisponibilidad()) {  // se imprime los turnos que mas frecuenta el usuario
	    				
	    			if(Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].getHoraInicio()<13) {
	    				System.out.print("Turno "+(entry.getKey())+": "+Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].getHoraInicio()+":00 AM\n");
					}else {
						System.out.print("Turno "+(entry.getKey())+": "+Medico.mapaMedico.get(cedulaDoctor).agenda.get(fecha)[entry.getKey()-1].getHoraInicio()+":00 PM\n");
					}
	    		}
		    }
			
			System.out.print("¿Desea ver la lista completa con los turnos disponibles? (S/N): "); //se pregunta si desea agendar un turno dentro de los frecuentes o desea ver la lista completa
			String desc = entrada.nextLine();
			if(desc.equals("S")) {
				System.out.println("\nLista de turnos disponibles con este doctor");  //se imprime la lista completa en caso de que el usuario desee verla
				System.out.print(Medico.mapaMedico.get(cedulaDoctor).obtenerTurnosDisponibles(fecha));
			}
			
		}else {
			System.out.println("\nLista de turnos disponibles con este doctor");  //imprime la lista completa para un cliente no frecuente
			System.out.print(Medico.mapaMedico.get(cedulaDoctor).obtenerTurnosDisponibles(fecha));
		}
		System.out.print("Ingrese el numero del turno seleccionado: ");
		int turno = entrada.nextInt();
		entrada.nextLine();
		Medico.mapaMedico.get(cedulaDoctor).asignarTurno(fecha, turno-1, cedula, opc-1);
		System.out.println("\nEl turno ha sido registrado");
		}else {
			System.out.print(Cliente.obtenerMascotasCliente(cedula)); //si el cliente no tiene mascotas registradas, se imprime un mensaje de advertencia
		}
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
			if(Cliente.validarCedula(cedula)) {// ciclo para leer la cedula del cliente, solo permite continuar al ingresar una cedula valida
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			}
			
		}
		String cedulaDoctor = "";
		boolean valido2=false;
		while(valido2==false) {
			
			System.out.print("Ingrese la cédula del médico que atendió el turno: ");
			cedulaDoctor = entrada.nextLine();
			 if(Medico.validarCedula(cedulaDoctor)) {
				 valido2=true;
			 }else {
				 System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			 }
			
		}
		System.out.print("Listado disponible de Medicamentos:\n");
		System.out.println("0. Ninguno");
		for (int i=0;i<Inventario.getMedicamentos().size();i++) { //se obtienen e imprimen los medicamentos disponibles
			System.out.print ((i+1) + ". " + Inventario.getMedicamentos().get(i).getNombre()+"\n");     
		}
		System.out.print("Seleccione el número de Medicamento que desea ordenar:");
		int nombreMedicamento = entrada.nextInt();
		entrada.nextLine();
		System.out.print("Ingrese la cantidad (en unidades) del medicamento: ");
        short cantidadMedicamento = entrada.nextShort();
		entrada.nextLine();
		System.out.print("Listado de turnos pendientes por pagar:\n");
		for (int i=0;i<Cliente.mapaClientes.get(cedula).turnosPendientes.size();i++) { //se obtienen e imprimen los turnos pendientes por pagar de cliente
			System.out.print ((i+1) + ". " + Cliente.mapaClientes.get(cedula).turnosPendientes.get(i).getHoraInicio()+":00"+" en la fecha:"+Cliente.mapaClientes.get(cedula).turnosPendientes.get(i).getFecha()+"\n");     
		}
		System.out.print("Seleccione el número de turno que desea pagar:");
		int turnoAPagar = entrada.nextInt();
		entrada.nextLine();
		if (nombreMedicamento == 0){ //ciclo que se ejecuta si la cantidadMedicamento fue 0, para ejecutar el llamado a factura con el constructor sin el objeto Medicamento
			Factura facturaSinMedicamento= new Factura(Medico.mapaMedico.get(cedulaDoctor), Cliente.mapaClientes.get(cedula), 0, Cliente.mapaClientes.get(cedula).turnosPendientes.get(turnoAPagar-1) );
			Cliente.mapaClientes.get(cedula).turnosPendientes.remove(turnoAPagar-1);
			System.out.println("\nEl cliente: " + Cliente.mapaClientes.get(cedula).getNombre()+ ", debe pagar un total de: $" + facturaSinMedicamento.calculoTotalFactura());
		}
		else { //se ejecuta si la cantidadMedicamento fue diferente de 0, para ejecutar el llamado a factura con el constructor con el objeto Medicamento
			Factura factura = new Factura(Medico.mapaMedico.get(cedulaDoctor), Cliente.mapaClientes.get(cedula), Inventario.getMedicamentos().get(nombreMedicamento-1), cantidadMedicamento,Cliente.mapaClientes.get(cedula).turnosPendientes.get(turnoAPagar-1) );
			Cliente.mapaClientes.get(cedula).turnosPendientes.remove(turnoAPagar-1);
			System.out.println("\nEl cliente: " + Cliente.mapaClientes.get(cedula).getNombre()+ ", debe pagar un total de: $" + factura.calculoTotalFactura());
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void estadoCaja() {              //Este metodo imprime en pantalla el dinero que debe 
												// haber en caja en el momento que se llama.
		System.out.println("En caja debería haber $"+ TurnoContab.TotalDiario());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mostrarDeudaMedicos() { //Este metodo muestra el capital que se le adeuda a cada medico
											   //en el momento que se llama la metodo
		HashMap<String, Double> deudaMedicos = TurnoContab.TotalMedico(); //Metodo que calcula
																			//lo que se le adeuda a cada doctor
		for(Entry<String, Double> e: deudaMedicos.entrySet() ){				//impresion por pantalla
			System.out.println("Al médico " + e.getKey()+ " se le adeuda $"+e.getValue());
			System.out.println("El dinero ha sido entregado al médico y retirado de la caja");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void mostrarInventario() {  //Este metodo imprime el inventario y notifica en caso de que hayan pocas unidades
											// de un medicamento
		System.out.println("INVENTARIO\n");
		ArrayList<Medicamento> medicamentos= Inventario.getMedicamentos(); //Accede a el inventario
		for(Medicamento producto:medicamentos){								//Impresion en pantalla
			System.out.println(producto.getNombre()+ " "+ producto.getCantidad());
			if (producto.getCantidad()<10){
				System.out.println("Bajas unidades de " + producto.getNombre()); //Notificacion de bajas unidades
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void cambiarTurno() {     //Este metodo realiza un cambio de turno y permite hacer retiros
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);                                //Captura si el ususario quiere hacer
		System.out.println("1. Nuevo turno contable sin retiro de la caja");   //un retiro o no
		System.out.println("2. Nuevo turno contable con retiro de la caja");
		System.out.print("Ingrese una opción: ");
		int eleccion = entrada.nextInt();                                       
		entrada.nextLine();
		if (eleccion==1)											//Realiza el cambio de turno sin retiro
		{
			TurnoContab.setInicial(TurnoContab.calcularCaja(0));       
			TurnoContab.facturas.clear();
			TurnoContab.setTotalmedicosturno(0);
		}
		else{														//Realiza cambio de turno con retiro
			System.out.print("Ingrese la cantidad que desea retirar:");
			int retirar = entrada.nextInt();
			entrada.nextLine();
			System.out.println("Se ha retirado el dinero.");
			TurnoContab.TotalMedico();
			TurnoContab.setInicial(TurnoContab.calcularCaja(retirar));
			TurnoContab.facturas.clear();
			TurnoContab.setTotalmedicosturno(0);
		}
		
		System.out.println("\nEl turno contable ha sido cambiado");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void informeTotal() {      //Muestra un informe combinando las opciones 1,2 y 3
		estadoCaja();                        //Metodos y codigos usados anteriormente (Opcion 1 2 y 3)
		mostrarDeudaMedicos();
		mostrarInventario();	
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	public static void Caja(){
		int opcc=100;
	
		while(opcc != 0) {
			System.out.println("Administrador Veterinaria\n");   //Imprime los mensajes del submenu
			System.out.println("1. Mostrar dinero en caja");
			System.out.println("2. Mostrar deuda medicos");
			System.out.println("3. Mostrar inventario");
			System.out.println("4. Cambiar turno");
			System.out.println("5. Informe Total");
			System.out.println("0. Salir\n");

			System.out.print("Digite una opcion: ");
			
			@SuppressWarnings("resource")
			Scanner entrada=new Scanner(System.in);
			opcc = entrada.nextInt();                            //Captura la eleccion del usuario
			entrada.nextLine();
			
			if(opcc == 1) {                                        //Dependiendo de la opcion elegida
				Interaccion.estadoCaja();         //Llama al metodo responsable de la opcion
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
				Interaccion.informeTotal();;
			}
			}
			if(opcc == 0) {
				System.out.println("Salir");
			}
			System.out.println("\n\n"); 
		}
	public static void registros() {
		int opc=100;
		
		while(opc != 0) {
			System.out.println("\nAdministrador Veterinaria\n");  
			System.out.println("1. Registrar Cliente");
			System.out.println("2. Registrar Mascota");
			System.out.println("3. Registrar Doctor");
			System.out.println("0. Salir\n");

			System.out.print("Digite una opcion: ");
			
			@SuppressWarnings("resource")
			Scanner entrada=new Scanner(System.in);
			opc = entrada.nextInt();
			entrada.nextLine();
			
			if(opc == 1) {
				Interaccion.registrarCliente();
			}
			if(opc == 2) {
				Interaccion.registrarMascota();
			}
			if(opc == 3) {
				Interaccion.registrarMedico();
			}
			
			System.out.println("\n\n"); 
		}
	}
	
public static void generarDiagnostico() {
		
		@SuppressWarnings("resource")
		Scanner entrada=new Scanner(System.in);
		String cedula = "";
		boolean valido=false;
		while(valido==false) {
			
			System.out.print("Ingrese la cédula del cliente que será atendido: ");
			cedula = entrada.nextLine();
			if(Cliente.validarCedula(cedula)) {
				valido=true;
			}else {
				System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			}
			
		}
		System.out.println("\nLista de mascotas de este cliente");
		System.out.print(Cliente.obtenerMascotasCliente(cedula));
		if(Cliente.mascotas.get(cedula)==null || Cliente.mascotas.get(cedula).size()==0) {
			System.out.println("El cliente no tiene mascotas registradas");}
		System.out.print("\n");
		System.out.print("Ingrese el número de la mascota que será atendida: ");
		int mascota = entrada.nextInt();
		entrada.nextLine();
		String cedulaDoctor = "";
		boolean valido2=false;
		while(valido2==false) {
			
			System.out.print("Ingrese la cedula del médico que atenderá el turno: ");
			cedulaDoctor = entrada.nextLine();
			 if(Medico.validarCedula(cedulaDoctor)) {
				 valido2=true;
			 }else {
				 System.out.print("La cédula no existe en el sistema, por favor ingrese una válida\n\n");
			 }
			
		}
		System.out.print("Ingrese la fecha de hoy (dd-mm-aaaa):");
		String date = entrada.nextLine();
		System.out.print("Ingrese el diagnóstico:");
		String Justificacion = entrada.nextLine();
		
        Diagnostico Diagnostico1 = new Diagnostico(date, Medico.mapaMedico.get(cedulaDoctor), Cliente.mapaClientes.get(cedula), Cliente.mascotas.get(cedula).get(mascota-1), Justificacion);
        Diagnostico1.Diagnos();
        Diagnostico1.recomendarMedicamentos();
        Diagnostico1.generarFormulaMedica();
        System.out.println(Diagnostico1);
			
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

	



