import tkinter as tk
from tkinter import messagebox
from Uimain import VentanaInicio
from Uimain import FieldFrame
from Clientes.Cliente import Cliente
from Clientes.Mascota import Mascota
from Veterinaria.Medico import Medico
from Uimain.fotos.excepciones import *

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
            elif((cedulaDuenno not in Cliente.mapaClientes)):
                error = cedulaInvalida(cedulaDuenno)
                raise error
        
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
            Medico.mapaMedico[cedula] = medico1
            messagebox.showinfo("Regristros", "El medico ha sido registrado")
            self.formulario.borrarCampos()
        
        except campoVacio as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoInt as error:
            messagebox.showwarning("Regristros", error.error)
        except tipoString as error:
            messagebox.showwarning("Regristros", error.error)