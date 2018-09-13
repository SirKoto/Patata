package Domini;

import java.util.Iterator;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @author daniel
 */
public class Grafo {
    
    private String nombre;
    // Los datos se guardan en un TreeMap con clave = idVertice (int)
    private TreeMap<Integer, Vertice> vertices; //*** Agregacion
    // Los datos se guardan en un TreeMap con clave = idArista (int)
    private TreeMap<Integer, Arista> aristas; //*** Agregacion
    
    // Constructoras
    
    /*
     * @pre cierto
     * @post grafo creado
     */
    public Grafo() {
        vertices = new TreeMap<Integer, Vertice>();
        aristas = new TreeMap<Integer, Arista>();
        Vertice v = new Vertice("inicial");
        vertices.put(1, v);
        v = new Vertice("final");
        vertices.put(2, v);
    }
    
    /*
     * @pre cierto
     * @post grafo creado
     */
    public Grafo(String n) {
        vertices = new TreeMap<Integer, Vertice>();
        aristas = new TreeMap<Integer, Arista>();
        nombre = n;
        Vertice v = new Vertice("inicial");
        vertices.put(1, v);
        v = new Vertice("final");
        vertices.put(2, v);
    }
    
    // Getter
    
    /*
     * @pre cierto
     * @post devuelve el nombre del grafo
     */
    public String getNombre() {
        return nombre;
    }
    
    // Setter
    
    /*
     * @pre cierto
     * @post introduce/modifica el nombre del grafo
     */
    public void setNombre(String n) {
        this.nombre = n;
    }
    
    // Metodos
    
    // Consultoras
    
    /*
    * Copia un grafo
    * @pre cierto
    * @post se crea una copia del grafo implicito a grafo
    */
    public static Grafo copyGrafo(Grafo grafo) {
        Grafo g = new Grafo();
        g.vertices = grafo.vertices;
        g.aristas = grafo.aristas;
        g.nombre = grafo.nombre;
        return g;
    }
    
    /*
     * id es el idVertice buscado
     * @pre cierto
     * @post devuelve si existe el vertice con id
     */
    public boolean existeVertice(int id) {
        return vertices.containsKey(id);
    }
    
    /*
     * id es el idArista buscado
     * @pre cierto
     * @post devuelve si existe la arista con id
     */
    public boolean existeArista(int id) {
        return aristas.containsKey(id);
    }
    
    /*
     * nombre es el nombre del vertice
     * @pre cierto
     * @post devuelve si el nombre esta siendo usado
     */
    public boolean comprovarNombreVertice(String nombre) {
        Iterator it = vertices.keySet().iterator();
        while (it.hasNext()) {
            int id = (Integer) it.next();
            Vertice v = vertices.get(id);
            if (v.getNombre().equals(nombre))
                return true;                        //*** el nombre esta repetido
        }
        return false;                               //*** el nombre no esta repetido
    }
    
    /*
     * nombre es el nombre de la arista
     * @pre cierto
     * @post devuelve si el nombre esta siendo usado
     */
    public boolean comprovarNombreArista(String nombre) {
        Iterator it = aristas.keySet().iterator();
        while (it.hasNext()) {
            int id = (Integer) it.next();
            Arista a = aristas.get(id);
            if (a.getNombre().equals(nombre))
                return true;                        //*** el nombre esta repetido
        }
        return false;                               //*** el nombre no esta repetido
    }
    
    /*
     * La o es el idVerticeOrigen y la d es el idVerticeDestino
     * IMPORTANTE: Se recomienda utilizar esta comprovacion antes de anadir una arista entre 'o' y 'd'
     * @pre o existe. d existe.
     * @post devuelve si hay ciclo en el grafo
     */
    public boolean hayCiclo(int o, int d) {
        TreeMap<Integer, Boolean> comprovar = new TreeMap<Integer, Boolean>();
        Iterator it = aristas.keySet().iterator();
        while (it.hasNext()) {
            int id = (Integer) it.next();
            comprovar.put(id, false);
        }
        comprovar.put(d, true);
        Stack<Integer> s = new Stack<Integer>();
        Vertice v = vertices.get(d);
        for (int i = 0; i < v.numeroAristasSalida(); i++) {
            int ida = v.iesimaAristaSalida(i);
            Arista a = aristas.get(ida);
            if (a.getVerticeDestino() == o)
                return true;                        //*** Hay ciclo
            s.add(a.getVerticeDestino());
        }
        while (!s.empty()) {
            int idv = s.pop();
            if (!comprovar.get(idv)) {
                comprovar.put(idv, true);
                Vertice vv = vertices.get(idv);
                for (int i = 0; i < vv.numeroAristasSalida(); i++) {
                    int ida = v.iesimaAristaSalida(i);
                    Arista a = aristas.get(ida);
                    if (a.getVerticeDestino() == o)
                        return true;                //*** Hay ciclo
                    s.add(a.getVerticeDestino());
                }
            }
        }
        return false;                               //*** No hay ciclos
    }
    
