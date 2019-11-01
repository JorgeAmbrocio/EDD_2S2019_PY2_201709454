/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive;
import edddrive.estructuras.*;
import edddrive.formularios.*;
import edddrive.classes.*;
import javax.swing.JOptionPane;


public class EDDDRIVE {

    /**
     * @param args the command line arguments
     */
    
    public static tablaHash usuarios ;
    public static fmrIngresar fmrIngresar_;
    public static fmrRegistro fmrRegistro_;
    
    public static funciones func ;
    public static procedimientos proc;
    public static void main(String[] args)  {
        // TODO code application logic here
        
        // iniciar variable que lleva la lógica de los procedimientos básicos
        func = new funciones();
        proc = new procedimientos();
        
        // iniciar los formularios
        fmrIngresar_ = new fmrIngresar ();
        fmrRegistro_= new fmrRegistro();
        
        
        // variables que contienen la raíz de los objetos necesarios
        usuarios = new tablaHash ();
        usuarios.insertar("admin", "admin", true);
        
        
        
        
        
    }
    
    
    
    
}
