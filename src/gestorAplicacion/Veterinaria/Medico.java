package gestorAplicacion.Veterinaria;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Clientes.Comunicacion;

public class Medico extends Persona implements Comunicacion, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	public  Map<String, Turno[]> agenda = new HashMap<>();
	public static Map<String, Medico> mapaMedico = new HashMap<>();
    public tipoMedico tipoMed;//general o especialista

    public Medico(String nombre, String cedula, String telefono, tipoMedico tipoMed) {
        super(nombre, cedula, telefono);
        this.tipoMed = tipoMed;
        mapaMedico.put(cedula, this);
    }
    
    
    public tipoMedico getTipoMed(){
        return tipoMed;
    }
    
    public void setTipoMed(tipoMedico tipoMed) {
        this.tipoMed = tipoMed;
    } 
    
    public void crearFecha(String fecha) { // valida si la fecha ingresada ya esta contemplada en la agenda del doctor, si no lo esta crea un arreglo de 24 posiciones con turno vacios y lo ingresa a la agenda del doctor
    	
    	if(!this.agenda.containsKey(fecha)) {
    		Turno[] turnos = new Turno[24];
    		for(int i = 1; i<25; i++) {
    			turnos[i-1] = new Turno((i-1),fecha,null,null,true,this);
    		}
    		this.agenda.put(fecha, turnos);
		}
		
	}
    public static String obtenerMedicos(tipoMedico tipoMed) { //recibe una constante del enum y devuelve los medicos cuyo atributo tipoMed concuerda con la constante
    	String cadena = "";
    	
    	for (Map.Entry<String, Medico> entry : Medico.mapaMedico.entrySet()) {
    		if(entry.getValue().getTipoMed().equals(tipoMed)){
    			cadena = cadena + (entry.getValue().getNombre()+" - Cedula: "+ entry.getValue().getCedula() +"\n");// acumula en un string los datos de todos los medicos que cumplen con la condicion
    		}
	    }
    	return cadena;
    }    
    public String obtenerTurnosDisponibles(String fecha) { //consulta la agenda del doctor en la fecha ingresada y devuelve un string con la acumulacion de todo los turnos cuyo atributo disponible es true
    	String cadena = "";
    	for(int i = 0; i<24; i++) {
			if(this.agenda.get(fecha)[i].isDisponibilidad()) {
				if(this.agenda.get(fecha)[i].getHoraInicio()<13) { //acumula un string diferente en cadena dependiendo de si el turno es AM o PM
					cadena = cadena + "Turno "+(i+1)+": "+this.agenda.get(fecha)[i].getHoraInicio()+":00 AM\n";
				}else {
					cadena = cadena + "Turno "+(i+1)+": "+this.agenda.get(fecha)[i].getHoraInicio()+":00 PM\n";
				}
			}	
		}
    	return cadena;
    	
    }
    public void asignarTurno(String fecha, int turno, String cedulaCliente, int mascota) { // recibe la informacion necesaria 
    	this.agenda.get(fecha)[turno].setDisponibilidad(false); //el objeto turno ya esta instanciado en la agenda del medico, solo se usan los set para darle valor a sus atributos
		this.agenda.get(fecha)[turno].setCliente(Cliente.mapaClientes.get(cedulaCliente));
		this.agenda.get(fecha)[turno].setMascota(Cliente.mascotas.get(cedulaCliente).get(mascota));
		Cliente.mapaClientes.get(cedulaCliente).registrarHora(turno+1); //guarda la hora del turno en un hassmap que se usara despues para recomendar horarios
		if(Cliente.mapaClientes.get(cedulaCliente).sumaRegistros()>3) { //si el numero de registro en el hasmap es mayor que 3, establece el cliente como un cliente frecuente
			Cliente.mapaClientes.get(cedulaCliente).setFrecuente(true);
		}
		Cliente.mapaClientes.get(cedulaCliente).turnosPendientes.add(this.agenda.get(fecha)[turno]);
    }
    public static boolean validarCedula(String cedula) { //valida si la cedula se encuentra registrada en el sistema
		return Medico.mapaMedico.containsKey(cedula);
	}
    public String toString() {
		return "nombre: " + nombre + "\n" + "cedula: " + cedula + "\n" + "telefono: " + telefono;
	}

 
	@Override
	public String saludar() {  //metodo abstracto que se hereda de personal
		return "Hola soy medico";
	}
	
	public String presentarse() {
		return "El medico " + this.nombre + " fue registrado";
	}
	@Override
	public String llamar() {//metodo que se hereda de comunicacion
		return "Llamo desde mi telefono " + this.telefono;
	}

}