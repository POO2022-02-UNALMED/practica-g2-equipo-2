from Clientes.Cliente import Cliente
from Veterinaria.Medico import Medico
import pickle
from Veterinaria.Contabilidad import Contabilidad
from Veterinaria.Inventario import Inventario

class Serializacion():
    
    @classmethod
    def serializar(cls):
        mapaClientes = Cliente.mapaClientes
        mapaMedicos = Medico.mapaMedico
        mascota = Cliente.mascotas
        facturas = Contabilidad.facturas
        medicamentos = Inventario.Medicamentos
        
        mapaClientes_file = open("../baseDatos/mapaClientes.pkl","wb")
        mapaMedicos_file = open("../baseDatos/mapaMedicos.pkl","wb")
        mascota_file = open("../baseDatos/mascota.pkl","wb")
        facturas_file = open("../baseDatos/facturas.pkl","wb")
        medicamentos_file = open("../baseDatos/medicamentos.pkl","wb")
        
        pickle.dump(mapaClientes, mapaClientes_file)
        pickle.dump(mapaMedicos, mapaMedicos_file)
        pickle.dump(mascota, mascota_file)
        pickle.dump(facturas, facturas_file)
        pickle.dump(medicamentos, medicamentos_file)
        
        mapaClientes_file.close()
        mapaMedicos_file.close()
        mascota_file.close()
        facturas_file.close()
        medicamentos_file.close()
    
    @classmethod   
    def deserializar(cls):
        
        mapaClientes_file = open("../baseDatos/mapaClientes.pkl","rb")
        mapaMedicos_file = open("../baseDatos/mapaMedicos.pkl","rb")
        mascota_file = open("../baseDatos/mascota.pkl","rb")
        facturas_file = open("../baseDatos/facturas.pkl","rb")
        medicamentos_file = open("../baseDatos/medicamentos.pkl","rb")
        
        Cliente.mapaClientes = pickle.load(mapaClientes_file)
        Medico.mapaMedico = pickle.load(mapaMedicos_file)
        Cliente.mascotas = pickle.load(mascota_file)
        Contabilidad.facturas = pickle.load(facturas_file)
        Inventario.Medicamentos = pickle.load(medicamentos_file)
        
        mapaClientes_file.close()
        mapaMedicos_file.close()
        mascota_file.close()
        facturas_file.close()
        medicamentos_file.close()