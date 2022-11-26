import tkinter as tk
from tkinter import messagebox
import os
import pathlib
from Uimain import VentanaInicio
from Uimain import FieldFrame
#from Uimain import VentanaInicio

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
        
        formulario = FieldFrame.FieldFrame(self,"Registrar Mascota",["Nombre","Especie","Raza","Sexo","Edad","Peso","Cedula dueño"],None,["entry","entry","entry","combo","entry","entry","entry"])
        formulario.entradas[3]['values']=["Macho", "Hembra"]
        formulario.entradas[3].set("Macho")
        formulario.place(relx=0.5, rely=0.5, anchor="center")
        
        #button1 = tk.Button(self, text ="StartPage", command = lambda : controller.show_frame(StartPage)) 
   
   
   
class registrarCliente(tk.Frame):  
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        formulario = FieldFrame.FieldFrame(self,"Registrar Cliente",["Nombre","Cedula","Telefono"],None,["entry","entry","entry"])
        formulario.place(relx=0.5, rely=0.5, anchor="center")
        
class registrarMedico(tk.Frame):  
    def __init__(self, parent, controller): 
        tk.Frame.__init__(self, parent) 
        
        formulario = FieldFrame.FieldFrame(self,"Registrar Medico",["Nombre","Cedula","Telefono","Cargo"],None,["entry","entry","entry","combo"])
        formulario.entradas[3]['values']=["General", "Especialista"]
        formulario.entradas[3].set("General")
        formulario.place(relx=0.5, rely=0.5, anchor="center")