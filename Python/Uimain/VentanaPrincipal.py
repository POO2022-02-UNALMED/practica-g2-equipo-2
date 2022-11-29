import tkinter as tk
from tkinter import messagebox
from Uimain import VentanaInicio
from Uimain import FieldFrame
from Clientes.Cliente import Cliente
from Clientes.Mascota import Mascota
from Veterinaria.Medico import Medico
from Veterinaria.Factura import Factura
from Uimain.excepciones import *

class VentanaPrincipal(tk.Tk):
    
    def __init__(self):
        super().__init__()
        self.title("Ventana inicio")
        self.geometry("500x400")
        
        self.menuBar = tk.Menu(self)
        self.config(menu=self.menuBar)
        
        self.archivo = tk.Menu(self.menuBar)
        self.menuBar.add_cascade(label="Archivo", menu=self.archivo)
        self.archivo.add_command(label="Aplicacion", command=self.descripcion)
        self.archivo.add_command(label="Salir", command=self.salir)
        
        self.pyc = tk.Menu(self.menuBar)
        self.menuBar.add_cascade(label="Procesos y Consultas", menu=self.pyc)
        self.registros = tk.Menu(self.pyc)
        self.factura = tk.Menu(self.pyc)
        self.pyc.add_command(label="Pagina Inicio",command = lambda : self.show_frame(StartPage))
        self.pyc.add_cascade(label="Registros", menu=self.registros)
        self.registros.add_command(label="Registrar Cliente",command = lambda : self.show_frame(registrarCliente))
        self.registros.add_command(label="Registrar Mascota",command = lambda : self.show_frame(registrarMascota))
        self.registros.add_command(label="Registrar Medico",command = lambda : self.show_frame(registrarMedico))
        self.pyc.add_command(label="Agendar Turno",command = lambda : self.show_frame(agendarTurno))
        self.pyc.add_cascade(label="Facturación", menu=self.factura)
        self.factura.add_command(label="Facturación con medicamento",command = lambda : self.show_frame(facturaConMed))
        
        self.ayuda = tk.Menu(self.menuBar)
        self.menuBar.add_command(label="Ayuda", command=self.msg_ayuda)
        
        container = tk.Frame(self)   
        container.pack(side = "top", fill = "both", expand = True)  
   
        container.grid_rowconfigure(0, weight = 1) 
        container.grid_columnconfigure(0, weight = 1) 
   
        self.frames = {}   
        
        for F in (StartPage, registrarMascota, registrarCliente, registrarMedico, agendarTurno, facturaConMed): 
   
            frame = F(container, self) 
            self.frames[F] = frame  
            frame.grid(row=0, column=0,sticky = 'nsew')
   
        self.show_frame(StartPage) 
        self.mainloop()
    
    
    def show_frame(self, cont): 
        frame = self.frames[cont] 
        frame.tkraise() 
           
        
    def salir(self):
        self.destroy()
        VentanaInicio.VentanaInicio()
       
    def descripcion(self):
        messagebox.showinfo("Descripcion", "Sistema administrativo para veterinaria, en el cual el administrador tendrá la posibilidad de llevar el control de los registros de usuarios y personal. Podrá agendar turnos a los usuarios verificando la disponibilidad y para clientes frecuentes, recomendará horarios frecuentes. El sistema tiene la opción de generar factura por servicios y productos, y de ver los datos contables (ventas totales del día, dinero en caja,etc).")

    def msg_ayuda(self):
        messagebox.showinfo("Ayuda", "Autores:\n- Juan Alejandro Espinosa\n- Maria Camila Arias\n- Sebastian Aguinaga\n- Hans Garcia")
        
        
        
        
