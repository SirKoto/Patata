package Domini;

import java.util.*;
/**
 * Created by xavivaio on 09/11/2014.
 */
/*
 * @autors David Guerrero i Dani Gil
 * @dataCreació 8/11/2014
 * @dataÚltimaModificació 9/11/2014
 */
public class EdmondsKarp {
    private int idAr = 0;

    //Constructora
    /*
     * Calcula el maximum flow del Graf G mitjançant l'algorisme BFS
     * @pre G no és null amb node inici 1 i node fi 2
     * @post G serà el graf resultant amb tots els camins possibles amb el màxim fluxe possible
     */
    public EdmondsKarp(){}
    //Getters
    /*
     * Retorna l'últim idAresta que s'ha fet servir
     * @pre La S'ha cridat a graphResidual com a mínim una vegada i per tant idAr té un valor diferent de 0
     * @post Es retorna el valor de l'últim idAresta que s'ha creat
     */
    public int getIdAr(){
        return idAr;
    }
    //Metodes
    /*
     * Busca el primer camí més curt del Graf G des del node sortida fins al node arribada i el retorna en un ArrayList
     * @pre G no és nul, s és el node inicial del graf i t és el node final del graf
     * @post Si hi ha camí entre inici i final, es tornarà un ArrayList amb els identificadors dels nodes per on passa el camí. En cas contrari tornarà l’ArrayList amb un únic element que serà -1
     */
    public ArrayList<Integer> camiEdmonds(Grafo graf, int s, int t) {
        Queue<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(s);
        Set<Integer> visitats = new HashSet<Integer>();
        boolean trobat = false;
        q.add(l);
        while (!trobat && !q.isEmpty()) {
            ArrayList<Integer> tractar = q.poll(); //agafem el primer element i l'eliminem de la llista
            int posactual = tractar.get(tractar.size()-1); //posicio a tractar de la llista(que es la ultima)
            if (!visitats.contains(posactual)) {
                Vector<Integer> adjacents = graf.verticesDestinoAdyacentes(posactual); //vector dels id's adjacents al vertex posactual
                for (int i = 0; i < adjacents.size() && !trobat; ++i) {
                    int idVAct = adjacents.get(i); //agafem l'id vertex actual
                    Vector<String> cjtarestes = graf.listarAristas();
                    Integer idArestaActual = -1;
                    for (int j = 0; j < cjtarestes.size() && idArestaActual == -1; j++) {
                        String arestaAux = cjtarestes.get(j); //string posicio j del v cjtarestes
                        String[] splAresta = arestaAux.split(":"); //splAresta[0] = arista
                        if ((idVAct == Integer.parseInt(splAresta[3])) && (posactual == Integer.parseInt(splAresta[2]))) {
                            idArestaActual = Integer.parseInt(splAresta[0]);
                        }
                    }
                    int fAct = graf.flowArista(idArestaActual); //agafem el fluxe de l'aresta
                    int cAct = graf.capacidadArista(idArestaActual); //agafem la capacitat de l'aresta

                    if (cAct - fAct > 0) {
                        tractar.add(idVAct); //insertem el vertex adjacent al cami
                        ArrayList<Integer> tractarAux = (ArrayList<Integer>) tractar.clone();
                        q.add(tractarAux); //insertem el cami amb el nou vertex adjacent a la cua
                        if (idVAct == t) {
                            trobat = true;
                            l = tractarAux;
                        }
                        tractar.remove(tractar.size() - 1); //fem un remove del vertex per seguir tenint el cami actual
                    }
                }
                visitats.add(posactual);
            }
           // else q.remove();
        }
        if (!trobat) {
            l.clear();
        }
        return l;
    }
}