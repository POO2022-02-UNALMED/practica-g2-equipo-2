package gestorAplicacion.Veterinaria;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public class TurnoContab {
    class Cliente{
      //  String nombre;
    }
    public static int Id;
    double esperado; // Este es el capital esperado al final del turno, teniendo en cuenta el capital inicial,los pagos y  las ventas durante el turno.
    double retiro;   //Capital que se desee retirar de la veterinaria.
    double caja;     //Este es el capital que quedará en caja para el siguiente turno, siendo el atributo "inicial" del siguiente. Su principal
                  //función es permitir retirar capital de la veterinaria
    Personal encargado; //personal encargado del turno contable    
    static ArrayList<Factura> facturas= new ArrayList<Factura>();    //Lista de facturas por turno
    int idTurno;   
    public int getIdTurno() {
        return idTurno;
    }
    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }
    double inicial;   //Este es el capital inicial que se encuentra en caja antes de empezar el turno, posiblemente de algún turno anterior
    public double getInicial() {
        return inicial;
    }
    public void setInicial(double inicial) {
        this.inicial = inicial;
    }

    public double getEsperado() {
        return esperado;
    }
    public void setEsperado(double esperado) {
        this.esperado = esperado;
    }

    public double getRetiro() {
        return retiro;
    }
    public void setRetiro(double retiro) {
        this.retiro = retiro;
    }

     public double getCaja() {
        return caja;
    }
    public void setCaja(double caja) {
        this.caja = caja;
    }
    
    public double Totaldiario(ArrayList<Factura> fac){  //calcula el total diario de dinero que debe haber en la caja
        double contador=getInicial();                   //Obtiene el capital incial en la caja
        for (int i=0;i<fac.size(); i++){                //itera todas las facturas del turno
            contador += (fac.get(i)).getTotalFactura(); //Hace la sumatoria
    }
        return contador;                                //Devuelve la sumatoria
    } 
    public HashMap<String, Integer> TotalMedico(ArrayList<Factura> fac){      // Calcular cuanto es el total que se le debe pagar a los médicos
        double auxiliar=0;                                                    //solo sirve para tener una variable double con valor cero
        ArrayList<String> medicost= new ArrayList<String>();                    //Array de todos los medicos que estuvieron en el turno
        HashMap<String, Integer> medicos = new HashMap<String, Integer>();    //HashMap para calcular lo que se le debe pagar al medico
        for (int i=0;i<fac.size(); i++){
            double cont=0;                                                                           // Es una variable para las operaciones
            if (medicos.containsKey(fac.get(i).getMedico().getNombre())){                           //Verifica que no esté el medico en en hashmap para agregarlo
                                                                                                    // en caso que se necesite
                cont=medicos.get(fac.get(i).getMedico().getNombre())+fac.get(i).getValorMedico();   //Hace la suma teniendo en cuenta el valor que se llevaba
                medicos.put(fac.get(i).getMedico().getNombre(), cont);                              //Sobreescribe el viejo valor de lo que se debía pagar y lo cambia
                                                                                                    //por el recién calculado
            }
            else{
                medicos.put(((fac.get(i)).getMedico()).getNombre(), auxiliar);                      //Añade el medico que no estab al HashMap
                medicost.add(fac.get(i).getMedico().getNombre());                                   //Añade a los medicos totales del día
            }
            }
            return medicos;
        } 
    }



    


