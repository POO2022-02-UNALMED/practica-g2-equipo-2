package gestorAplicacion.Veterinaria;
import gestorAplicacion.Clientes.Cliente;
import java.util.Scanner;

public class Factura {
    public Medico medico;
    public Cliente cliente;
    public Medicamento medicamento;
    public Turno turno; //horario de la cita
    private double valorTurno; //dependiendo de la hora, el valor
    private short cantidadMedicamento; //tableta mg
    private double valorMedico; //general o especialista
    public double totalFactura;
    public double dctoFactura;
    public double totalFacturaDcto;
    public boolean cancelado;
  
    public Factura(Medico medico, Cliente cliente, Medicamento medicamento, Turno turno) {
        this.medico = medico;
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.turno = turno;
        this.valorMedico=getValorMedico(medico);              //Calcula lo que se le debe pagar al medico y lo pone en su atributo
        medicamento.ModificarInventario(1);       //Modifica el inventario
    }   
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    public short getCantidadMedicamento() {
        return cantidadMedicamento;
    }
    public void setCantidadMedicamento(short cantidadMedicamento) {
        this.cantidadMedicamento = cantidadMedicamento;
    }
    public double getValorMedico(Medico medico) {
        if (medico.getTipoMed().toString() == "Especialista"){ //calculo del valor de la cita de acuerdo al tipo de medico
            return valorMedico = 80.000;
        }   
        else    
            return valorMedico = 40.000;
    }
    public double getValorMedico(){
        return this.valorMedico;
    }
    public void setValorMedico(double valorMedico) {
        this.valorMedico = valorMedico;
    }
    public double getValorTurno(Turno turno) {
        if (turno.getHoraInicio() >= 18 || turno.getHoraInicio() < 8){ //condicion para valor cita según horario atención, cómo se veran las horas?
            return valorTurno = 40.000;
        }   
        else     
            return valorTurno = 25.000;
    }
    public void setValorTurno(double valorTurno) {
        this.valorTurno = valorTurno;
    } 
    public boolean isCancelado() {
        return cancelado;
    }
    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    public double getTotalFactura() { 
        double calculoValorTotal;
        double calculoTotalMedicamento = this.cantidadMedicamento * medicamento.getPrecio();
        double calculoTotalTurno = this.valorTurno + this.valorMedico;
        calculoValorTotal = calculoTotalMedicamento + calculoTotalTurno;
        return calculoValorTotal;
    }
    public void setTotalFactura(double totalFactura){
        this.totalFactura = totalFactura;
    }
    public double getDctoFactura() {
        if (cliente.IsFrecuente() == true ){//falta crear método frecuente en clase Cliente
            dctoFactura=this.totalFactura*0.1;
            totalFacturaDcto = totalFactura-dctoFactura;
            return totalFacturaDcto;
        } 
        else
            return totalFactura;
    }
    public void setDctoFactura(double dctoFactura) {
        this.dctoFactura = dctoFactura;
    }
    public String toString(){
        if (cliente.IsFrecuente() == true ){
            return "El cliente: " + cliente.getNombre()+ "debe pagar un total de: $ " + getDctoFactura();
        }
        else 
            return "El cliente: " + cliente.getNombre()+ "debe pagar un total de: $ " + getTotalFactura();
    }
}