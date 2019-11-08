/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author David Ventura
 */
public class pila {
    
    public class nodo {
        nodo arriba ;
        nodo abajo;
        
        String dato;
        String marcaTiempo ;
        
        public nodo (String dato_) {
            this.dato = dato_;
            this.marcaTiempo = this.crearEstampaTiempo();
        }
        
        
        public String crearEstampaTiempo () {
            java.util.Date fecha = new java.util.Date();

            String marca_tiempo = "";

            String dia, mes , hora, minuto, segundo ;

            if (fecha.getDate() < 10) { dia = "0" + fecha.getDate();} else {dia = "" + fecha.getDate()  ;}
            if (fecha.getMonth() +1 < 10) {mes = "0" + (fecha.getMonth() +1) ;} else {mes = "" + (fecha.getMonth() +1);}

            if (fecha.getHours() < 10 ) {hora = "0" + fecha.getHours(); } else {hora = "" + fecha.getHours(); }
            if (fecha.getMinutes() < 10 ) {minuto = "0" + fecha.getMinutes(); } else {minuto = "" + fecha.getMinutes(); }
            if (fecha.getSeconds() < 10 ) {segundo = "0" + fecha.getSeconds(); } else {segundo = "" + fecha.getSeconds(); }


            return dia + "-" + mes + "-"  + (fecha.getYear()  + 1900) + "::" + hora + ":" + minuto + ":" + segundo ;
        }
        
    }
    
    public nodo tope ;
    public int tamano; 
    public void insertarDato (String dato ) {
        
        nodo nd = new nodo(dato);
        
        if (tope == null) {
            this.tope  = nd;
        }else {
            
            nd.abajo = this.tope;
            
            this.tope.arriba = nd;
            
            this.tope = nd;
            
        }
        
        this.tamano ++;
    }
    
    public void crearGrafico  () {
        
        String cont = "";
        
        cont += "digraph g{";
        
        cont += "   node[shape = record]; \n";
        cont += "   rankdir=LR;\n";
        
        cont += "   datos[label=\"";
        
        cont +=         this.getContenido();
        
        cont +=     "\"];\n";
        cont += "\n}";
        
        
        FileWriter archivo;
        PrintWriter pw;
        
        try {
            archivo =new FileWriter ("C:/arte/Report/vista_pila.txt") ;
            pw =new PrintWriter (archivo);
            pw.print(cont);
            archivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte pila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public String getContenido  () {
        String cont = "";
        
        nodo nd = this.tope;
        int indice =0;
        
        while (nd != null) {
            
            cont += "<f" + indice + "> " + nd.dato + " " + nd.marcaTiempo + " |";
            
            nd  = nd.abajo; indice ++;
        }
        
        return cont;
    }
    
    
    
}
