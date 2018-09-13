package Domini;

import java.util.ArrayList;

/**
 * @autor Flor
 * @dataCreación 13/11/2014
 * @dataÚltimaModificación 18/11/2014
 */

public class MaximumFlow {

    //Constructoras

    public void MaximumFlow() {
        
    }

    //Metodos

    /**
     * @pre origen y destino son dos vertices existentes del grafo
     * @post El grafo G queda modificado con en numero de agentes que pasa por cada arista (0 o 1). 
     *       Las rutas que toman los agentes quedan guardadas en rutas.
     */
    public void MaximumFlow(Grafo G, int numAgentes, Rutas rutas, int origen, int destino, String codigoAlgoritmo) {
        int numRutasCalculadas = 0;
        int idAristaLibre = 0;
        boolean sigueBuscando = true;
        while (sigueBuscando) {
            sigueBuscando = G.existeArista(idAristaLibre);
            if (sigueBuscando) {
                idAristaLibre++;
            }
        }
        GestioGraphResidual grafResidual = new GestioGraphResidual();
        Dijkstra d = new Dijkstra();
        EdmondsKarp ek = new EdmondsKarp();
        FordFulkerson ff = new FordFulkerson();
        ArrayList<Integer> aux = new ArrayList<Integer>();
        boolean isEmpty = false;
        while (!isEmpty && numRutasCalculadas < numAgentes) {
            switch (codigoAlgoritmo){
                case "FF":
                    aux = ff.buscarCamino(G, origen, destino); //Llamada al FF
                    break;
                case "EK":
                    aux = ek.camiEdmonds(G, origen, destino); //Llamada a BFS
                    break;
                case "FFD":
                    aux = d.calcularCaminos(G, origen, destino); //Llamada al dijkstra
                    break;
                default:
                    System.out.println("No tengo un codigo de algoritmo valido, usando FF+D");
                    aux = d.calcularCaminos(G, origen, destino); //Llamada al dijkstra
                    break;
            } 
            numRutasCalculadas++;
            isEmpty = aux.isEmpty();
            if (!isEmpty) {
                //Añadimos la ruta a rutas
                rutas.anadirRuta(aux);
                //Llamamos a gestiona de grafResidual
                grafResidual.gestiona(G, aux, idAristaLibre);
                idAristaLibre = grafResidual.getIdAr();
                idAristaLibre++;
            }
        }
    }
}
