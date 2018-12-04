/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.*;
import Factory.Factory;
import Procesos.BaseDatos;
import Procesos.Fechas;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roban
 */
public class LibroDiario extends javax.swing.JFrame {

    private static Factory factory;
    static Usuario usuario;

    /**
     * Creates new form LibroDiario
     */
    public LibroDiario(Usuario usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        factory = new Factory();
        cargarColumnasTabla();
        cargarModeloTabla();
        BaseDatos base = factory.baseDatos();
        String nombre_empresa = base.obtenerEmpresa_SegunID(usuario.getEmpresa()).getNomre_empresa();
        this.lbl_empresa.setText(nombre_empresa);
        
        /* Decoración UI */
         try
        {
            InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Font sizedFont = font.deriveFont(16f);
            
            lbl_empresa.setFont(sizedFont);
            tabla_partidas.setFont(sizedFont);
            lbl_totalMovimientos.setFont(sizedFont);
            
            
        }
        catch (FontFormatException | IOException ex)
        {
            
        }
         
        btn_cerrar.setOpaque(false);
        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.setBorderPainted(false);
    }

    DefaultTableModel modeloTabla = new DefaultTableModel();

    private void cargarColumnasTabla() {
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Partida N°");
        modeloTabla.addColumn("Ingreso");
        modeloTabla.addColumn("Egreso");
        
        DefaultTableCellRenderer R = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                            Object value, boolean isSelected, boolean hasFocus,
                            int row, int column)
            {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                            row, column);
                
                try
                {
                    InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Bold.ttf");
                    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                    Font sizedFont = font.deriveFont(16f);
                    setFont(sizedFont);
                }
                catch(FontFormatException | IOException ex){    } 
                return this;
            }
 
        };
        
        //Se cambia la fuente para la primera columna
        tabla_partidas.getColumnModel().getColumn(0).setCellRenderer(R);
    }

    private void cargarModeloTabla() {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        BaseDatos base = factory.baseDatos();
        
        /* Solo muestra el Libro diario de la empresa 3 */
        ArrayList<Partida> listaPartidas = base.obtenerPartidas_SegunEmpresa(3);
        ArrayList<Transaccion> listaTransacciones = base.obtenerTransacciones_SegunEmpresa(3);
        
        
        int numFilas = listaPartidas.size() + listaPartidas.size() + listaTransacciones.size();
        modeloTabla.setNumRows(numFilas);
        Double totalIngresos = 0.0;
        Double totalEgresos = 0.0;
        int filaDetalles = 0;
        int filaTitulo = 0;
        int filaTransaccion = 1;

        int[] cantidadTransaccionesPorPartida = new int[listaPartidas.size()];

        for (int i = 0; i < listaPartidas.size(); i++) {
            Partida partidaOBJ = listaPartidas.get(i);
            cantidadTransaccionesPorPartida[i] = base.cantidadTransaccionesSegunPartida(partidaOBJ.getId());
        }

        for (int i = 0; i < listaPartidas.size(); i++) {

            Partida partidaOBJ = listaPartidas.get(i);
            String fecha = partidaOBJ.getFecha();
            int partidaNum = partidaOBJ.getNumPartida();
            String descripcion = partidaOBJ.getDescripcion();
            float totalIngresosPartida = partidaOBJ.getTotalIngresos();
            float totalEgresosPartida = partidaOBJ.getTotalEgresos();

            filaDetalles = filaDetalles + 1 + cantidadTransaccionesPorPartida[i];
            
            modeloTabla.setValueAt(fecha, filaTitulo, 0);
            modeloTabla.setValueAt("Partida N° " + partidaNum, filaTitulo, 1);
            modeloTabla.setValueAt("Descripción: ", filaDetalles, 0);
            modeloTabla.setValueAt(descripcion, filaDetalles, 1);
            modeloTabla.setValueAt("$ " + totalIngresosPartida, filaDetalles, 2);
            modeloTabla.setValueAt("$ " + totalEgresosPartida, filaDetalles, 3);
            filaDetalles++;
            filaTitulo = cantidadTransaccionesPorPartida[i] + filaTitulo + 2;

        }

        int partidaAlmacenada = listaTransacciones.get(0).getIdPartida();

        for (int i = 0; i < listaTransacciones.size(); i++) {

            Transaccion transaccionOBJ = listaTransacciones.get(i);

            int partida_actual = transaccionOBJ.getIdPartida();

            if (partidaAlmacenada == partida_actual) {
                partidaAlmacenada = partida_actual;
            } else {
                filaTransaccion = filaTransaccion + 2;
                partidaAlmacenada = partida_actual;
            }

            int cuenta = transaccionOBJ.getCuenta();
            float trasaccionIngreso = transaccionOBJ.getTrasaccionIngreso();
            float transaccionEgresos = transaccionOBJ.getTransaccionEgresos();

            //Si es de saldo acreedor o deudor lo que hace es añadir una tabulación para que se puedan distinguir
            if (transaccionOBJ.getTrasaccionIngreso() != 0.00) {
                modeloTabla.setValueAt(base.obtenerNombreCuentaSegunID(cuenta), filaTransaccion, 1);
            } else {
                modeloTabla.setValueAt("           " + base.obtenerNombreCuentaSegunID(cuenta), filaTransaccion, 1);
            }

            totalIngresos = totalIngresos + trasaccionIngreso;
            totalEgresos = totalEgresos + transaccionEgresos;
            
            modeloTabla.setValueAt("$ " + trasaccionIngreso, filaTransaccion, 2);
            modeloTabla.setValueAt("$ " + transaccionEgresos, filaTransaccion, 3);
            filaTransaccion++;

        }
        this.lbl_totalMovimientos.setText("Total de Movimientos     Ingresos: $ " + (double) Math.round(totalIngresos * 100d) / 100d + "     Egresos: $" + (double) Math.round(totalEgresos * 100d) / 100d);
        //this.lbl
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_empresa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_partidas = new javax.swing.JTable();
        lbl_totalMovimientos = new javax.swing.JLabel();
        btn_cerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1500, 1500));
        setMinimumSize(new java.awt.Dimension(1125, 785));
        setPreferredSize(new java.awt.Dimension(1125, 785));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                apertura(evt);
            }
        });
        getContentPane().setLayout(null);

        lbl_empresa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_empresa.setText("Empresa XXX");
        getContentPane().add(lbl_empresa);
        lbl_empresa.setBounds(10, 650, 780, 30);

        tabla_partidas.setModel(modeloTabla);
        tabla_partidas.setRowHeight(25);
        jScrollPane1.setViewportView(tabla_partidas);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 150, 1070, 403);

        lbl_totalMovimientos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_totalMovimientos.setText("jLabel2");
        getContentPane().add(lbl_totalMovimientos);
        lbl_totalMovimientos.setBounds(10, 680, 800, 30);

        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrar);
        btn_cerrar.setBounds(890, 650, 200, 60);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/LibroDiario.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1130, 740);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apertura(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_apertura
        
        
    }//GEN-LAST:event_apertura

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibroDiario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibroDiario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibroDiario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibroDiario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibroDiario(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_totalMovimientos;
    private javax.swing.JTable tabla_partidas;
    // End of variables declaration//GEN-END:variables
}
