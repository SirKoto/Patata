package Persistencia;

import Persistencia.com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @autor Víctor
 * @dataCreación 26/10/2014
 * @dataÚltimaModificación 5/11/2014
 */

public class ImportarMatriz {

    //Metodos

    /**
     * @pre path es valido
     * @post Devuelve una matriz con los datos el archivo
     *      Cada linea del archivo es una fila de la matriz
     *      Cada parametro del archivo es una columna de la matriz
     */
    @SuppressWarnings("empty-statement")
    public String[][] importar(String path) throws FileNotFoundException, IOException {
        CsvReader cvsReader;
        File fLectura = new File(path);
        if(fLectura.exists()){
            FileReader freader = new FileReader(fLectura);
            cvsReader = new CsvReader(freader, ":".charAt(0));
            int col;
            int fil = 0;
            cvsReader.readRecord();
            int filasTotales = Integer.parseInt(cvsReader.get(0));
            String[][] matriz = new String[filasTotales][];
            while (cvsReader.readRecord() && fil < filasTotales) {
                col = cvsReader.getColumnCount();
                matriz[fil] = new String[col];
                for (int i = 0; i < col; i++) {
                    matriz[fil][i] = cvsReader.get(i);
                }
                fil += 1;
            }
            return matriz;
        }
        else {
            System.out.println("En el path que has introduccido no existe este archivo.");
            return null;
        }
    }
    
    /**
     * @pre cierto
     * @post Devuelve cierto si el fichero existe en el path y falso en caso contrario
     */
    public boolean existeFichero(String path){
        File fLectura = new File(path);
        boolean existe = fLectura.exists();
        return existe;
    }
}
