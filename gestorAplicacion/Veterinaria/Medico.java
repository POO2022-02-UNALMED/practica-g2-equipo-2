package gestorAplicacion.Veterinaria;
import java.util.HashMap;
import java.util.Map;

public class Medico extends Personal {
    private  Map<String, Turno[]> agenda = new HashMap<>();
	public static Map<String, Personal> mapaPersonal = new HashMap<>();
    public String tipoMedico; //general o especialista

    public Medico(String nombre, String cedula, String telefono, String cargo, Map<String, Turno[]> agenda,
            String tipoMedico) {
        super(nombre, cedula, telefono);
        this.agenda = agenda;
        this.tipoMedico = tipoMedico;
        mapaPersonal.put(cedula, this);
    }
    public Map<String, Turno[]> getAgenda() {
        return agenda;
    }
    public void setAgenda(Map<String, Turno[]> agenda) {
        this.agenda = agenda;
    }
    public static Map<String, Personal> getMapaPersonal() {
        return mapaPersonal;
    }
    public static void setMapaPersonal(Map<String, Personal> mapaPersonal) {
        Medico.mapaPersonal = mapaPersonal;
    }
    public String getTipoMedico() {
        return tipoMedico;
    }
    public void setTipoMedico(String tipoMedico) {
        this.tipoMedico = tipoMedico;
    }
    public void crearFecha(String fecha) {
		Turno[] turnos = new Turno[24];
		for(int i = 1; i<25; i++) {
			turnos[i] = new Turno(i,fecha,null,null,true,this);
		}
		this.agenda.put(fecha, turnos);
	}   
}
