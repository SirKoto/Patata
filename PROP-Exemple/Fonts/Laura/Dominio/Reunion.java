package Domini;

/**
 * @autor Laura
 * @dataCreación 27/10/2014
 * @dataÚltimaModificación 18/11/2014
 */

public class Reunion {

    private String codigo;
    private int numAgentes;
    private String puebloOrigen;
    private String puebloDestino;
    private String algoritmo;

    //Constructoras
    
    /**
     * @pre cierto
     * @post crea una Reunion con codigo = codigoReunion, numAgentes = numAgentes,
     * puebloOrigen = puebloOrigen y puebloDestino = puebloDestino
     */
    public Reunion(String codigoReunion, int numAgentes, String puebloOrigen, String puebloDestino, String codigoAlgoritmo) {
        this.codigo = codigoReunion;
        this.numAgentes = numAgentes;
        this.puebloOrigen = puebloOrigen;
        this.puebloDestino = puebloDestino;
        this.algoritmo = codigoAlgoritmo;
    }
    
    //Getters
    
    /**
     * @pre cierto
     * @post devuelve el código de la Reunión del parámetro implícito
     */
    public String getCodigo(){
        return this.codigo;
    }
    
    /**
     * @pre cierto
     * @post devuelve el numAgentes de la Reunión del parámetro implícito
     */
    public int getNumAgentes(){
        return this.numAgentes;
    }
    
    /**
     * @pre cierto
     * @post devuelve el puebloOrigen de la Reunión del parámetro implícito
     */
    public String getPuebloOrigen(){
        return this.puebloOrigen;
    }
    
    /**
     * @pre cierto
     * @post devuelve el puebloDestino de la Reunión del parámetro implícito
     */
    public String getPuebloDestino(){
        return this.puebloDestino;
    }
    
    /**
     * @pre cierto
     * @post devuelve el código del algoritmo del parámetro implícito
     */
    public String getCodigoAlgoritmo(){
        return this.algoritmo;
    }
    
    //Setters
    
    /**
     * @pre: cierto
     * @post: Se le ha asignado codigo a la Reunion del parámetro implícito.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * @pre: cierto
     * @post: Se le ha asignado n como número de agentes a la Reunion del parámetro implícito.
     */
    public void setNumAgentes(String n) {
        this.numAgentes = Integer.parseInt(n);
    }
    
    /**
     * @pre: cierto
     * @post: Se le ha asignado puebloOrigen a la Reunion del parámetro implícito.
     */
    public void setPuebloOrigen(String puebloOrigen) {
        this.puebloOrigen = puebloOrigen;
    }
    
    /**
     * @pre: cierto
     * @post: Se le ha asignado puebloDestino a la Reunion del parámetro implícito.
     */
    public void setPuebloDestino(String puebloDestino) {
        this.puebloDestino = puebloDestino;
    }
    
    /**
     * @pre: cierto
     * @post: Se le ha asignado algoritmo a la Reunion del parámetro implícito.
     */
    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }
}
