package Domini;

import java.util.Scanner;
import java.util.Vector;

/**
 * @autor Joan i Flor
 * @dataCreación 24/10/2014
 * @dataÚltimaModificación 11/12/2014
 */
public class CtrlDomini {

    private Reunion r;
    private Mapa mapa;
    private Rutas rutas;
    private boolean mapaCreado = false;
    private boolean reunionCreada = false;
    private boolean reunionHecha = false;

    //Constructoras

    /**
     * Constructora vacia
     *
     * @pre cierto
     * @post Crea una instancia vacia de CtrlDomini
     */
    public CtrlDomini() {
    }

    //Getters

    /**
     * @pre cierto
     * @post consulta si hay un mapa cargado en el sistema o no.
     */
    public boolean getMapaCreado() {
        return this.mapaCreado;
    }

    /**
     * @pre cierto
     * @post consulta si la reunión ya ha sido realizada o no.
     */
    public boolean getReunionHecha() {
        return this.reunionHecha;
    }

    /**
     * @pre cierto
     * @post consulta si la reunión ya esta creada o no.
     */
    public boolean getReunionCreada() {
        return this.reunionCreada;
    }
    
    /**
     * @pre: la reunion está creada
     * @post: devuelve el nombre de la reunión del sistema
     */
    public String getNombreReunion(){
        return this.r.getCodigo();
    }

    /**
     * @pre el mapa esta creado en el sistema
     * @post devuelve el nombre del mapa cargado en el sistema
     */
    public String getNombreMapa() {
        return this.mapa.getNombre();
    }

    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el vector resultante es el conjunto de pueblos del mapa.
     */
    public Vector<String> getPueblosMapa() {
        return this.mapa.getPueblosMapa();
    }

    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el vector resultante es conjunto de tramos del mapa.
     */
    public Vector<String> getTramosMapa() {
        return this.mapa.getTramosMapa();
    }

