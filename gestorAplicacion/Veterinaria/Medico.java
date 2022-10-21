package gestorAplicacion.Veterinaria;
import java.util.HashMap;
import java.util.Map;

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
		Turno[] turnos = new Turno[24];
		for(int i = 1; i<25; i++) {
			turnos[i-1] = new Turno((i-1),fecha,null,null,true,this);
		}
		this.agenda.put(fecha, turnos);
	}  
}