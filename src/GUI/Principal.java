/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.RangoFecha;
import Entidades.TransaccionPopulada;
import Entidades.Usuario;
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
public class Principal extends javax.swing.JFrame {

    private static Factory factory;
    static Usuario usuario;

    /**
     * Creates new form Principal
     */
    public Principal(Usuario usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        factory = new Factory();
        cargarColumnasTabla();
        cargarModeloTabla();
        this.lbl_empresa.setText(usuario.getEmpresa());
        this.lbl_bienvenida.setText("Bienvenido " + usuario.getNombres());
        this.lbl_finanzasEmpresa.setText("Finanzas de " + usuario.getEmpresa());
    }

    DefaultTableModel modeloTabla = new DefaultTableModel();

    /*
    Este metodo define las columnas que va a tener la tabla
    */
    
    private void cargarColumnasTabla() {

        modeloTabla.addColumn("Partida N°");
        modeloTabla.addColumn("Cuenta");
        modeloTabla.addColumn("Ingreso");
        modeloTabla.addColumn("Egreso");
        modeloTabla.addColumn("Fecha");
    }

    
    /*
    Este metodo lo que hace es traer un array de transacciones desde la Base de Datos,
    y mostrarla de manera ordenada en la tabla de la pantalla principal
    */
    private void cargarModeloTabla() {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        BaseDatos base = factory.baseDatos();
        Fechas fechas = factory.fechas();
        RangoFecha rango = fechas.EsteMes();

        ArrayList<TransaccionPopulada> listaTransacciones = base.obtenerTransaccionesTODOS();
        int numeroTransacciones = listaTransacciones.size();
        modeloTabla.setNumRows(numeroTransacciones);
        Double totalIngresos = 0.0;
        Double totalEgresos = 0.0;
        for (int i = 0; i < numeroTransacciones; i++) {
            TransaccionPopulada transaccionPopulada = listaTransacciones.get(i);

            int partidaNum = transaccionPopulada.getNumPartida();
            String cuenta = transaccionPopulada.getNombre_cuenta();
            float ingreso = transaccionPopulada.getTrasaccionIngreso();
            float egreso = transaccionPopulada.getTransaccionEgresos();
            String fecha = transaccionPopulada.getFecha();

            totalIngresos = totalIngresos + ingreso;
            totalEgresos = totalEgresos + egreso;

            modeloTabla.setValueAt(partidaNum, i, 0);
            modeloTabla.setValueAt(cuenta, i, 1);
            modeloTabla.setValueAt("$ " + ingreso, i, 2);
            modeloTabla.setValueAt("$ " + egreso, i, 3);
            modeloTabla.setValueAt(fecha, i, 4);

        }
        this.lbl_totalMovimientos.setText("Total de Movimientos     Ingresos: $ " + (double) Math.round(totalIngresos * 100d) / 100d + "     Egresos: $" + (double) Math.round(totalEgresos * 100d) / 100d);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtnG_periodoTiempo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rbtn_mes = new javax.swing.JRadioButton();
        rbtn_semana = new javax.swing.JRadioButton();
        rbtn_dia = new javax.swing.JRadioButton();
        lbl_totalMovimientos = new javax.swing.JLabel();
        btn_nuevaPartida = new javax.swing.JButton();
        txt_buscar = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbl_finanzasEmpresa = new javax.swing.JLabel();
        lbl_empresa = new javax.swing.JLabel();
        lbl_bienvenida = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        bMenu_archivo = new javax.swing.JMenu();
        btnM_salir = new javax.swing.JMenuItem();
        bMenu_vista = new javax.swing.JMenu();
        menu_libroDiario = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        rbtnG_periodoTiempo.add(rbtn_mes);
        rbtn_mes.setText("Este mes");

        rbtnG_periodoTiempo.add(rbtn_semana);
        rbtn_semana.setText("Esta semana");

        rbtnG_periodoTiempo.add(rbtn_dia);
        rbtn_dia.setText("Este día");

        lbl_totalMovimientos.setText("Total de Movimientos: $XXXX.xx");

        btn_nuevaPartida.setText("Nueva Partida");
        btn_nuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevaPartidaActionPerformed(evt);
            }
        });

        btn_buscar.setText("Buscar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtn_mes)
                        .addGap(18, 18, 18)
                        .addComponent(rbtn_semana)
                        .addGap(18, 18, 18)
                        .addComponent(rbtn_dia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_totalMovimientos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_nuevaPartida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_buscar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar)
                    .addComponent(btn_nuevaPartida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtn_mes)
                    .addComponent(rbtn_semana)
                    .addComponent(rbtn_dia)
                    .addComponent(lbl_totalMovimientos))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbl_finanzasEmpresa.setText("Finanzas de la Empresa (Inserte Nombre EMPRESA)");

        lbl_empresa.setText("(Inserte Nombre EMPRESA)");

        lbl_bienvenida.setText("Bienvenido (Nombre del usuario)");
        lbl_bienvenida.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_finanzasEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_empresa))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_bienvenida)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_finanzasEmpresa)
                    .addComponent(lbl_empresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_bienvenida)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        bMenu_archivo.setText("Archivo");

        btnM_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        btnM_salir.setText("Salir");
        btnM_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnM_salirActionPerformed(evt);
            }
        });
        bMenu_archivo.add(btnM_salir);

        jMenuBar1.add(bMenu_archivo);

        bMenu_vista.setText("Vista");

        menu_libroDiario.setText("Libro Diario");
        bMenu_vista.add(menu_libroDiario);

        jMenuItem2.setText("Libro Mayor");
        bMenu_vista.add(jMenuItem2);

        jMenuItem3.setText("Ajuste de IVA");
        bMenu_vista.add(jMenuItem3);

        jMenuItem5.setText("Balance de Comprobación");
        bMenu_vista.add(jMenuItem5);

        jMenuItem4.setText("Estado de Resultados");
        bMenu_vista.add(jMenuItem4);

        jMenuItem6.setText("Balance General");
        bMenu_vista.add(jMenuItem6);

        jMenuBar1.add(bMenu_vista);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevaPartidaActionPerformed
        NuevaEntrada nuevaEntrada = factory.nuevaEntrada();
        nuevaEntrada.setVisible(true);
    }//GEN-LAST:event_btn_nuevaPartidaActionPerformed

    private void btnM_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnM_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnM_salirActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu bMenu_archivo;
    private javax.swing.JMenu bMenu_vista;
    private javax.swing.JMenuItem btnM_salir;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_nuevaPartida;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_bienvenida;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_finanzasEmpresa;
    private javax.swing.JLabel lbl_totalMovimientos;
    private javax.swing.JMenuItem menu_libroDiario;
    private javax.swing.ButtonGroup rbtnG_periodoTiempo;
    private javax.swing.JRadioButton rbtn_dia;
    private javax.swing.JRadioButton rbtn_mes;
    private javax.swing.JRadioButton rbtn_semana;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
