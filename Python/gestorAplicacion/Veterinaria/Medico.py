from Veterinaria.Persona import Persona
class Medico(Persona):
    mapaMedico= dict()
    
    def __init__(self, nombre, cedula, telefono, cargo):
        super().__init__(nombre, cedula, telefono)
        self.cargo = cargo
        self.agenda = dict()
    
    def getTipoMed(self):
        return self.cargo
    
    def setTipoMed(self, cargo):
        self.cargo = cargo