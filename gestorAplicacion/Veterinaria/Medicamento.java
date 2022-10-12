package gestorAplicacion.Veterinaria;

public class Medicamento {
    private int idMed;
    public String nombre;
    public String presentacion;
    public float concentracion;
    public float cantidad;
    public float precio;

    public Medicamento(int idMed, String nombre, String presentacion, float concentracion, float cantidad, float precio) {
        this.idMed = idMed;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.concentracion = concentracion;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public int getIdMed() {
        return idMed;
    }
    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public float getConcentracion() {
        return concentracion;
    }
    public void setConcentracion(float concentracion) {
        this.concentracion = concentracion;
    }
    public float getCantidad() {
        return cantidad;
    }
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void guardarMedicamento(int idMed, String nombre, String presentacion, float concentracion, float cantidad, float precio){
        this.idMed = idMed;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.concentracion = concentracion;
        this.cantidad = cantidad;
        this.precio = precio;
    }
  	
}
