package gestorAplicacion.Veterinaria;

import java.util.*;
import gestorAplicacion.Veterinaria.Medicamento;
import gestorAplicacion.Veterinaria.Personal;
import gestorAplicacion.Veterinaria.Stock;
import gestorAplicacion.Clientes.Mascota;
import java.util.ArrayList;
import java.util.HashMap;


public class Diagnostico extends Mascota{
	private String fecha;
	private Personal veterinario;
	private String descripcion = "";
	private ArrayList<Medicamento> medicamentos = new ArrayList<>();
	private	Mascota mascota;
	String sintomasLista[] = {"dolor", "inflama", "parasito", "parásito"};
	String sintomasDeterminados[] = {};
	String medicinasDeterminadas[] = {};
	int numeroSintomas = 0;
	public String medicamentosAprobados[] = {"Robenacoxib", "Amoxicilina", "Praziquantel y Emodepside"};
	HashMap<String, String> SintomasMedicinas = new HashMap<String, String>();
	
	public Diagnostico(String date, Personal persona, String duenno, String nombreMascota, String especie, String raza, int edad, int peso) {
		super();
		this.fecha = date;
		this.veterinario = persona;
		setNombreCliente(duenno);
		setNombre(nombreMascota);
		setEspecie(especie);
		setRaza(raza);
		setEdad(edad);
		setPeso(peso);
	}
	
	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fech) {
		this.fecha = fech;
	}
	
	public Personal getVeterinario() {
		return this.veterinario;
	}

	public void setVeterinario(Personal vet) {
		this.veterinario = vet;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String des) {
		this.descripcion = des;
	}
	
	public ArrayList<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamento> med) {
		this.medicamentos = med;
	}
	
	public Mascota getMascota() {
		return this.mascota;
	}

	public void setMascota(Mascota peti) {
		this.mascota = peti;
	}
	
	
	public HashMap<String, String> MedicinasEnfermedades(){
		SintomasMedicinas.put(sintomasLista[0], medicamentosAprobados[0]);
		SintomasMedicinas.put(sintomasLista[1], medicamentosAprobados[1]);
		SintomasMedicinas.put(sintomasLista[2], medicamentosAprobados[2]);
		SintomasMedicinas.put(sintomasLista[3], medicamentosAprobados[2]);
		return SintomasMedicinas;
	}
	
	public String Diagnos(){
		Scanner sc= new Scanner(System.in); 
		String diag = sc.nextLine();          //Ingreso de diagnostico en texto por parte del personal; se lee el diagnostico
		
		boolean pain = diag.contains(sintomasLista[0]);
		boolean inflamation = diag.contains(sintomasLista[1]);
		boolean parasite = diag.contains(sintomasLista[2]);
		boolean paarasite = diag.contains(sintomasLista[3]);
		
		HashMap<String, Boolean> Sintomas = new HashMap<String, Boolean>();
		
		Sintomas.put(sintomasLista[0], pain);
		Sintomas.put(sintomasLista[1], inflamation);
		Sintomas.put(sintomasLista[2], parasite);
		Sintomas.put(sintomasLista[3], paarasite);
		
		for(Map.Entry<String, Boolean> entry : Sintomas.entrySet()) {
			if (entry.getValue()) {
				sintomasDeterminados[numeroSintomas] = entry.getKey();
				numeroSintomas++;
			}
		}
						
		return diag;
	}
	
	public String[] recomendarMedicamentos() {
		int i = 0;
		for(String entry: sintomasDeterminados) {
			medicinasDeterminadas[i] = SintomasMedicinas.get(entry);
			i++;
		}
				
		return medicinasDeterminadas;
	}
	
	public String generarFormulaMedica() {
		String diagnostico = "Fecha: " + fecha + "\n" + "\nDatos del Prestador" + "\nVeterinario: " + veterinario.getNombrePersonal() + "\n" + "\nDatos del Paciente" +"\nCliente: " + getNombreCliente() + "\nNombreMascota: " 
				+ getNombre() + "\nEdad: " + getEdad()+ "\nPeso: "+ getPeso() + "\nEspecie: " + getEspecie()+ "\nRaza: " 
				+ getRaza() + "\n" + "\n" +"Diagnostico" +"\nJustificación: " + Diagnos() + "\n" + "\nDiagnóstico(s): " + sintomasDeterminados;
		return diagnostico;
	}
		
	public void guardarDiagnostico() {
		
	}
	
	public void imprimirFormula() {
		
	}
	
}
