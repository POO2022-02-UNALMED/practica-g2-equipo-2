import tkinter as tk
from tkinter import messagebox
from Uimain import VentanaInicio
from Uimain import FieldFrame
from Clientes.Cliente import Cliente
from Clientes.Mascota import Mascota
from Veterinaria.Medico import Medico
from Veterinaria.Factura import Factura
from Uimain.excepciones import *
from baseDatos.Serializacion import Serializacion
from tkinter import simpledialog
from Veterinaria.Contabilidad import Contabilidad
from Veterinaria.Inventario import Inventario
from Veterinaria.Medicamento import Medicamento
from Veterinaria.TipoMedico import TipoMedico

class VentanaPrincipal(tk.Tk):
    
    def __init__(self):
        super().__init__()
        
        Medicamento(0,"Onsior","pastillas",50,5000)
        Medicamento(0,"Amoxi-tabs","tabletas",9,5000)
        Medicamento(0,"Nemex-2","pastillas",50,5000)
        
        #Serializacion.deserializar()
        
        self.protocol("WM_DELETE_WINDOW", self.on_closing)
        
        self.title("Vet-Admin")
        self.geometry("650x400")
        
        self.menuBar = tk.Menu(self)
        self.config(menu=self.menuBar)
        
        self.archivo = tk.Menu(self.menuBar)
        self.menuBar.add_cascade(label="Archivo", menu=self.archivo)
        self.archivo.add_command(label="Aplicacion", command=self.descripcion)
        self.archivo.add_command(label="Salir", command=self.salir)
        
        self.pyc = tk.Menu(self.menuBar)
        self.menuBar.add_cascade(label="Procesos y Consultas", menu=self.pyc)
        self.registros = tk.Menu(self.pyc)
        self.pyc.add_command(label="Pagina Inicio",command = lambda : self.show_frame(StartPage))
        self.pyc.add_cascade(label="Registros", menu=self.registros)
        self.registros.add_command(label="Registrar Cliente",command = lambda : self.show_frame(registrarCliente))
        self.registros.add_command(label="Registrar Mascota",command = lambda : self.show_frame(registrarMascota))
        self.registros.add_command(label="Registrar Medico",command = lambda : self.show_frame(registrarMedico))
        self.pyc.add_command(label="Agendar Turno",command = lambda : self.show_frame(agendarTurno))
        self.pyc.add_command(label="Facturación", command = lambda : self.show_frame(factura))
        
        self.contabilidad=tk.Menu(self.pyc)
        self.pyc.add_cascade(label="Contabilidad",menu=self.contabilidad)
        self.contabilidad.add_command(label="Mostrar dinero en caja", command=self.DineroCaja)
        self.contabilidad.add_command(label="Mostar pago a veterinarios", command=self.DeudMed)
        self.contabilidad.add_command(label="Mostrar inventario", command=self.Inven)
        self.cambioturno=tk.Menu(self.contabilidad)
        self.contabilidad.add_cascade(label="Cambio de turno",menu=self.cambioturno)
        self.cambioturno.add_command(label="Cambio de turno sin retiro", command=self.CamTurn)
        self.cambioturno.add_command(label="Cambio de turno con retiro", command=self.CamTurnR)
        
        self.ayuda = tk.Menu(self.menuBar)
        self.menuBar.add_command(label="Ayuda", command=self.msg_ayuda)
        
        container = tk.Frame(self)   
        container.pack(side = "top", fill = "both", expand = True)  
   
        container.grid_rowconfigure(0, weight = 1) 
        container.grid_columnconfigure(0, weight = 1) 
   
        self.frames = {}   
        
        for F in (StartPage, registrarMascota, registrarCliente, registrarMedico, agendarTurno, factura): 
   
            frame = F(container, self) 
            self.frames[F] = frame  
            frame.grid(row=0, column=0,sticky = 'nsew')
   
        self.show_frame(StartPage) 
        self.mainloop()
    
    def DineroCaja(self):
        Contabilidad.calcularCaja()
        if(Contabilidad.getCaja()==0):
            messagebox.showinfo("Dinero en caja","No hay dinero en caja")
        else:    
            messagebox.showinfo("Dinero en caja",str(Contabilidad.getCaja()))
    
    def DeudMed(self):
        cad=Contabilidad.TotalMedico()
        mess=""
        for i in cad:
            mess+="A el veterinario "+str(i.getNombre())+" se le adeuda: $"+str(cad[i])+"\n"
        if(len(cad)==0):
            mess="No hay pagos pendientes"
        messagebox.showinfo("Deuda veterinarios", mess)
    
    def Inven(self):
        mess=""
        for i in Inventario.getMedicamentos():
            if i.getCantidadDisponible()<11:
                mess+= str(i.getNombre())+": "+ str(i.getCantidadDisponible())+" bajas unidades " "\n"
            else:
                mess+= str(i.getNombre())+": "+ str(i.getCantidadDisponible())+"\n"
        messagebox.showinfo("Inventario", mess)
    
    def CamTurn(self):
        Contabilidad.CambioTurno()
        messagebox.showinfo("Cambio de turno", "Cambio de turno exitoso")
    
    def CamTurnR(self):
        inp=simpledialog.askinteger("Cambio de turno", "Ingrese el dinero que desee retirar",parent=self)
        try:
            if inp<0:
                error=tipoIntNeg()
                raise error
            
            Contabilidad.CambioTurno(inp)
            messagebox.showinfo("Cambio de turno",  "Cambio de turno exitoso")
            
        except tipoIntNeg as error:
            messagebox.showwarning("Error",error.error)


        
    
    
    def show_frame(self, cont): 
        frame = self.frames[cont] 
        frame.tkraise() 
           
        
    def salir(self):
        Serializacion.serializar()
        self.destroy()
        VentanaInicio.VentanaInicio()
       
    def descripcion(self):
        messagebox.showinfo("Descripcion", "Sistema administrativo para veterinaria, en el cual el administrador tendrá la posibilidad de llevar el control de los registros de usuarios y personal. Podrá agendar turnos a los usuarios verificando la disponibilidad y para clientes frecuentes, recomendará horarios frecuentes. El sistema tiene la opción de generar factura por servicios y productos, y de ver los datos contables (ventas totales del día, dinero en caja,etc).")

    def msg_ayuda(self):
        messagebox.showinfo("Ayuda", "Autores:\n- Juan Alejandro Espinosa\n- Maria Camila Arias\n- Sebastian Aguinaga\n- Hans Garcia")
        
    def on_closing(self):
        Serializacion.serializar()
        self.destroy() 
        
        
