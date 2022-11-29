import tkinter as tk
import os
import pathlib
path = os.path.join(pathlib.Path(__file__).parent.absolute())
#path = os.path.dirname(path)
#import Uimain.VentanaPrincipal
from uiMain import VentanaPrincipal
#from tkinter import ttk


class VentanaInicio(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Vet-Admin")
        self.geometry("500x400")
        #self.config(bg="skyblue")
        self.configure(bg="gray")
        
        #--- Menu -----------------------------------
        self.menuBar = tk.Menu(self)
        self.config(menu=self.menuBar)
        
        self.inicio = tk.Menu(self.menuBar)
        self.menuBar.add_cascade(label="Inicio", menu=self.inicio)
        
        self.inicio.add_command(label="Salir", command=self.salirr)
        self.inicio.add_command(label="Descripcion", command=self.descripcion)
        
        #--- Panel p3 --------------------------------
        self.P3 = tk.Frame(self)
        self.bienvenida = tk.Label(self.P3, text="¡Bienvenido a vet-admin!")
        self.bienvenida.place(relx=0.5, rely=0.5, anchor="center")
        
        #--- Panel p4 --------------------------------
        self.P4 = tk.Frame(self)
        self.icon = 0
        self.prb = tk.PhotoImage(file=path+"/uiMain/fotos/sistema/1.png")
        self.prbl = tk.Label(self.P4)
        self.prbl.config(image=self.prb) 
        self.prbl.bind("<Enter>",self.cambiarIcono)
        self.prbl.place(relx=0.5, rely=0.5, anchor="center")
        self.ingresar = tk.Button(self.P4, text="Ingresar", command=self.ingresar)
        self.ingresar.pack(side="bottom")
        
        #--- Panel p5 --------------------------------
        self.P5 = tk.Frame(self)
        self.hv = tk.Label(self.P5, text="Maria Camila Arias\nIngeniera en semana,\nrockstar los fines de semana")
        self.hv.place(relx=0.5, rely=0.5, anchor="center")
        self.P5.bind("<Button>", self.cambiarFotos)
        self.hv.bind("<Button>", self.cambiarFotos)
        
        #--- Panel p6 --------------------------------
        self.P6 = tk.Frame(self)
        self.subframe = tk.Frame(self.P6)
        self.fotos = 0
        self.fotosCamila=[]
        self.fotosCamila.append(tk.PhotoImage(file=path+"/uiMain/fotos/camila/1.png")) 
        self.fotosCamila.append(tk.PhotoImage(file=path+"/uiMain/fotos/camila/2.png"))  
        self.fotosCamila.append(tk.PhotoImage(file=path+"/uiMain/fotos/camila/3.png"))  
        self.fotosCamila.append(tk.PhotoImage(file=path+"/uiMain/fotos/camila/4.png"))
        self.fotosSebastian=[]
        self.fotosSebastian.append(tk.PhotoImage(file=path+"/uiMain/fotos/sebastian/1.png")) 
        self.fotosSebastian.append(tk.PhotoImage(file=path+"/uiMain/fotos/sebastian/2.png"))  
        self.fotosSebastian.append(tk.PhotoImage(file=path+"/uiMain/fotos/sebastian/3.png"))  
        self.fotosSebastian.append(tk.PhotoImage(file=path+"/uiMain/fotos/sebastian/4.png"))
        self.fotosAlejandro=[]
        self.fotosAlejandro.append(tk.PhotoImage(file=path+"/uiMain/fotos/alejandro/1.png")) 
        self.fotosAlejandro.append(tk.PhotoImage(file=path+"/uiMain/fotos/alejandro/2.png"))  
        self.fotosAlejandro.append(tk.PhotoImage(file=path+"/uiMain/fotos/alejandro/3.png"))  
        self.fotosAlejandro.append(tk.PhotoImage(file=path+"/uiMain/fotos/alejandro/4.png"))
        self.fotosHans=[]
        self.fotosHans.append(tk.PhotoImage(file=path+"/uiMain/fotos/hans/1.png")) 
        self.fotosHans.append(tk.PhotoImage(file=path+"/uiMain/fotos/hans/2.png"))  
        self.fotosHans.append(tk.PhotoImage(file=path+"/uiMain/fotos/hans/3.png"))  
        self.fotosHans.append(tk.PhotoImage(file=path+"/uiMain/fotos/hans/4.png"))
        self.label_f1 = tk.Label(self.subframe,image=self.fotosCamila[0])
        self.label_f2 = tk.Label(self.subframe,image=self.fotosCamila[1])
        self.label_f3 = tk.Label(self.subframe,image=self.fotosCamila[2])
        self.label_f4 = tk.Label(self.subframe,image=self.fotosCamila[3])
        self.label_f1.grid(row=0, column=0,sticky = 'w')
        self.label_f2.grid(row=1, column=0,sticky = 'w')
        self.label_f3.grid(row=0, column=1,sticky = 'w')
        self.label_f4.grid(row=1, column=1,sticky = 'w')
        self.subframe.place(relx=0.5, rely=0.5, anchor="center")
        
        
        self.P3.grid(row=0, column=0,sticky = 'nsew', padx=1, pady=1)
        self.P4.grid(row=1, column=0,sticky = 'nsew', padx=1, pady=1)
        self.P5.grid(row=0, column=1,sticky = 'nsew', padx=1, pady=1)
        self.P6.grid(row=1, column=1,sticky = 'nsew', padx=1, pady=1)
        self.columnconfigure(0, weight=1)
        self.rowconfigure(0, weight=1)
        self.columnconfigure(1, weight=1)
        self.rowconfigure(1, weight=2)
        
        
        
        self.mainloop()
    
    def cambiarFotos(self, event):
        
        if(self.fotos==0):
            self.fotos=1
            self.hv['text']="Sebastian Aguinaga\nIngenieria de sistemas e informatica"
            self.label_f1['image'] = self.fotosSebastian[0]
            self.label_f2['image'] = self.fotosSebastian[1]
            self.label_f3['image'] = self.fotosSebastian[2]
            self.label_f4['image'] = self.fotosSebastian[3]
        elif(self.fotos==1):
            self.fotos=2
            self.hv['text']="Hans Garcia\nIngenieria de sistemas e informatica"
            self.label_f1['image'] = self.fotosHans[0]
            self.label_f2['image'] = self.fotosHans[1]
            self.label_f3['image'] = self.fotosHans[2]
            self.label_f4['image'] = self.fotosHans[3]
        elif(self.fotos==2):
            self.fotos=3
            self.hv['text']="Juan Alejandro Espinosa\nIngenieria de sistemas e informatica"
            self.label_f1['image'] = self.fotosAlejandro[0]
            self.label_f2['image'] = self.fotosAlejandro[1]
            self.label_f3['image'] = self.fotosAlejandro[2]
            self.label_f4['image'] = self.fotosAlejandro[3]
        else:
            self.fotos=0
            self.hv['text']="Maria Camila Arias\nIngeniera en semana,\nrockstar los fines de semana"
            self.label_f1['image'] = self.fotosCamila[0]
            self.label_f2['image'] = self.fotosCamila[1]
            self.label_f3['image'] = self.fotosCamila[2]
            self.label_f4['image'] = self.fotosCamila[3]
        
    def salirr(self):
        self.destroy()
        
    def descripcion(self):
        self.bienvenida['text']="Este es un sistema administrativo\npara veterinarias."
        
    def cambiarIcono(self,event):
        if(self.icon==0):
            self.icon = 1
            self.prb['file'] = path+"/uiMain/fotos/sistema/2.png"
        elif(self.icon==1):
            self.icon = 2
            self.prb['file'] = path+"/uiMain/fotos/sistema/3.png"
        elif(self.icon==2):
            self.icon = 3
            self.prb['file'] = path+"/uiMain/fotos/sistema/4.png"
        else:
            self.icon = 0
            self.prb['file'] = path+"/uiMain/fotos/sistema/1.png"
    def ingresar(self):
        
        self.destroy()
        VentanaPrincipal.VentanaPrincipal()
        
        

        
if __name__ == "__main__":
    VentanaInicio()
