package Veterinaria;

import Clientes.Cliente;
import Clientes.Mascota;

public class Sistema {

	public static void main(String args[]) {
		
		Cliente cliente1 = Cliente.registrarCliente();
		System.out.print("\n");  
		System.out.print(cliente1);
		System.out.print("\n"); 
		System.out.print("\n"); 
		Mascota mascota1 = Mascota.registrarMascota();
		System.out.print("\n"); 
		System.out.print(mascota1); 
		
	}
	
	
}