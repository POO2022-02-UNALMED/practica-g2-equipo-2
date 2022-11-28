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
        
    def registrarHora(self, hora):  #cuando el cliente pide un turno, se invoca este metodo y se le pasa la hora del turno que se pidio
        aumento = self.registroHoras.get(hora) + 1
        self.registroHoras[hora] = aumento # aunmenta en uno el registro correspodiente a la hora del turno para llevar la cuenta de cuantas veces ha pedido turno en dicha hora
    
    def sumaRegistros(self): #suma todo los registros del cliente para saber cuantos turno ha pedido en total
        suma = 0
        for i in range(24):
            suma = suma + self.registroHoras.get(i)
        
        return suma
    

        
    def __str__(self):
        return "nombre: " + self.nombre + "\ncedula: " + str(self.cedula) + "\ntelefono: " + str(self.telefono)
    
    @classmethod
    def obtenerMascotas(cls, cedula):
        if(len(Cliente.mascotas[cedula])==0):
            return 0
        else:
            mascotas = []
            for i in range(len(Cliente.mascotas[cedula])):
                mascotas.append(Cliente.mascotas[cedula][i].getNombreMascota())
            return mascotas