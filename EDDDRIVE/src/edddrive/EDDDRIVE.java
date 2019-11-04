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
    public static fmrInicio fmrInicio_;
    public static fmrVisor fmrVisor_ ;
    
    public static funciones func ;
    public static procedimientos proc;
    public static void main(String[] args)  {
        // TODO code application logic here
        
        
        
        
        // iniciar variable que lleva la lógica de los procedimientos básicos
        func = new funciones();
        proc = new procedimientos();
        
        // iniciar los formularios
        fmrIngresar_ = new fmrIngresar (); fmrIngresar_.setLocationRelativeTo(null);
        fmrRegistro_= new fmrRegistro(); fmrRegistro_.setLocationRelativeTo(null);
        fmrInicio_ = new fmrInicio(); fmrInicio_.setLocationRelativeTo(null);
        fmrVisor_ = new fmrVisor() ;
        
        // variables que contienen la raíz de los objetos necesarios
        usuarios = new tablaHash ();
        usuarios.insertar("admin", "admin", true);
        
        usuario us = usuarios.buscar("admin");
        us.directorio.crearArchivo("a.exe", "contenido");
        us.directorio.crearArchivo("b.exe", "contenido");
        us.directorio.crearArchivo("c.exe", "contenido");
        us.directorio.crearArchivo("d.exe", "contenido");
        us.directorio.crearArchivo("e.exe", "contenido");
        us.directorio.crearArchivo("f.exe", "contenido");
        us.directorio.crearArchivo("g.exe", "contenido");
        us.directorio.crearArchivo("h.exe", "contenido");
        us.directorio.crearArchivo("i.exe", "contenido");
        us.directorio.crearArchivo("j.exe", "contenido");
        us.directorio.crearArchivo("k.exe", "contenido");
        us.directorio.crearArchivo("l.exe", "contenido");
        us.directorio.crearArchivo("m.exe", "contenido");
        us.directorio.crearArchivo("o.exe", "contenido");
        us.directorio.crearArchivo("p.exe", "contenido");
        us.directorio.crearArchivo("q.exe", "contenido");
        us.directorio.crearArchivo("r.exe", "contenido");
        us.directorio.crearArchivo("s.exe", "contenido");
        us.directorio.crearArchivo("t.exe", "contenido");
        us.directorio.crearArchivo("u.exe", "contenido");
        
        us.directorio.crearCarpeta("a");
        us.directorio.crearCarpeta("b");
        us.directorio.crearCarpeta("c");
        us.directorio.crearCarpeta("d");
        us.directorio.crearCarpeta("e");
        us.directorio.crearCarpeta("f");
        us.directorio.crearCarpeta("g");
        us.directorio.crearCarpeta("h");
        us.directorio.crearCarpeta("i");
        us.directorio.crearCarpeta("j");
        us.directorio.crearCarpeta("k");
        us.directorio.crearCarpeta("l");
        us.directorio.crearCarpeta("m");
        us.directorio.crearCarpeta("n");
        us.directorio.crearCarpeta("o");
        us.directorio.crearCarpeta("p");
        
        
        fmrIngresar_.show();
        
        
        
        
    }
    
    public void cargarCarpeta () {
        
    }
    
    
    
    
}