class StartPage(tk.Frame): 
    def __init__(self, parent, controller):  
        tk.Frame.__init__(self, parent) 
          
        self.subframe = tk.Frame(self)
        bienvenido = tk.Label(self.subframe, text="¡Bienvenido!",font=("Verdana",15))
        label = tk.Label(self.subframe, text ="En la parte superior encontrará la barra de menús.\n\
    En el menú Archivo podrá acceder a:\n\
    * Aplicación: seleccionando esta opción obtendrá información básica de lo que puede hacer en la aplicación.\n\
    * Salir: cerrará esta ventana y volverá a la ventana de Inicio. Si desde la ventana de Inicio selecciona de\n\
    nuevo la opción Salir entonces finalizará la aplicación.\n\
    \n\
    En el menú Procesos y Consultas podrá acceder a:\n\
    * Registros: desde esta opcion podra registrar clientes, mascotas o medicos\n\
    * Agendar Turno: con esta opción podra asignar un turno a un cliente,\n\
    para clientes frecuentes se muestran turnos recomendados.\n\
    * Facturas: a través de esta opción podra generar cobros de turnos o medicamentos \n\
    * Contabilidad: utilizando esta opción se puede acceder a la informacion contable de la caja\n\
    * Administración: seleccionando esta opción el administrador podrá llevar una cuenta de cuántas citas, \n\
    exámenes y entregas se han generado y de qué tipo; podrá contratar nuevos médicos para la plantilla y\n\
    construir nuevos consultorios; por último, podrá abastecerse de medicamentos.\n\
    \n\
    En el menú Ayuda podrá acceder a:\n\
    * Acerca de: seleccionando esta opción podrá ver el nombre de las personas encargadas del desarrollo de esta\n\
    aplicación.")
        bienvenido.pack()
        label.pack() 
        self.subframe.place(relx=0.5, rely=0.5, anchor="center")
           
   
   
