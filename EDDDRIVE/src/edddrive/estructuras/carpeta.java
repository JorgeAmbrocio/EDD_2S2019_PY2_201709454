/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import edddrive.classes.archivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
    
    
    public boolean buscarCarpeta (String nombre_) {
        
        carpeta cp = carpetas.getDato(nombre);
        
        return cp != null;
        
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
    
    
    public void crearGrafico () {
        String cont = "";
        
        
        cont += "digraph {\n";
        
        cont += this.getContenido(this);
        
        cont += "\n}";
        
        FileWriter archivo;
        PrintWriter pw;
        
        try {
            archivo =new FileWriter ("C:/arte/Report/vista_grafo.txt") ;
            pw =new PrintWriter (archivo);
            pw.print(cont);
            archivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte Carpeta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    public String getContenido (carpeta folder) {
        
        String cont = "";
        
        // verifica que el folder no sea nulo
        
        if  (folder != null) {
            
            // verifica que el flder tenga más folders
            if (folder.carpetas != null) {
                
                // recorre todas las carpetas del folder
                edddrive.estructuras.listaDobleEnlazada.nodo nd = folder.carpetas.inicio;
                
                while (nd !=null) {
                    
                    cont += "\"" +folder.nombre +  "\" -> \"" + nd.dato_.nombre  + "\"\n";
                    
                    cont += this.getContenido(nd.dato_);
                    
                    nd = nd.siguiente;
                }
            }
        }else {
            
        }
        
        
        return cont;
    }
    
    
}
