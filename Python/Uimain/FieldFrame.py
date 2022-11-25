import tkinter as tk
import tkcalendar as tkc

class FieldFrame(tk.Frame):
    
    def __init__(self, tituloCriterios, criterios, tituloValores, valores):
        super().__init__()
        self.titulo1 = tk.Label(self,text=tituloCriterios)
        self.titulo2 = tk.Label(self,text=tituloValores)
        self.titulo1.grid(row=0,column=0, padx=5, pady=5)
        self.titulo2.grid(row=0,column=1, padx=5, pady=5)
        self.labels = []
        self.entradas = []
        for i in range(len(criterios)):
            if(valores==None):
                self.labels.append(tk.Label(self,text=criterios[i]))
                self.labels[i].grid(row=i+1,column=0, padx=5, pady=5)
                self.entradas.append(tk.Entry(self))
                self.entradas[i].grid(row=i+1,column=1, padx=5, pady=5)
            else:
                self.labels.append(tk.Label(self,text=criterios[i]))
                self.labels[i].grid(row=i+1,column=0, padx=5, pady=5)
                self.entradas.append(tk.Entry(self))
                self.entradas[i].insert(0,valores[i])
                self.entradas[i].grid(row=i+1,column=1, padx=5, pady=5)

#Esto es para probar si la clase quedo bien

root = tk.Tk()
root.geometry("500x400")
FieldFrame("TITULOS",["Nombre","Telefono","Nacionalidad"],"ENTRADAS",["Pedro","300","Colombiano"]).pack()
root.mainloop()