class registrarMascota(tk.Frame): 
      
    def __init__(self, parent, controller): 
          
        tk.Frame.__init__(self, parent) 
        
        self.formulario = FieldFrame.FieldFrame(self,"Registrar Mascota",["Nombre","Especie","Raza","Sexo","Edad","Peso","Cedula dueño"],["Ingrese un nombre","","Ingrese una raza","","Ingrese una edad","Ingrese un peso","Ingrese una cedula"],["entry","combo","entry","combo","entero","entero","entero"])
        self.formulario.entradas[1]['values']=["Perro", "Gato"]
        self.formulario.entradas[1].set("Perro")
        self.formulario.entradas[3]['values']=["Macho", "Hembra"]
        self.formulario.entradas[3].set("Macho")
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.registrar
        
    def registrar(self):
        nombre = self.formulario.entradas[0].get().replace(' ','')
        especie = self.formulario.entradas[1].get()
        raza = self.formulario.entradas[2].get().replace(' ','')
        sexo = self.formulario.entradas[3].get()
        edad = self.formulario.entradas[4].get()
        peso = self.formulario.entradas[5].get()
        cedulaDuenno = self.formulario.entradas[6].get()
        
        try:
            
            if(len(nombre)==0 or len(raza)==0 or len(edad)==0 or len(peso)==0 or len(cedulaDuenno)==0 or nombre==self.formulario.valores[0] or raza==self.formulario.valores[2] or edad==self.formulario.valores[4] or peso==self.formulario.valores[5] or cedulaDuenno==self.formulario.valores[6]):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if(len(self.formulario.entradas[i].get())==0 or self.formulario.entradas[i].get()==self.formulario.valores[i]):
                        lista.append(self.formulario.criterios[i])
                
                error = campoVacio(lista)
                raise error
            elif((not edad.isdecimal()) or (not peso.isdecimal()) or (not cedulaDuenno.isdecimal())):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isdecimal()) and self.formulario.tipos[i]=="entero"):
                        lista.append(self.formulario.criterios[i])
                error = tipoInt(lista)
                raise error
            elif((not nombre.isalpha()) or (not raza.isalpha())):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isalpha()) and self.formulario.tipos[i]=="entry"):
                        lista.append(self.formulario.criterios[i])
                error = tipoString(lista)
                raise error
            elif((int(cedulaDuenno) not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaDuenno)
                raise error
            
            cedulaDuenno = int(cedulaDuenno)
            mascota1 = Mascota(nombre, especie, raza, sexo, edad, peso, Cliente.mapaClientes[cedulaDuenno])
            Cliente.mascotas[cedulaDuenno].append(mascota1)
            messagebox.showinfo("Regristros", "La mascota ha sido registrada")
            self.formulario.borrarCampos()
            
        except campoVacio as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoInt as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoString as error:
            messagebox.showwarning("Regristros", error.error)
        except cedulaInvalida as error:
            messagebox.showwarning("Regristros", error.error)
        #button1 = tk.Button(self, text ="StartPage", command = lambda : controller.show_frame(StartPage)) 
   
   
   
class registrarCliente(tk.Frame):  
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        self.formulario = FieldFrame.FieldFrame(self,"Registrar Cliente",["Nombre","Cedula","Telefono"],["Ingrese un nombre","Ingrese una cedula","Ingrese un telefono"],["entry","entero","entero"])
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.registrar
        
    def registrar(self):
        nombre = self.formulario.entradas[0].get().replace(' ','')
        cedula = self.formulario.entradas[1].get()
        telefono = self.formulario.entradas[2].get()
        
        try:
        
            if(len(nombre)==0 or len(cedula)==0 or len(telefono)==0 or nombre==self.formulario.valores[0] or cedula==self.formulario.valores[1] or telefono==self.formulario.valores[2]):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if(len(self.formulario.entradas[i].get())==0 or self.formulario.entradas[i].get()==self.formulario.valores[i]):
                        lista.append(self.formulario.criterios[i])
                
                error = campoVacio(lista)
                raise error
            elif((not cedula.isdecimal()) or (not telefono.isdecimal())):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isdecimal()) and self.formulario.tipos[i]=="entero"):
                        lista.append(self.formulario.criterios[i])
                error = tipoInt(lista)
                raise error
            elif(not nombre.isalpha()):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isalpha()) and self.formulario.tipos[i]=="entry"):
                        lista.append(self.formulario.criterios[i])
                error = tipoString(lista)
                raise error
        
            cedula = int(cedula)
            telefono = int(cedula)
            cliente1 = Cliente(nombre, cedula, telefono)
            Cliente.mapaClientes[cedula] = cliente1
            Cliente.mascotas[cedula] = []
            messagebox.showinfo("Regristros", "El cliente ha sido registrado")
            self.formulario.borrarCampos()
            
        except campoVacio as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoInt as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoString as error:
            messagebox.showwarning("Regristros", error.error)
        
        
