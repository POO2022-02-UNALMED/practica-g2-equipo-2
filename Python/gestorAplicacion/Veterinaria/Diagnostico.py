from Veterinaria import Inventario

class Diagnostico:
	veterinario = ""
	duenno = ""
	mascota = ""
	fecha = ""
	justificacion = ""
	diagnostico = ""
	medicamentos = []
	sintomasLista = ["dolor", "inflama", "parasito", "parásito"]
	sintomasDeterminados = ["","","",""]
	sintomasFinales = ""
	numeroSintomas = 0
	medicamentosAprobados = ["Robenacoxib", "Amoxicilina", "Pyrantel pamoate"]
 
	SintomasAMedicinas = {}
	medicinasDeterminadas = {}
	numeroMedicinasRecomendadas = 0
	medicinaAComercial = {}
		
	def __init__(self, date, vete, due, mas, Justificacion):
		self._veterinario = vete
		self._duenno = due
		self._mascota = mas
		self._fecha = date
		self._justificacion = Justificacion
	
	
	def getFecha(self):
		return self.fecha
	

	def setFecha(self, fech): 
		self.fecha = fech
	

	def getMedicamentos(self): 
		return self.medicamentos
	

	def setMedicamentos(self, med):
		self.medicamentos = med
	
		
	def getJustificacion(self):
		return self.justificacion
	
	
	def setJustificacion(self, just):
		self.justificacion = just
	

	#Sintomas
	
	def Diagnos():
		
		Diagnostico.SintomasAMedicinas[Diagnostico.sintomasLista[0]] = Diagnostico.medicamentosAprobados[0]
		Diagnostico.SintomasAMedicinas[Diagnostico.sintomasLista[1]] = Diagnostico.medicamentosAprobados[1]
		Diagnostico.SintomasAMedicinas[Diagnostico.sintomasLista[2]] = Diagnostico.medicamentosAprobados[2]
		Diagnostico.SintomasAMedicinas[Diagnostico.sintomasLista[3]] = Diagnostico.medicamentosAprobados[2]		
		
		pain = Diagnostico.getJustificacion().contains(Diagnostico.sintomasLista[0])
		inflamation = Diagnostico.getJustificacion().contains(Diagnostico.sintomasLista[1])
		parasite = Diagnostico.getJustificacion().contains(Diagnostico.sintomasLista[2])
		paarasite = Diagnostico.getJustificacion().contains(Diagnostico.sintomasLista[3])
		
		Sintomas = {}
		
		Sintomas[Diagnostico.sintomasLista[0]] = pain
		Sintomas[Diagnostico.sintomasLista[1]] = inflamation
		Sintomas[Diagnostico.sintomasLista[2]] = parasite
		Sintomas[Diagnostico.sintomasLista[3]] = paarasite
		
		for key in Sintomas:
			if (Sintomas[key]):
				Diagnostico.sintomasDeterminados[Diagnostico.numeroSintomas] = key
				numeroSintomas += 1
			
				
	# Recomendacion de medicamentos

	listaMedicamentosRecomendados = "\n"
	recomendacion = " los siguientes medicamentos según la dósis especificada: \n"

	def recomendarMedicamentos(): 
		
		Diagnostico.medicinaAComercial[Diagnostico.medicamentosAprobados[0]] = Inventario.Medicamentos.get(0).getNombre()
		Diagnostico.medicinaAComercial[Diagnostico.medicamentosAprobados[1]] = Inventario.Medicamentos.get(1).getNombre()
		Diagnostico.medicinaAComercial[Diagnostico.medicamentosAprobados[2]] = Inventario.Medicamentos.get(2).getNombre()
		
		for e in Diagnostico.sintomasDeterminados:
			if (e == "dolor"):
					Diagnostico.medicinasDeterminadas[Diagnostico.SintomasAMedicinas.get(e)] = "         Presentación: 6 mg  Tableta con o sin Recubrimiento Gatos o 20 mg Tableta con o sin Recubrimiento Perros." + "\n          Suministrar: Vía Oral 1 tableta cada 1 día(s) por 3 día(s)."
					Diagnostico.listaMedicamentosRecomendados = Diagnostico.listaMedicamentosRecomendados + "\n        - " + Diagnostico.medicinaAComercial.get(Diagnostico.SintomasAMedicinas.get(e))
					Diagnostico.sintomasFinales.append("\n     - Dolor")
					numeroMedicinasRecomendadas += 1
			
			elif (e == "inflama"):
					Diagnostico.medicinasDeterminadas[Diagnostico.SintomasAMedicinas.get(e)] = "         Presentación: 100 mg  Tabletas con o sin Recubrimiento  Perros y Gatos." + "\n          Suministrar: Vía Oral 11 a 22 mg/kg cada 12 a 24 hora(s) de 10 a 14 día(s)."
					Diagnostico.listaMedicamentosRecomendados = Diagnostico.listaMedicamentosRecomendados + "\n        - " + Diagnostico.medicinaAComercial.get(Diagnostico.SintomasAMedicinas.get(e))
					Diagnostico.sintomasFinales.append ("\n     - Inflamación")
					numeroMedicinasRecomendadas += 1
				
			elif (e == "parasito" or e == "parásito"):
					Diagnostico.medicinasDeterminadas[Diagnostico.SintomasAMedicinas.get(e)] = "         Presentación: 100 mL  Jarábe  Perros y Gatos." + "\n          Suministrar: Vía Oral 5 mL / 10 lb según edad:\n           A. Cada 2 semana(s) hasta las 12 semanas de edad.\n           B. Cada 1 mes(es) hasta los 6 mes(es) de edad.\n           C. Después de los 6 mes(es) de edad en adelante: Cada 3 mes(es)."
					Diagnostico.listaMedicamentosRecomendados = Diagnostico.listaMedicamentosRecomendados + "\n        - " + Diagnostico.medicinaAComercial.get(Diagnostico.SintomasAMedicinas.get(e))
					Diagnostico.sintomasFinales.append ("\n     - Parásitos")
					numeroMedicinasRecomendadas += 1
						
		
		return Diagnostico.medicinasDeterminadas	
    
	def generarFormulaMedica(self):	
		
		for key in Diagnostico.medicinasDeterminadas:
				recomendacion = recomendacion + "\n        - " + key + "\n " + Diagnostico.medicinasDeterminadas[key] + "\n"
		
		
		Diagnostico.diagnostico = "\nFecha: " + self.fecha + "\n" + "\n\nDatos del Prestador" + "\n--> Médico Veterinario: " + self.veterinario.getNombre() + "\n" + "\nDatos del Paciente" + "\n--> Cliente: " + self.duenno.getNombre() + "\n--> Nombre de la Mascota: " + self.mascota.getNombreMascota() + "\n--> Edad: " + self.mascota.getEdad()+ " año(s)" +"\n--> Peso: "+ self.mascota.getPeso() + " kg" + "\n--> Especie: " + self.mascota.getEspecie()+ "\n--> Raza: " + self.mascota.getRaza() + "\n--> Sexo: " + self.mascota.getSexo() + "\n" + "\n" + "\nFÓRMULA MÉDICA VETERINARIA" +"\n\nDiagnóstico" +"\n--> Justificación: " + self.justificacion + "\n--> Síntomas:" + Diagnostico.sintomasFinales + "\n" + "\n" + "Medicamentos y Prescripción \n--> Recomendación: Suministrar a " + self.mascota.getNombreMascota() + Diagnostico.recomendacion + "\n\nFORMULA MÉDICA VÁLIDA POR 30 DÍAS A PARTIR DE LA FECHA DE EXPEDICIÓN" +"\n\nPor favor, diríjase a su clínica veterinaria más cercana para adquirir los medicamentos en el tiempo establecido, de lo contrario podría requerir una nueva valoración por médicina veterinaria." + "\n" + "\n" + "Medicinas Comerciales Recomendadas" + Diagnostico.listaMedicamentosRecomendados + "\n" + "\n" + "\n"
		
		return Diagnostico.diagnostico

def __repr__(self):
    return Diagnostico.diagnostico