package gestorAplicacion.Veterinaria;
import java.util.ArrayList;
import gestorAplicacion.Clientes.Cliente;

public class Factura {
    public ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
    public Personal vendedor;
    public Personal medico;
    public Cliente cliente;
    public int totalaPagar;
    public boolean cancelado;
    
    public Factura(ArrayList<Medicamento> medicamentos, Personal vendedor, Personal medico, Cliente cliente,
            int totalaPagar, boolean cancelado) {
        this.medicamentos = medicamentos;
        this.vendedor = vendedor;
        this.medico = medico;
        this.cliente = cliente;
        this.totalaPagar = totalaPagar;
        this.cancelado = cancelado;
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
    public int getTotalaPagar() {
        return totalaPagar;
    }
    public void setTotalaPagar(int totalaPagar) {
        this.totalaPagar = totalaPagar;
    }
    public boolean isCancelado() {
        return cancelado;
    }
    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
}
