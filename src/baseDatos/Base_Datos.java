package baseDatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import gestorAplicacion.Veterinaria.Factura;
import gestorAplicacion.Veterinaria.Medicamento;
import gestorAplicacion.Veterinaria.Medico;

public class Base_Datos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<String, Cliente> mapaClientes = new HashMap<>();
	public int cantidadClientes = 0;
	public Map<String, ArrayList<Mascota>> mascotas = new HashMap<>();
	public Map<String, Medico> mapaMedico = new HashMap<>();
	public ArrayList<Medicamento> Medicamentos= new ArrayList<Medicamento>();
	public ArrayList<Factura> facturas= new ArrayList<Factura>();
		
}
