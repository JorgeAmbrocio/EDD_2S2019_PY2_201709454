/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import java.security.MessageDigest;
import edddrive.classes.usuario;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author dventura
 */
public class tablaHash {
    
    public enum estado { vacio, ocupado, borrado }
    
    public class celda {
        public int llave_ = 0;
        public usuario usuario_ = null;
        public estado estado_ = estado.vacio;
    }
    
    int tMax, usado;
    double factorCarga ;
    celda tabla[];
    
    // constructor
    public tablaHash () {
        this.tMax = 7 ;
        this.usado = 0;
        this.factorCarga  = 0;
        this.tabla = new celda[this.tMax];
        
        for  (int i = 0 ; i < this.tMax ; i++) { this.tabla[i] = new celda() ;}
        
    }
    
    
    public void insertar (String usuario_, String contrasena_, boolean encriptar) {
        
        // verifica que no existan los usuarios
        usuario u = this.buscar(usuario_);
        
        if (u != null) {
            JOptionPane.showMessageDialog(null, "Usuario ya existe " + usuario_);
            return;
        }
        
        
        // verifica si aún tenemos espacio para insertar el dato
        if (this.factorCarga  > 0.75) {
            this.redimencionarTabla();
        }
        
        // llave 
        int llave = this.getSumaASCII(usuario_);
        
        // calcular indice
        int indice = this.getHash(llave);
        
        // encriptar si es necesario
        if (encriptar) {
            // encriptar
            contrasena_ = this.sha256(contrasena_);
        }
        
        
        // insertar el dato
        this.tabla[indice].usuario_ = new usuario (usuario_ , contrasena_) ;
        this.tabla[indice].llave_ = llave;
        this.tabla[indice].estado_ = estado.ocupado;
        
        this.usado += 1;
        this.factorCarga = (double) ( (double) this.usado /  (double) this.tMax);
    }
    
    public usuario buscar (String usuario_) {
        
        // obtiene el índice del elemento a buscar
        int indice = (( this.getSumaASCII(usuario_) ) % this.tMax);
        
        int intento = 0;
        while (this.tabla[indice].estado_ != estado.vacio) {
            
            if  ( this.tabla[indice].estado_ == estado.ocupado  ) {
                
                if (this.tabla[indice].usuario_.getUsuario_().equalsIgnoreCase(usuario_) ) {
                    return this.tabla[indice].usuario_;
                }else {
                    if (indice == 0 || indice == 1 ) { indice = 1; }
                
                    indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                    intento ++;
                }
            }else if (this.tabla[indice].estado_ == estado.borrado) {
                if (indice == 0 || indice == 1 ) { indice = 1; }
                
                    indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                    intento ++;
                    
            }
        }
        
        return null;
    }
    
    public void eliminar (String usuario_) {
        // obtiene el índice del elemento a buscar
        int indice = (( this.getSumaASCII(usuario_) ) % this.tMax);
        
        int intento = 0;
        while (this.tabla[indice].estado_ != estado.vacio) {
            
            if  ( this.tabla[indice].estado_ == estado.ocupado ) {
                
                if (this.tabla[indice].usuario_.getUsuario_().equalsIgnoreCase(usuario_) ) {
                    this.tabla[indice] = new celda();
                    this.tabla[indice].estado_ = estado.borrado;
                    
                    this.usado --;
                    this.factorCarga = (double) ( (double) this.usado /  (double) this.tMax);
                    
                    return;
                }else {
                    if (indice == 0 || indice == 1 ) { indice = 1; }
                
                    indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                    intento ++;
                }
            }else if (this.tabla[indice].estado_ == estado.borrado ) {
                    if (indice == 0 || indice == 1 ) { indice = 1; }
                
                    indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                    intento ++;
            }
        }
        
    }
    
