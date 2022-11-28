class Turno:
    
    def __init__(self, horaInicio, fecha, mascota, cliente, disponibilidad, veterinario):
        self.horaInicio = horaInicio
        self.fecha = fecha
        self.mascota = mascota
        self.cliente = cliente
        self.disponibilidad = disponibilidad
        self.veterinario = veterinario
        
    def getHoraInicio(self):
        return self.horaInicio
    
    def setHoraInicio(self, horaInicio):
        self.horaInicio = horaInicio
        
    def getFecha(self):
        return self.fecha
    
    def setFecha(self, fecha):
        self.fecha = fecha
        
    def getMascota(self):
        return self.mascota
    
    def setMascota(self, mascota):
        self.mascota = mascota
        
    def getCliente(self):
        return self.cliente
    
    def setCliente(self, cliente):
        self.cliente = cliente
        
    def isDisponibilidad(self):
        return self.disponibilidad
    
    def setDisponibilidad(self, disponibilidad):
        self.disponibilidad = disponibilidad
        
    def getVeterinario(self):
        return self.veterinario
    
    def setVeterinario(self, veterinario):
        self.veterinario = veterinario
        