package Veterinaria;

import Clientes.Cliente;
import Clientes.Mascota;

public class Turno {

	public int horaInicio;
	private int horaFin;
	private String fecha;
	private Mascota mascota;
	private Cliente cliente;
	private boolean disponibilidad;
	private Personal veterinario;
	
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	public int getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
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
