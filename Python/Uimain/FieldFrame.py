import tkinter as tk
import tkcalendar as tkc
from datetime import datetime
from Uimain.Placeholder_Entry import Placeholder_Entry
#from _datetime import date

class FieldFrame(tk.Frame):
    
    def __init__(self,padre, tituloCriterios, criterios, valores, tipos):
        super().__init__(padre)
        self.criterios = criterios
        self.valores=valores
        self.tipos=tipos
        self.titulo1 = tk.Label(self,text=tituloCriterios,font=("Verdana", 20))
        self.titulo1.grid(row=0,column=0,columnspan=2, padx=5, pady=5)
        self.labels = []
        self.entradas = []
        for i in range(len(criterios)):
            if(valores==None):
                self.labels.append(tk.Label(self,text=criterios[i]))
                self.labels[i].grid(row=i+1,column=0, padx=5, pady=5)
                if(tipos[i]=="calendar"):
                    now = datetime.now()
                    self.entradas.append(tkc.DateEntry(self,selectmode='day',year=now.year,month=now.month,day=now.day,width=20))
                elif(tipos[i]=="combo"):
                    self.entradas.append(tk.ttk.Combobox(self,state="readonly",width=20))
                else:
                    self.entradas.append(tk.Entry(self,width=23))
                self.entradas[i].grid(row=i+1,column=1, padx=5, pady=5)
            else:
                self.labels.append(tk.Label(self,text=criterios[i]))
                self.labels[i].grid(row=i+1,column=0, padx=5, pady=5)
                if(tipos[i]=="calendar"):
                    now = datetime.now()
                    self.entradas.append(tkc.DateEntry(self,selectmode='day',year=now.year,month=now.month,day=now.day,width=20))
                elif(tipos[i]=="combo"):
                    self.entradas.append(tk.ttk.Combobox(self,state="readonly",width=20))
                else:
                    self.entradas.append(Placeholder_Entry(self, placeholder=valores[i]))
                self.entradas[i].grid(row=i+1,column=1, padx=5, pady=5)
        self.aceptar = tk.Button(self, text="Aceptar")
        self.aceptar.grid(row=len(criterios)+2,column=0, padx=5, pady=10)
        self.borrar = tk.Button(self, text="Borrar",command=self.borrarCampos)
        self.borrar.grid(row=len(criterios)+2,column=1, padx=5, pady=10)

    def borrarCampos(self):
        for i in range(len(self.tipos)):
            if(self.tipos[i]=="calendar"):
                now = datetime.now()
                self.entradas[i].set_date(now)
            if(self.tipos[i]=="combo"):
                self.entradas[i].current(0)
            if(self.tipos[i]=="entry"):
                self.entradas[i].delete(0,last=len(self.entradas[i].get()))
                
    
#Esto es para probar si la clase quedo bien

#root = tk.Tk()
#root.geometry("500x400")
#formulario = FieldFrame("TITULOS",["Nombre","Fecha","Genero"],"ENTRADAS",["Pedro","300","Colombiano"],["entry","calendar","combo"])
#formulario.entradas[2]['values']=["Macho", "Hembra"]
#formulario.entradas[2].set("Macho")
#formulario.pack()
#root.mainloop()