    /*
     * @pre cierto
     * @post devuelve un vector de strings con formato 'id:nombre' por linea
     */
    public Vector<String> listarVertices() {
        Vector<String> datos = new Vector<String>();
        Iterator it = vertices.keySet().iterator();
        while (it.hasNext()) {
            int id = (Integer) it.next();
            Vertice v = vertices.get(id);
            String s = "";
            s += id;
            s += ":";
            s += v.getNombre();
            datos.add(s);
        }
        return datos;
    }
    
    /*
     * @pre cierto
     * @post devuelve un vector de strings con formato 'id:nombre:idVerticeOrigen:idVerticeDestino:capacidad:flow:peso' por linea
     */
    public Vector<String> listarAristas() {
        Vector<String> sdatos = new Vector<String>();
        Iterator it = aristas.keySet().iterator();
        while (it.hasNext()) {
            int id = (Integer) it.next();
            Arista a = aristas.get(id);
            String s = "";
            s += id;
            s += ":";
            s += a.getNombre();
            s += ":";
            s += a.getVerticeOrigen();
            s += ":";
            s += a.getVerticeDestino();
            s += ":";
            s += a.getCapacidad();
            s += ":";
            s += a.getFlow();
            s += ":";
            s += a.getPeso();
            sdatos.add(s);
        }
        return sdatos;
    }
    
    /*
     * o es el idVerticeOrigen
     * @pre o existe
     * @post devuelve un vector de Integers con los idVerticeDestino que tiene el idVerticeOrigen = o.
     */
    public Vector<Integer> verticesDestinoAdyacentes(int o) {
        Vector<Integer> datos = new Vector<Integer>();
        Iterator it = aristas.keySet().iterator();
        while (it.hasNext()) {
            int idj = (Integer) it.next();
            Arista a = aristas.get(idj);
            if (o == a.getVerticeOrigen())
                datos.add(a.getVerticeDestino());
        }
        return datos;
    }
    
    /*
     * d es el idVerticeDestino
     * @pre d existe.
     * @post devuelve un vector de Integers con los idVerticeOrigen que tiene el idVerticeDestino = d.
     */
    public Vector<Integer> verticesOrigenAdyacentes(int d) {
        Vector<Integer> datos = new Vector<Integer>();
        Iterator it = aristas.keySet().iterator();
        while (it.hasNext()) {
            int idj = (Integer) it.next();
            Arista a = aristas.get(idj);
            if (d == a.getVerticeDestino())
                datos.add(a.getVerticeOrigen());
        }
        return datos;
    }
    
    /*
     * id es el idArista
     * @pre idArista existe.
     * @post devuelve el flujo de la arista idArista.
     */
    public int flowArista(int id) {
        Arista a = aristas.get(id);
        return a.getFlow();
    }
    
    /*
     * id es el idArista
     * @pre idArista existe.
     * @post devuelve el peso de la arista idArista.
     */
    public int pesoArista(int id) {
        Arista a = aristas.get(id);
        return a.getPeso();
    }
    
    /*
     * id es el idArista
     * @pre idArista existe.
     * @post devuelve la capacidad de la arista idArista.
     */
    public int capacidadArista(int id) {
        Arista a = aristas.get(id);
        return a.getCapacidad();
    }
    
    /*
    * id es el identificador del vertice
    * @pre id del vertice existe
    * @post devuelve el vertice con Id->id
    */
    public Vertice getVertice(int id) {
        return vertices.get(id);
    }
 
    /*
    * id es el identificador de la arista
    * @pre id de la arista existe
    * @post devuelve la arista con Id->id
    */
    public Arista getArista(int id) {
        return aristas.get(id);
    }
    
    /*
    * Devuelve la arista que une dos vertices o null si no existe
    * @pre los vertices id1 i id2 existen
    * @post devuelve la arista que une dos vertices o null si no existe
    */
    public Arista getAristaEntreV(int id1, int id2) {
        int i = 0;
        int aux = -1;
        boolean trobat = false;
        while(!trobat && i < this.getVertice(id1).numeroAristasSalida()) {
            aux = this.getVertice(id1).iesimaAristaSalida(i);
            trobat = (this.getArista(aux).getVerticeDestino() == id2);
            ++i;
        }
        if (trobat)
            return this.getArista(aux);
        return null;
    }
    
