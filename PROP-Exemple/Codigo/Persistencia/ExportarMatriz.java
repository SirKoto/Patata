package Persistencia;

import Persistencia.com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @autor Víctor
 * @dataCreación 26/10/2014
 * @dataÚltimaModificación 5/11/2014
 */


public class ExportarMatriz {

    //Metodos

    /**
     * @pre path es valido y la matriz tiene el contenido a exportar
     * @post La matriz se ha exportado al archivo "path". Si la matriz se ha exportado correctamente devuelve true.
     */
    public boolean exportar(String path, String[][] matriz) throws IOException {
        boolean guardado = false;
        CsvWriter writercsv;
        File fichero = new File(path);
        FileWriter fwriter = new FileWriter(fichero);
        // Creamos la clase que nos permite escribir en el fichero CSV.
        writercsv = new CsvWriter(fwriter, ":".charAt(0));

        writercsv.write(Integer.toString(matriz.length));
        writercsv.endRecord();

        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz[i].length; ++j) {
                writercsv.write(matriz[i][j]);
            }
            writercsv.endRecord();
        }
        writercsv.close();
        guardado = true;
        return guardado;
    }
}
