package Domini;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Stack;

/**
 *
 * @author Francesc Fecha de creación: 10/11/2014 Fecha de última modificación:
 * 26/11/2014
 */
public class FordFulkerson {

    class pair {//clase auxiliar para incluir en el conjunto de vertices un boolean de visitado
        int id;
        boolean visto;
    }

    //Constructor
    /**
     * @pre cierto
     * @post FordFulkerson creado
     */
    public FordFulkerson() {
		
    }

    //Methods
    /**
     * @pre G es correcto y s, t y el resto de identificadores de vertices
     * pertenecen a Vertice de G
     * @post Si existe un camino con flujo > 0 de s a t en G devuelve la secuencia de vertices de éste. En caso contrario la secuencia esta vacia
     */
    public ArrayList<Integer> buscarCamino (Grafo G, int s, int t) {
		ArrayList<Integer> camino = new ArrayList<>();
		Vector<String> V = new Vector<>();
        V = G.listarVertices();
        ArrayList<pair> vertices = new ArrayList<>();
        for (int i = 0; i < V.size(); ++i) {//obtener id vertices y vistos
            String[] parts = V.get(i).split(":");
            int id = Integer.parseInt(parts[0]);
            pair p = new pair();
            p.id = id;
            p.visto = false;
            vertices.add(p);
        }
        camino.add(s);
        if (s == t) {
            camino.add(t);
        } else {
            Stack<Integer> pila = new Stack<Integer>();
            pila.push(s);
            boolean encontrado = false;
            int flujoenc = 0;
            while (!encontrado && !pila.empty()) {
                for (int i = 0; i < vertices.size(); ++i) {
                    pair p = vertices.get(i);
                    if (p.id == pila.lastElement()) {
                        p.visto = true;
                        vertices.set(i, p);
                        i = vertices.size();
                    }
                }
                Vector<Integer> vad = new Vector<>();
                vad = G.verticesDestinoAdyacentes(pila.lastElement());
                int flujoprov = 0;
                int idvprov = -1;
                for (int i = 0; i < vad.size(); ++i) {
                    boolean encprov = false;
                    for (int j = 0; j < vertices.size(); ++j) {
                        if (!encprov) {
                            pair p = vertices.get(j);
                            if (p.id == vad.get(i)) {
                                encprov = true;
                                if (!p.visto) {
                                    int aux = G.getAristaEntreV(pila.lastElement(), p.id).getCapacidad() - G.getAristaEntreV(pila.lastElement(), p.id).getFlow();
                                    if (flujoprov < aux && (aux > 0 || G.getAristaEntreV(pila.lastElement(), p.id).getCapacidad() == 0)) {
                                        flujoprov = aux;
                                        idvprov = p.id;
                                    }
                                }
                            }
                        }
                    }
                }
                if (idvprov != -1 && flujoprov > 0) {
                    if (flujoenc > flujoprov || flujoenc == 0) {
                        flujoenc = flujoprov;
                    }
                    pila.push(idvprov);
                    camino.add(idvprov);
                    if (idvprov == t) {
                        encontrado = true;
                    }
                } else {//vertice "sin salida"
                    pila.remove(pila.size() - 1);//eliminamos de la pila (volver atras)
                    camino.remove(camino.size() - 1);//eliminamos de camino
                }
            }
        }
        return camino;
    }
}
