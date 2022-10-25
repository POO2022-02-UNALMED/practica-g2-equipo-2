package gestorAplicacion.Veterinaria;
import java.io.Serializable;

import gestorAplicacion.Clientes.Animal;
import gestorAplicacion.Clientes.Cliente;


public class Factura implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Medico medico;
    public Cliente cliente;
    public Medicamento medicamento;
    public Turno turno;
    private static final double DESCUENTO = 0.1;
    private double valorTurno; //dependiendo de la hora, el valor
    private int cantidadMedicamento; //tableta mg
    private double valorMedico; //general o especialista
    public double totalFactura;
  
    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Factura(Medico medico, Cliente cliente, Medicamento medicamento, int cantidadMedicamento, Turno turno ) {
        this.medico = medico;
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.cantidadMedicamento = cantidadMedicamento;
        this.turno = turno;
        this.valorTurno = this.calcularValorTurno(turno); 
        Animal animal1 = turno.getMascota();
        this.totalFactura = this.calculoTotalFactura() + animal1.sobrecargoEdad();
        medicamento.ModificarInventario(cantidadMedicamento); 
        TurnoContab.agregarFactura(this);
    }
       
    public Factura(Medico medico, Cliente cliente, int cantidadMedicamento, Turno turno) {
    	this(medico, cliente, null, cantidadMedicamento, turno);
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

    public double getValorTurno() {
        return valorTurno;
    }

    public void setValorTurno(double valorTurno) {
        this.valorTurno = valorTurno;
    }

    public int getCantidadMedicamento() {
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

    public double calcularValorMedico (Medico medico){
        if (medico.getTipoMed() == tipoMedico.Especialista){ //calculo del valor de la cita de acuerdo al tipo de medico
            return valorMedico = 80000;
        }   
        else    
            return valorMedico = 40000;
    }

    public double calcularValorTurno(Turno turno){
        if (turno.getHoraInicio() >= 18 || turno.getHoraInicio() < 8){ //condicion para valor cita según horario atención, cómo se veran las horas?
            return valorTurno = 40000;
        }   
        else     
            return valorTurno = 25000;
    }

    public double totalFacturasinDcto(){
        double calculoValorTotal;
        if (cantidadMedicamento == 0){
            return  calculoValorTotal = calcularValorTurno(turno) + calcularValorMedico(medico);
        }
        else{
            double calculoTotalMedicamento = cantidadMedicamento * medicamento.getPrecio();
            double calculoTotalTurno = calcularValorTurno(turno) + calcularValorMedico(medico);
            calculoValorTotal = calculoTotalMedicamento + calculoTotalTurno;
            return calculoValorTotal;
        }
    
    }

    public double calculoTotalFactura(){
        double totalFacturaDcto;
        if (cliente.isFrecuente() == true ){
            totalFacturaDcto = this.totalFacturasinDcto()-(this.totalFacturasinDcto()*DESCUENTO);
            return totalFacturaDcto;
        } 
        else
            return this.totalFacturasinDcto();
    }

}