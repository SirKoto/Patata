
import Presentacio.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JoanVila
 */
public class Smiley {
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CtrlPresentacio c = new CtrlPresentacio();
                    c.iniciaPrograma();
                } catch (IOException ex) {
                    Logger.getLogger(Smiley.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