class registrarMedico(tk.Frame):  
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        self.formulario = FieldFrame.FieldFrame(self,"Registrar Medico",["Nombre","Cedula","Telefono","Cargo"],["Ingrese un nombre", "Ingrese una cedula", "Ingrese un telefono", ""],["entry","entero","entero","combo"])
        self.formulario.entradas[3]['values']=["General", "Especialista"]
        self.formulario.entradas[3].set("General")
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.registrar
        
    def registrar(self):
        nombre = self.formulario.entradas[0].get().replace(' ','')
        cedula = self.formulario.entradas[1].get()
        telefono = self.formulario.entradas[2].get()
        cargo = self.formulario.entradas[3].get()
        
        try:
        
            if(len(nombre)==0 or len(cedula)==0 or len(telefono)==0 or nombre==self.formulario.valores[0] or cedula==self.formulario.valores[1] or telefono==self.formulario.valores[2]):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if(len(self.formulario.entradas[i].get())==0 or self.formulario.entradas[i].get()==self.formulario.valores[i]):
                        lista.append(self.formulario.criterios[i])
                
                error = campoVacio(lista)
                raise error
            elif((not cedula.isdecimal()) or (not telefono.isdecimal())):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isdecimal()) and self.formulario.tipos[i]=="entero"):
                        lista.append(self.formulario.criterios[i])
                error = tipoInt(lista)
                raise error
            elif(not nombre.isalpha()):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isalpha()) and self.formulario.tipos[i]=="entry"):
                        lista.append(self.formulario.criterios[i])
                error = tipoString(lista)
                raise error
        
            medico1 = Medico(nombre, int(cedula), int(telefono), cargo)
            Medico.mapaMedico[int(cedula)] = medico1
            messagebox.showinfo("Regristros", "El medico ha sido registrado")
            self.formulario.borrarCampos()
        
        except campoVacio as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoInt as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoString as error:
            messagebox.showwarning("Regristros", error.error)
            
            
            