    /**
     * @pre: hay una reunion cargada en el sistema
     * @post: el vector resultante es conjunto de tramos del mapa.
     */
    public String[] getParamsReunion() {
        String[] params = new String[6];
        params[0] = this.r.getCodigo();
        params[1] = Integer.toString(this.r.getNumAgentes());
        params[2] = this.r.getPuebloOrigen();
        params[3] = this.r.getPuebloDestino();
        params[4] = this.r.getCodigoAlgoritmo();
        params[5] = this.mapa.getCriterio();
        return params;
    }

    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: devuelve el numero de pueblos del mapa
     */
    public int getNumPueblos() {
        return this.mapa.getNumPueblos();
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: devuelve el numero de tramos del mapa
     */
    public int getNumTramos(){
        return this.mapa.getNumTramos();
    }

    //Metodos

    /**
     * @pre: cierto
     * @post: se eliminan el mapa y la reunión
     */
    public void reinicioTotal(){
        this.r = null;
        this.mapa = null;
        this.reunionCreada = false;
        this.mapaCreado = false;
    }

    /**
     * @pre No existe un mapa cargado en el sistema.
     * @post Se ha creado un mapa con nombre = nombreMapa, los vértices de
     * vertices, las aristas de aristas y los pesos de pesos.
     */
    public void crearMapaConTodosParametros(String nombreMapa, String[][] vertices, String[][] aristas, String[][] pesos) {
        this.mapa = new Mapa(nombreMapa);
        Grafo gAux = new Grafo();

        //Añadir vértices
        for (int i = 0; i < vertices.length; i++) {
            gAux.altaVertice(Integer.parseInt(vertices[i][0]), vertices[i][1]);
        }

        //Añadir aristas y pesos
        for (int i = 0; i < aristas.length; i++) {
            gAux.altaArista(Integer.parseInt(aristas[i][0]), aristas[i][1], Integer.parseInt(aristas[i][2]), Integer.parseInt(aristas[i][3]), 1);

            Vector<String> peso = new Vector<String>(2);
            peso.add(0, aristas[i][0]);
            peso.add(1, pesos[i][0]);
            gAux.modificarPesoArista(peso);

            Tramo t = new Tramo(aristas[i][0], aristas[i][1], Integer.parseInt(pesos[i][0]), Integer.parseInt(pesos[i][1]), Integer.parseInt(pesos[i][2]));
            this.mapa.altaTramo(aristas[i][0], t);
        }

        this.mapa.setGrafo(gAux);
        this.mapaCreado = true;
    }

    /**
     * @pre cierto
     * @post los flows del mapa quedan reseteados.
     */
    public void resetFlows() {
        this.mapa.resetFlows();
    }
    
    /**
     * @pre: cierto
     * @post: se borran las aristas residuales del mapa y los flows
     */
    public void resetMapa(){
        this.mapa.resetMapa();
    }

    /**
     * @pre: cierto
     * @post: las rutas quedan reseteadas
     */
    public void resetRutas() {
        this.rutas.resetRutas();
    }

    /**
     * Params es un vector con la siguiente informacion: String codigoReunion,
     * int numAgentes, String puebloOrigen, String puebloDestino
     *
     * @pre: cierto
     * @post: si hay un mapa en el sistema crea una reunion con los parametros
     * pasados al metodo.
     */
    public void cGestionReuniones(String[] params) {
        if (!this.mapaCreado) {
            System.out.println("Antes de crear una reunión debes crear un mapa!");
        } else {
            //params[0]= codigo reunión
            //params[1]= número de agentes
            //params[2]= pueblo origen
            //params[3]= pueblo destino
            //params[4]= criterio
            //params[5]= algoritmo (1-FF, 2-EK, 3-FFD)

            //Si la reunion esta creada, no se llama a la constructora
            if (this.reunionCreada) {
                this.r.setCodigo(params[0]);
                this.r.setNumAgentes(params[1]);
                this.r.setPuebloOrigen(params[2]);
                this.r.setPuebloDestino(params[3]);
                this.r.setAlgoritmo(params[5]);
                if (!params[4].equals(this.mapa.getCriterio())) {
                    this.mapa.cambiarCriterio(params[4]);
                }
            } else {
                this.r = new Reunion(params[0], Integer.parseInt(params[1]), params[2], params[3], params[5]);
                System.out.println("Reunion " + this.r.getCodigo() + " creada");
                if (!params[4].equals(this.mapa.getCriterio())) {
                    this.mapa.cambiarCriterio(params[4]);
                }
                this.reunionCreada = true;
            }
        }
    }

    /**
     * @pre: cierto
     * @post: Si hay un reunion creada en el sistema, la reunion se realiza y
     * las rutas que toman los agentes quedan guardadas.
     */
    public void cHacerReunion() {
        //Comprovar que existeixi la reunion
        if (!this.reunionCreada) {
            System.out.println("Antes de simular una reunión debes crearla!");
        } else {
            String origenReunion = this.r.getPuebloOrigen();
            String destinoReunion = this.r.getPuebloDestino();
            String codigoAlgoritmo = this.r.getCodigoAlgoritmo();
            this.rutas = new Rutas(this.r.getNumAgentes());
            mapa.simularReunionMapa(this.rutas, this.r.getNumAgentes(), origenReunion, destinoReunion, codigoAlgoritmo);
            this.reunionHecha = true;
        }
    }
    
    /**
     * @pre: se ha hecho una reunión al sistema y no hay solución
     * @post: los agentes que no han podido llegar al destino intentan ir del pueblo de origen a 
     * puebloDestino respetando las rutas de los agentes que han llegado al primer destino
     */
    public void cHacerSubreunion(String puebloDestino) {
        String origenReunion = this.r.getPuebloOrigen();
        String destinoReunion = puebloDestino;
        String codigoAlgoritmo = this.r.getCodigoAlgoritmo();
        Integer numAgentesRest = this.r.getNumAgentes() - this.rutas.getNumRutas();
        mapa.simularReunionMapa(this.rutas, numAgentesRest, origenReunion, destinoReunion, codigoAlgoritmo);
    }
    
    /**
     * @pre: cierto
     * @post: los resultados de la reunion del sistema quedan borrados
     */
    public void resetResultados(){
        if (this.reunionHecha){
            this.rutas = null;
            this.mapa.resetMapa();
            this.reunionHecha = false;
        }
    }

    /**
     * @pre: cierto
     * @post: si la reunion esta creada y realizada retorna una matriz con los resultados
     */
    public String[][] consultarResultados() {
        int n = r.getNumAgentes();
        String pO = r.getPuebloOrigen();
        String pD = r.getPuebloDestino();

        String[][] result = new String[n][];

        if (!this.reunionHecha) {
            System.out.println("Antes de ver los resultados tienes que hacer la simulación!");
        } else {
            System.out.println("Por cada linea se muestra el id del agente seguido de ':' y los pueblos por los que pasa 'NombrePueblo(idPueblo)'");
            Vector<String> pueblos = mapa.getPueblos();
            for (int i = 0; i < n; i++) {
                int numPueblosI = this.rutas.getNumPueblos(i);
                result[i] = new String[numPueblosI + 1];  //+ 1 porque en la primera columna pondremos el id del agente
                result[i][0] = Integer.toString(i);
                int[] idPueblosI = this.rutas.getPueblos(i);
                String[] nomPueblosI = new String[idPueblosI.length];
                for (int k = 0; k < idPueblosI.length; k++) {
                    String nombrePueblo;
                    for (int l = 0; l < pueblos.size(); l++) {
                        String[] puebloAux = pueblos.get(l).split(":");
                        if (idPueblosI[k] == Integer.parseInt(puebloAux[0])) {
                            nomPueblosI[k] = puebloAux[1];
                        }
                    }
                }
                for (int j = 1; j < numPueblosI + 1; j++) {
                    result[i][j] = (nomPueblosI[j - 1] + "(" + Integer.toString(idPueblosI[j - 1]) + ")");
                }
            }
        }
        return result;
    }

    /**
     * @pre: cierto
     * @post: si la reunión esta realizada el resultados sera un boleano que
     * indicara si la reunion tiene solución o no.
     */
    public boolean haySolucion() {
        boolean sol = false;
        if (!this.reunionHecha) {
            System.out.println("Antes de ver los resultados tienes que hacer la simulación!");
        } else {
            if (rutas.getNumRutas() == r.getNumAgentes()) {
                sol = true;
            }
        }
        return sol;
    }

    /**
     * @pre: el parametro nuevoDestino tiene que ser el identificador de una de
     * las ciudades del mapa.
     * @post: Se cambia el destino donde que desea realizar la reunión.
     */
    public void cNuevoDestino(String nuevoDestino) {
        this.reunionHecha = false;
        r.setPuebloDestino(nuevoDestino);
    }

    /**
     * @pre: el path es correcto y tiene el formato requerido.
     * @post: Si el sistema no tiene ningun mapa en el sistema todavia se creará
     * un el mapa con los datos de los ficheros del path.
     *
     */
    public void crearMapaConPesos(String path) {
        //A partir de un fichero crear el mapa (grafo+tramos)
        if (this.mapaCreado) {
            System.out.println("No puedes importar un mapa si ya tienes uno en el sistema");
        } else {
            CtrlPersistencia cp = new CtrlPersistencia();
            boolean existeV = cp.existeFichero(path + "V");
            boolean existeA = cp.existeFichero(path + "A");
            if (existeV && existeA) {
                String[] nomMapa = path.split("/");
                int siz = nomMapa.length;
                this.mapa = new Mapa(nomMapa[siz-1]);
                this.mapa.importarMapa(path);
                this.mapaCreado = true;
            } else {
                System.out.println("El archivo no existe");
            }
        }
    }

    /**
     * @pre: Hay un mapa cargado en el sistema y id es el id de un pueblo valido
     * @post: se le asigna el nombre "nombre" al pueblo "id"
     */
    public void modificarPueblo(String id, String nombre){
        this.mapa.modificarPueblo(id, nombre);
    }
    
    /**
     * @pre: Hay un mapa cargado en el sistema y id es el id de una arista válida
     * @post: la arista queda modificada con el valor de los parametros que se le pasan
     */
    public void modificarTramo(String id, String nombre, String tiempo, String kilometros, String costeBillete){
        this.mapa.modificarTramo(id, nombre, tiempo, kilometros, costeBillete);
    }
    
    /**
     * @pre: el path es correcto
     * @post: Si hay un mapa en el sistema se crearan 2 archivos en el path
     * indicado uno con los datos de las Ciudades (vertices) y otro con los
     * datos de los tramos (aristas)
     */
    public void exportarMapaConPesos(String path) {
        if (!this.mapaCreado) {
            System.out.println("No puedes exportar el mapa si no està creado");
        } else {
            this.mapa.exportarMapa(path);
        }
    }

    /**
     * @pre: Hay un mapa en el sistema
     * @post: el resultado es el mapa que hay en ese momento en el sistema.
     */
    public Mapa devolverMapaActual() {
        Mapa m = this.mapa;
        return m;
    }
}
