from TipoMedico import TipoMedico
import Contabilidad


class Factura:
    DESCUENTO = 0.1
    
    def __init__(self, medico, cliente, cantidadMedicamento: int, turno, medicamento) -> None:
        # Constructor para factura con venta de Medicamento
        self.medico = medico
        self.cliente = cliente
        self.medicamento = medicamento
        self.cantidadMedicamento = cantidadMedicamento
        self.turno = turno
        self.totalFactura= self.calculoTotalFactura
        Contabilidad.agregarFactura(self)

    def __init__(self,medico, cliente, turno):
        self.medico = medico
        self.cliente = cliente
        self.turno = turno
        self.cantidadMedicamento = 0
        self.totalFactura= self.calculoTotalFactura
        Contabilidad.agregarFactura(self)


    
    def getTotalFactura(self):
        return self.totalFactura

    def getMedico(self):
        return self.medico
        
    def setMedico(self, medico):
        self.medico = medico

    def getCliente(self):
        return self.cliente    

    def setCliente(self, cliente):
        self.cliente = cliente

    def getMedicamento(self):
        return self.medicamento

    def setMedicamento(self, medicamento):
        self.medicamento = medicamento

    def getTurno(self):
        return self.turno    

    def setTurno(self, turno):
        self.turno = turno
    
    def getCantidadMedicamentos(self):
        return self.cantidadMedicamento

    def setCantidadMedicamento(self, cantidadMedicamento):
        self.cantidadMedicamento = cantidadMedicamento

    def calcularValorMedico(self,medico):
        if(medico.getTipoMedico() == TipoMedico.ESPECIALISTA.value):
            valorMedico = 80000   
        else: 
            valorMedico = 40000

    def calcularValorTurno(self,turno):
        if(turno.getHoraInicio() >= 18) or (turno.getHoraInicio() < 8):
            valorTurno = 40000   
        else: 
            valorMedico = 25000        

    def totalFacturasinDcto(self,cantidadMedicamento,calcularValorTurno,calcularValorMedico,medicamento):
        self.calculoValorTotal = calculoValorTotal
        if(self.cantidadMedicamento == 0):
            self.calculoValorTotal = calcularValorTurno() + calcularValorMedico() 
        else: 
            self.calculoTotalMedicamento = cantidadMedicamento * medicamento.getPrecio()
            self.calculoTotalTurno = calcularValorTurno() + calcularValorMedico()
            calculoValorTotal = self.calculoTotalMedicamento + self.calculoTotalTurno

    def calculoTotalFactura(self,cliente,totalFacturasinDcto, DESCUENTO):
        if(cliente.isFrecuente() == True):
            self.totalFacturaDcto = self.totalFacturasinDcto-(self.totalFacturasinDcto*DESCUENTO)
        else:
            self.totalFacturasinDcto   