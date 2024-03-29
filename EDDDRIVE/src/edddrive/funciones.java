/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive;

/**
 *
 * @author David Ventura
 */
import edddrive.EDDDRIVE.*;
import edddrive.EDDDRIVE;
import edddrive.classes.*;
import edddrive.estructuras.arbolAVL.nodo;
import edddrive.estructuras.*;
import edddrive.formularios.*;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class funciones {
    
    public enum reportes {
        tablaHash, grafo, matrizAdyacente, arbolAVL, pila;
    }
    
    public usuario usuarioActual;
    public carpeta carpetaActual;
    
    public String rutaReportes;
    public String rutaArchivos;
    
    public tablaHash usuarios_errores;
    
    public funciones() {
        this.rutaReportes ="C:/arte/Report/";
        this.rutaArchivos ="C:/arte/Files/";
        
        this.usuarios_errores = new tablaHash();
    }
    
    public void cargaMasivaUsuarios() {
        
        edddrive.EDDDRIVE.bitacora.insertarDato(this.usuarioActual.getUsuario_() + " ha creado una carga masiva de usuarios.");
        
        JOptionPane.showMessageDialog(null, "Selecciona el archivo de carga masiva usuarios.");
        
        JFileChooser filechooser = new JFileChooser ();
        
        filechooser.showOpenDialog(filechooser);
        
        try {
            // abre el archivo y extrae la información necesaria
            String ruta = filechooser.getSelectedFile().getAbsolutePath();
            File archivo = new File (ruta) ;
            FileReader  fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader (fr);
            
            String linea = br.readLine() ;
            
            // recorre cada fila del archivo
            while ( (  linea = br.readLine() ) != null ) {
                
                String datos[] = linea.split(",");
                this.RegistrarUsuario_(datos[0], datos[1]);
            }
            
            JOptionPane.showMessageDialog(null, "La carga ha sido realizaa correctamente", "CARGA", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "No se ha podido encontrar el archivo.");}
        
        
    }
    
    public void cargaMasivaArchivos () {
        edddrive.EDDDRIVE.bitacora.insertarDato(this.usuarioActual.getUsuario_() + " ha creado una carga masiva de archivos.");
        
        JOptionPane.showMessageDialog(null, "Selecciona el archivo de carga masiva usuarios.");
        
        JFileChooser filechooser = new JFileChooser ();
        
        filechooser.showOpenDialog(filechooser);
        
        try {
            // abre el archivo y extrae la información necesaria
            String ruta = filechooser.getSelectedFile().getAbsolutePath();
            File archivo = new File (ruta) ;
            FileReader  fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader (fr);
            
            String linea = br.readLine() ;
            
            // recorre cada fila del archivo
            while ( (  linea = br.readLine() ) != null ) {
                
                String datos[] = linea.split(",");
                this.carpetaActual.crearArchivo(datos[0], datos[1]);
            }
            
            JOptionPane.showMessageDialog(null, "La carga ha sido realizaa correctamente", "CARGA", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "No se ha podido encontrar el archivo.");}
        
        
        
        this.cargarCarpeta();
        
    }
    
    
    public boolean Ingresar (String usuario , String contrasena) {
        
        
        contrasena = EDDDRIVE.usuarios.sha256(contrasena);
        
        usuario us = EDDDRIVE.usuarios.buscar(usuario);
        
        if (us == null) {
            // no existe el usuario
            JOptionPane.showMessageDialog(null, "El usuario no existe.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else {
            // verificar la contrasena
            if (us.getContrasena_().equalsIgnoreCase(contrasena)) {
                // con iguales
                JOptionPane.showMessageDialog(null, "Acceso exitoso.", ":-)", JOptionPane.INFORMATION_MESSAGE);
                this.usuarioActual  = us;
                this.carpetaActual = us.directorio;
                
                this.cargarCarpeta(); //edddrive.EDDDRIVE.func
                
                return true;
            }else {
                // no son iguales
                JOptionPane.showMessageDialog(null, "La contraseña no coincide.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        return false;
    }
    
    public void RegistrarUsuario (String usuario , String contrasena) {
        
        // valida la contraseña
        if (contrasena.length() < 8) {
            // contrasena muy corta
            JOptionPane.showMessageDialog(null, "Tu contraseña debe poseer al menos 8 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return ;
        }
        
        // ingresa el usuario
        edddrive.EDDDRIVE.usuarios.insertar(usuario, contrasena, true);
        edddrive.EDDDRIVE.bitacora.insertarDato( "Se ha registrado un nuevo usuario: " + usuario);
        
        JOptionPane.showMessageDialog(null, "Registro exitoso, regresa al formualrio de ingreso para iniciar sesión.", ":-)", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void RegistrarUsuario_ (String usuario, String contrasena) {
        
        // valida la contraseña
        if (contrasena.length() < 8) {
            // contrasena muy corta
            
            this.usuarios_errores.insertar(usuario, contrasena, true);
            return ;
        }
        
        // ingresa el usuario
        edddrive.EDDDRIVE.usuarios.insertar(usuario, contrasena, true);
        
    }
    
    public void setUsuario  (usuario us){
        this.usuarioActual = us;
    }
    
    public void cargarCarpeta () {
        // limpia el panel 
        JScrollPane jsp = edddrive.EDDDRIVE.fmrInicio_.getPanel(0);
        jsp.removeAll();
        
        JScrollPane jsp1 = edddrive.EDDDRIVE.fmrInicio_.getPanel(1);
        jsp.removeAll();
        
        edddrive.EDDDRIVE.fmrInicio_.getPanel(0).removeAll();
        edddrive.EDDDRIVE.fmrInicio_.getPanel(1).removeAll();
        
        
        // inserta el nuevo panel
        int [] nn = {0,0};
        this.cargaCarpeta_(jsp, this.carpetaActual.archivos.raiz, nn);
        
        this.cargaCarpeta__(jsp1, this.carpetaActual.carpetas);
        jsp1.repaint();
        jsp.repaint();
        
        edddrive.EDDDRIVE.fmrInicio_.setRuta(this.carpetaActual.getRuta(this.carpetaActual, ""));
    }
    
    public int[] cargaCarpeta_ (JScrollPane jsp , arbolAVL.nodo nd, int [] xy) {
        
        if (nd ==null) {
            // no se puede añadir nada más
            
        }else {
            // verificar si tiene hijos
            if (nd.izquierda != null) {
                xy = this.cargaCarpeta_(jsp, nd.izquierda, xy);
                
                xy[0] += 120;

                if (xy[0] >= 900) {
                    xy[0] = 0 ; xy[1] += 60;
                }
                
            }
            
            archivo ar = (archivo) nd.contenido;
            ar.setLocation(xy[0],xy[1]);
            jsp.add((archivo) nd.contenido);
            
            
            
            if (nd.derecha != null) {
                
                xy[0] += 120;

                if (xy[0] >= 900) {
                    xy[0] = 0 ; xy[1] += 60;
                }
                xy = this.cargaCarpeta_(jsp, nd.derecha, xy);   
            }
        }
        
        return xy;
        
    }
    
    
    public void cargaCarpeta__ (JScrollPane jsp, listaDobleEnlazada lde) {
        
        // crear coordenadas iniciales
        int x = 0 ; int y = 0;
        
        listaDobleEnlazada.nodo tmp  = lde.inicio ;
        
        // recorre hasta en contrar el nodo nulo o final
        while  (tmp != null) {
            // insetar en el jscroll pane
            tmp.dato_.setLocation(x, y);
            jsp.add(tmp.dato_);
            x += 120;
            
            if (x > 900) {
                x = 0 ; y += 60;
            }
            
            tmp = tmp.siguiente;
        }
        
        
        
    }
    
    public void crearImagen (reportes rp) {
        
        edddrive.EDDDRIVE.bitacora.insertarDato( this.usuarioActual.getUsuario_() + " ha generado los reportes de estructuras.");
        
        
        this.carpetaActual.archivos.CrearReporte(1);
                
        try {
            String comando = "dot " + this.rutaReportes + "vista_arbol.txt -o " + this.rutaReportes + "vista_arbol.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null , "No se pudo crear la imagen árbol");
        }
        
        this.carpetaActual.crearGrafico();
                
        try {
            String comando = "dot " + this.rutaReportes + "vista_grafo.txt -o " + this.rutaReportes + "vista_grafo.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null , "No se pudo crear la imagen grafo");
        }
        
        
        edddrive.EDDDRIVE.bitacora.crearReporte();
                
        try {
            String comando = "dot " + this.rutaReportes + "vista_pila.txt -o " + this.rutaReportes + "vista_pila.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null , "No se pudo crear la imagen tabla hash");
        }
        
        
        edddrive.EDDDRIVE.usuarios.crearReporte("vista_hash.txt");
                
        try {
            String comando = "dot " + this.rutaReportes + "vista_hash.txt -o " + this.rutaReportes + "vista_hash.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null , "No se pudo crear la imagen tabla hash");
        }
        
        edddrive.EDDDRIVE.func.usuarios_errores.crearReporte("vista_hash2.txt");
        try {
            String comando = "dot " + this.rutaReportes + "vista_hash2.txt -o " + this.rutaReportes + "vista_hash2.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null , "No se pudo crear la imagen tabla de usuarios no ingresados.");
        }
        
        edddrive.EDDDRIVE.func.carpetaActual.crearGrafico_();
        try {
            String comando = "dot " + this.rutaReportes + "vista_matriz.txt -o " + this.rutaReportes + "vista_matriz.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null , "No se pudo crear la imagen tabla de usuarios no ingresados.");
        }
        
    }
    
    public void descargarArchivo (archivo ar) {
        //  descarga el arhchivo en la computadora
        
        edddrive.EDDDRIVE.bitacora.insertarDato( this.usuarioActual.getUsuario_() + " ha descargado el archivo " + ar.nombre_);
        
        
        //solicita la b´squeda de la carpeta en la que sedesa almacenar el archivo
        String folder  = "C:/";
        String archivo = this.rutaArchivos + ar.nombre_;
        // CREA EL ARCHIVO DE ESCRITURA
        FileWriter file;
        PrintWriter pw ;
        
        try {
        
            file = new FileWriter (archivo) ;
            pw = new PrintWriter (file);
            
            pw.print(ar.contenido_);
            
            file.close();
            
            JOptionPane.showMessageDialog(null, "Descarga exitosa.");
            
            
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo.");}
        
        
        
        
        
        
    }
    
    public void goAtras () {
        if (this.carpetaActual.padre != null) {
            this.carpetaActual = this.carpetaActual.padre;
        }
        
        this.cargarCarpeta();
    }
    
    public void compartir (archivo ar) {
        
        Object[] obj = edddrive.EDDDRIVE.usuarios.getListaUsuarios();
        
        Object u = JOptionPane.showInputDialog(null, "Selecciona un usuario", "COMPARTIR", JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
        
        String nombre = (String) u;
        edddrive.EDDDRIVE.usuarios.buscar(nombre).directorio.crearArchivo(ar.nombre_, ar.contenido_);
        
        edddrive.EDDDRIVE.bitacora.insertarDato( this.usuarioActual.getUsuario_() + " ha compartido el archivo " + ar.nombre_ + " con el usuario " + (String) u);
        
    }
    
    
    
    
}
