package gestorAplicacion.Veterinaria;

import java.util.HashMap;
import java.util.Map;

public class Personal {
	
	private String nombre;
	private String cedula;
	private String telefono;
	private String cargo;
	private  Map<String, Turno[]> agenda = new HashMap<>();
	public static Map<String, Personal> mapaPersonal = new HashMap<>();
	
	public Personal(String nombre, String cedula, String telefono, String cargo) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.cargo = cargo;
		mapaPersonal.put(cedula, this);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public void crearFecha(String fecha) {
		Turno[] turnos = new Turno[24];
		for(int i = 1; i<25; i++) {
			turnos[i] = new Turno(i,fecha,null,null,true,this);
		}
		this.agenda.put(fecha, turnos);
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
