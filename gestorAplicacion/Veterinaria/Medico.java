package gestorAplicacion.Veterinaria;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.Clientes.Cliente;

public class Medico extends Personal {
    public  Map<String, Turno[]> agenda = new HashMap<>();
	public static Map<String, Medico> mapaMedico = new HashMap<>();
    public tipoMedico tipoMed;//general o especialista

    public Medico(String nombre, String cedula, String telefono, tipoMedico tipoMed) {
        super(nombre, cedula, telefono);
        this.tipoMed = tipoMed;
        mapaMedico.put(cedula, this);
    }
    
    
    public tipoMedico getTipoMed() {
        return tipoMed;
    }
    public void setTipoMed(tipoMedico tipoMed) {
        this.tipoMed = tipoMed;
    } 
    
    public void crearFecha(String fecha) {
    	
    	if(!this.agenda.containsKey(fecha)) {
    		Turno[] turnos = new Turno[24];
    		for(int i = 1; i<25; i++) {
    			turnos[i-1] = new Turno((i-1),fecha,null,null,true,this);
    		}
    		this.agenda.put(fecha, turnos);
		}
		
	}
    public static String obtenerMedicos(tipoMedico tipoMed) {
    	String cadena = "";
    	
    	for (Map.Entry<String, Medico> entry : Medico.mapaMedico.entrySet()) {
    		if(entry.getValue().getTipoMed().equals(tipoMed)){
    			cadena = cadena + (entry.getValue().getNombre()+" - Cedula: "+ entry.getValue().getCedula() +"\n");
    		}
	    }
    	return cadena;
    }
    
    public String obtenerTurnosDisponibles(String fecha) {
    	String cadena = "";
    	for(int i = 0; i<24; i++) {
			if(this.agenda.get(fecha)[i].isDisponibilidad()) {
				if(this.agenda.get(fecha)[i].getHoraInicio()<13) {
					cadena = cadena + "Turno "+(i+1)+": "+this.agenda.get(fecha)[i].getHoraInicio()+":00 AM\n";
				}else {
					cadena = cadena + "Turno "+(i+1)+": "+this.agenda.get(fecha)[i].getHoraInicio()+":00 PM\n";
				}
			}	
		}
    	return cadena;
    	
    }
    public void asignarTurno(String fecha, int turno, String cedulaCliente, int mascota) {
    	this.agenda.get(fecha)[turno].setDisponibilidad(false);
		this.agenda.get(fecha)[turno].setCliente(Cliente.mapaClientes.get(cedulaCliente));
		this.agenda.get(fecha)[turno].setMascota(Cliente.mascotas.get(cedulaCliente).get(mascota));
		
    }
    public static boolean validarCedula(String cedula) {
		return Medico.mapaMedico.containsKey(cedula);
	}
}