    public int numeroAristasEntradaDeUnVertice(int id) {
        Vertice v = vertices.get(id);
        return v.numeroAristasEntrada();
    }
    
    public int numeroAristasSalidaDeUnVertice(int id) {
        Vertice v = vertices.get(id);
        return v.numeroAristasSalida();
    }
    
    // modificadoras
    
    /*
     * En datos hay [idVertice, nombre]
     * @pre idVertice no existe. nombre no existe.
     * @post vertice añadido
     */
    public void altaVertice(Vector<String> datos) {
        int id = Integer.parseInt(datos.get(0));    //*** El identificador esta en la posicion 0
        String n = datos.get(1);                    //*** El nombre esta en la posicion 1
        Vertice v = new Vertice();
        v.setNombre(n);
        vertices.put(id, v);
    }
    
    /*
     * En datos hay [idArista, nombre, idVerticeOrigen, idVerticeDestino, capacidad]
     * @pre idArista no existe. idVerticeOrigen existe. idVerticeDestino existe. nombre no existe. idVerticeOrigen != idVerticeDestino.
     *      no hay ciclos. idVerticeDestino != 1. idVerticeOrigen != 2. capacidad > 0
     * @post arista añadida
     */
    public void altaArista(Vector<String> datos) {
        int id = Integer.parseInt(datos.get(0));    //*** El identificador esta en la posicion 0
        String n = datos.get(1);                    //*** El nombre esta en la posicion 1
        int o = Integer.parseInt(datos.get(2));     //*** El id del vertice origen esta en la posicion 2
        int d = Integer.parseInt(datos.get(3));     //*** El id del vertice destino esta en la posicion 3
        int c = Integer.parseInt(datos.get(4));     //*** La capacidad de la arista esta en la posicion 4
        Arista a = new Arista(n, o, d, c);
        aristas.put(id, a);
        Vertice vo = vertices.get(o);
        vo.anadirAristaSalida(o);
        Vertice vd = vertices.get(d);
        vd.anadirAristaEntrada(d);
    }
    
    /*
     * id es el idVertice
     * @pre idVertice existe. No hay aristas conectadas al vertice. idVertice != 1 && idVertice != 2
     * @post vertice eliminado
     */
    public void eliminarVertice(int id) {
        vertices.remove(id);
    }
    
    /*
     * id es el idArista
     * @pre idArista existe.
     * @post arista eliminada
     */
    public void eliminarArista(int id) {
        Arista a = aristas.get(id);
        int o = a.getVerticeOrigen();
        int d = a.getVerticeDestino();
        Vertice vo = vertices.get(o);
        vo.eliminarAristaSalida(id);
        Vertice vd = vertices.get(d);
        vd.eliminarAristaEntrada(id);
        aristas.remove(id);
    }
    
    /*
     * En datos hay [idVertice, nombreNuevo]
     * @pre idVertice existe. nombreOriginal != nombreNuevo. nombreNuevo no existe. idVertice != 1 && idVertice != 2
     * @post nombre modificado
     */
    public void modificarNombreDeVertice(Vector<String> datos) {
        int idv = Integer.parseInt(datos.get(0));   //*** El identificador esta en la posicion 0
        String nom = (String) datos.get(1);         //*** El nombre esta en la posicion 1
        Vertice v = vertices.get(idv);
        v.setNombre(nom);
        vertices.put(idv, v);
    }
    
    /*
     * En datos hay [idArista, nombreNuevo]
     * @pre id existe. nombreOriginal != nombreNuevo. nombreNuevo no existe.
     * @post nombre modificado
     */
    public void modificarNombreDeArista(Vector<String> datos) {
        int ida = Integer.parseInt(datos.get(0));   //*** El identificador esta en la posicion 0
        String nom = (String) datos.get(1);         //*** El nombre esta en la posicion 1
        Arista a = aristas.get(ida);
        a.setNombre(nom);
        aristas.put(ida, a);
    }
    
    /*
     * En datos hay [idArista, idVerticeOrigen]
     * @pre id existe. idVerticeOrigen existe. idVerticeOrigen != idVerticeDestino. idVerticeOrigenNuevo != idVerticeOrigenOriginal.
     *      idVerticeOrigen != 2. no hay ciclo
     * @post Vertice origen modificado
     */
    public void modificarVerticeOrigenDeArista(Vector<String> datos) {
        int ida = Integer.parseInt(datos.get(0));   //*** El identificador esta en la posicion 0
        int oNuevo = Integer.parseInt(datos.get(1));//*** El vertice origen esta en la posicion 1
        Arista a = aristas.get(ida);                //*** Obtener arista con id = ida
        int oViejo = a.getVerticeOrigen();          //*** Obtener id viejo del vertice
        Vertice voViejo = vertices.get(oViejo);     //*** Obtener vertice viejo
        voViejo.eliminarAristaSalida(ida);          //*** Eliminar vertice viejo
        Vertice vNuevo = vertices.get(oNuevo);      //*** Obtener vertice nuevo
        vNuevo.anadirAristaSalida(ida);             //*** anadir id del vertice nuevo
        a.setVerticeOrigen(oNuevo);                 //*** cambiar el id viejo por el nuevo
        aristas.put(ida, a);                        //*** Modificar vertice origen
    }
    
