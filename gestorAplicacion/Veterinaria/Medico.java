package Veterinaria;

public class Medico extends Personal {
    public static String tipoMedico; //general o especialista

    public static String getTipoMedico() {
        return tipoMedico;
    }

    public void setTipoMedico(String tipoMedico) {
        Medico.tipoMedico = tipoMedico;
    }

    
}
