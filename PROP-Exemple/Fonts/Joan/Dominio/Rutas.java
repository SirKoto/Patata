package Domini;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @autor Flor i Joan
 * @dataCreación 12/11/2014
 * @dataÚltimaModificación 14/11/2014
 */

public class Rutas {

    private int numAgentes;
    private int siguienteAsignacion = 0;
    private HashMap<Integer, ArrayList<Integer>> ruta;

    
    //Constructoras

    /**
     * @pre cierto
     * @post crea una ruta con numAgentes = numAgentes
     */
    public Rutas(int numAgentes) {
        this.numAgentes = numAgentes;
        this.ruta = new HashMap<Integer, ArrayList<Integer>>();
    }

    
    //Getters
    
    /**
     * @pre: cierto
     * @post: se obtiene el número de rutas
     */
    public int getNumRutas(){
        return ruta.size();
    }
    
    /**
     * @pre: cierto
     * @post: se obtiene el número de pueblos por el que pasa un agente
     */
    public int getNumPueblos(int idAgente){
        return this.ruta.get(idAgente).size();
    }
    
    /**
     * @pre: cierto
     * @post: se obtienen los id de los pueblos por el que pasa un agente
     */
    public int[] getPueblos(int idAgente){
        ArrayList<Integer> listPueblos = this.ruta.get(idAgente);
        int tamArray = listPueblos.size();
        int[] pueblos = new int[tamArray];
        for (int i = 0; i < tamArray; i++){
            pueblos[i] = listPueblos.get(i);
        }
        return pueblos;
    }
    
    //Setters
    
    //Métodos
    
    /**
     * @pre: cierto
     * @post: se ha añadido una ruta al parámetro implícito.
     */
    public void anadirRuta(ArrayList<Integer> lista){
        this.ruta.put(siguienteAsignacion, lista);
        this.siguienteAsignacion++;
    }
    
    /**
     * @pre: cierto
     * @post: Se han eliminado todas las rutas del parámetro implícito.
     */
    public void resetRutas() {
        this.ruta.clear();
    }
}
