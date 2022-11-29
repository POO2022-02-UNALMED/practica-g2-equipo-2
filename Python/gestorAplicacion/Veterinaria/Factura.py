from Veterinaria import TipoMedico
from Veterinaria import Contabilidad


class Factura:
    DESCUENTO = 0.1
    
    def __init__(self, medico, cliente, turno, cantidadMedicamento = 0, medicamento = None):
        # Constructor para factura con venta de Medicamento
        self.medico = medico
        self.cliente = cliente
        self.medicamento = medicamento
        self.cantidadMedicamento = cantidadMedicamento
        self.turno = turno
        self.totalFactura= self.calculoTotalFactura
        Contabilidad.Contabilidad.agregarFactura(self)


    
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

    def calcularValorMedico(self):
        if(self.medico.getTipoMedico() == TipoMedico.TipoMedico.ESPECIALISTA.value):
            return 80000   
        else: 
            return 40000

    def calcularValorTurno(self):
        if(self.turno.getHoraInicio() >= 18) or (self.turno.getHoraInicio() < 8):
            return 40000   
        else: 
            return 25000        

    def totalFacturasinDcto(self):
        
        if(self.cantidadMedicamento == 0):
            self.calculoValorTotal = self.calcularValorTurno() + self.calcularValorMedico() 
        else: 
            self.calculoTotalMedicamento = self.cantidadMedicamento * self.medicamento.getPrecio()
            self.calculoTotalTurno = self.calcularValorTurno() + self.calcularValorMedico()
            calculoValorTotal = self.calculoTotalMedicamento + self.calculoTotalTurno
        self.calculoValorTotal = calculoValorTotal

    def calculoTotalFactura(self):
        if(self.cliente.isFrecuente() == True):
            self.totalFacturaDcto = self.totalFacturasinDcto-(self.totalFacturasinDcto*Factura.DESCUENTO)
        else:
            self.totalFacturasinDcto