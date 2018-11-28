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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    }

    DefaultTableModel modeloTabla = new DefaultTableModel();

    private void cargarColumnasTabla() {
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Partida N°");
        modeloTabla.addColumn("Ingreso");
        modeloTabla.addColumn("Egreso");
    }

    private void cargarModeloTabla() {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        BaseDatos base = factory.baseDatos();

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

        jPanel1 = new javax.swing.JPanel();
        lbl_libroDiario = new javax.swing.JLabel();
        lbl_empresa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_partidas = new javax.swing.JTable();
        lbl_totalMovimientos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                apertura(evt);
            }
        });

        lbl_libroDiario.setText("Libro Diario");

        lbl_empresa.setText("Empresa XXX");

        jLabel1.setText("Periodo Contable del XXX al XXX");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(435, 435, 435)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_empresa)
                            .addComponent(lbl_libroDiario)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jLabel1)))
                .addContainerGap(511, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_libroDiario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_empresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tabla_partidas.setModel(modeloTabla);
        jScrollPane1.setViewportView(tabla_partidas);

        lbl_totalMovimientos.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_totalMovimientos)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(lbl_totalMovimientos)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apertura(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_apertura
        
        
    }//GEN-LAST:event_apertura

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_libroDiario;
    private javax.swing.JLabel lbl_totalMovimientos;
    private javax.swing.JTable tabla_partidas;
    // End of variables declaration//GEN-END:variables
}