    /*
     * En datos hay [idArista, idVerticeDestino]
     * @pre id existe. idVerticeDestino existe. idVerticeOrigen != idVerticeDestino. idVerticeDestinoNuevo != idVerticeDestinoOriginal.
     *      idVerticeDestino != 1. no hay ciclo
     * @post Vertice destino modificado
     */
    public void modificarVerticeDestinoDeArista(Vector<String> datos) {
        int ida = Integer.parseInt(datos.get(0));   //*** El identificador esta en la posicion 0
        int dNuevo = Integer.parseInt(datos.get(1));//*** El id del vertice destino esta en la posicion 1
        Arista a = aristas.get(ida);                //*** Obtener arista con id = ida
        int dViejo = a.getVerticeOrigen();          //*** Obtener id viejo del vertice
        Vertice voViejo = vertices.get(dViejo);     //*** Obtener vertice viejo
        voViejo.eliminarAristaEntrada(ida);         //*** Eliminar vertice viejo
        Vertice vNuevo = vertices.get(dNuevo);      //*** Obtener vertice nuevo
        vNuevo.anadirAristaEntrada(ida);            //*** anadir id del vertice nuevo
        a.setVerticeDestino(dNuevo);                //*** cambiar el id viejo por el nuevo
        aristas.put(ida, a);                        //*** Modificar vertice destino
    }
    
    /*
     * En datos hay [idArista, flow]
     * @pre idArista existe.
     * @post modifica el flujo de la arista idArista.
     */
    public void modificarFlowArista(Vector<String> datos) {
        int id = Integer.parseInt(datos.get(0));    //*** El identificador esta en la posicion 0
        int flow = Integer.parseInt(datos.get(1));  //*** El flow esta en la posicion 1
        Arista a = aristas.get(id);
        a.setFlow(flow);
        aristas.put(id, a);
    }
    
    /*
     * En datos hay [idArista, capacidad]
     * @pre idArista existe. capacidad >= 0
     * @post modifica la capacidad de la arista idArista.
     */
    public void modificarCapacidadArista(Vector<String> datos) {
        int id = Integer.parseInt(datos.get(0));    //*** El identificador esta en la posicion 0
        int capacidad = Integer.parseInt(datos.get(1));//*** La capacidad esta en la posicion 1
        Arista a = aristas.get(id);
        a.setCapacidad(capacidad);
        aristas.put(id, a);
    }
    
    /*
     * En datos hay [idArista, peso]
     * @pre idArista existe. peso >= 0
     * @post modifica el peso de la arista idArista.
     */
    public void modificarPesoArista(Vector<String> datos) {
        int id = Integer.parseInt(datos.get(0));    //*** El identificador esta en la posicion 0
        int peso = Integer.parseInt(datos.get(1));  //*** La peso esta en la posicion 1
        Arista a = aristas.get(id);
        a.setPeso(peso);
        aristas.put(id, a);
    }
    
//Aquestes funcions substituien la cadena de Strings que va dir la Rosa, dijous ho parlem amb l'Horacio per acabar de treure dubtes
    
    /*
    * Donem d’alta un vèrtex
    * @pre id no existeix i nom no existeix
    * @post afegim un vèrtex amb Id->id i Nom->nom
    */
    public void altaVertice(int id, String nom) {
        Vertice v = new Vertice(nom);
        vertices.put(id, v);
    }
    
    /*
    * Donem d’alta una arista
    * @pre id no existeix, nom no existeix, origen existeix al graf i desti existeix al graf
    * @post es crea una nova arista amb id->id, nom->nom, origen->origen, desti-> desti i  * capacitat->capacitat
    */
    public void altaArista(int id, String nom, int origen, int desti, int capacitat) {
        Arista a = new Arista(nom, origen, desti, capacitat);
        aristas.put(id, a);
        Vertice vo = vertices.get(origen);
        vo.anadirAristaSalida(origen);
        Vertice vd = vertices.get(desti);
        vd.anadirAristaEntrada(desti);
    }
}
