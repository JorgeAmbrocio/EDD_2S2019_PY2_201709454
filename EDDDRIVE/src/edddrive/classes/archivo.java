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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
public class archivo extends JButton implements ActionListener {
    public String nombre_;
    public String estampaTiempo_;
    public String extension_;
    public String contenido_;
    
    public JPopupMenu pum;
    public JMenuItem  jmiEliminar, jmiModificar, jmiDescargar, jmiCompartir;
    
    
    public archivo () {
        
    }

    public archivo(String nombre_,  String contenido_) {
        this.nombre_ = nombre_;
        String [] partes = nombre_.split("\\.");
        this.extension_ = partes[1];
        this.contenido_ = contenido_;
        this.estampaTiempo_ = this.crearEstampaTiempo();
        
        this.addActionListener(this);
        
        this.setText(this.nombre_ );
        this.setSize(120, 60);
        
        
        
        
        this.pum = new JPopupMenu (); // iniciar popup
        
        
        // crear los objetos del menús
        
        this.jmiEliminar  = new JMenuItem(); // iniciar el menu
        this.jmiEliminar.setText("Eliminar");
        this.jmiEliminar.addActionListener(this); // añadir al item de menú la acción
        
        this.jmiModificar  = new JMenuItem(); // iniciar el menu
        this.jmiModificar.setText("Modificar contenido");
        this.jmiModificar.addActionListener(this); // añadir al item de menú la acción
        
        this.jmiDescargar  = new JMenuItem(); // iniciar el menu
        this.jmiDescargar.setText("Descargar");
        this.jmiDescargar.addActionListener(this); // añadir al item de menú la acción
        
        this.jmiCompartir  = new JMenuItem(); // iniciar el menu
        this.jmiCompartir.setText("Compartir");
        this.jmiCompartir.addActionListener(this); // añadir al item de menú la acción

        // añadir al menu el item
        this.pum.add(this.jmiEliminar); 
        this.pum.add(this.jmiModificar); 
        this.pum.add(this.jmiDescargar); 
        this.pum.add(this.jmiCompartir); 
        
        
        this.setComponentPopupMenu(pum); // añadir al botón el menú
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        if (ae.getSource() == this.jmiEliminar) {
            edddrive.EDDDRIVE.func.carpetaActual.archivos.eliminar(this.nombre_);
            edddrive.EDDDRIVE.bitacora.insertarDato( edddrive.EDDDRIVE.func.usuarioActual.getUsuario_() + "  ha eliminado el archivo " + this.nombre_);
        
        }else if  (ae.getSource() == this.jmiDescargar) {
            
            edddrive.EDDDRIVE.func.descargarArchivo(this);
            
        }else if (ae.getSource() == this.jmiCompartir) {
            
            edddrive.EDDDRIVE.func.compartir(this);
            
            
        }else if  (ae.getSource() == this.jmiModificar) {
            //String nuevo_contenido = JOptionPane.showInputDialog(null, "Ingresa el nuevo contenido del archivo " + this.nombre_ + ":", "MODIFICAR");
            //edddrive.EDDDRIVE.func.carpetaActual.archivos.modificar(this.nombre_, nuevo_contenido);
            
            //this.contenido_ = nuevo_contenido;
            edddrive.EDDDRIVE.fmrModificarArchivo_.show();
            edddrive.EDDDRIVE.bitacora.insertarDato( edddrive.EDDDRIVE.func.usuarioActual.getUsuario_() + "  ha modificado el archivo " + this.nombre_);
        
        }else if (ae.getSource() == this) {
            // abrir el archivo
            String contenido  = "";
            
            contenido +=  "Nombre: "  + this.nombre_ +  "\n";
            contenido += "Contenido: " + this.contenido_ + " \n";
            
            JOptionPane.showMessageDialog(null, contenido, "ABRIR ARCHIVO", JOptionPane.NO_OPTION);
            
        }
        
        edddrive.EDDDRIVE.func.cargarCarpeta();
        
        
    }
    
}
