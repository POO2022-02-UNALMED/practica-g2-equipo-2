package gestorAplicacion.Veterinaria;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Mascota;

public class Turno {
	
	private int horaInicio;
	private String fecha;
	private Mascota mascota;
	private Cliente cliente;
	private boolean disponibilidad;
	private Personal veterinario;
	
	
	public Turno(int horaInicio, String fecha, Mascota mascota, Cliente cliente, boolean disponibilidad,
			Personal veterinario) {
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
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
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
	public Personal getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Personal veterinario) {
		this.veterinario = veterinario;
	}
	
	
	
}
