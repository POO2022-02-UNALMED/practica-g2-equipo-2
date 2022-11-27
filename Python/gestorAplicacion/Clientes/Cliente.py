from Veterinaria.Persona import Persona

class Cliente(Persona):
    mapaClientes = dict()
    cantidadClientes = 0
    mascotas = dict()
    
    def __init__(self, nombre, cedula, telefono):
        super().__init__(nombre, cedula, telefono)
        self.frecuente = False
        self.turnosPendientes = []
        self.registroHoras = dict()
        for i in range(24):
            self.registroHoras[i] = 0
        Cliente.cantidadClientes+=1
        
    def isFrecuente(self):
        return self.frecuente

    def setFrecuente(self, frecuente):
        self.frecuente=frecuente
        
    def __str__(self):
        return "nombre: " + self.nombre + "\ncedula: " + str(self.cedula) + "\ntelefono: " + str(self.telefono)