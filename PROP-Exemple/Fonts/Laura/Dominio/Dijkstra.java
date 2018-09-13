package Domini;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * @autor Laura i Joan
 * @dataCreación 5/11/2014
 * @dataÚltimaModificación 15/11/2014
 */

public class Dijkstra {
    
    //Constructoras
    
    /**
     * @pre cierto
     * @post crea una instrancia de la clase Dijkstra
     */
    public Dijkstra() {
    
    }

    //Metodos
    
    /**
     * G es el grafo, ‘s’ es el id del vértice origen, ’y’ es el id del vertice destino
     * @pre 's' y 'y' son dos id's vértices del grafo. Los ids empiezan por 0 y son consecutivos
     * @post Si existe camino entre ‘s’ y ‘y’, el ArrayList resultante contiene los vértices de dicho camino. En caso contrario, el ArrayList está vacío.
     */    
    public ArrayList<Integer> calcularCaminos(Grafo G, int s, int y) {
        //s es el origen, y es el destino y cami es donde queda almazenado el camino resultante de hacer dijkstra
        ArrayList<Integer> result = new ArrayList<Integer>();

        Vector<String> vertices = G.listarVertices();
        Vector<String> aristas = G.listarAristas();

        int n = vertices.size();

        //Inicializamos el vector de distancias d
        int infinit = 1000000000;
        int[] d = new int[n];
        for (int i = 0; i < d.length; i++) {
            d[i] = infinit;
        }

        //Inicializamos el vector de caminos cami
        int[] cami = new int[n];
        for (int i = 0; i < cami.length; i++) {
            cami[i] = -1;
        }
        d[s] = 0;
        cami[s] = 0;

        //Inicializamos el vector de visitados a falso
        boolean[] S = new boolean[n];
        for (int i = 0; i < S.length; i++) {
            S[i] = false;
        }

        //Inicializamos la priority queue de pairs <peso,vertice>
        PriorityQueue<ArcP> Q = new PriorityQueue<ArcP>(aristas.size(), new Comparator<ArcP>() {
            @Override
            public int compare(ArcP x, ArcP y) {
                return (x.first <= y.first ? -1 : 1);
            }
        });
        ArcP inici = new ArcP(0, s);
        Q.add(inici);

        ArrayList<AristaVDestPeso> aristasVU = new ArrayList<AristaVDestPeso>();
        int indexAristasVU;

        while (Q.size() != 0) {
            int u = Q.peek().second;
            Q.poll();
            if (!S[u]) {
                S[u] = true;

                indexAristasVU = 0;
                for (int j = 0; j < aristas.size(); j++) {
                    String aristaAux = aristas.get(j);
                    String[] atrArista = aristaAux.split(":");
                    int capacidad = Integer.parseInt(atrArista[4]);
                    int flow = Integer.parseInt(atrArista[5]);
                    if (u == Integer.parseInt(atrArista[2]) && flow < capacidad && capacidad != 0) {
                        AristaVDestPeso avdp = new AristaVDestPeso(atrArista[0], atrArista[3], atrArista[6]);
                        aristasVU.add(indexAristasVU, avdp);
                        indexAristasVU += 1;
                    }
                }

                int siz = indexAristasVU; //Numero de aristas que salen del vertice u
                for (int i = 0; i < siz; i++) {  //Per a cada aresta que surt de u, obtenir el seu pes i el vèrtex al que va
                    int v = Integer.parseInt(aristasVU.get(i).verticeDestino);
                    int c = Integer.parseInt(aristasVU.get(i).peso);
                    if (d[v] > d[u] + c) {
                        d[v] = d[u] + c;
                        cami[v] = u;
                        ArcP ap = new ArcP(d[v], v);
                        Q.add(ap);
                    }
                }
            }
        }
        if (d[y] != infinit) {
            ordena_cami(cami, result, s, y);
        }
        return result;
    }
    
    /**
     * 
     */
    private void ordena_cami(int[] cami, ArrayList<Integer> result, int s, int u) {
        if (s == u) {
            result.add(u);
        } else {
            ordena_cami(cami, result, s, cami[u]);
            result.add(u);
        }
    }
}
