package gestorAplicacion.Veterinaria;

import java.io.Serializable;

public class Medicamento implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMed;
    public String nombre;
    public String presentacion;
    public int cantidadDisponible;  
    public float precio;
    public Medicamento(int idMed, String nombre, String presentacion, int cantidadDisponible, float precio) {
        this.idMed = idMed;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cantidadDisponible = cantidadDisponible;
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
        return cantidadDisponible;
    }
    public void setCantidad(int cantidad) {
        this.cantidadDisponible = cantidad;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void ModificarInventario(int sustraendo){          //modifica el atributo cantidad para llevar un inventario
        int viejo= this.cantidadDisponible;
        this.cantidadDisponible= viejo-sustraendo;
    }

    /*public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }*/
         	
}
