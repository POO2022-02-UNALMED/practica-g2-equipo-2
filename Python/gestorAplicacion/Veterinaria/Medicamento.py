from gestorAplicacion.Veterinaria import Inventario
class Medicamento:
    def __init__(self, idMed,nombre,presentacion,cantidadDisponible,precio):
        self.idMed = idMed
        self.nombre = nombre
        self.presentacion = presentacion
        self.cantidadDisponible = cantidadDisponible
        self.precio = precio
        Inventario.Inventario.AñadirMedicamento(self)


    def getIdMed(self):
        return self.idMed
        
    def setIdMed(self, idMed):
        self.idMed = idMed

    def getNombre(self):
        return self.nombre
        
    def setNombre(self, nombre):
        self.nombre = nombre  

    def getPresentacion(self):
        return self.presentacion
        
    def setPresentacion(self, presentacion):
        self.presentacion = presentacion

    def getCantidadDisponible(self):
        return self.cantidadDisponible
        
    def setCantidadDisponible(self, cantidadDisponible):
        self.cantidadDisponible = cantidadDisponible  

    def getPrecio(self):
        return self.precio

    def setPrecio(self, precio):
        self.precio = precio          

             
