from gestorAplicacion.Veterinaria import Medicamento

class Inventario:
    
    Medicamentos=[]
    
    @classmethod
    def getMedicamentos(cls):
        return cls.Medicamentos
    
    @classmethod
    def AñadirMedicamento(cls, nuevo):
        cls.Medicamentos.append(nuevo)

    
    

