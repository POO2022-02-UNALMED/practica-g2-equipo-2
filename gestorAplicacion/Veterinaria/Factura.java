package gestorAplicacion.Veterinaria;
import java.util.ArrayList;
import gestorAplicacion.Clientes.Cliente;

public class Factura {
    public ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
    public Personal vendedor;
    public Personal medico;
    public Cliente cliente;
    public float total;
    public boolean cancelado;
    public Medicamento medicamento;
    public Turno turno;
     
    public Factura(ArrayList<Medicamento> medicamentos, Personal vendedor, Personal medico, Cliente cliente,
            float total, boolean cancelado, Medicamento medicamento, Turno turno) {
        this.medicamentos = medicamentos;
        this.vendedor = vendedor;
        this.medico = medico;
        this.cliente = cliente;
        this.total=total;
        this.cancelado = cancelado;
        this.medicamento = medicamento;
        this.turno = turno;
    }
    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
    public Personal getVendedor() {
        return vendedor;
    }
    public void setVendedor(Personal vendedor) {
        this.vendedor = vendedor;
    }
    public Personal getMedico() {
        return medico;
    }
    public void setMedico(Personal medico) {
        this.medico = medico;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public boolean isCancelado() {
        return cancelado;
    }
    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    public Medicamento getMedicamento() {
        return medicamento;
    }
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    void ingresarProductos(Medicamento medicamento){
    }
    void ingresarServicio(Turno turno){
    }
}
