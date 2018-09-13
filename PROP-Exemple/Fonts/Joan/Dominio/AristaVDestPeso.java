package Domini;

/**
 * @autor Joan
 * @dataCreación 6/11/1014
 * @dataÚltimaModificación 7/11/2014
 */

public class AristaVDestPeso {
    public String arista;
    public String verticeDestino;
    public String peso;
    
    //Constructoras
    
    /**
     * @pre cierto
     * @post crea una instrancia de la clase ArsitaVDestPeso con arista = a,
     * verticeDestino = d y peso = p
     */
    public AristaVDestPeso(String a, String d, String p){
        this.arista = a;
        this.verticeDestino = d;
        this.peso = p;
    }
}
