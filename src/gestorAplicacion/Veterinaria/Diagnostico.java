package gestorAplicacion.Veterinaria;

import java.util.*;
import java.io.Serializable;
import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import java.util.ArrayList;
import java.util.HashMap;


public class Diagnostico extends Mascota implements Serializable{
	private static final long serialVersionUID = 8L;
	public Stock stock = new Stock();
	private String fecha;
	private String justificacion = "";
	private String recomendacion = "Medicamento y Prescripción \n Se recomienda al cliente suministrar a la mascota los siguientes medicamentos según la dósis especificada: \n";
	private String diagnostico = "";
	private ArrayList<Medicamento> medicamentos = new ArrayList<>();
	private	Mascota mascota;
	private String sintomasLista[] = {"dolor", "inflama", "parasito", "parásito"};
	private String sintomasDeterminados[] = {};
	int numeroSintomas = 0;
	public String medicamentosAprobados[] = {"Robenacoxib", "Amoxicilina", "Pyrantel pamoate"};
	private HashMap<String,String> SintomasAMedicinas;
	private HashMap<String, String> medicinasDeterminadas;
	private String listaMedicamentosRecomendados[];
	int numeroMedicinasRecomendadas = 0;
	private HashMap<String, String> medicinaAComercial;
		
	public Diagnostico(String date, Medico veterinario, Cliente duenno, String nombreMascota, String especie, String raza, int edad, int peso, String Justificacion) {
		super(nombreMascota, especie, raza, edad, peso, duenno, veterinario);
		this.fecha = date;
		this.justificacion = Justificacion;
	}
	
	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fech) {
		this.fecha = fech;
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
	
	public String getJustificacion() {
		return this.justificacion;
	}
	
	public void setJustificacion(String just) {
		this.justificacion = just;
	}

	//Sintomas
	
	public void Diagnos(){
		
		SintomasAMedicinas.put(sintomasLista[0], medicamentosAprobados[0]);
		SintomasAMedicinas.put(sintomasLista[1], medicamentosAprobados[1]);
		SintomasAMedicinas.put(sintomasLista[2], medicamentosAprobados[2]);
		SintomasAMedicinas.put(sintomasLista[3], medicamentosAprobados[2]);		
		
		boolean pain = getJustificacion().contains(sintomasLista[0]);
		boolean inflamation = getJustificacion().contains(sintomasLista[1]);
		boolean parasite = getJustificacion().contains(sintomasLista[2]);
		boolean paarasite = getJustificacion().contains(sintomasLista[3]);
		
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
	}
	
	//Recomendacion de medicamentos
	
	public HashMap<String,String> recomendarMedicamentos() {
		
		medicinaAComercial.put(medicamentosAprobados[0], stock.Onsior.getNombre());
		medicinaAComercial.put(medicamentosAprobados[1], stock.Amoxicilina.getNombre());
		medicinaAComercial.put(medicamentosAprobados[2], stock.Profender.getNombre());
		
		for(String e: sintomasDeterminados) {
			if (e == "dolor");
				medicinasDeterminadas.put(SintomasAMedicinas.get(e),"Presentación: 6 mg  Tableta con o sin Recubrimiento Gatos   o   20 mg Tableta con o sin Recubrimiento Perros" + "\nSuministrar (vía Oral) 1 tableta cada 1 día(s) por 3 día(s).");
				listaMedicamentosRecomendados[numeroMedicinasRecomendadas] = medicinaAComercial.get(SintomasAMedicinas.get(e));
				numeroMedicinasRecomendadas++;
			
			if (e == "inflama");
				medicinasDeterminadas.put(SintomasAMedicinas.get(e),"Presentación: 100 mg  Tabletas con o sin Recubrimiento  Perros y Gatos" + "\nSuministrar (vía Oral) 11 a 22 mg/kg cada 12 a 24 hora(s) de 10 a 14 día(s).");
				listaMedicamentosRecomendados[numeroMedicinasRecomendadas] = medicinaAComercial.get(SintomasAMedicinas.get(e));
				numeroMedicinasRecomendadas++;
			
			if (e == "parasito" || e == "parásito");
				medicinasDeterminadas.put(SintomasAMedicinas.get(e),"Presentación: 100 mL  Jarábe  Perros y Gatos" + "\nSuministrar (vía Oral) 5 mL / 10 lb según edad: - Cada 2 semana(s) hasta las 12 semanas de edad. - Cada mes hasta los 6 meses de edad. - Después de los 6 meses de edad en adelante: Cada 3 meses.");
				listaMedicamentosRecomendados[numeroMedicinasRecomendadas] = medicinaAComercial.get(SintomasAMedicinas.get(e));
				numeroMedicinasRecomendadas++;
		}			
		return medicinasDeterminadas;
	}
	
	public String generarFormulaMedica() {	
				
		for(Map.Entry<String, String> entry : medicinasDeterminadas.entrySet()) {
				this.recomendacion = this.recomendacion + entry.getKey() + ", " + entry.getValue() + "\n";
		}
		
		this.diagnostico = "Fecha: " + fecha + "\n" + "\nDatos del Prestador" + "\nVeterio: " + getVeterinario().getNombre() + "\n" + "\nDatos del Paciente" + "\nCliente: " + getDuenno().getNombre() + "\nNombreMascota: " + getNombreMascota() + "\nEdad: " + getEdad()+ "\nPeso: "+ getPeso() + "\nEspecie: " + getEspecie()+ "\nRaza: " + getRaza() + "\n" + "\n" +"Diagnostico" +"\nJustificación: " + justificacion + "\n" + "\nDiagnóstico(s): " + recomendacion;
		
		return diagnostico;
	}
	
	@Override
	public String toString() {
		return diagnostico;
	}
	
	public void guardarDiagnostico() {
		
	}
}

