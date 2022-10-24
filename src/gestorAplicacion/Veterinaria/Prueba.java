package gestorAplicacion.Veterinaria;

import gestorAplicacion.Clientes.*;

public class Prueba {
    public static void main(String[] args){
        Medico julian = new Medico("Julian", "1001", "3142567890", tipoMedico.General);
        Cliente felipe = new Cliente("Felipe", "1002", "3114956784");
        Mascota toby = new Mascota("Toby", "Retriever", "Golden", "M", 2, 34, felipe);
        
        Diagnostico diag1 = new Diagnostico("23/10/2022", julian, felipe, toby, "Se realiza consulta de medicina veterinaria por paciente el cual su dueño expresa que tiene parasitos.");
        diag1.Diagnos();
        diag1.recomendarMedicamentos();
        diag1.generarFormulaMedica();
        System.out.println(diag1);
    }
}