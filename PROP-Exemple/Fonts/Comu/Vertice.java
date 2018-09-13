package Domini;

import java.util.ArrayList;

/**
 * @author daniel
 */
public class Vertice {
    
    private String nombre;
    private ArrayList<Integer> aristasEntrada; //*** (-->O) Agregacion
    private ArrayList<Integer> aristasSalida; //*** (<--O) Agregacion
    
    
    // Constructor
    /**
     * @pre cierto
     * @post crea una instancia de Vertice con aristasEntrada y aristasSalida vacia
     */
    public Vertice() {
        aristasEntrada = new ArrayList<Integer>();
        aristasSalida = new ArrayList<Integer>();
    }
    /**
     * @pre cierto
     * @post crea una instancia de Vertice con aristasEntrada y aristasSalida vacios 
     *      y nombre = n
     */
    public Vertice(String n) {
        aristasSalida = new ArrayList<Integer>();
        aristasEntrada = new ArrayList<Integer>();
        nombre = n;
    }
    
    // Getters
    
    /**
     * @pre cierto
     * @post devuelve el nombre del vertice
     */
    public String getNombre() {
        return nombre;
    }
    
    // Setters
    
    /**
     * @pre cierto
     * @post introduce/modifica el nombre del vertice
     */
    public void setNombre(String nombre) {
    	if (!"inicial".equals(nombre) && !"final".equals(nombre))
            this.nombre = nombre;
    }
    
    // metodos
    
    // modificadoras
    
    /**
     * @pre id no existe. id > 0
     * @post anade el identificador de entrada de la arista del vertice
     */
    public void anadirAristaEntrada(int id) {
    	if (!"inicial".equals(nombre)) {
            aristasEntrada.add(id);
        } else {
        }
    }
    
    /**
     * @pre id no existe. id > 0
     * @post anade el identificador de salida de la arista del vertice
     */
    public void anadirAristaSalida(int id) {
    	if (!"final".equals(nombre))
            aristasSalida.add(id);
    }
    
    /**
     * @pre id existe. id > 0
     * @post elimina el identificador de entrada de la arista del vertice
     */
    public void eliminarAristaEntrada(int id) {
        int i = aristasEntrada.indexOf(id);
        if (i != -1)
            aristasEntrada.remove(i);
    }
    
    /**
     * @pre id existe. id > 0
     * @post elimina el identificador de salida de la arista del vertice
     */
    public void eliminarAristaSalida(int id) {
        int i = aristasSalida.indexOf(id);
        if (i != -1)
            aristasSalida.remove(i);
    }
    
    // consultoras
    
    /**
     * @pre cierto
     * @post devuelve si existe el identificador de la arista de entrada id
     */
    public boolean existeAristaEntrada(int id) {
        return aristasEntrada.contains(id);
    }
    
    /**
     * @pre cierto
     * @post devuelve si existe el identificador de la arista de salida id
     */
    public boolean existeAristaSalida(int id) {
        return aristasSalida.contains(id);
    }
    
    /**
     * @pre cierto
     * @post devuelve el numero de aristas de entrada del vertice
     */
    public int numeroAristasEntrada() {
        return aristasEntrada.size();
    }
    
    /**
     * @pre cierto
     * @post devuelve el numero de aristas de salida del vertice
     */
    public int numeroAristasSalida() {
        return aristasSalida.size();
    }
    
    /**
     * @pre i >= 0 && aristasEntrada.size();
     * @post devuelve la iesima arista de entrada del vertice
     */
    public int iesimaAristaEntrada(int i) {
        return aristasEntrada.get(i);
    }
    
    /**
     * @pre i >= 0 && i < aristasSalida.size()
     * @post devuelve la iesima arista de salida del vertice
     */
    public int iesimaAristaSalida(int i) {
        return aristasSalida.get(i);
    }
}
