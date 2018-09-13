package Domini;

/**
 * @autor Flor
 * @dataCreación 28/10/2014
 * @dataÚltimaModificación 10/11/2014
 */

public class Tramo {

    private String id;
    private String nombre;
    private int tiempo;
    private int kilometros;
    private int costeBillete;
    
    //Constructoras
    
     /**
     * @pre cierto
     * @post crea un Tramo con identificador = id, nombre = nombre, tiempo = tiempo, 
     * kilometros = kilometros y coste de billete = costeBillete
     */
    public Tramo(String id, String nombre, int tiempo, int kilometros, int costeBillete) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.kilometros = kilometros;
        this.costeBillete = costeBillete;
    }
    
    //Getters
    
    /**
     * @pre cierto
     * @post devuelve el identificador del Tramo del parámetro implícito
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * @pre cierto
     * @post devuelve el nombre del Tramo del parámetro implícito
     */
    public String getNombre() {
        return this.nombre;
    }
    
    /**
     * @pre cierto
     * @post devuelve el tiempo del Tramo del parámetro implícito
     */
    public int getTiempo(){
        return this.tiempo;
    }
    
    /**
     * @pre cierto
     * @post devuelve los kilometros del Tramo del parámetro implícito
     */
    public int getKilometros(){
        return this.kilometros;
    }
    
    /**
     * @pre cierto
     * @post devuelve el coste del billete del Tramo del parámetro implícito
     */
    public int getCosteBillete() {
        return this.costeBillete;
    }
    
    //Setters
    
    /**
     * @pre cierto
     * @post introduce/modifica el identificador del Tramo del parámetro implícito
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @pre cierto
     * @post introduce/modifica el tiempo del Tramo del parámetro implícito
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    /**
     * @pre cierto
     * @post introduce/modifica los kilometros del Tramo del parámetro implícito
     */
    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }
    
    /**
     * @pre cierto
     * @post introduce/modifica el coste del billete del Tramo del parámetro implícito
     */
    public void setCosteBillete(int costeBillete) {
        this.costeBillete = costeBillete;
    }
    
}
