package gestorAplicacion.Veterinaria;
import java.util.ArrayList;
import java.util.HashMap;

public class Medicamento {
    
    private int idMed;
    public String nombre;
    public String presentacion;
    public int cantidad;  
    public float precio;
    public Medicamento(int idMed, String nombre, String presentacion, int cantidad, float precio) {
        this.idMed = idMed;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
        this.precio = precio;
        Inventario.setMedicamentos(this);
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
    public void ModificarInventario(int sustraendo){          //modifica el atributo cantidad para llevar un inventario
        int viejo= this.cantidad;
        this.cantidad= viejo-sustraendo;
    }

    /*public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }*/
         	
}