class agendarTurno(tk.Frame): 
    def __init__(self, parent, controller):  
        tk.Frame.__init__(self, parent) 
          
        self.formulario = FieldFrame.FieldFrame(self, "Agendar turno", ["Cedula Cliente", "Lista de Mascotas","Tipo de medico", "Lista de medicos","Fecha"],["Ingrese la cedula del cliente","","","",""],["entry","combo","combo","combo","calendar"])
        
        buscarMascotas = tk.Button(self.formulario, text="Buscar mascotas", command=self.obtenerMascotas)
        buscarMascotas.grid(row=1,column=2, padx=5, pady=5)
        buscarMedicos = tk.Button(self.formulario, text="Buscar medicos", command=self.obtenerMedicos)
        buscarMedicos.grid(row=3,column=2, padx=5, pady=5)
        self.formulario.entradas[2]['values']=["General", "Especialista"]
        self.formulario.entradas[2].set("General")
        self.formulario.entradas[1]['values']=["Debe buscar mascotas"]
        self.formulario.entradas[1].set("Debe buscar mascotas")
        self.formulario.entradas[3]['values']=["Debe buscar medicos"]
        self.formulario.entradas[3].set("Debe buscar medicos")
        
        self.formulario.aceptar['command'] = self.asignarTurno
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        
    def obtenerMascotas(self):
        cedulaCliente = self.formulario.entradas[0].get()
        
        try:
            if(len(cedulaCliente)==0 or cedulaCliente == self.formulario.valores[0]):
                error = campoVacio(["Cedula Cliente"])
                raise error
            elif(not cedulaCliente.isdecimal()):
                error = tipoInt(["Cedula Cliente"])
                raise error
            elif((int(cedulaCliente) not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaCliente)
                raise error
            mascotas = Cliente.obtenerMascotas(int(cedulaCliente))
            if(mascotas == 0):
                messagebox.showwarning("Agendar Turno", "El cliente ingresado no posee mascotas")
            else:
                self.formulario.entradas[1]['values'] = mascotas
                self.formulario.entradas[1].current(0)
                messagebox.showinfo("Agendar Turno", "Se han obtenido las mascotas de este cliente,\npor favor seleccione una en la lista desplegable")
        
        except campoVacio as error:
            messagebox.showwarning("Agendar Turno", error.error)
        except tipoInt as error:
            messagebox.showwarning("Agendar Turno", error.error)
        except cedulaInvalida as error:
            messagebox.showwarning("Agendar Turno", error.error)
        
    def obtenerMedicos(self):
        tipo = self.formulario.entradas[2].get()
        medicos = Medico.obtenerNombresMedicos(tipo)
        if(medicos == 0):
            messagebox.showwarning("Agendar Turno", "No hay medicos registrados del tipo seleccionado")
        else:
            self.formulario.entradas[3]['values'] = medicos
            self.formulario.entradas[3].current(0)
            messagebox.showinfo("Agendar Turno", "Se han obtenido los medicos de este tipo,\npor favor seleccione uno en la lista desplegable")
    
    def asignarTurno(self):
        cedulaCliente = self.formulario.entradas[0].get()
        mascota = self.formulario.entradas[1].current()
        nombreMascota = self.formulario.entradas[1].get()
        tipo = self.formulario.entradas[2].get()
        medico = self.formulario.entradas[3].current()
        nombreMedico = self.formulario.entradas[3].get()
        cedulasMedicos = Medico.obtenerCedulasMedicos(tipo)
        fecha = self.formulario.entradas[4].get_date()
        
        try:
            if(len(cedulaCliente)==0 or cedulaCliente == self.formulario.valores[0]):
                error = campoVacio(["Cedula Cliente"])
                raise error
            elif(not cedulaCliente.isdecimal()):
                error = tipoInt(["Cedula Cliente"])
                raise error
            elif((int(cedulaCliente) not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaCliente)
                raise error
            elif(nombreMascota == "Debe buscar mascotas" or nombreMedico == "Debe buscar medicos"):
                lista = []
                if(nombreMascota == "Debe buscar mascotas"):
                    lista.append("Mascota")
                if(nombreMedico == "Debe buscar medicos"):
                    lista.append("Medico")
                error = comboInvalido(lista)
                raise error
            
            Medico.mapaMedico[cedulasMedicos[medico]].crearFecha(fecha)
            if(Cliente.mapaClientes[int(cedulaCliente)].isFrecuente()):
                respuesta = messagebox.askyesno("Cliente frecuente", "El cliente ingresado es frecuente,\n¿Desea ver los turnos recomendados para este cliente?")
                if(respuesta):
                    frecuentes = Cliente.mapaClientes[int(cedulaCliente)].obtenerTurnosFrecuentes(fecha, cedulasMedicos[medico])
                    if(len(frecuentes)==0):
                        messagebox.showwarning("Cliente frecuente","Los turno recomendados para este cliente no se encuentran disponibles en esta fecha, se mostraran los demas turnos dispobiles")
                        result = "Ver todos los disponibles"
                    else:
                        frecuentes.append("Ver todos los disponibles")
                        result = self.askComboValue(frecuentes)
                    if(result == "Ver todos los disponibles"):
                        disponibles = Medico.mapaMedico[cedulasMedicos[medico]].obtenerTurnosDisponibles(fecha)
                        result = self.askComboValue(disponibles)
                    for i in range(24):
                        if(result==("Turno "+str(i+1)+": "+str(i)+":00 AM") or result==("Turno "+str(i+1)+": "+str(i)+":00 PM")):
                            res = i
                    Medico.mapaMedico[cedulasMedicos[medico]].asignarTurno(fecha, res, int(cedulaCliente), mascota)
                else:
                    disponibles = Medico.mapaMedico[cedulasMedicos[medico]].obtenerTurnosDisponibles(fecha)
                    result = self.askComboValue(disponibles)
                    for i in range(24):
                        if(result==("Turno "+str(i+1)+": "+str(i)+":00 AM") or result==("Turno "+str(i+1)+": "+str(i)+":00 PM")):
                            res = i    
                    Medico.mapaMedico[cedulasMedicos[medico]].asignarTurno(fecha, res, int(cedulaCliente), mascota)
            else:                 
                disponibles = Medico.mapaMedico[cedulasMedicos[medico]].obtenerTurnosDisponibles(fecha)
                result = self.askComboValue(disponibles)
                for i in range(24):
                    if(result==("Turno "+str(i+1)+": "+str(i)+":00 AM") or result==("Turno "+str(i+1)+": "+str(i)+":00 PM")):
                        res = i       
                        Medico.mapaMedico[cedulasMedicos[medico]].asignarTurno(fecha, res, int(cedulaCliente), mascota)
            messagebox.showinfo("Turno asignado", "El turno ha sido asignado")
            self.formulario.entradas[1]['values']=["Debe buscar mascotas"]
            self.formulario.entradas[3]['values']=["Debe buscar medicos"]
            self.formulario.borrarCampos()
        
        except campoVacio as error:
            messagebox.showwarning("Agendar Turno", error.error)
        except tipoInt as error:
            messagebox.showwarning("Agendar Turno", error.error)
        except cedulaInvalida as error:
            messagebox.showwarning("Agendar Turno", error.error)
        except comboInvalido as error:
            messagebox.showwarning("Agendar Turno", error.error)
    
    def askComboValue(self, values):
        top = tk.Toplevel() # use Toplevel() instead of Tk()
        top.geometry("200x90")
        tk.Label(top, text='Seleccione un turno').pack(pady=10)
        box_value = tk.StringVar()
        combo = tk.ttk.Combobox(top, textvariable=box_value, values=values, state="readonly")
        combo.current(0)
        combo.pack()
        combo.bind('<<ComboboxSelected>>', lambda _: top.destroy())
        top.grab_set()
        top.wait_window(top)  # wait for itself destroyed, so like a modal dialog
        return box_value.get()
    

class factura(tk.Frame):   
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        self.formulario = FieldFrame.FieldFrame(self,"Generar factura",["Cédula Cliente","Cédula Médico","Turno a pagar","Fecha Turno","Medicamento","Cantidad medicamento"],
                                                ["Ingrese la cedula","Ingrese la cedula","","","","Ingrese una cantidad"],["entry","entry","combo","entry","combo","entry"])
        self.formulario.entradas[4]['values']=["Onsior", "Amoxi-Tabs C","Nemex-2"]
        self.formulario.entradas[4].set("Onsior")
        self.formulario.entradas[2].set("Debe buscar turnos pendientes")
        self.buscarTurno = tk.Button(self.formulario, text="Buscar Turnos")
        self.buscarTurno['command'] = self.obtenerTurnosAPagar
        self.formulario.entradas[3]['state']="disabled"
        self.formulario.entradas[2].bind('<<ComboboxSelected>>', self.cambiarFecha)
        self.buscarTurno.grid(row=3, column=2, padx=5, pady=5)
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.factura

    def cambiarFecha(self, event):
        cedulaCliente = self.formulario.entradas[0].get()
        turnoAPagar = self.formulario.entradas[2].current()
        
        try:
            if(len(cedulaCliente)==0 or cedulaCliente == self.formulario.valores[0]):
                error = campoVacio(["Cedula Cliente"])
                raise error
            elif(not cedulaCliente.isdecimal()):
                error = tipoInt(["Cedula Cliente"])
                raise error
            elif((int(cedulaCliente) not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaCliente)
                raise error
            cedulaCliente = int(cedulaCliente)
            self.formulario.entradas[3]['state']="normal"
            self.formulario.entradas[3].delete(0,len(self.formulario.entradas[3].get()))
            self.formulario.entradas[3].insert(0,str(Cliente.mapaClientes[cedulaCliente].turnosPendientes[turnoAPagar].getFecha()))
            self.formulario.entradas[3]['state']="disabled"
        
        except campoVacio as error:
            messagebox.showwarning("Facturacion", error.error)
        except tipoInt as error:
            messagebox.showwarning("Facturacion", error.error)
        except cedulaInvalida as error:
            messagebox.showwarning("Facturacion", error.error)
    
    def obtenerTurnosAPagar(self):
        cedulaCliente = self.formulario.entradas[0].get()
        try:
            if(len(cedulaCliente)==0 or cedulaCliente == self.formulario.valores[0]):
                error = campoVacio(["Cedula Cliente"])
                raise error
            elif(not cedulaCliente.isdecimal()):
                error = tipoInt(["Cedula Cliente"])
                raise error
            elif((int(cedulaCliente) not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaCliente)
                raise error
            cedulaCliente = int(cedulaCliente)        
            pendientes = Cliente.mapaClientes[int(cedulaCliente)].obtenerTurnosPendientes()
            if(pendientes == 0):
                messagebox.showwarning("Facturacion", "El cliente ingresado no tiene turnos pendientes")
            else:
                
                self.formulario.entradas[2]['values']=pendientes
                self.formulario.entradas[2].current(0)
                turnoAPagar = self.formulario.entradas[2].current()
                self.formulario.entradas[3]['state']="normal"
                self.formulario.entradas[3].delete(0,len(self.formulario.entradas[3].get()))
                self.formulario.entradas[3].insert(0,str(Cliente.mapaClientes[cedulaCliente].turnosPendientes[turnoAPagar].getFecha()))
                self.formulario.entradas[3]['state']="disabled"
                messagebox.showinfo("Pagar Turno", "Se han obtenido siguientes turnos pendientes por pagar,\npor favor seleccione uno en la lista desplegable")

        except campoVacio as error:
            messagebox.showwarning("Facturacion", error.error)
        except tipoInt as error:
            messagebox.showwarning("Facturacion", error.error)
        except cedulaInvalida as error:
            messagebox.showwarning("Facturacion", error.error)

    def factura(self):
        cedulaCliente = self.formulario.entradas[0].get()
        cedulaMedico = self.formulario.entradas[1].get()
        cantidadMed= self.formulario.entradas[5].get()
        turnoAPagar = self.formulario.entradas[2].current()
        medicamento = self.formulario.entradas[4].current()
        turno2 = self.formulario.entradas[2].get()
        
        
        try:
            
            if(len(cedulaCliente)==0 or len(cedulaMedico)==0 or len(cantidadMed)==0 or cedulaCliente==self.formulario.valores[0] or cedulaMedico==self.formulario.valores[1] or cantidadMed==self.formulario.valores[5]):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if(len(self.formulario.entradas[i].get())==0 or self.formulario.entradas[i].get()==self.formulario.valores[i]):
                        lista.append(self.formulario.criterios[i])
                
                error = campoVacio(lista)
                raise error
            elif((not cantidadMed.isdecimal()) or (not cedulaCliente.isdecimal()) or (not cedulaMedico.isdecimal())):
                lista = []
                for i in range(len(self.formulario.criterios)):
                    if((not self.formulario.entradas[i].get().isdecimal()) and self.formulario.tipos[i]=="entero"):
                        lista.append(self.formulario.criterios[i])
                error = tipoInt(lista)
                raise error
            elif((int(cedulaCliente) not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaCliente)
                raise error
            elif((int(cedulaMedico) not in Medico.mapaMedico)):
                error = cedulaInvalida(cedulaMedico)
                raise error
            elif(turno2 == "Debe buscar turnos pendientes"):
                lista = []
                lista.append("Turno")
                error = comboInvalido(lista)
                raise error
        
            cedulaMedico = int(cedulaMedico)
            cedulaCliente = int(cedulaCliente)
            cantidadMed = int(cantidadMed)
            Factura1= Factura(Medico.mapaMedico[cedulaMedico], Cliente.mapaClientes[cedulaCliente], Cliente.mapaClientes[cedulaCliente].turnosPendientes[turnoAPagar], cantidadMed, Inventario.Medicamentos[medicamento])
            messagebox.showinfo("Facturación", "El total a pagar del cliente "+Cliente.mapaClientes[cedulaCliente].getNombre()+" es de: $" + str(Factura1.calculoTotalFactura()))
            self.formulario.borrarCampos()
            Cliente.mapaClientes[cedulaCliente].turnosPendientes.pop(turnoAPagar)
            self.formulario.entradas[2].set("Debe buscar turnos pendientes")
            self.formulario.entradas[2]['values']=["Debe buscar turnos pendientes"]
        
        except campoVacio as error:
            messagebox.showwarning("Facturacion", error.error)
        except tipoInt as error:
            messagebox.showwarning("Facturacion", error.error)
        except cedulaInvalida as error:
            messagebox.showwarning("Facturacion", error.error)
        except comboInvalido as error:
            messagebox.showwarning("Facturacion", error.error)
       


        
        
        
        