from Veterinaria import Factura

class Contabilidad:
    facturas=[]
    totalmedicosturno=0
    inicial=0
    esperado=0 
    retiro=0
    caja=0
    
    
    
    @classmethod
    def agregarFactura(cls, fac):
        cls.facturas.append(fac)

    @classmethod
    def getFacturas(cls):
        return cls.facturas
    
    @classmethod
    def setFacturas(cls, nuevas):
        cls.facturas=nuevas

    @classmethod
    def getTotalMedicosTurno(cls):
        return cls.totalmedicosturno
    
    @classmethod
    def SetTotalMedicosTurno(cls,nuevo):
        cls.totalmedicosturno=nuevo

    @classmethod
    def getInicial(cls):
        return cls.inicial
    
    @classmethod
    def setInicial(cls,nuevo):
        cls.inicial= nuevo
    
    @classmethod
    def getEsperado(cls):
        return cls.esperado
    
    @classmethod
    def setEsperado(cls,nuevo):
        cls.esperado= nuevo
    
    @classmethod
    def getRetiro(cls):
        return cls.retiro
    
    @classmethod
    def setRetiro(cls,nuevo):
        cls.retiro= nuevo
    
    @classmethod
    def getCaja(cls):
        return cls.caja
    
    @classmethod
    def setCaja(cls,nuevo):
        cls.caja= nuevo
    def CambioTurno(cls, retiro=0):
        cls.setInicial(cls.calcularCaja-retiro)
        cls.setFacturas([])
        cls.SetTotalMedicosTurno(0)

    def calcularCaja(cls):
        termina=cls.getInicial+cls.TotalDiario()-cls.getTotalMedicosTurno
        cls.setCaja(termina)
        return termina
    
    @classmethod
    def totalDiario(cls):
        acumulador=0
        for i in cls.facturas:
            acumulador+=i.getTotalFactura()
        return acumulador
    
    @classmethod
    def TotalMedico(cls):
        acum=0
        medicos={}
        for i in cls.facturas:
            if i.getMedico() in medicos:
                medicos[i.getMedico()]+=i.calcularValorMedico(i.getMedico().medico)
                acum+=i.calcularValorMedico(i.getMedico().medico)
            else:
                medicos[i.getMedico]=i.calcularValorMedico(i.getMedico().medico)
                acum+=i.calcularValorMedico(i.getMedico().medico)
        cls.SetTotalMedicosTurno(acum)

        return medicos
    