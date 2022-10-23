package uiMain;
import java.util.Scanner;
import java.util.Spliterators.AbstractLongSpliterator;
import gestorAplicacion.Veterinaria.*;

public class Sistema {

	public static void main(String args[]) {
	
		Datos.leerDatos();
		
		int opc=25;
		
		while(opc != 0) {
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
				System.out.println(Interaccion.generarFactura());
			}
			if(opc==6){
				Interaccion.Caja();
			}
			if(opc == 0) {
				Datos.guardarDatos();
			}
			System.out.println("\n\n"); 
		}
		
			
	}
	
	
}
