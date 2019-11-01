/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.classes;

/**
 *
 * @author David Ventura
 */
public class archivo {
    public String nombre_;
    public String estampaTiempo_;
    public String extension_;
    public String contenido_;
    
    public archivo () {
        
    }

    public archivo(String nombre_, String extension_, String contenido_) {
        this.nombre_ = nombre_;
        this.extension_ = extension_;
        this.contenido_ = contenido_;
        this.estampaTiempo_ = this.crearEstampaTiempo();
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
