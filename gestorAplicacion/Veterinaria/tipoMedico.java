package gestorAplicacion.Veterinaria;

import java.io.Serializable;

public enum tipoMedico implements Serializable{
    General("General"), Especialista("Especialista");

    private String tipoMed;
    private tipoMedico(String tipo){
        this.tipoMed = tipo;
    }
    public String getTipoMed() {
        return tipoMed;
    }
}