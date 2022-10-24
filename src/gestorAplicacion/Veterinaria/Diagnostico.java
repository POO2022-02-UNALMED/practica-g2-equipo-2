package gestorAplicacion.Veterinaria;

import java.util.*;
import java.io.Serializable;
import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;
import baseDatos.Base_Datos;
import java.util.ArrayList;
import java.util.HashMap;


public class Diagnostico implements Serializable{
	private static final long serialVersionUID = 8L;
	private Medico veterinario;
	private Cliente duenno;
	private Mascota mascota;
	private String fecha;
	private String justificacion = "";
	private Base_Datos bDatos = new Base_Datos();
	private String diagnostico = "";
	private ArrayList<Medicamento> medicamentos = new ArrayList<>();
	private String sintomasLista[] = {"dolor", "inflama", "parasito", "parásito"};
	private String[] sintomasDeterminados = new String[4];
	private StringBuilder sintomasFinales = new StringBuilder();
	int numeroSintomas = 0;
	public String medicamentosAprobados[] = {"Robenacoxib", "Amoxicilina", "Pyrantel pamoate"};
	private HashMap<String,String> SintomasAMedicinas = new HashMap<String,String>();
	private HashMap<String, String> medicinasDeterminadas = new HashMap<String,String>();
	int numeroMedicinasRecomendadas = 0;
	private HashMap<String, String> medicinaAComercial = new HashMap<String,String>();
		
	public Diagnostico(String date, Medico vete, Cliente due, Mascota mas, String Justificacion) {
		this.veterinario = vete;
		this.duenno = due;
		this.mascota = mas;
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

	private String listaMedicamentosRecomendados[] = {"","",""};
	private String recomendacion = " los siguientes medicamentos según la dósis especificada: \n";

	public HashMap<String,String> recomendarMedicamentos() {
		
		medicinaAComercial.put(medicamentosAprobados[0], bDatos.Onsior.getNombre());
		medicinaAComercial.put(medicamentosAprobados[1], bDatos.AmoxiTabs.getNombre());
		medicinaAComercial.put(medicamentosAprobados[2], bDatos.Nemex2.getNombre());
		
		for(String e: sintomasDeterminados){
			if (e == "dolor"){
					medicinasDeterminadas.put(SintomasAMedicinas.get(e),"         Presentación: 6 mg  Tableta con o sin Recubrimiento Gatos o 20 mg Tableta con o sin Recubrimiento Perros." + "\n          Suministrar: Vía Oral 1 tableta cada 1 día(s) por 3 día(s).");
					listaMedicamentosRecomendados[numeroMedicinasRecomendadas] = medicinaAComercial.get(SintomasAMedicinas.get(e));
					sintomasFinales.append("\n     - Dolor");
					numeroMedicinasRecomendadas++;
			}
			else if (e == "inflama"){
					medicinasDeterminadas.put(SintomasAMedicinas.get(e),"         Presentación: 100 mg  Tabletas con o sin Recubrimiento  Perros y Gatos." + "\n          Suministrar: Vía Oral 11 a 22 mg/kg cada 12 a 24 hora(s) de 10 a 14 día(s).");
					listaMedicamentosRecomendados[numeroMedicinasRecomendadas] = medicinaAComercial.get(SintomasAMedicinas.get(e));
					sintomasFinales.append ("\n     - Inflamación");
					numeroMedicinasRecomendadas++;
			}	
			else if (e == "parasito" || e == "parásito"){
					medicinasDeterminadas.put(SintomasAMedicinas.get(e),"         Presentación: 100 mL  Jarábe  Perros y Gatos." + "\n          Suministrar: Vía Oral 5 mL / 10 lb según edad:\n           A. Cada 2 semana(s) hasta las 12 semanas de edad.\n           B. Cada 1 mes(es) hasta los 6 mes(es) de edad.\n           C. Después de los 6 mes(es) de edad en adelante: Cada 3 mes(es).");
					listaMedicamentosRecomendados[numeroMedicinasRecomendadas] = medicinaAComercial.get(SintomasAMedicinas.get(e));
					sintomasFinales.append ("\n     - Parásitos");
					numeroMedicinasRecomendadas++;
			}			
		}
		return medicinasDeterminadas;
	}
	
	public String generarFormulaMedica() {	
		
		for(Map.Entry<String, String> entry : medicinasDeterminadas.entrySet()) {
				this.recomendacion = this.recomendacion + "\n        - " +entry.getKey() + "\n " + entry.getValue() + "\n";
		}
		
		this.diagnostico = "\nFecha: " + fecha + "\n" + "\n\nDatos del Prestador" + "\n--> Médico Veterinario: " + this.veterinario.getNombre() + "\n" + "\nDatos del Paciente" + "\n--> Cliente: " + this.duenno.getNombre() + "\n--> Nombre de la Mascota: " + this.mascota.getNombreMascota() + "\n--> Edad: " + this.mascota.getEdad()+ " año(s)" +"\n--> Peso: "+ this.mascota.getPeso() + " kg" + "\n--> Especie: " + this.mascota.getEspecie()+ "\n--> Raza: " + this.mascota.getRaza() + "\n--> Sexo: " + this.mascota.getSexo() + "\n" + "\n" + "\nFÓRMULA MÉDICA VETERINARIA" +"\n\nDiagnóstico" +"\n--> Justificación: " + justificacion + "\n--> Síntomas:" + sintomasFinales + "\n" + "\n" + "Medicamentos y Prescripción \n--> Recomendación: Suministrar a " + this.mascota.getNombreMascota() + recomendacion + "\n\nFORMULA MÉDICA VÁLIDA POR 30 DÍAS A PARTIR DE LA FECHA DE EXPEDICIÓN" +"\n\nPor favor, diríjase a su clínica veterinaria más cercana para adquirir los medicamentos en el tiempo establecido, de lo contrario podría requerir una nueva valoración por médicina veterinaria." + "\n" + "\n" + "\n";
		
		return diagnostico;
	}
	
	@Override
	public String toString () {
		return diagnostico;
	}
	
	public void guardarDiagnostico() {
		
	}
}

