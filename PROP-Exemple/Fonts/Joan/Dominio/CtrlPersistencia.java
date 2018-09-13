package Domini;

import Persistencia.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

/**
 * @autor Joan i Victor
 * @dataCreación 25/10/2014
 * @dataÚltimaModificación 5/11/2014
 */

public class CtrlPersistencia {

    //Constructoras
    
    /**
     * @pre cierto
     * @post crea una instancia de la case CtrlPersistencia
     */
    public CtrlPersistencia() {     
    }
    
    //Métodos
    
    /**
     * @pre cierto
     * @post Devuelve cierto si el fichero existe en el path y falso en caso contrario
     */
    public boolean existeFichero(String path){
        ImportarMatriz im = new ImportarMatriz();
        return im.existeFichero(path);
    }
    
    /**
     * @pre  En la variable ‘path’ Se le puede pasar:
     *       O bien la ruta completa donde se guarda el archivo. 
     *       Por ejemplo: /Users/JoanVila/Desktop/Path 
     *       O bien sólo el nombre del archivo. En este caso el archivo debe de 
     *       estar guardado en la carpeta del proyecto.
     *       el archivo tiene que existir y tener el formato requerido.
     * @post se leerá el archivo. Cada linea del archivo es una linea de 
     *       la matriz, cada parametro del archivo será un columna de la 
     *       matriz resultante.
    */
    public String[][] importarMatrizDatos(String path) throws IOException {
        ImportarMatriz im = new ImportarMatriz();
        String[][] matriz = im.importar(path);
        return matriz;
    }
    
    /**
     * @pre  En la variable ‘path’ Se le puede pasar:
     *       O bien la ruta completa donde se guarda el archivo. 
     *       Por ejemplo: /Users/JoanVila/Desktop/NombreArchivo 
     *       En este caso se crearà el archivo NombreArchivo en el escritorio
     *       O bien sólo el nombre del archivo. En este caso el archivo queda 
     *       guardado en la carpeta del proyecto.

     * @post Se creará el archivo del path con los datos que contiene la matriz.
     *       Cada fila de la matriz es una fila el archivo. Cada columna de la  
     *       matriz es un parametro de la linea del archivo. Los parametros se
     *       separan por ‘:’. 
     *       Si el archivo se ha guardado correctamente se devuelve cierto.
     */ 
    public boolean exportarMatrizDatos(String path, String[][] matriz) throws IOException {
        ExportarMatriz em = new ExportarMatriz();
        boolean b = em.exportar(path, matriz);
        return b;
    }
    
    /**
     * @pre ‘path’ es el nombre del archivo que se le pasó a exportarGrafo en el 
     * momento de exportarlo.
     * @post El grafo resultante es el que estaba guardado en el archivo
    */
    public Grafo importarGrafo(String path) throws IOException {
        Grafo g = new Grafo();
        ImportarMatriz im = new ImportarMatriz();
        String[][] matrizV = im.importar(path + "V");
        String[][] matrizA = im.importar(path + "A");

        Vector<String> vparams = new Vector<String>(2);
        for (int i = 0; i < matrizV.length; i++) {
            vparams.add(0, matrizV[i][0]);
            vparams.add(1, matrizV[i][1]);
            g.altaVertice(vparams);
        }

        Vector<String> aparams = new Vector<String>(5);
        Vector<String> flow = new Vector<String>(2);
        Vector<String> peso = new Vector<String>(2);
        for (int i = 0; i < matrizA.length; i++) {
            aparams.add(0, matrizA[i][0]);
            aparams.add(1, matrizA[i][1]);
            aparams.add(2, matrizA[i][2]);
            aparams.add(3, matrizA[i][3]);
            aparams.add(4, matrizA[i][4]);
            g.altaArista(aparams);
            flow.add(0, matrizA[i][0]);
            flow.add(1, matrizA[i][5]);
            g.modificarFlowArista(flow);
            peso.add(0, matrizA[i][0]);
            peso.add(1, matrizA[i][6]);
            g.modificarPesoArista(peso);
        }

        return g;
    }
    
    /**
     * @pre: El path es correcto y el formato de los ficheros del path es el requerido
     * @post: El sistema incluira el grafo y los tramos
     */
    public void importarGrafoYTramos(String path, Grafo G, HashMap<String,Tramo> tramos) throws IOException{
        ImportarMatriz im = new ImportarMatriz();
        String[][] matrizV = im.importar(path + "V");
        String[][] matrizA = im.importar(path + "A");

        Vector<String> vparams = new Vector<String>(2);
        for (int i = 0; i < matrizV.length; i++) {
            vparams.add(0, matrizV[i][0]);
            vparams.add(1, matrizV[i][1]);
            G.altaVertice(vparams);
        }

        Vector<String> aparams = new Vector<String>(5);
        Vector<String> flow = new Vector<String>(2);
        Vector<String> peso = new Vector<String>(2);
        Tramo tram;
        for (int i = 0; i < matrizA.length; i++) {
            aparams.add(0, matrizA[i][0]);
            aparams.add(1, matrizA[i][1]);
            aparams.add(2, matrizA[i][2]);
            aparams.add(3, matrizA[i][3]);
            aparams.add(4, matrizA[i][4]);
            G.altaArista(aparams);
            flow.add(0, matrizA[i][0]);
            flow.add(1, matrizA[i][5]);
            G.modificarFlowArista(flow);
            peso.add(0, matrizA[i][0]);
            peso.add(1, matrizA[i][6]);
            G.modificarPesoArista(peso);
            int tiempo = Integer.parseInt(matrizA[i][6]);
            int kilometros = Integer.parseInt(matrizA[i][7]);
            int costeBillete = Integer.parseInt(matrizA[i][8]);
            tram = new Tramo(matrizA[i][0], matrizA[i][1], tiempo, kilometros, costeBillete);
            tramos.put(matrizA[i][0], tram);
        }        
    }
    
    /**
     * @pre cierto
     * @post El grafo queda guardado en dos archivos distintos: ‘path’+V 
     * (Donde se guardan los vétices con su información) y ‘path’+A 
     * (Donde se guardan las aristas y su información). 
     * Devuelve true si el grafo se ha exportado con éxito.
     */
    public boolean exportarGrafo(String path, Grafo G) throws IOException {
        Vector<String> vertices = G.listarVertices();
        Vector<String> aristas = G.listarAristas();
        int vsize = vertices.size();
        int asize = aristas.size();

        String[][] matrizV = new String[vsize][];
        String[][] matrizA = new String[asize][];

        String[] lineaM;

        //Vertices
        for (int i = 0; i < vsize; i++) {
            matrizV[i] = new String[2];
            lineaM = vertices.get(i).split(":");
            for (int j = 0; j < 2; j++) {
                matrizV[i][0] = lineaM[0];
                matrizV[i][1] = lineaM[1];
            }
        }

        //Aristas
        for (int i = 0; i < asize; i++) {
            matrizA[i] = new String[7];
            lineaM = aristas.get(i).split(":");
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; ++k){
                    matrizA[i][k] = lineaM[k];
                }
            }
        }

        ExportarMatriz em = new ExportarMatriz();
        boolean bV = em.exportar(path + "V", matrizV);
        boolean bA = em.exportar(path + "A", matrizA);
        return bV && bA;
    }
}
