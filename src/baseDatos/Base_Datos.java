package baseDatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
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

	//Sintomas y/o enfermedades tratadas
	public String sintomasLista[] = {"dolor", "inflama", "parasito", "parásito"};

	//Medicamentos: Componentes activos
	public String medicamentosAprobados[] = {"Robenacoxib", "Amoxicilina", "Pyrantel pamoate"};

	//Medicamentos: Nombres comerciales
	public Medicamento Onsior = new Medicamento(1000001, "Onsior", "Tabletas", 50, 3500);
	public Medicamento AmoxiTabs = new Medicamento(1000002, "Amoxi-Tabs®", "Tabletas", 50, 2800);
	public Medicamento Nemex2 = new Medicamento(1000003, "Nemex®-2", "Jarábe", 40, 2100);

}
