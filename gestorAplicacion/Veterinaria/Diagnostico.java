package gestorAplicacion.Veterinaria;

import java.util.ArrayList;
import Clientes.Mascota;
import Clientes.Cliente;

public class Diagnostico extends Mascota{
	private String fecha;
	private Personal veterinario;
	private String descripcion = "";
	private ArrayList<Medicamento> medicamentos = new ArrayList<>();
	private	Mascota mascota;
	
	public Diagnostico(String date, Personal persona, Cliente dueño, String nombreMascota, String especie, String raza, float edad, float peso, float altura) {
		this.fecha = date;
		this.veterinario = persona;
		setNombreDueño(dueño);
		setNombreMascota(nombreMascota);
		setEspecie(especie);
		setRaza(raza);
		setEdad(edad);
		setPeso(peso);
		setAltura(altura);		
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
	
	public String generarFormulaMedica() {
		ArrayList<String> diagnostico = new ArrayList<>();
		diagnostico = 
		
	}
	public void guardarDiagnostico() {
		
	}
}
