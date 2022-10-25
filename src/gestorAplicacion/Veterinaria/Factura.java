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
    private static final double DESCUENTO = 0.1; //descuento que se aplicará en las facturas de clientes frecuentes
    private double valorTurno; //dependiendo de la hora, el valor
    private int cantidadMedicamento; //atributo que se ingresará por consola
    private double valorMedico; //general o especialista
    public double totalFactura;
  
    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Factura(Medico medico, Cliente cliente, Medicamento medicamento, int cantidadMedicamento, Turno turno ) { 
        //constructor para factura con venta de Medicamento
        this.medico = medico;
        this.cliente = cliente;
        this.medicamento = medicamento;
        this.cantidadMedicamento = cantidadMedicamento;
        this.turno = turno;
        this.valorTurno = this.calcularValorTurno(turno); 
        Animal animal1 = turno.getMascota(); //implementación de ligadura dinámica
        this.totalFactura = this.calculoTotalFactura() + animal1.sobrecargoEdad(); //adición de sobrecargo a la factura total
        medicamento.ModificarInventario(cantidadMedicamento); //llamada al método que le va restando la cantidad de medicamento ingresada por el usuario a la cantidad disponible total 
        TurnoContab.agregarFactura(this); //llamada al método que agrega las facturas realizadas
    }
       
    public Factura(Medico medico, Cliente cliente, int cantidadMedicamento, Turno turno) {
        //constructor para factura sin venta de Medicamento
        this.medico = medico;
        this.cliente = cliente;
        this.turno = turno;
        this.cantidadMedicamento = 0; //sin importar el número que ingrese el usuario, la cantidad será 0.
        Animal animal1 = turno.getMascota();
        this.totalFactura = this.calculoTotalFactura() + animal1.sobrecargoEdad();
        TurnoContab.agregarFactura(this);
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
        if (medico.getTipoMed() == tipoMedico.Especialista){ //cálculo del valor cita de acuerdo al tipo de medico (Especialista retorna un valor, General otro)
            return valorMedico = 80000;
        }   
        else    
            return valorMedico = 40000;
    }

    public double calcularValorTurno(Turno turno){
        if (turno.getHoraInicio() >= 18 || turno.getHoraInicio() < 8){ //cálculo del valor cita según horario atención (Nocturno o Diurno)
            return valorTurno = 40000;
        }   
        else     
            return valorTurno = 25000;
    }

    public double totalFacturasinDcto(){ //cálculo de la factura sin dcto, para clientes no frecuentes
        double calculoValorTotal;
        if (cantidadMedicamento == 0){ //cálculo de la factura, sin dcto y sin compra de medicamento
            return  calculoValorTotal = calcularValorTurno(turno) + calcularValorMedico(medico); //suma de los cálculos de los 2 métodos inmediatamente anteriores
        }
        else{ //cálculo de la factura, sin dcto y con compra de medicamento
            double calculoTotalMedicamento = cantidadMedicamento * medicamento.getPrecio(); //multiplicación cantidad medicamento ingresada por el usuario por el precio del medicamento
            double calculoTotalTurno = calcularValorTurno(turno) + calcularValorMedico(medico);
            calculoValorTotal = calculoTotalMedicamento + calculoTotalTurno; //suma de los dos cálculos inmediatamente anteriores
            return calculoValorTotal;
        }
    
    }

    public double calculoTotalFactura(){ //cálculo de la factura con dcto, para clientes frecuentes
        double totalFacturaDcto;
        if (cliente.isFrecuente() == true ){ //condición para verificar que el cliente es frecuente
            totalFacturaDcto = this.totalFacturasinDcto()-(this.totalFacturasinDcto()*DESCUENTO); //si el cliente es frecuente, se realiza el cálculo de la factura aplicando 10% de descuento
            return totalFacturaDcto;
        } 
        else
            return this.totalFacturasinDcto();
    }

}