package Presentacio;

import Domini.CtrlDomini;
import Domini.CtrlPersistencia;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @autor Joan Flor Víctor Laura
 * @dataCreación 3/11/2014
 * @dataÚltimaModificación 11/12/2014
 */

public class CtrlPresentacio {

    private CtrlDomini cd;
    private CtrlPersistencia cp;
    private VistaPrincipal vistaPrincipal;

    //Constructoras
    
     /**
     * @pre cierto
     * @post crea una instancia de la case CtrlPresentación, una de CtrlDomini 
     *      y una de CtrlPersistencia
     */
    public CtrlPresentacio() throws IOException {
        this.cd = new CtrlDomini();
        this.cp = new CtrlPersistencia();
        this.vistaPrincipal = new VistaPrincipal(this); //Le pasamos por parametro el controlador de presentación
    }

    //Getters

    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: devuelve el numero de pueblos del mapa
     */
    public int getNumPueblos(){
        return this.cd.getNumPueblos();
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: devuelve el numero de tramos del mapa
     */
    public int getNumTramos(){
        return this.cd.getNumTramos();
    }

    /**
     * @pre: hay una reunión cargada en el sistema
     * @post: devuelve los parametros de la reunión
     */
    public String[] getParamsReunion(){
        return this.cd.getParamsReunion();
    }

    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el string resultante es nombre del mapa y el String mapa es el mapa.
     */
    public String getNombreMapa(){
        return this.cd.getNombreMapa();
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el vector resultante es el conjunto de pueblos del mapa.
     */
    public Vector<String> getPueblosMapa(){
        return this.cd.getPueblosMapa();
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: el vector resultante es conjunto de tramos del mapa.
     */
    public Vector<String> getTramosMapa(){
        return this.cd.getTramosMapa();
    }

    //Métodos
    
    /**
     * @pre: cierto
     * @post: se ha iniciado el menu principal del programa
     */
    public void iniciaPrograma() throws IOException {
        new VistaBienvenida(this).setVisible(true);
    }
    
    /**
     * @pre: cierto
     * @post: se cierra la vista de bienvenida para abrir la vista que gestiona todos los casos de uso
     */
    public void cierraBienvenida(){
        this.vistaPrincipal.setVisible(true);
    }

    /**
     * @pre: se ha creado un mapa
     * @post: cierra la vista de crear mapa y abre la vista de ver el mapa cargado en el sistema
     */
    public void avisoMapaCreado(){
        this.vistaPrincipal.cierraCrearMapaAbreVistaMapa();
    }
    
    /**
     * @pre: cierto
     * @post: devuelve true si hay un mapa cargado en el sistema
     */
    public boolean hayMapaCargado() {
        return this.cd.getMapaCreado();
    }
    
    /**
     * @pre: cierto
     * @post: devuelve true si hay una reunion creada en el sistema
     */
    public boolean hayReunion(){
        return this.cd.getReunionCreada();
    }
    
    /**
     * @pre: hay una reunión cargada en el sistema
     * @post: dest queda asignado como ciudad de destino de la reunión
     */
    public void cambiarDestinoReunion(String dest){
        this.cd.cNuevoDestino(dest);
    }
    
    /**
     * @pre: cierto
     * @post: se eliminan el mapa, la reunión y el resultado que haya en el sistema
     */
    public void reinicioTotal(){
        this.resetResultados();
        this.cd.reinicioTotal();
        this.vistaPrincipal.borrarParamsReunion();
    }

     /**
     * @pre: cierto
     * @post: se ha creado un mapa con todos los parametros especificados 
     */
    public void crearMapaConTodosParametros(String nombreMapa, String[][] mapCiudades, String[][] mapCaminos, String[][] mapPesos){
        this.cd.crearMapaConTodosParametros(nombreMapa, mapCiudades, mapCaminos, mapPesos);
    }
    
    /**
     * @pre: path lleva aun archivo de resultados
     * @post: se importa el resultado de la simulación que hay guardado en path
     */
    String[][] importarMatrizResultados(String path) throws IOException{
        return this.cp.importarMatrizDatos(path);
    }
    
    /**
     * @pre: Hay un mapa cargado en el sistema y id es el id de un pueblo valido
     * @post: se le asigna el nombre "nombre" al pueblo "id"
     */
    public void modificarPueblo(String id, String nombre){
        this.cd.modificarPueblo(id, nombre);
    }
    
    /**
     * @pre: Hay un mapa cargado en el sistema y id es el id de una arista válida
     * @post: la arista queda modificada con el valor de los parametros que se le pasan
     */
    public void modificarTramo(String id, String nombre, String tiempo, String kilometros, String costeBillete){
        this.cd.modificarTramo(id, nombre, tiempo, kilometros, costeBillete);
    }
    
    /**
     * @pre: No hay un mapa en el sistema todavia
     * @post: El sistema tiene un mapa y es el que se ha creado a través
     * de los datos de los ficheros indicados.
     */
    public boolean importarFichero(String path) {
        boolean sePuede = true;
        if (this.cd.getMapaCreado()){
            sePuede = false;
        }
        else{
            this.cd.crearMapaConPesos(path);
        }
        return sePuede;
    }

    /**
     * @pre: cierto
     * @post: El mapa que tiene en ese momento el sistema es exportado en forma de
     * dos fichero al path que se indique.
     */
    private void exportarMapa() {
        System.out.println("Exportar mapa a un fichero");
        System.out.println("Escribe el path del fichero:");
        Scanner escaner = new Scanner(System.in);
        String path = escaner.next();
        this.cd.exportarMapaConPesos(path);
    }
    
    /**
     * @pre: hay un mapa cargado en el sistema
     * @post: El mapa que tiene en ese momento el sistema es exportado en forma de
     * dos fichero al path que se indique.
     */
    public void exportarMapaConPesos(String path){
        this.cd.exportarMapaConPesos(path);
    }

    /**
     * @pre: params contiene los 6 parametros necesarios para crear una reunion
     * @post: Se ha creado una reunión
     */
    public void altaReunion(String[] params){
        this.cd.cGestionReuniones(params);
    }

    /**
     * @pre: Hay una reunión cargada al sistema
     * @post: se realiza la reunión
     */
    public void cHacerReunion() {
        if (cd.getReunionCreada()){
            this.cd.cHacerReunion();
            System.out.println("Se ha hecho la reunión, ya puedes consultar los resultados");
        }
        else {
            System.out.println("Antes de simular una reunión debes crearla!");
        }
    }
    
    /**
     * @pre: se ha hecho una reunión al sistema y no hay solución
     * @post: los agentes que no han podido llegar al destino intentan ir del pueblo de origen a 
     * puebloDestino respetando las rutas de los agentes que han llegado al primer destino
     */
    public void cHacerSubreunion(String puebloDestino) {
        this.cd.cHacerSubreunion(puebloDestino);
    }
    
    /**
     * @pre: la reunion está creada
     * @post: devuelve el nombre de la reunión del sistema
     */
    public String getNombreReunion(){
        return this.cd.getNombreReunion();
    }
    
    /**
     * @pre: cierto
     * @post: devuelve si la simulación se ha hecho o no
     */
    public boolean getReunionHecha(){
        return this.cd.getReunionHecha();
    }
    
    /**
     * @pre: Se ha realizado la simulación
     * @post: devuelve si la simulación tiene solución
     */
    public boolean haySolucion(){
        return this.cd.haySolucion();
    }
    
    /**
     * @pre: Se ha creado y realizado anteriormente una reunión
     * @post: si la reunion esta creada y realizada retorna una matriz con los
     * resultados
     */
    public String[][] consultarResultados(){
        return this.cd.consultarResultados();
    }
    
    /**
     * @pre: cierto
     * @post: los resultados que hay en la matriz result quedan guardados en path
     */
    public void exportarResultados(String path, String[][] result) throws IOException{
        this.cp.exportarMatrizDatos(path, result);
    }
    
    /**
     * @pre: cierto
     * @post: los resultados de la reunion del sistema quedan borrados
     */
    public void resetResultados(){
        this.vistaPrincipal.eliminaResultados();
        this.cd.resetResultados();
    }
    
    /**
     * @pre: cierto
     * @post: se borran las aristas residuales del mapa
     */
    public void resetMapa(){
        this.cd.resetMapa();
    }
}
