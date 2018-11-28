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
import java.util.ArrayList;
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
    }
    DefaultTableModel modeloTabla = new DefaultTableModel();

    private void cargarColumnasTabla() {
        modeloTabla.addColumn("");
        modeloTabla.addColumn("");
        modeloTabla.addColumn("");
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

        jPanel1 = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        lbl_empresa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl_titulo.setText("Balance General");

        lbl_empresa.setText("Empresa XXX");

        jLabel3.setText("lbl_periodoContable");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(544, 544, 544)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbl_empresa)
                    .addComponent(lbl_titulo))
                .addContainerGap(547, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_empresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_titulo;
    // End of variables declaration//GEN-END:variables
}
