/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.Usuario;
import Factory.Factory;
import static GUI.Principal.USUARIO;
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
public class EstadoResultados extends javax.swing.JFrame {

    private static Factory factory;
    static Usuario usuario;

    /**
     * Creates new form EstadoResultados
     */
    public EstadoResultados(Usuario usuario) {
        initComponents();
        factory = new Factory();
        this.setLocationRelativeTo(null);
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
        modeloTabla.setNumRows(9);
        modeloTabla.setValueAt("Ingresos", 0, 0);
        modeloTabla.setValueAt("(-) Costos", 1, 0);
        modeloTabla.setValueAt("(=) Utilidad Bruta", 2, 0);
        modeloTabla.setValueAt("(-) Gastos de Operación", 3, 0);
        modeloTabla.setValueAt("(=) Utilidad de Operación", 4, 0);
        modeloTabla.setValueAt("(-) Reserva Legal", 5, 0);
        modeloTabla.setValueAt("(=) Utilidad antes de Impuesto", 6, 0);
        modeloTabla.setValueAt("(-) Impuesto por Pagar", 7, 0);
        modeloTabla.setValueAt("(=) Utilidad Neta", 8, 0);

        float cuentasIngresos = Math.abs(base.SumatoriaIngresos_SegunClasificacion("Ingresos"));
        float cuentasCostos = base.SumatoriaIngresos_SegunClasificacion("Costo");
        float UtilidadBruta = cuentasIngresos - cuentasCostos;
        float cuentasGastos = base.SumatoriaIngresos_SegunClasificacion("Gastos");
        float UtilidadOperacion = UtilidadBruta - cuentasGastos;
        float ReservaLegal = (float) (UtilidadOperacion * 0.07);
        float UtilidadAntesImpuesto = UtilidadOperacion - ReservaLegal;
        float ImpuestoPagar = (float) (UtilidadAntesImpuesto * 0.25);
        float UtilidadNeta = UtilidadAntesImpuesto - ImpuestoPagar;

        modeloTabla.setValueAt(cuentasIngresos, 0, 1);
        modeloTabla.setValueAt(cuentasCostos, 1, 1);
        modeloTabla.setValueAt(UtilidadBruta, 2, 1);
        modeloTabla.setValueAt(cuentasGastos, 3, 1);
        modeloTabla.setValueAt(UtilidadOperacion, 4, 1);
        modeloTabla.setValueAt(ReservaLegal, 5, 1);
        modeloTabla.setValueAt(UtilidadAntesImpuesto, 6, 1);
        modeloTabla.setValueAt(ImpuestoPagar, 7, 1);
        modeloTabla.setValueAt(UtilidadNeta, 8, 1);

    }

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
        jScrollPane1.setBounds(10, 150, 1090, 450);

        lbl_empresa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_empresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_empresa.setText("Empresa XXXX");
        getContentPane().add(lbl_empresa);
        lbl_empresa.setBounds(0, 70, 1130, 30);

        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrar);
        btn_cerrar.setBounds(890, 660, 200, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/EstadoResultados.png"))); // NOI18N
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
            java.util.logging.Logger.getLogger(EstadoResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstadoResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstadoResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstadoResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstadoResultados(usuario).setVisible(true);
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
