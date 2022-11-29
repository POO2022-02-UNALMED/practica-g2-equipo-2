class SomeError(Exception):
    def __init__(self, error):
        self.error = "Manejo errores de la Aplicacion:\n" + error

class excepcionC1(SomeError):
    def __init__(self, error):
        super().__init__("ExcepcionC1\n\n"+error)

class excepcionC2(SomeError):
    def __init__(self, error):
        super().__init__("ExcepcionC2\n\n"+error)
        
class campoVacio(excepcionC1):
    def __init__(self, campos):
        lista = ""
        for i in campos:
            lista = lista +"- "+ i + "\n"
        super().__init__("Debe llenar los siguientes campos:\n"+lista)

class comboInvalido(excepcionC1):
    def __init__(self, campos):
        lista = ""
        for i in campos:
            lista = lista +"- "+ i + "\n"
        super().__init__("Debe seleccionar una opcion en las siguientes listas desplegables:\n"+lista)

class cedulaInvalida(excepcionC1):
    def __init__(self, cedula):
        super().__init__("La cedula " + str(cedula) + " no se encuentra registrada,\npor favor registrela antes de continuar")

class tipoInt(excepcionC2):
    def __init__(self, campos):
        lista = ""
        for i in campos:
            lista = lista +"- "+ i + "\n"
        super().__init__("Los siguientes campos deben contener numeros enteros:\n"+lista)

class tipoString(excepcionC2):
    def __init__(self, campos):
        lista = ""
        for i in campos:
            lista = lista +"- "+ i + "\n"
        super().__init__("Los siguientes campos deben tener solo caracteres alfabeticos:\n"+lista)
class tipoIntNeg(excepcionC2):
    def __init__(self, campos):
        super().__init__("Por favor ingrese un numero entero positivo")