class StartPage(tk.Frame): 
    def __init__(self, parent, controller):  
        tk.Frame.__init__(self, parent) 
          
        
        label = tk.Label(self, text ="¡Bienvenido!",font=("Verdana", 20)) 
        label.place(relx=0.5, rely=0.5, anchor="center")
           
   
   
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
        nombre = self.formulario.entradas[0].get()
        especie = self.formulario.entradas[1].get()
        raza = self.formulario.entradas[2].get()
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
        nombre = self.formulario.entradas[0].get()
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
            print(Cliente.mapaClientes[cedula])
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
        nombre = self.formulario.entradas[0].get()
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
        buscarMascotas.grid(row=1,column=2, padx=5, pady=10)
        buscarMedicos = tk.Button(self.formulario, text="Buscar medicos", command=self.obtenerMedicos)
        buscarMedicos.grid(row=3,column=2, padx=5, pady=10)
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
                            print(res)
                    Medico.mapaMedico[cedulasMedicos[medico]].asignarTurno(fecha, res, int(cedulaCliente), mascota)
                else:
                    disponibles = Medico.mapaMedico[cedulasMedicos[medico]].obtenerTurnosDisponibles(fecha)
                    result = self.askComboValue(disponibles)
                    for i in range(24):
                        if(result==("Turno "+str(i+1)+": "+str(i)+":00 AM") or result==("Turno "+str(i+1)+": "+str(i)+":00 PM")):
                            res = i
                            print(res)            
                    Medico.mapaMedico[cedulasMedicos[medico]].asignarTurno(fecha, res, int(cedulaCliente), mascota)
            else:                 
                disponibles = Medico.mapaMedico[cedulasMedicos[medico]].obtenerTurnosDisponibles(fecha)
                result = self.askComboValue(disponibles)
                print(result)
                for i in range(24):
                    if(result==("Turno "+str(i+1)+": "+str(i)+":00 AM") or result==("Turno "+str(i+1)+": "+str(i)+":00 PM")):
                        res = i
                        print(res)            
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
        
        self.formulario = FieldFrame.FieldFrame(self,"Generar factura",["Cédula Cliente","Cédula Médico","Turno a pagar","Medicamento","Cantidad medicamento"],None,["entry","entry","combo","combo","entry"])
        self.formulario.entradas[3]['values']=["Onsior", "Amoxi-Tabs C","Nemex-2"]
        self.formulario.entradas[3].set("Onsior")
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.factura

    def obtenerTurnosAPagar(self):
        cedulaCliente = int(self.formulario.entradas[0].get())
        pendientes = Cliente.mapaClientes[int(cedulaCliente)].obtenerTurnosPendiente()
        result = self.askComboValue(pendientes)
        print(result)
        if(pendientes == 0):
            messagebox.showwarning("Pagar Turno", "No hay turnos pendientes por pagar")
        else:
            for i in range(24):
                if(result==("Turno "+str(i+1)+": "+str(i)+":00 AM") or result==("Turno "+str(i+1)+": "+str(i)+":00 PM")):
                    res = i
                    print(res)            
                    Medico.mapaMedico[cedulasMedicos[medico]].asignarTurno(fecha, res, int(cedulaCliente), mascota)
            self.formulario.entradas[2]['values'] = pendientes
            self.formulario.entradas[2].current(0)
            messagebox.showinfo("Pagar Turno", "Se han obtenido siguientes turnos pendientes por pagar,\npor favor seleccione uno en la lista desplegable")
   
    def factura(self):
        cedulaCliente = int(self.formulario.entradas[0].get())
        cedulaMedico = int(self.formulario.entradas[1].get())
        cantidadMed= int(self.formulario.entradas[4].get())
        turnoAPagar = int(self.formulario.entradas[2].get())
        medicamento = self.formulario.entradas[3].get()
        Factura1= Factura(Medico.mapaMedico[cedulaMedico], Cliente.mapaClientes[cedulaCliente], cantidadMed ,turnoAPagar, medicamento)
        messagebox.showinfo("Facturación", "El total ha pagar es de: $" + Factura1.calculoTotalFactura())
        self.formulario.borrarCampos()

    def askComboValue(self, values):
        top = tk.Toplevel() # use Toplevel() instead of Tk()
        top.geometry("200x90")
        tk.Label(top, text='Seleccione el turno que desea pagar').pack(pady=10)
        box_value = tk.StringVar()
        combo = tk.ttk.Combobox(top, textvariable=box_value, values=values, state="readonly")
        combo.current(0)
        combo.pack()
        combo.bind('<<ComboboxSelected>>', lambda _: top.destroy())
        top.grab_set()
        top.wait_window(top)  # wait for itself destroyed, so like a modal dialog
        return box_value.get()
       


        
        
        
        