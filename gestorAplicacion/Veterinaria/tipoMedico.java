package gestorAplicacion.Veterinaria;

public enum tipoMedico {
    General("General"), Especialista("Especialista");

    private String tipoMed;
    private tipoMedico(String tipo){
        this.tipoMed = tipo;
    }
    public String getTipoMed() {
        return tipoMed;
    }
}
