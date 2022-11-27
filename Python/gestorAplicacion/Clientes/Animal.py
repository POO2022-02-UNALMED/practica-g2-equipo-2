class Animal:
    
    def __init__(self, especie, nombreMascota, edad):
        self.especie = especie
        self.nombreMascota = nombreMascota
        self.edad = edad
        
    def getNombreMascota(self):
        return self.nombreMascota
    
    def setNombreMascota(self, nombreMascota):
        self.nombreMascota=nombreMascota
        
    def getEspecie(self):
        return self.nombreMascota
    
    def setEspecie(self, especie):
        self.especie=especie
        
    def getEdad(self):
        return self.edad
    
    def setEdad(self, edad):
        self.edad=edad