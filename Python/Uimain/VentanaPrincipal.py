import tkinter as tk
from tkinter import messagebox
from Uimain import VentanaInicio
from Uimain import FieldFrame
from Clientes.Cliente import Cliente
from Clientes.Mascota import Mascota
from Veterinaria.Medico import Medico

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
        self.pyc.add_command(label="Startpage",command = lambda : self.show_frame(StartPage))
        self.pyc.add_cascade(label="Registros", menu=self.registros)
        self.registros.add_command(label="Registrar Cliente",command = lambda : self.show_frame(registrarCliente))
        self.registros.add_command(label="Registrar Mascota",command = lambda : self.show_frame(registrarMascota))
        self.registros.add_command(label="Registrar Medico",command = lambda : self.show_frame(registrarMedico))
        
        
        self.ayuda = tk.Menu(self.menuBar)
        self.menuBar.add_command(label="Ayuda", command=self.msg_ayuda)
        
        
        
        container = tk.Frame(self)   
        container.pack(side = "top", fill = "both", expand = True)  
   
        container.grid_rowconfigure(0, weight = 1) 
        container.grid_columnconfigure(0, weight = 1) 
   
        
        self.frames = {}   
   
        
        
        for F in (StartPage, registrarMascota, registrarCliente, registrarMedico): 
   
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
        messagebox.showinfo("Descripcion", "Este es un administrador para veterinarias")

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
        
        self.formulario = FieldFrame.FieldFrame(self,"Registrar Mascota",["Nombre","Especie","Raza","Sexo","Edad","Peso","Cedula dueño"],None,["entry","combo","entry","combo","entry","entry","entry"])
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
        peso = int(self.formulario.entradas[5].get())
        cedulaDuenno = int(self.formulario.entradas[6].get())
        if((cedulaDuenno not in Cliente.mapaClientes)):
            messagebox.showerror("Cedula invalida", "La cedula ingresada no se encuentra registrada,\npor favor registrela antes de continuar")
        else:
            mascota1 = Mascota(nombre, especie, raza, sexo, edad, peso, Cliente.mapaClientes[cedulaDuenno])
            Cliente.mascotas[cedulaDuenno].append(mascota1)
            messagebox.showinfo("Regristros", "La mascota ha sido registrada")
            self.formulario.borrarCampos()
        
        #button1 = tk.Button(self, text ="StartPage", command = lambda : controller.show_frame(StartPage)) 
   
   
   
class registrarCliente(tk.Frame):  
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        self.formulario = FieldFrame.FieldFrame(self,"Registrar Cliente",["Nombre","Cedula","Telefono"],["Pedro","100","300"],["entry","entry","entry"])
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.registrar
        
    def registrar(self):
        nombre = self.formulario.entradas[0].get()
        cedula = self.formulario.entradas[1].get()
        telefono = self.formulario.entradas[2].get()
        
        if(len(nombre)==0 or len(cedula)==0 or len(telefono)==0):
            messagebox.showerror("Regristros", "Debe llenar todos los campos")
        elif(not cedula.isdecimal()):
            messagebox.showerror("Regristros", "El campo cedula debe contener un numero")
        elif(not telefono.isdecimal()):
            messagebox.showerror("Regristros", "El campo telefono debe contener un numero")
        else:
            cliente1 = Cliente(nombre, int(cedula), int(telefono))
            Cliente.mapaClientes[cedula] = cliente1
            Cliente.mascotas[cedula] = []
            print(Cliente.mapaClientes[cedula])
            messagebox.showinfo("Regristros", "El cliente ha sido registrado")
            self.formulario.borrarCampos()
            
        
        
        
class registrarMedico(tk.Frame):  
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        self.formulario = FieldFrame.FieldFrame(self,"Registrar Medico",["Nombre","Cedula","Telefono","Cargo"],None,["entry","entry","entry","combo"])
        self.formulario.entradas[3]['values']=["General", "Especialista"]
        self.formulario.entradas[3].set("General")
        self.formulario.place(relx=0.5, rely=0.5, anchor="center")
        self.formulario.aceptar['command'] = self.registrar
        
    def registrar(self):
        nombre = self.formulario.entradas[0].get()
        cedula = int(self.formulario.entradas[1].get())
        telefono = int(self.formulario.entradas[2].get())
        cargo = self.formulario.entradas[3].get()
        medico1 = Medico(nombre, cedula, telefono, cargo)
        Medico.mapaMedico[cedula] = medico1
        messagebox.showinfo("Regristros", "El medico ha sido registrado")
        self.formulario.borrarCampos()