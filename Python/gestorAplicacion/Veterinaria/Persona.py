
class Persona:
    
    def __init__(self, nombre, cedula, telefono):
        self.nombre = nombre
        self.cedula = cedula
        self.telefono = telefono
    
    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre=nombre
        
    def getCedula(self):
        return self.cedula

    def setCedula(self, cedula):
        self.cedula=cedula
        
    def getTelefono(self):
        return self.telefono

    def setTelefono(self, telefono):
        self.telefono=telefono