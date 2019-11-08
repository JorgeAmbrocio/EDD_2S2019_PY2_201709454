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
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class funciones {
    
    public enum reportes {
        tablaHash, grafo, matrizAdyacente, arbolAVL, pila;
    }
    
    public usuario usuarioActual;
    public carpeta carpetaActual;
    
    public String rutaReportes;
    
    public funciones() {
        this.rutaReportes ="C:/arte/Report/";
    }
    
    
    public void cargaMasivaUsuarios() {
        
        
        
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
        
        JOptionPane.showMessageDialog(null, "Registro exitoso, regresa al formualrio de ingreso para iniciar sesión.", ":-)", JOptionPane.INFORMATION_MESSAGE);
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
        
        // inserta el nuevo panel
        int [] nn = {0,0};
        this.cargaCarpeta_(jsp, this.usuarioActual.directorio.archivos.raiz, nn);
        
        this.cargaCarpeta__(jsp1, this.carpetaActual.carpetas);
        jsp1.repaint();
        jsp.repaint();
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
        
        
        if (null != rp) switch (rp) {
            case arbolAVL:
                
                this.carpetaActual.archivos.CrearReporte(1);
                
                try {
                    String comando = "dot " + this.rutaReportes + "vista_arbol.txt -o " + this.rutaReportes + "vista_arbol.png -Tpng";
                    Runtime.getRuntime().exec(comando);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null , "No se pudo crear la imagen árbol");
                }
                
                break;
            case grafo:
                
                this.carpetaActual.crearGrafico();
                
                try {
                    String comando = "dot " + this.rutaReportes + "vista_grafo.txt -o " + this.rutaReportes + "vista_grafo.png -Tpng";
                    Runtime.getRuntime().exec(comando);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null , "No se pudo crear la imagen grafo");
                }
                
                break;
            case matrizAdyacente:
                
                break;
            case pila:
                
                break;
            case tablaHash:
                
                edddrive.EDDDRIVE.usuarios.crearReporte();
                
                try {
                    String comando = "dot " + this.rutaReportes + "vista_hash.txt -o " + this.rutaReportes + "vista_hash.png -Tpng";
                    Runtime.getRuntime().exec(comando);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null , "No se pudo crear la imagen tabla hash");
                }
                
                break;
            default:
                break;
        }
        
        
        
    }
    
    
    
}
