from Clientes.Animal import Animal
class Mascota(Animal):
    
    cantidadMascotas = 0
    def __init__(self, nombreMascota, especie, raza, sexo, edad, peso, duenno):
        super().__init__(especie, nombreMascota, edad)
        self.raza = raza
        self.sexo = sexo
        self.peso = peso
        self.duenno = duenno
        self.id = Mascota.cantidadMascotas
        Mascota.cantidadMascotas+=1
    
    def getRaza(self):
        return self.raza
    
    def setRaza(self, raza):
        self.raza=raza
    
    def getSexo(self):
        return self.sexo
    
    def setSexo(self, sexo):
        self.sexo=sexo
        
    def getPeso(self):
        return self.peso
    
    def setPeso(self, peso):
        self.peso=peso
        
    def getDuenno(self):
        return self.duenno
    
    def setDuenno(self, duenno):
        self.duenno=duenno
        
    def getId(self):
        return self.id
    
    
    