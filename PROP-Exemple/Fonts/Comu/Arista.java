package Domini;

/**
 * @author daniel
 */
public class Arista {
    
    private String nombre;
    private int origen; //*** Agregacion
    private int destino; //*** Agregacion
    private int flow;
    private int capacidad;
    private int peso;
    
    // Constructoras
    
    public Arista() {
        peso = -1;
    }
    
    public Arista(String nombre, int origen, int destino, int capacidad) {
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.capacidad = capacidad;
        peso = -1;
    }
    
    // Getters
    
    /*
     * @pre cierto
     * @post devuelve el nombre de la arista
     */
    public String getNombre() {
        return nombre;
    }
    
    /*
     * @pre cierto
     * @post devuelve el identificador del vertice origen
     */
    public int getVerticeOrigen() {
        return origen;
    }
    
    /*
     * @pre cierto
     * @post devuelve el identificador del vertice destino
     */
    public int getVerticeDestino() {
        return destino;
    }
    
    /*
     * @pre cierto
     * @post devuelve el flujo de la arista
     */
    public int getFlow() {
        return flow;
    }
    
    /*
     * @pre cierto
     * @post devuelve la capacidad de la arista
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /*
     * @pre cierto
     * @post devuelve el peso de la arista
     */
    public int getPeso() {
        return peso;
    }
    
    // Setters
    
    /*
     * @pre cierto
     * @post modifica el nombre de la arista
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /*
     * @pre cierto
     * @post modifica el identificador del vertice origen de la arista
     */
    public void setVerticeOrigen(int origen) {
        this.origen = origen;
    }
    
    /*
     * @pre cierto
     * @post modifica el identificador del vertice destino de la arista
     */
    public void setVerticeDestino(int destino) {
        this.destino = destino;
    }
    
    /*
     * @pre cierto
     * @post modifica el flujo de la arista
     */
    public void setFlow(int flow) {
        this.flow = flow;
    }
    
    /*
     * @pre cierto
     * @post modifica la capacidad de la arista
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    /*
     * @pre cierto
     * @post modifica el peso de la arista
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
}
