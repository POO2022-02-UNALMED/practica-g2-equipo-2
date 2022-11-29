from gestorAplicacion.Veterinaria.Persona import Persona
from gestorAplicacion.Veterinaria.Turno import Turno
from gestorAplicacion.Clientes import Cliente
class Medico(Persona):
    mapaMedico= dict()
    
    def __init__(self, nombre, cedula, telefono, cargo):
        super().__init__(nombre, cedula, telefono)
        self.cargo = cargo
        self.agenda = dict()
    
    def getTipoMedico(self):
        return self.cargo
    
    def setTipoMedico(self, cargo):
        self.cargo = cargo
        
    def crearFecha(self, fecha):
        if(fecha not in self.agenda):
            turnos = []
            
            for i in range(24):
                turnos.append(Turno(i, fecha, None, None, True, self))
            self.agenda[fecha] = turnos
            
    def obtenerTurnosDisponibles(self, fecha):
        lista = []
        for i in range(24):
            if(self.agenda[fecha][i].isDisponibilidad()):
                if(self.agenda[fecha][i].getHoraInicio()<13):
                    lista.append("Turno "+str(i+1)+": "+str(self.agenda[fecha][i].getHoraInicio())+":00 AM")
                else:
                    lista.append("Turno "+str(i+1)+": "+str(self.agenda[fecha][i].getHoraInicio())+":00 PM")
        if(len(lista)==0):
            return 0
        else:
            return lista
        
        
    def asignarTurno(self, fecha, turno, cedulaCliente, mascota): # recibe la informacion necesaria 
        self.agenda[fecha][turno].setDisponibilidad(False); #el objeto turno ya esta instanciado en la agenda del medico, solo se usan los set para darle valor a sus atributos
        self.agenda[fecha][turno].setCliente(Cliente.Cliente.mapaClientes[cedulaCliente])
        self.agenda[fecha][turno].setMascota(Cliente.Cliente.mascotas[cedulaCliente][mascota])
        Cliente.Cliente.mapaClientes.get(cedulaCliente).registrarHora(turno) #guarda la hora del turno en un hassmap que se usara despues para recomendar horarios
        
        if(Cliente.Cliente.mapaClientes.get(cedulaCliente).sumaRegistros()>3): #si el numero de registro en el hasmap es mayor que 3, establece el cliente como un cliente frecuente
            Cliente.Cliente.mapaClientes[cedulaCliente].setFrecuente(True)
            
        Cliente.Cliente.mapaClientes[cedulaCliente].turnosPendientes.append(self.agenda[fecha][turno])
        
    
        
    @classmethod
    def obtenerNombresMedicos(cls, tipo):
        lista=[]
        for cedula in Medico.mapaMedico:
            if(Medico.mapaMedico[cedula].getTipoMedico()==tipo):
                lista.append(Medico.mapaMedico[cedula].getNombre())
        if(len(lista)==0):
            return 0
        else:
            return lista
    
    @classmethod
    def obtenerCedulasMedicos(cls, tipo):
        lista=[]
        for cedula in Medico.mapaMedico:
            if(Medico.mapaMedico[cedula].getTipoMedico()==tipo):
                lista.append(Medico.mapaMedico[cedula].getCedula())
        if(len(lista)==0):
            return 0
        else:
            return lista