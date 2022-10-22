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
  
    public Factura(Medico medico, Cliente cliente, Medicamento medicamento, Turno turno, short cantidadMedicamento) {
        this.medico = medico;
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.turno = turno;
        this.valorMedico = valorMedico(medico);
        this.valorTurno = this.valorTurno(); //va con this o sin this
        this.totalFactura = this.totalFactura();
        this.cantidadMedicamento = cantidadMedicamento;
        medicamento.ModificarInventario(cantidadMedicamento); //this?
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

    public double getValorTurno() {
        return valorTurno;
    }

    public void setValorTurno(double valorTurno) {
        this.valorTurno = valorTurno;
    }

    public short getCantidadMedicamento() {
        return cantidadMedicamento;
    }

    public void setCantidadMedicamento(short cantidadMedicamento) {
        this.cantidadMedicamento = cantidadMedicamento;
    }

    public double getValorMedico() {
        return valorMedico;
    }

    public void setValorMedico(double valorMedico) {
        this.valorMedico = valorMedico;
    }

    public double valorMedico (Medico medico){
        if (medico.getTipoMed().toString() == "Especialista"){ //calculo del valor de la cita de acuerdo al tipo de medico
            return valorMedico = 80.000;
        }   
        else    
            return valorMedico = 40.000;
    }

    public double valorTurno(){
        if (turno.getHoraInicio() >= 18 || turno.getHoraInicio() < 8){ //condicion para valor cita según horario atención, cómo se veran las horas?
            return valorTurno = 40.000;
        }   
        else     
            return valorTurno = 25.000;
    }

    public double totalFacturasinDcto(){
        double calculoValorTotal;
        double calculoTotalMedicamento = this.cantidadMedicamento * medicamento.getPrecio();
        double calculoTotalTurno = this.valorTurno + this.valorMedico;
        calculoValorTotal = calculoTotalMedicamento + calculoTotalTurno;
        return calculoValorTotal;
    }

    public double totalFactura(){
        double dctoFactura;
        double totalFacturaDcto;
        if (cliente.IsFrecuente() == true ){//falta crear método frecuente en clase Cliente
            dctoFactura=this.totalFacturasinDcto()*0.1;
            totalFacturaDcto = this.totalFacturasinDcto()-dctoFactura;
            return totalFacturaDcto;
        } 
        else
            return this.totalFacturasinDcto();
    }

    public String datosFactura(){
        return "El cliente: " + cliente.getNombre()+ "debe pagar un total de: $ " + totalFactura();
    
    }
    
    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }
}