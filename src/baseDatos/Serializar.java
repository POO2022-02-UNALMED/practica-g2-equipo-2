package baseDatos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Veterinaria.Inventario;
import gestorAplicacion.Veterinaria.Medico;
import gestorAplicacion.Veterinaria.TurnoContab;

public class Serializar {

	public static void guardarDatos(){
		Base_Datos base = new Base_Datos();
		base.mapaClientes = Cliente.mapaClientes;
		base.cantidadClientes = Cliente.cantidadClientes;
		base.mapaMedico = Medico.mapaMedico;
		base.mascotas = Cliente.mascotas;
		base.Medicamentos = Inventario.Medicamentos;
		base.facturas = TurnoContab.facturas;
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/baseDatos/temp/base.txt"))){
			
			oos.writeObject(base);
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Salir");
	
	}
	
}
