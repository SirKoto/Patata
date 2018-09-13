package Domini;

import Persistencia.ExportarMatriz;
import Persistencia.ImportarMatriz;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @autor Joan i Flor
 * @dataCreación 5/11/2014
 * @dataÚltimaModificación 11/12/2014
 */

public class Mapa{
    
    private String nombre;
    private Grafo mapa;
    private HashMap<String,Tramo> tramos;
    private String criterio = "tiempo";
    private int ultimaAristaBuena = 0;
    
    //Constructoras
    
    /**
     * @pre cierto
     * @post crea un Mapa con nombre = n
     */
    public Mapa(String n){
        this.nombre = n;
        this.mapa = new Grafo();
        this.tramos = new HashMap<String,Tramo>();
    }
    
    //Getters
    
    /**
     * @pre cierto
     * @post devuelve el criterio del Mapa del parámetro implícito
     */
    public String getCriterio(){
        return this.criterio;
    }
    
    /**
     * @pre cierto
     * @post devuelve el nombre del Mapa del parámetro implícito
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: devuelve el numero de pueblos del mapa
     */
    public int getNumPueblos(){
        Vector<String> pueblos = this.mapa.listarVertices();
        return pueblos.size();
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: devuelve el numero de tramos del mapa
     */
    public int getNumTramos(){
        Vector<String> tramos = this.mapa.listarAristas();
        return tramos.size();
    }

        /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el vector resultante es el conjunto de pueblos del mapa.
     */
    public Vector<String> getPueblosMapa(){
        return mapa.listarVertices();
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el vector resultante es conjunto de tramos del mapa.
     */
    public Vector<String> getTramosMapa(){
        return mapa.listarAristas();
    }

    /**
     * @pre: cierto
     * @post: devuelve un vector de strings con formato 'id:nombre'
     */
    Vector<String> getPueblos() {
        Vector<String> pueblos = mapa.listarVertices();
        return pueblos;
    }
    
    //Setters
    
    /**
     * @pre cierto
     * @post introduce el grafo que representará el mapa del parámetro implícito
     */
    public void setGrafo(Grafo G){
        //Estem creant aliasing!!!
        this.mapa = G;
    }
    
    //Metodos 
    
    /**
     * @pre cierto
     * @post Se añade un Tramo t con id = id en el Mapa del parámetro implícito
     */
    public void altaTramo (String id, Tramo t){
        tramos.put(id, t);
        ++this.ultimaAristaBuena;
    }
    
    /**
     * @pre: Hay un mapa cargado en el sistema y id es el id de un pueblo valido
     * @post: se le asigna el nombre "nombre" al pueblo "id"
     */
    public void modificarPueblo(String id, String nombre){
        Vector<String> nuevoV = new Vector<String>(2);
        nuevoV.add(0, id);
        nuevoV.add(1, nombre);
        this.mapa.modificarNombreDeVertice(nuevoV);
    }
    
    /**
     * @pre: Hay un mapa cargado en el sistema y id es el id de una arista válida
     * @post: la arista queda modificada con el valor de los parametros que se le pasan
     */
    public void modificarTramo(String id, String nombre, String tiempo, String kilometros, String costeBillete){
        Vector<String> aristaNombre = new Vector<String> (2);
        aristaNombre.add(0, id);
        aristaNombre.add(1, nombre);
        this.mapa.modificarNombreDeArista(aristaNombre);
        this.tramos.remove(id);
        Tramo tAux = new Tramo(id, nombre, Integer.parseInt(tiempo), Integer.parseInt(kilometros), Integer.parseInt(costeBillete));
        this.tramos.put(id, tAux);
        switch (this.criterio){
            case "tiempo":
                Vector<String> aristaPeso = new Vector<String> (2);
                aristaPeso.add(0, id);
                aristaPeso.add(1, tiempo);
                this.mapa.modificarPesoArista(aristaPeso);
                break;
            case "kilometros":
                Vector<String> aristaPeso2 = new Vector<String> (2);
                aristaPeso2.add(0, id);
                aristaPeso2.add(1, kilometros);
                this.mapa.modificarPesoArista(aristaPeso2);
                break;
            case "costeBillete":
                Vector<String> aristaPeso3 = new Vector<String> (2);
                aristaPeso3.add(0, id);
                aristaPeso3.add(1, costeBillete);
                this.mapa.modificarPesoArista(aristaPeso3);
                break;
            default:
                System.out.println("Error al cambiar los pesos");
                break;
        }
    }
    
    /**
     * @pre: el idArista no lo tiene ninguna otra arista del mapa/grafo
     * @post:  la arista/tramo se ha añadido al Mapa del parametro implicito
     */
    public void anadirArista(String idArista,String nombre,String idVerticeOrigen,String idVerticeDestino,int p1,int p2,int p3){
        Vector<String> aparams = new Vector<String>(5);
        aparams.add(0, idArista);
        aparams.add(1, nombre);
        aparams.add(2, idVerticeOrigen);
        aparams.add(3, idVerticeDestino);
        String capacidad;
        if ("tiempo".equals(this.getCriterio())){
            capacidad = Integer.toString(p1);
        }
        else{
            if ("kilometros".equals(this.getCriterio())){
                capacidad = Integer.toString(p2);
            }
            else{
                capacidad = Integer.toString(p3);
            }
        }
        aparams.add(4, capacidad);
        Tramo t = new Tramo(idArista,nombre,p1,p2,p3);
        this.altaTramo(idArista, t);
        mapa.altaArista(aparams);
    }
    
    /**
     * @pre El criterio que se pasa por parámetro debede ser "tiempo", "kilometros" o "costeBillete"
     * @post modifica el criterio del Mapa del parámetro implícito
     */
    public void cambiarCriterio(String criterio){
  
        if(! this.criterio.equals(criterio)) {
            
            Vector<String> aristas = this.mapa.listarAristas();
            
            if(criterio.equals("tiempo")) {
                for(int i = 0; i < aristas.size(); ++i) {
                    String aristaAux = aristas.get(i);
                    String[] atrArista = aristaAux.split(":");
                    Tramo tAux = tramos.get(atrArista[0]);
                    Vector<String> datos = new Vector<String> (2);
                    datos.add(0, atrArista[0]);
                    datos.add(1, Integer.toString(tAux.getTiempo()));
                    mapa.modificarPesoArista(datos);
                }
                this.criterio = criterio;
            }
            else if(criterio.equals("kilometros")) {
                for(int i = 0; i < aristas.size(); ++i) {
                    String aristaAux = aristas.get(i);
                    String[] atrArista = aristaAux.split(":");
                    Tramo tAux = tramos.get(atrArista[0]);
                    Vector<String> datos = new Vector<String> (2);
                    datos.add(0, atrArista[0]);
                    datos.add(1, Integer.toString(tAux.getKilometros()));
                    mapa.modificarPesoArista(datos);
                }
                this.criterio = criterio;
            }
            else if(criterio.equals("costeBillete")) {
                for(int i = 0; i < aristas.size(); ++i) {
                    String aristaAux = aristas.get(i);
                    String[] atrArista = aristaAux.split(":");
                    Tramo tAux = tramos.get(atrArista[0]);
                    Vector<String> datos = new Vector<String> (2);
                    datos.add(0, atrArista[0]);
                    datos.add(1, Integer.toString(tAux.getCosteBillete()));
                    mapa.modificarPesoArista(datos);
                }
                this.criterio = criterio;
            }
            else {
                System.out.println("Error en cambiarCriterio");
                //Throw error
            }
        }
    }
    /**
     * @pre: Hay una reunion en el sistema
     * @post: Se ha realizado la reunion y tenemos los resultados en el sistema
     */
    public void simularReunionMapa(Rutas rutas, int numAgentes, String origenReunion, String destinoReunion, String codigoAlgoritmo) {
        MaximumFlow mf = new MaximumFlow();
        mf.MaximumFlow(this.mapa, numAgentes, rutas, Integer.parseInt(origenReunion), Integer.parseInt(destinoReunion), codigoAlgoritmo);
    }
    
    /**
     * @pre: cierto
     * @post: todos los flows con valor diferente de 0 pasar a valer 0
     */
    public void resetFlows() {
        
        Vector<String> aristas = this.mapa.listarAristas();
        
        for(int i = 0; i < aristas.size(); ++i) {
            String aristaAux = aristas.get(i);
            String[] atrArista = aristaAux.split(":");
            if(Integer.parseInt(atrArista[5]) != 0) {
                Vector<String> datos = new Vector<String> (2);
                datos.add(0, atrArista[0]);
                datos.add(1, "0");
                mapa.modificarFlowArista(datos);
            }
        }
    }
    
    /**
     * @pre: cierto
     * @post: se ponen los flows a 0 y se eliminan las aristas residuales
     */
    public void resetMapa(){
        this.resetFlows();
        //Eliminar aristas residuales
        Vector<String> tramos = this.getTramosMapa();
        for (int i = this.ultimaAristaBuena; i < tramos.size(); i++){
            String aristaAux = tramos.get(i);
            String[] atrArista = aristaAux.split(":");
            this.mapa.eliminarArista(Integer.parseInt(atrArista[0]));
        }
    }

    /**
     * @pre: el path es correcto
     * @post: el mapa actual es exportado a 2 ficheros
     *      el fichero path+'V' tendrá las vertices
     *      el fichero path+'A' tendrá las aristas
     */
    public void exportarMapa(String path) {
        Vector<String> vertices = this.mapa.listarVertices();
        Vector<String> aristas = this.mapa.listarAristas();
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
            matrizA[i] = new String[9];
            lineaM = aristas.get(i).split(":");
            for (int j = 0; j < 6; j++) {
                matrizA[i][j] = lineaM[j];
            }
            Tramo tAux = tramos.get(lineaM[0]);
            matrizA[i][6] = Integer.toString(tAux.getTiempo());
            matrizA[i][7] = Integer.toString(tAux.getKilometros());
            matrizA[i][8] = Integer.toString(tAux.getCosteBillete());
        }

        ExportarMatriz em = new ExportarMatriz();
        
        try {
            boolean bV = em.exportar(path + "V", matrizV);
        } catch (IOException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            boolean bA = em.exportar(path + "A", matrizA);
        } catch (IOException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @pre: No hay ningún mapa, el path es correcto y tiene el formato requerido
     *      del mapa
     * @post: el mapa actual es el que hemos importado
     */
    public void importarMapa(String path) {
        try {
            ImportarMatriz im = new ImportarMatriz();
            String[][] matrizV = im.importar(path + "V");
            if( matrizV != null ){
                String[][] matrizA = im.importar(path + "A");

                Vector<String> vparams = new Vector<String>(2);
                for (int i = 0; i < matrizV.length; i++) {
                    vparams.add(0, matrizV[i][0]);
                    vparams.add(1, matrizV[i][1]);
                    this.mapa.altaVertice(vparams);
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
                    this.mapa.altaArista(aparams);
                    flow.add(0, matrizA[i][0]);
                    flow.add(1, matrizA[i][5]);
                    this.mapa.modificarFlowArista(flow);
                    peso.add(0, matrizA[i][0]);
                    peso.add(1, matrizA[i][6]);
                    this.mapa.modificarPesoArista(peso);
                    Tramo tAux = new Tramo(matrizA[i][0], matrizA[i][1], Integer.parseInt(matrizA[i][6]), Integer.parseInt(matrizA[i][7]), Integer.parseInt(matrizA[i][8]));
                    this.tramos.put(matrizA[i][0], tAux);
                    ++this.ultimaAristaBuena;
                }
                System.out.println("Se ha importado el mapa " + path + " al sistema");
            }
        } catch (IOException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}