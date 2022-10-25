package gestorAplicacion.Veterinaria;

import java.io.Serializable;
import gestorAplicacion.Clientes.Animal;
import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;

public class Turno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private int horaInicio;
	private String fecha;
	private Animal mascota;
	private Cliente cliente;
	private boolean disponibilidad;
	private Persona veterinario;
	
	
	public Turno(int horaInicio, String fecha, Animal mascota, Cliente cliente, boolean disponibilidad,
			Persona veterinario) {
		super();
		this.horaInicio = horaInicio;
		this.fecha = fecha;
		this.mascota = mascota;
		this.cliente = cliente;
		this.disponibilidad = disponibilidad;
		this.veterinario = veterinario;
	}
	
	
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Animal getMascota() {
		return mascota;
	}
	public void setMascota(Animal mascota) {
		this.mascota = mascota;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Persona getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Persona veterinario) {
		this.veterinario = veterinario;
	}
	
	
	
}
