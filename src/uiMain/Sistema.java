// Autores:
// Juan Alejandro Espinosa Caceres
// Maria Camila Arias Betancur
// Sebastian Aguinaga Velasquez
// Hans Guillermo Garcia Vargas

package uiMain;
import java.util.Scanner;

import baseDatos.Deserializar;
import baseDatos.Serializar;
public class Sistema {

	public static void main(String args[]) {
		
		
		Deserializar.leerDatos();
		int opc=100;
		
		while(opc != 0) {
			System.out.println("Administrador Veterinaria\n");
			System.out.println("1. Registrar Datos");
			System.out.println("2. Pedir Turno");
			System.out.println("3. Recomendar medicamentos");
			System.out.println("4. Generar Factura");
			System.out.println("5. Datos contables");         //Abre el submenu de datos contables
			System.out.println("0. Salir\n");

			System.out.print("Digite una opcion: ");
			
			@SuppressWarnings("resource")
			Scanner entrada=new Scanner(System.in);
			opc = entrada.nextInt();
			entrada.nextLine();
			
			if(opc == 1) {
				Interaccion.registros();
			}
			if(opc == 2) {
				Interaccion.agendarTurno();
			}
			if(opc == 3) {
				Interaccion.generarDiagnostico();
			}
			if(opc == 4) {
				Interaccion.generarFactura();
			}
			if(opc == 5){
				Interaccion.Caja();
			}
			if(opc == 0) {
				Serializar.guardarDatos();;
			}
			System.out.println(""); 
		}
		
		
		
	}
	
	
}

