/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.*;
import Factory.Factory;
import Procesos.BaseDatos;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roban
 */
public class BalanceComprobacion extends javax.swing.JFrame {

    private static Factory factory;
    static Usuario usuario;

    /**
     * Creates new form BalanceComprobacion
     */
    public BalanceComprobacion(Usuario usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        factory = new Factory();
        cargarColumnasTabla();
        cargarModeloTabla();
        BaseDatos base = factory.baseDatos();
        this.lbl_empresa.setText(base.obtenerEmpresa_SegunID(usuario.getEmpresa()).getNomre_empresa());
        
        /* Decoración UI */
         try
        {
            InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Font sizedFont = font.deriveFont(16f);
            
            lbl_empresa.setFont(sizedFont);
            tabla_balanceComprobacion.setFont(sizedFont);
            
            
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
        modeloTabla.addColumn("Cuentas");
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
        /* Se centra el texto */
        R.setHorizontalAlignment(JLabel.LEFT);
        
        //Se cambia la fuente para la primera columna
        tabla_balanceComprobacion.getColumnModel().getColumn(0).setCellRenderer(R);
    }

    private void cargarModeloTabla() {
        BaseDatos base = factory.baseDatos();

        modeloTabla.setNumRows(7);
        modeloTabla.setValueAt("Cuentas de Activo: ", 0, 0);
        modeloTabla.setValueAt("Cuentas de Pasivo: ", 1, 0);
        modeloTabla.setValueAt("Cuentas de Capital: ", 2, 0);
        modeloTabla.setValueAt("Cuentas de Ingresos: ", 3, 0);
        modeloTabla.setValueAt("Cuentas de Costos: ", 4, 0);
        modeloTabla.setValueAt("Cuentas de Gastos: ", 5, 0);
        modeloTabla.setValueAt("Total: ", 6, 0);

        float cuentasActivo = base.SumatoriaIngresos_SegunClasificacion("Activo");
        float cuentasPasivo = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Pasivo"));
        float cuentasCapital = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Capital"));
        float cuentasIngresos = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Ingresos"));
        float cuentasCostos = base.SumatoriaIngresos_SegunClasificacion("Costo");
        float cuentasGastos = base.SumatoriaIngresos_SegunClasificacion("Gastos");

        modeloTabla.setValueAt(cuentasActivo, 0, 1);
        modeloTabla.setValueAt(cuentasPasivo, 1, 2);
        modeloTabla.setValueAt(cuentasCapital, 2, 2);
        modeloTabla.setValueAt(cuentasIngresos, 3, 2);
        modeloTabla.setValueAt(cuentasCostos, 4, 1);
        modeloTabla.setValueAt(cuentasGastos, 5, 1);

        float totalIngresos = cuentasActivo + cuentasCostos + cuentasGastos;
        float totalEgresos = cuentasPasivo + cuentasCapital + cuentasIngresos;

        modeloTabla.setValueAt(totalIngresos, 6, 1);
        modeloTabla.setValueAt(totalEgresos, 6, 2);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table_balanceComprobacion = new javax.swing.JScrollPane();
        tabla_balanceComprobacion = new javax.swing.JTable();
        lbl_empresa = new javax.swing.JLabel();
        btn_cerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1500, 1500));
        setMinimumSize(new java.awt.Dimension(1125, 785));
        setPreferredSize(new java.awt.Dimension(1125, 785));
        setResizable(false);
        getContentPane().setLayout(null);

        tabla_balanceComprobacion.setModel(modeloTabla);
        tabla_balanceComprobacion.setRowHeight(25);
        table_balanceComprobacion.setViewportView(tabla_balanceComprobacion);

        getContentPane().add(table_balanceComprobacion);
        table_balanceComprobacion.setBounds(20, 150, 1060, 450);

        lbl_empresa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_empresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_empresa.setText("Empresa XXXX");
        getContentPane().add(lbl_empresa);
        lbl_empresa.setBounds(0, 70, 1130, 30);

        btn_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrar);
        btn_cerrar.setBounds(890, 660, 200, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/BalanceComprobación.png"))); // NOI18N
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
            java.util.logging.Logger.getLogger(BalanceComprobacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BalanceComprobacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BalanceComprobacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceComprobacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BalanceComprobacion(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JTable tabla_balanceComprobacion;
    private javax.swing.JScrollPane table_balanceComprobacion;
    // End of variables declaration//GEN-END:variables
}
