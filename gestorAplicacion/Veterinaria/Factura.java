package Veterinaria;
import Clientes.Cliente;
import java.util.Scanner;

public class Factura {
    public Medico medico;
    public Cliente cliente;
    public Medicamento medicamento;
    public Turno turno; //horario de la cita
    private double valorTurno; //dependiendo de la hora, el valor
    private static short cantidadMedicamento; //tableta mg
    private double valorMedico; //general o especialista
    public double totalFactura;
    public boolean cancelado;
  
    public Factura(Medico medico, Cliente cliente, Medicamento medicamento, Turno turno, double valorTurno,
            short cantidadMedicamento,boolean cancelado) {
        this.medico = medico;
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.turno = turno;
        this.valorTurno = valorTurno;
        Factura.cantidadMedicamento = cantidadMedicamento;
        this.cancelado = cancelado;
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
    public static void setCantidadMedicamento(short cantidadMedicamento) {
        Factura.cantidadMedicamento = cantidadMedicamento;
    }
    public double getValorMedico(Medico medico) {
        if (Medico.getTipoMedico()== "Especialista") //calculo del valor de la cita de acuerdo al tipo de medico
            return valorMedico = 80.000;
        else    
            return valorMedico = 40.000;
    }
    public void setValorMedico(double valorMedico) {
        this.valorMedico = valorMedico;
    }
    public double getValorTurno(Turno turno) {
        if (Turno.getHoraInicio() >= 18 && Turno.getHoraInicio() < 8) //condicion para valor cita según horario atención, cómo se veran las horas?
            return valorTurno = 40.000;
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
        double calculoTotalMedicamento= cantidadMedicamento * Medicamento.getPrecio();
        double calculoTotalTurno= valorTurno + valorMedico;
        this.totalFactura = calculoTotalMedicamento + calculoTotalTurno;
        return calculoTotalMedicamento + calculoTotalTurno;
    }
    public void setTotalFactura(double totalFactura){
        this.totalFactura = totalFactura;
    }
    // falta descuento cliente frecuente
    public static void main(String[] args){ //pedirle al admin la cantidadMedicamento
        Scanner cantMed = new Scanner(System.in);
        System.out.println("Ingrese la cantidad (en tabletas) del medicamento: ");
            short cantidadMedicamento = cantMed.nextShort();
            Factura.setCantidadMedicamento(cantidadMedicamento);
    }
}