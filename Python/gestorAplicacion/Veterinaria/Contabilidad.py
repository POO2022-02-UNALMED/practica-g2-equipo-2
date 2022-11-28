import Factura

class Contabilidad:
    facturas=[]
    totalmedicosturno=0
    inicial=0
    esperado=0 
    retiro=0
    caja=0
    
    def agregarFactura(cls, fac):
        cls.facturas.append(fac)

    def getFacturas(cls):
        return cls.facturas

    def setFacturas(cls, nuevas):
        cls.facturas=nuevas

    def getTotalMedicosTurno(cls):
        return cls.totalmedicosturno
    
    def SetTotalMedicosTurno(cls,nuevo):
        cls.totalmedicosturno=nuevo

    def getInicial(cls):
        return cls.inicial
    
    def setInicial(cls,nuevo):
        cls.inicial= nuevo
    
    def getEsperado(cls):
        return cls.esperado
    
    def setEsperado(cls,nuevo):
        cls.esperado= nuevo
    
    def getRetiro(cls):
        return cls.retiro
    
    def setRetiro(cls,nuevo):
        cls.retiro= nuevo
    
    def getCaja(cls):
        return cls.caja
    
    def setCaja(cls,nuevo):
        cls.caja= nuevo
    
    def calcularCaja(cls, retiro):
        termina=cls.TotalDiario()-cls.getTotalMedicosTurno-retiro
        return termina
    
    def totalDiario(cls):
        acumulador=0
        for i in cls.facturas:
            acumulador+=i.getTotalFactura()
        return acumulador
    
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
    
            
                


    


