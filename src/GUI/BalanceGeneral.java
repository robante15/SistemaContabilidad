/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.Partida;
import Entidades.Transaccion;
import Entidades.Usuario;
import Factory.Factory;
import Procesos.BaseDatos;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roban
 */
public class BalanceGeneral extends javax.swing.JFrame {

    private static Factory factory;
    static Usuario usuario;

    /**
     * Creates new form BalanceGeneral
     */
    public BalanceGeneral(Usuario usuario) {
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
            jTable1.setFont(sizedFont);
            
            
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
        modeloTabla.addColumn("");
        modeloTabla.addColumn("");
        modeloTabla.addColumn("");
        
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
        /* Se centra el texto */
        R.setHorizontalAlignment(JLabel.LEFT);
        
        //Se cambia la fuente para la primera columna
        jTable1.getColumnModel().getColumn(0).setCellRenderer(R);
    }

    private void cargarModeloTabla() {
        BaseDatos base = factory.baseDatos();

        modeloTabla.setNumRows(4);
        modeloTabla.setValueAt("Activos: ", 0, 0);
        modeloTabla.setValueAt("Pasivos: ", 1, 0);
        modeloTabla.setValueAt("Capital: ", 2, 0);
        modeloTabla.setValueAt("Total: ", 3, 0);

        float cuentasActivo = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Activo"));
        float cuentasPasivo = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Pasivo"));
        float cuentasCapital = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Capital"));
        
        float cuentasIngresos = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Ingresos"));
        float cuentasCostos = base.SumatoriaIngresos_SegunClasificacion("Costo");
        float UtilidadBruta = cuentasIngresos - cuentasCostos;
        float cuentasGastos = base.SumatoriaIngresos_SegunClasificacion("Gastos");
        float UtilidadOperacion = UtilidadBruta - cuentasGastos;
        float ReservaLegal = (float) (UtilidadOperacion * 0.07);
        float UtilidadAntesImpuesto = UtilidadOperacion - ReservaLegal;
        float ImpuestoPagar = (float) (UtilidadAntesImpuesto * 0.25);
        float UtilidadNeta = UtilidadAntesImpuesto - ImpuestoPagar;

        cuentasCapital = cuentasCapital + ReservaLegal + UtilidadNeta + ImpuestoPagar;
        
        modeloTabla.setValueAt(cuentasActivo, 0, 1);
        modeloTabla.setValueAt(cuentasPasivo, 1, 2);
        modeloTabla.setValueAt(cuentasCapital, 2, 2);


        float totalIngresos = cuentasActivo;
        float totalEgresos = cuentasPasivo + cuentasCapital;

        modeloTabla.setValueAt(totalIngresos, 3, 1);
        modeloTabla.setValueAt(totalEgresos, 3, 2);
    }

    /*private void cargarModeloTabla() {
        BaseDatos base = factory.baseDatos();

        float TotalActivo = 0;
        float TotalPasivo = 0;
        float TotalCapital = 0;

        ArrayList<Transaccion> listaTransaccionesActivo = base.obtenerTransacciones_SegunClasificacion("Activo");
        ArrayList<Transaccion> listaTransaccionesPasivo = base.obtenerTransacciones_SegunClasificacion("Pasivo");
        ArrayList<Transaccion> listaTransaccionesCapital = base.obtenerTransacciones_SegunClasificacion("Capital");
        int numFilas = listaTransaccionesActivo.size() + listaTransaccionesPasivo.size() + listaTransaccionesCapital.size() + 1;

        modeloTabla.setNumRows(numFilas);

        for (int i = 0; i < listaTransaccionesActivo.size(); i++) {

            Transaccion transaccionActivoOBJ = listaTransaccionesActivo.get(i);
            int cuenta = transaccionActivoOBJ.getCuenta();
            float totalIngresosActivo = transaccionActivoOBJ.getTrasaccionIngreso();
            float totalEgresosActivo = transaccionActivoOBJ.getTransaccionEgresos();
            float totalTransaccionActivo = totalIngresosActivo - totalEgresosActivo;

            modeloTabla.setValueAt(base.obtenerNombreCuentaSegunID(cuenta), i, 0);
            modeloTabla.setValueAt("$ " + totalTransaccionActivo, i, 1);

            TotalActivo = TotalActivo + totalTransaccionActivo;

        }

        for (int i = 0; i < listaTransaccionesPasivo.size(); i++) {

            Transaccion transaccionPasivoOBJ = listaTransaccionesPasivo.get(i);
            int cuenta = transaccionPasivoOBJ.getCuenta();
            float totalIngresosPasivo = transaccionPasivoOBJ.getTrasaccionIngreso();
            float totalEgresosPasivo = transaccionPasivoOBJ.getTransaccionEgresos();
            float totalTransaccionPasivo = totalIngresosPasivo - totalEgresosPasivo;

            modeloTabla.setValueAt(base.obtenerNombreCuentaSegunID(cuenta), i + listaTransaccionesActivo.size(), 0);
            modeloTabla.setValueAt("$ " + totalTransaccionPasivo, i + listaTransaccionesActivo.size(), 2);

            TotalPasivo = TotalPasivo + totalTransaccionPasivo;
        }
        
        for (int i = 0; i < listaTransaccionesCapital.size(); i++) {

            Transaccion transaccionCapitalOBJ = listaTransaccionesCapital.get(i);
            int cuenta = transaccionCapitalOBJ.getCuenta();
            float totalIngresosCapital = transaccionCapitalOBJ.getTrasaccionIngreso();
            float totalEgresosCapital = transaccionCapitalOBJ.getTransaccionEgresos();
            float totalTransaccionCapital = totalIngresosCapital - totalEgresosCapital;

            modeloTabla.setValueAt(base.obtenerNombreCuentaSegunID(cuenta), i + listaTransaccionesActivo.size(), 0);
            modeloTabla.setValueAt("$ " + totalTransaccionCapital, i + listaTransaccionesActivo.size() + listaTransaccionesPasivo.size(), 2);

            TotalCapital = TotalCapital + totalTransaccionCapital;
        }

    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_empresa = new javax.swing.JLabel();
        btn_cerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1500, 1500));
        setMinimumSize(new java.awt.Dimension(1125, 785));
        setPreferredSize(new java.awt.Dimension(1125, 785));
        setResizable(false);
        getContentPane().setLayout(null);

        jTable1.setModel(modeloTabla);
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 150, 1080, 450);

        lbl_empresa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_empresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_empresa.setText("Empresa XXX");
        getContentPane().add(lbl_empresa);
        lbl_empresa.setBounds(0, 70, 1120, 30);

        btn_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrar);
        btn_cerrar.setBounds(880, 660, 210, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/BalanceGeneral.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1130, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(BalanceGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BalanceGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BalanceGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BalanceGeneral(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_empresa;
    // End of variables declaration//GEN-END:variables
}
