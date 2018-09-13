package Domini;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
 
/*
 * @autor Andreu Feliu Pinto
 * @dataCreació 8/11/2014
 * @dataÚltimaModificació 14/11/2014
 */
public class GestioGraphResidual {
    private class GuardarAdreces {
        private int idArestaPositiva;
        private int idArestaNegativa;
 
        public GuardarAdreces(int idP, int idN){
            idArestaPositiva = idP;
            idArestaNegativa = idN;
        }
 
        public int llegirIdArestaPositiva() {
            return idArestaPositiva;
        }
 
        public int llegirIdArestaNegativa() {
            return idArestaNegativa;
        }
 
    }
    private int idAr = 0;
 
    //Constructora
 
    /**
     * Creadora de la classe
     * @pre Cert
     * @post Es crea una instància de la classe
     */
    public GestioGraphResidual(){
 
    }
 
    //Getters
    /**
     * Retorna l'últim idAresta que s'ha fet servir
     * @pre La funció gestiona s'ha cridat com a mínim una vegada i per tant idAr té un valor diferent de 0
     * @post Es retorna el valor de l'últim idAresta que s'ha creat
     */
    public int getIdAr(){
        return idAr;
    }
 
 
 
    private  int getAristaEntreV (Grafo graph ,int id1, int id2){
        Vector<String> cjtarestes = graph.listarAristas();
        int idArestaActual = -1;
        for (int j = 0; j < cjtarestes.size() && idArestaActual == -1; j++) {
            String arestaAux = cjtarestes.get(j); //string posicio j del v cjtarestes
            String[] splAresta = arestaAux.split(":"); //splAresta[0] = arista
            if ((id2 == Integer.parseInt(splAresta[3])) && (id1 == Integer.parseInt(splAresta[2]))) {
                idArestaActual = Integer.parseInt(splAresta[0]);
            }
        }
        return idArestaActual;
    }
 
 
 
 
 
    //Metodes
 
    /**
     * Donat un Graph i un camí dins d'aquest, crea les arestes inverses i modifica els fluxes per poder iterar un algorisme de maximumflow una altra vegada
     * @pre El graph és correcte i a llistavertex hi ha els ids dels nodes que formen un camí des de la sortida fins a l'arribada incloses
     * @post El camí ha estat tapat amb el fluxe màxim que hi pot passar i s'han creat les arestes en el sentit contrari pertinents
     */
    public void gestiona(Grafo graph, ArrayList<Integer> llistavertex, int idArista){
        int vertex1;
        int vertex2;
        int amodificar;
        int inversa;
        int aux;
        int minim = -1;
        int size = llistavertex.size()-1;;
        int i = 0;
 
 
        GuardarAdreces[] adreces = new GuardarAdreces[size];
        Iterator<Integer> it = llistavertex.iterator();
        vertex1 = it.next();
        while(it.hasNext()){
            vertex2 = it.next();
            amodificar = getAristaEntreV(graph, vertex1, vertex2);
            inversa = getAristaEntreV(graph, vertex2, vertex1);
            if(inversa == -1){
                graph.altaArista(idArista, "ArestaResidual " + String.valueOf(vertex2) +  "-" + String.valueOf(vertex1), vertex2,vertex1,0); //el nom de l'Aresta no es fa servir per res, es podria canviar si es necessités
                inversa = idArista;
                ++idArista;
                aux = graph.getArista(amodificar).getPeso();
                aux *= -1;
                graph.getArista(inversa).setPeso(aux);
            }
            aux = graph.getArista(amodificar).getCapacidad() - graph.getArista(amodificar).getFlow();
            if(minim == -1 || minim > aux){
                minim = aux;
            }
            adreces[i] = new GuardarAdreces(amodificar, inversa);
            ++i;
            vertex1 = vertex2;
        }
        for(i = 0; i < size; ++i){
            aux = graph.getArista(adreces[i].idArestaPositiva).getFlow() + minim;
            graph.getArista(adreces[i].idArestaPositiva).setFlow(aux);
            aux = graph.getArista(adreces[i].idArestaNegativa).getFlow() - minim;
            graph.getArista(adreces[i].idArestaNegativa).setFlow(aux);
        }
        idAr = idArista;
    }
}