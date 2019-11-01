/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive;
import edddrive.estructuras.*;
import edddrive.estructuras.matrizAdyacente.nodo;
import edddrive.classes.*;
import javax.swing.JOptionPane;


public class EDDDRIVE {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args)  {
        // TODO code application logic here
        
        archivo ar = new archivo ("archivo1", ".exe", "este es el contenido del arhcio");
        System.out.println(ar.crearEstampaTiempo());
        
    }
    
    
    
    
}
