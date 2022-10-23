package gestorAplicacion.Veterinaria;
import gestorAplicacion.Clientes.Cliente;
import gestorAplicacion.Veterinaria.tipoMedico;


public class Factura {
    public Medico medico;
    public Cliente cliente;
    public Medicamento medicamento;
    public Turno turno;
    private double valorTurno; //dependiendo de la hora, el valor
    private short cantidadMedicamento; //tableta mg
    private double valorMedico; //general o especialista
    public double totalFactura;
  
    public Factura(Medico medico, Cliente cliente, Medicamento medicamento, short cantidadMedicamento, Turno turno ) {
        this.medico = medico;
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.cantidadMedicamento = cantidadMedicamento;
        this.turno = turno;
        this.valorMedico = valorMedico(medico);
        this.valorTurno = this.valorTurno(turno); 
        this.totalFactura = this.totalFactura();
        medicamento.ModificarInventario(cantidadMedicamento); 
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
        if (medico.getTipoMed() == tipoMedico.Especialista){ //calculo del valor de la cita de acuerdo al tipo de medico
            return valorMedico = 80.000;
        }   
        else    
            return valorMedico = 40.000;
    }

    public double valorTurno(Turno turno){
        if (turno.getHoraInicio() >= 18 || turno.getHoraInicio() < 8){ //condicion para valor cita según horario atención, cómo se veran las horas?
            return valorTurno = 40.000;
        }   
        else     
            return valorTurno = 25.000;
    }

    public double totalFacturasinDcto(){
        double calculoValorTotal;
        double calculoTotalMedicamento = cantidadMedicamento * medicamento.getPrecio();
        double calculoTotalTurno = valorTurno(turno) + valorMedico(medico);
        calculoValorTotal = calculoTotalMedicamento + calculoTotalTurno;
        return calculoValorTotal;
    }

    public double totalFactura(){
        double dctoFactura;
        double totalFacturaDcto;
        if (cliente.isFrecuente() == true ){
            dctoFactura = this.totalFacturasinDcto()*0.1;
            totalFacturaDcto = this.totalFacturasinDcto()-dctoFactura;
            return totalFacturaDcto;
        } 
        else
            return this.totalFacturasinDcto();
    }

    public String datosFactura(){
        return "El cliente: " + cliente.getNombre()+ "debe pagar un total de: $ " + this.totalFactura();
    
    }
    
    public double getTotalFactura() {
        return this.totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }
}