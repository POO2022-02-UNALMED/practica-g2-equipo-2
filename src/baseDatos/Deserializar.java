package baseDatos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Veterinaria.Inventario;
import gestorAplicacion.Veterinaria.Medico;
import gestorAplicacion.Veterinaria.TurnoContab;

public class Deserializar {
	
public static void leerDatos() {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/baseDatos/temp/base.txt"))){
			while(true) {
				Base_Datos base = (Base_Datos) ois.readObject();
				Cliente.cantidadClientes = base.cantidadClientes;
				Cliente.mapaClientes = base.mapaClientes;
				Cliente.mascotas = base.mascotas;
				Medico.mapaMedico = base.mapaMedico;
				Inventario.Medicamentos = base.Medicamentos;
				TurnoContab.facturas = base.facturas;
			}
			
		}catch(ClassNotFoundException e) {
		}catch(EOFException e) {
		}catch(IOException e) {
			
		}
		
	}

}