    public void redimencionarTabla () {
        
        // guardar copias del estado actual 
        int aux = this.tMax;
        celda [] aux_celda = this.tabla;
        
        // generar nuevos valores
        this.tMax = this.buscarProximoPrimo(tMax + 1 );
        this.tabla = new celda[this.tMax];
        this.factorCarga = 0.0;
        this.usado = 0;
        
        // iniciar los valores
        for (int i = 0 ; i  < this.tMax ; i++) { this.tabla[i] = new celda(); }
        
        // insertar todos los valores anteriores en la nueva tabla
        for (int i = 0 ; i < aux ; i ++ ) { 
            
            
            if (aux_celda[i].estado_ == estado.ocupado) {
                usuario us = aux_celda[i].usuario_;
                this.insertar(us.getUsuario_(), us.getContrasena_(), false);
            }
            
        }
    }
    
    public int buscarProximoPrimo (int i) {
        i += 1;
        
        while (!this.esPrimo(i)) {
            i += 1;
        }
        
        System.out.println(i);
        
        return i;
    }
    
    
    public boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        
        while ((primo) && (contador!=numero)){
          if (numero % contador == 0){
            primo = false;
          }
          contador++;
        }
        return primo;
    }

    public String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }

    public int getSumaASCII (String cadena) {
        int suma  = 0;
        
        
        for (int x=0;x<cadena.length();x++){
            suma += cadena.codePointAt(x);
            System.out.println(cadena.charAt(x) + " = " + cadena.codePointAt(x));;
        }
        
        System.out.println(cadena + " = " + suma);
        return suma;
    }
    
    public int getHash (int llave) {
        
        // obtiene el índice del elemento a buscar
        int indice = ((llave ) % this.tMax);
        
        int intento = 0;
        while (true) {
            
            if (this.tabla[indice].estado_ == estado.vacio || this.tabla[indice].estado_ == estado.borrado ) {
                return indice;
            }else{
                
                if (indice == 0 || indice == 1 ) { indice = 1; }
                
                indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                intento ++;
            }
        }
        
        
    }
    
    public void crearReporte(String nombre) {
        String cont= "";
        
        cont += "digraph G{\n";
        
        
        cont += "tbl[\n";
        
        cont += "   shape=plaintext;\n";
        cont += "   label=<\n";
        cont += "       <table border='0' cellborder='1' color='blue' cellspacing='0'>\n";
                        
        cont += "           <tr><td>Indice</td><td>Datos</td></tr>\n";
        
        cont += this.getContenidoReporte();
        
        cont += "       </table>\n";
        cont += "   >];\n";
        cont += "\n}";
        
        FileWriter archivo;
        PrintWriter pw ;
        
        try {
            archivo = new FileWriter (edddrive.EDDDRIVE.func.rutaReportes + nombre) ;
            pw = new PrintWriter (archivo);
            pw.print(cont);
            archivo.close();
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "No se ha escrito el contenido del reporte hash.", "Error", JOptionPane.ERROR_MESSAGE);}
        
    }
    
    public String getContenidoReporte () {
        String cont = "";
        
        for (int i = 0 ; i < this.tMax ; i ++) {
            
            
            if (this.tabla[i].estado_ == edddrive.estructuras.tablaHash.estado.ocupado) {
                cont += "<tr>\n";
            
                cont += "   <td>" + i + ").</td>\n";
                cont += "   <td>nombre:" + this.tabla[i].usuario_.getUsuario_() + " contraseña:" + this.tabla[i].usuario_.getContrasena_() + "</td>\n";

                cont += "</tr>\n";
            }else {
                cont += "<tr>\n";
            
                cont += "   <td>" + i + ").</td>\n";
                cont += "   <td>" + "" + " " + "" + "</td>\n";

                cont += "</tr>\n";
            }
            
        }
        
        return cont;
    }
    
    public Object[] getListaUsuarios () {
        Object[] obj = new Object[this.usado];
        
        int indice = 0;
        for (int i = 0 ; i < this.tMax; i ++) {
            
            if (this.tabla[i].estado_ == estado.ocupado) {
                obj[indice] = this.tabla[i].usuario_.getUsuario_();
                indice ++;
            }
            
        }
        
        
        return obj;
    }
    
}
