package gestorAplicacion.Veterinaria;

import java.util.ArrayList;

public class Inventario {
    public static ArrayList<Medicamento> Medicamentos= new ArrayList<Medicamento>();
    	
	public static ArrayList<Medicamento> getMedicamentos() {
        return Medicamentos;
    }

    public static void setMedicamentos(Medicamento nuevoMedicamento) {
        Medicamentos.add(nuevoMedicamento);
    }
    
}
