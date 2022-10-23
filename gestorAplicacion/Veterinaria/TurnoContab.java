package gestorAplicacion.Veterinaria;
import java.util.ArrayList;
import java.util.HashMap;


public class TurnoContab {
    public static double totalmedicosturno;
    
    public static double inicial;   //Este es el capital inicial que se encuentra en caja antes de empezar el turno, posiblemente de algún turno anterior
    public static double esperado; // Este es el capital esperado al final del turno, teniendo en cuenta el capital inicial,los pagos y  las ventas durante el turno.
    public static double retiro;   //Capital que se desee retirar de la veterinaria.
    public static double caja;     //Este es el capital que quedará en caja para el siguiente turno, siendo el atributo "inicial" del siguiente. Su principal
                                    //función es permitir retirar capital de la veterinaria
    public static ArrayList<Factura> facturas= new ArrayList<Factura>();    //Lista de facturas por turno 


    public static double getTotalmedicosturno() {
        return totalmedicosturno;
    }
    public static void setTotalmedicosturno(double totalmedicosturno) {
        TurnoContab.totalmedicosturno = totalmedicosturno;
    }
    public static double getInicial() {
        return TurnoContab.inicial;
    }
    public static void setInicial(double inicial) {
       TurnoContab.inicial = inicial;
    }

    public static double getEsperado() {
        return TurnoContab.esperado;
    }
    public static void setEsperado(double esperado) {
        TurnoContab.esperado = esperado;
    }

    public static double getRetiro() {
        return TurnoContab.retiro;
    }
    public static void setRetiro(double retiro) {
        TurnoContab.retiro = retiro;
    }

     public static double getCaja() {
        return TurnoContab.caja;
    }
    public static void setCaja(double caja) {
        TurnoContab.caja = caja;
    }
    

    public static double calcularCaja(double retiro){                                        //Este metodo calcula el dinero que debe haber en caja luego de pagar a
                                                                                            // los medicos y en caso de haber un retiro también debe tenerlo en cuenta
        double termina= TurnoContab.TotalDiario()-TurnoContab.getTotalmedicosturno()-retiro;        
        return termina;
        
    }


    public static double TotalDiario(){  //calcula el total diario de dinero que debe haber en la caja
        double contador=TurnoContab.getInicial();                   //Obtiene el capital incial en la caja
        for (int i=0;i<TurnoContab.facturas.size(); i++){                //itera todas las facturas del turno
            contador += (TurnoContab.facturas.get(i)).getTotalFactura(); //Hace la sumatoria
    }
        return contador;                 //Devuelve la sumatoria
    }
    
    
    public static HashMap<String, Integer> TotalMedico(){      // Calcular cuanto es el total que se le debe pagar a los medicos
        double totalmeds=0;                                                    //Se usara para calcular el total que se debe pagar a todos los medicos
        double auxiliar=0;                                                    //solo sirve para tener una variable double con valor cero
        ArrayList<String> medicost= new ArrayList<String>();                    //Array de todos los medicos que estuvieron en el turno
        HashMap<String, Integer> medicos = new HashMap<String, Integer>();    //HashMap para calcular lo que se le debe pagar al medico
        for (int i=0;i<TurnoContab.facturas.size(); i++){                                        //recorre array de todas las facturas
            double cont=0;                                                                           // Es una variable para las operaciones
            if (medicos.containsKey(TurnoContab.facturas.get(i).getMedico().getNombre())){                           //Verifica que no esté el medico en en hashmap para agregarlo
                                                                                                    // en caso que se necesite
                cont=medicos.get(TurnoContab.facturas.get(i).getMedico().getNombre())+TurnoContab.facturas.get(i).getValorMedico();   //Hace la suma teniendo en cuenta el valor que se llevaba
                medicos.put(TurnoContab.facturas.get(i).getMedico().getNombre(), cont);                              //Sobreescribe el viejo valor de lo que se debía pagar y lo cambia
                                                                                                    //por el recién calculado
                totalmeds+=cont;                                                                 //Acumulador para saber el costo de todos los medicos juntos en total
            }
            else{
                medicos.put(((TurnoContab.facturas.get(i)).getMedico()).getNombre(), TurnoContab.facturas.get(i).getValorMedico());                      //Añade el medico que no estab al HashMap
                medicost.add(TurnoContab.facturas.get(i).getMedico().getNombre());                                   //Añade a los medicos totales del dia
                totalmeds+=TurnoContab.facturas.get(i).getValorMedico();                                             //Acumulador para saber el costo de todos los medicos juntos en total
            }
            }
            setTotalmedicosturno(totalmeds);
            return medicos;
        } 
    }



