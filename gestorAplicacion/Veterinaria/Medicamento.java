package Veterinaria;
import java.util.ArrayList;

public class Medicamento {
    private ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>(); 
    private int idMed;
    public String nombre;
    public static String presentacion;
    public int cantidad;
    public float precio;
    public Medicamento(int idMed, String nombre, String presentacion, int cantidad, float precio) {
        this.idMed = idMed;
        this.nombre = nombre;
        Medicamento.presentacion = presentacion;
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
    public static void setPresentacion(String presentacion) {
        Medicamento.presentacion = presentacion;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
    enum presentacion{
        Tableta120mg ("Presentación 1"), Tableta150mg ("Presentación 2"), Tableta250mg ("Presentación 3"), Tableta500mg ("Presentación 4");
        private presentacion(String presentacion){
            Medicamento.presentacion = presentacion;
         }
        public static String getPresentacion() {
            return presentacion;
        }
        private static String presentacion;
    }
      	
}
