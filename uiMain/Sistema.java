package uiMain;
import java.util.Scanner;
import gestorAplicacion.Veterinaria.*;
public class Sistema {

	public static void main(String args[]) {
		Medicamento onsior = new Medicamento(1000001, "Onsior", "Tabletas", 50, 3500);
		Medicamento amoxicilina = new Medicamento(1000002, "Amoxicilina", "Tabletas", 50, 2800);
		Medicamento profender = new Medicamento(1000003, "Profender", "Pipeta", 40, 2100);
		int opc=0;
		
		while(opc != 5) {
			System.out.println("Administrador Veterinaria\n");
			System.out.println("1. Registrar Cliente");
			System.out.println("2. Registrar Mascota");
			System.out.println("3. Registrar Doctor");
			System.out.println("4. Turno");
			System.out.println("5. Generar Factura");
			System.out.println("6. Datos contables");
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
			if(opc == 4) {
				Interaccion.agendarTurno();
			}
			if(opc == 5) {
				Interaccion.generarFactura();
			}
			if(opc==6){
				Interaccion.Caja();
			}
			if(opc == 0) {
				System.out.println("Salir");
			}
			System.out.println("\n\n"); 
		}
		
		
		
	}
	
	
}
