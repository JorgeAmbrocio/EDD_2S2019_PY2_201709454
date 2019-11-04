/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import edddrive.classes.archivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author David Ventura
 */

public class carpeta extends JButton implements ActionListener {
    public String nombre;
    public carpeta padre;
    public arbolAVL archivos;
    public listaDobleEnlazada carpetas;
    
    public carpeta () {
        this.archivos = new arbolAVL();
        this.carpetas  = new listaDobleEnlazada();
        
        this.setText(nombre);
        this.setSize(120,60);
    }
    
    public void crearCarpeta (String nombre_) {
        carpeta tmp = new carpeta();
        tmp.nombre = nombre_;
        tmp.setText(nombre_);
        tmp.padre = this;
        this.carpetas.insertarFinal(nombre_, tmp);
    }
    
    public void crearArchivo (String nombre,  String contenido) {
        archivo tmp = new archivo(nombre,  contenido);
        this.archivos.insertar(nombre , tmp);
    }
    
    public void modificarArchivo (String nombre,  String contenido) {
        
    }
    
    
    public void eliminarArchivo (String nombre) {
        this.archivos.eliminar(nombre);
    }
    
    public void eliminarCarpeta(String nombre){
        this.carpetas.eliminarDato(nombre);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
