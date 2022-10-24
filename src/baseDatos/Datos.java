package baseDatos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Veterinaria.Inventario;
import gestorAplicacion.Veterinaria.Medico;

public class Datos {

	public static void guardarDatos(){
		Base_Datos base = new Base_Datos();
		base.mapaClientes = Cliente.mapaClientes;
		base.cantidadClientes = Cliente.cantidadClientes;
		base.mapaMedico = Medico.mapaMedico;
		base.mascotas = Cliente.mascotas;
		base.Medicamentos = Inventario.Medicamentos;
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("base.ddr"))){
			
			oos.writeObject(base);
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Salir");
	
	}
	
	public static void leerDatos() {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("base.ddr"))){
			while(true) {
				Base_Datos base = (Base_Datos) ois.readObject();
				Cliente.cantidadClientes = base.cantidadClientes;
				Cliente.mapaClientes = base.mapaClientes;
				Cliente.mascotas = base.mascotas;
				Medico.mapaMedico = base.mapaMedico;
				Inventario.Medicamentos = base.Medicamentos;
			}
			
		}catch(ClassNotFoundException e) {
		}catch(EOFException e) {
		}catch(IOException e) {
			
		}
		
	}


	
}
