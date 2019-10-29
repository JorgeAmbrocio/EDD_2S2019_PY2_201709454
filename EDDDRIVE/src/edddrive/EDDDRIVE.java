/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive;
import edddrive.estructuras.*;
import edddrive.estructuras.matrizAdyacente.nodo;
import edddrive.classes.usuario;
import javax.swing.JOptionPane;


public class EDDDRIVE {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args)  {
        // TODO code application logic here
        
        matrizAdyacente mt = new matrizAdyacente();
        
        
        
        
        System.out.println(mt.existeX(5));
        System.out.println(mt.existeY(5));
        
        mt.crearX(5);
        mt.crearY(5);
      
        System.out.println(mt.existeX(5));
        System.out.println(mt.existeY(5));
        
        mt.insertar("5_5", 5, 5);
        mt.insertar("0_3", 0, 3);
        mt.insertar("0_0", 0, 0);
        
        
    }
    
    
    
    
}
