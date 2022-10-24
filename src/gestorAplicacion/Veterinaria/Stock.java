package gestorAplicacion.Veterinaria;

import java.util.HashMap;

public class Stock {
	public Medicamento Onsior = new Medicamento(1000001, "Onsior", "Tabletas", 50, 3500);
	public Medicamento Amoxicilina = new Medicamento(1000002, "Amoxicilina", "Tabletas", 50, 2800);
	public Medicamento Profender = new Medicamento(1000003, "Profender", "Pipeta", 40, 2100);
	public String medicamentosAprobados[] = {"Robenacoxib", "Amoxicilina", "Praziquantel y Emodepside"};
	public HashMap<String, Medicamento> medicinaComercial = new HashMap<String, Medicamento>();
	
	public HashMap<String, Medicamento> prescripcionAComercial(){
		medicinaComercial.put(medicamentosAprobados[0], Onsior);
		medicinaComercial.put(medicamentosAprobados[1], Amoxicilina);
		medicinaComercial.put(medicamentosAprobados[2], Profender);
		
		return medicinaComercial;
	}
	
}
