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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author David Ventura
 */

public class carpeta extends JButton implements ActionListener {
    public String nombre;
    public carpeta padre;
    public arbolAVL archivos;
    public listaDobleEnlazada carpetas;
    
    
    public JPopupMenu pum;
    public JMenuItem jmiEliminar, jmiModificar;
    
    public carpeta () {
        this.archivos = new arbolAVL();
        this.carpetas  = new listaDobleEnlazada();
        
        this.setText(nombre);
        this.setSize(120,60);
        
        this.addActionListener(this);
        
        this.pum = new JPopupMenu();
        
        this.jmiEliminar  = new JMenuItem ();
        this.jmiEliminar.setText("Eliminar");
        this.jmiEliminar.addActionListener(this);
        
        this.jmiModificar  = new JMenuItem ();
        this.jmiModificar.setText("Modificar");
        this.jmiModificar.addActionListener(this);
        
        // anadir las opciones al pop upmnu
        this.pum.add(this.jmiEliminar);
        this.pum.add(this.jmiModificar);
        
        this.setComponentPopupMenu(pum);
        
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
    
    
    public void eliminarArchivo (String nombre) {
        this.archivos.eliminar(nombre);
    }
    
    public void eliminarCarpeta(String nombre){
        this.carpetas.eliminarDato(nombre);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == this) {
            edddrive.EDDDRIVE.func.carpetaActual = this;
            edddrive.EDDDRIVE.func.cargarCarpeta();
        }else if (ae.getSource() == this.jmiEliminar) {
            edddrive.EDDDRIVE.func.carpetaActual.eliminarCarpeta(this.nombre);
            
        }else if (ae.getSource() == this.jmiModificar) {
            this.nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la carpeta");
        }
        
        edddrive.EDDDRIVE.func.cargarCarpeta();
        
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
    
    
    public void crearGrafico_ () {
        
        String conte = "digraph {\n";
        
        conte += "node[shape=box] ; \n";
        
        String rank  = "{ rank = same; ";
        rank += this.getContenido_rank(this);
        rank = rank.substring(0 , rank.length() -2 );
        rank += "}; \n";
        String cont = this.getContenido_(this, null);
        
        
        
        cont += rank;
        
        
        conte += cont;
        conte += "\n}";
        
        
        
        FileWriter file ;
        PrintWriter pw ;
        
        try  {
            file = new FileWriter ("C:/arte/Report/vista_matriz.txt") ;
            pw = new PrintWriter (file);
            
            pw.print (conte);
            
            pw.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido crear el reporte de matriz");
        }
        
        
    }
    
    public String getContenido_ (carpeta folder, carpeta hermano) {
        String cont = "";
        
            
        
        
        // crear nodo cabecera x
        cont += this.getRuta_(folder, "") + "x[label=\""  + folder.nombre + "\"];\n";
        
        // crear nodo cabecera y
        cont += this.getRuta_(folder, "") + "y[label=\"" + folder.nombre + "\"];\n";
        
        // crear nodo intersección
        if (folder.padre != null) {
            cont += this.getRuta_(folder, "") + "[label=\"" + folder.nombre + "\"];\n";
        }
        
        // crear conexión de la intersección en la carpeta actual en Y
        if (hermano == null) {
            // si no tiene hermano, conecta el nodo a la raiz
            if (folder.padre == null) {
                //cont += "_y -> " + this.getRuta_(folder, "") + ";\n";
            }else {
                cont += this.getRuta_(folder.padre, "") + "y -> " + this.getRuta_(folder, "") + ";\n" ;
                
                cont += this.getRuta_(folder.padre, "") + "y -> " + this.getRuta_(folder, "") + "y;\n" ;
                cont += this.getRuta_(folder.padre, "") + "x -> " + this.getRuta_(folder, "") + "x;\n" ;
            }
        }else {
            
            if (folder.padre != null) {
                cont += this.getRuta_(hermano, "") + " -> " + this.getRuta_(folder, "") + ";\n";
            }
            
            cont += this.getRuta_(hermano, "") + "x -> " + this.getRuta_(folder, "") + "x;\n";
            cont += this.getRuta_(hermano, "") + "y -> " + this.getRuta_(folder, "") + "y;\n";
        }
        
        //crear conexion de la interseccion en la carpeta actual en X
        if (folder.padre != null) {
            cont += this.getRuta_(folder, "") + "x -> " + this.getRuta_(folder, "") + ";\n" ;
        
        }
        // crear conexxión del eje y con el eje y
        String apunta ;
        
        
        //cont +=  " -> " + this.getRuta_(folder, "") + ";\n";        
        
        
        // crear nodos hijos
        listaDobleEnlazada.nodo nd = folder.carpetas.inicio;
        
        String rank = "{ rank = same ; " + this.getRuta_(folder, "") + "y, ";
        
        while (nd != null) {
            
            carpeta hermano_ = null;
            
            if (nd != folder.carpetas.inicio) {
                hermano_ = nd.anterior.dato_;
                
            }else {
                
            }
            
            // crear de manera recursiva los encabezados e intersecciones de las carpetas hijo
            cont += this.getContenido_(nd.dato_, hermano_);
            
            cont +=    "\n";
            
            // crear en un mismo rank todos los hijos de la carpeta padre
            rank += this.getRuta_(nd.dato_, "") +  ", ";
            
            nd  = nd.siguiente;
        }
        
        rank = rank.substring(0, rank.length()-2);
        rank += "} \n";
        
        cont += rank;
        
        return cont;
    }
    
    public String getContenido_rank (carpeta folder) {
        
        String rank = this.getRuta_(folder, "") + "x, ";
        
        // crear nodos hijos
        listaDobleEnlazada.nodo nd = folder.carpetas.inicio;
        
        
        while (nd != null) {
            
            rank += this.getContenido_rank(nd.dato_);
            
            nd  = nd.siguiente;
        }
        
        
        return rank;
        
    }
    
    public String getRuta  (carpeta folder, String ruta) {
        
        if(ruta.equals("")) {
            ruta =  folder.nombre  ;
        }else {
            ruta =  folder.nombre + "\\"  + ruta ;
        }
        
        
        if (folder.padre != null) {
            ruta = this.getRuta(folder.padre, ruta);
        }else {
            ruta = "\\" + ruta ;
        }
        
        return ruta;
        
    }
    
    public String getRuta_ (carpeta folder, String ruta) {
        
        String nombre_ ;
        
        if (folder.nombre.equals("/")) {
            nombre_ = "";
        
        }else {
            nombre_ = folder.nombre;
        
        }
        
        
        if(ruta.equals("")) {
            ruta =  nombre_  ;
        }else {
            ruta =  nombre_ + "_"  + ruta ;
        }
        
        
        if (folder.padre != null) {
            ruta = this.getRuta_(folder.padre, ruta);
        }else {
            ruta = "_" + ruta ;
        }
        
        return ruta;
        
    }
    
}
