from gestorAplicacion.Veterinaria.Persona import Persona
from gestorAplicacion.Veterinaria import Medico

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
    
    def obtenerTurnosFrecuentes(self, fecha, cedulaMedico): #devuelve un mapa con los turno que mas frecuenta el usuario
        
        #ordena el hashmap de registro hora segun sus valores de manera descendente para que las hras mas frecuentas queden en las primera posiciones
        result = dict(sorted(self.registroHoras.items(), key=lambda item:item[1], reverse=True))
        i=0
        lista = []
        for clave in result:
            if(i<2):
                if(Medico.Medico.mapaMedico[cedulaMedico].agenda[fecha][clave-1].isDisponibilidad()):
                    if(clave<13):
                        lista.append("Turno "+str(clave+1)+": "+str(clave)+":00 AM")
                    else:
                        lista.append("Turno "+str(clave+1)+": "+str(clave)+":00 PM")
                #crea un nuevo mapa solo con los registros de las horas mas frecuentadas
                i+=1
        
        return lista
    
    def obtenerTurnosPendientes(self):
        lista = []
        for i in range(len(self.turnosPendientes)):
            if(self.turnosPendientes[i].getHoraInicio()<13):
                lista.append("Turno "+str(self.turnosPendientes[i].getHoraInicio()+1)+": "+str(self.turnosPendientes[i].getHoraInicio())+":00 AM" + " - " + str(self.turnosPendientes[i].getFecha()))
            else:
                lista.append("Turno "+str(self.turnosPendientes[i].getHoraInicio()+1)+": "+str(self.turnosPendientes[i].getHoraInicio())+":00 PM" + " - " + str(self.turnosPendientes[i].getFecha()))
        if(len(lista)==0):
            return 0
        else:
            return lista
    
        
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