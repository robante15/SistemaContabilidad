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
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roban
 */
public class Principal extends javax.swing.JFrame {

    private static Factory factory;
    static Usuario USUARIO;

    /**
     * Creates new form Principal
     */
    public Principal(Usuario usuario) {
        initComponents();
        USUARIO = usuario;
        this.setLocationRelativeTo(null);
        factory = new Factory();
        BaseDatos base = factory.baseDatos();
        cargarColumnasTabla();
        cargarModeloTabla();
        this.setTitle("Panel principal: [MODO " + usuario.getRol().toUpperCase() + "]");
        this.lbl_empresa.setText(base.obtenerEmpresa_SegunID(usuario.getEmpresa()).getNomre_empresa());
        this.Lbl_Nombre_Usuario.setText(usuario.getUsuario());
        this.lbl_finanzasEmpresa.setText("Finanzas generales de la empresa");
        
        /* Decoración UI */
        try
        {
            InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Font sizedFont = font.deriveFont(16f);
                   
            lbl_finanzasEmpresa.setFont(sizedFont);
            lbl_empresa.setFont(sizedFont);
            lbl_bienvenida.setFont(sizedFont);
            table_transaccionesRecientes.setFont(sizedFont);
            txt_buscar.setFont(sizedFont);
            lbl_totalMovimientos.setFont(sizedFont);
            rbtn_mes.setFont(sizedFont);
            rbtn_semana.setFont(sizedFont);
            rbtn_dia.setFont(sizedFont);
        }
        catch (FontFormatException | IOException ex)
        {
            
        }
        
        try
        {
            InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Bold.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Font sizedFont = font.deriveFont(16f);
                   
            Lbl_Nombre_Usuario.setFont(sizedFont);
        }
        catch (FontFormatException | IOException ex)
        {
            
        }
        
        
        
        btn_nuevaPartida.setOpaque(false);
        btn_nuevaPartida.setContentAreaFilled(false);
        btn_nuevaPartida.setBorderPainted(false);
        
        rbtn_mes.setOpaque(false);
        rbtn_semana.setOpaque(false);
        rbtn_dia.setOpaque(false);
        
        btn_buscar.setOpaque(false);
        btn_buscar.setContentAreaFilled(false);
        btn_buscar.setBorderPainted(false);
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
        
        /* Decoración para centrar el texto de la primera columna y poner el texto en negrita unicamente a la primera columna */
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
        R.setHorizontalAlignment(JLabel.CENTER);
        
        //Se cambia la fuente para la primera columna
        table_transaccionesRecientes.getColumnModel().getColumn(0).setCellRenderer(R);
        
        
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
        ArrayList<TransaccionPopulada> listaTransacciones = base.obtenerTransaccionesTODOS(USUARIO.getEmpresa());
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
        this.lbl_totalMovimientos.setText("Total de Movimientos:     Ingresos: $ " + (double) Math.round(totalIngresos * 100d) / 100d + "     Egresos: $" + (double) Math.round(totalEgresos * 100d) / 100d);

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
        btn_buscar = new javax.swing.JButton();
        txt_buscar = new javax.swing.JTextField();
        rbtn_dia = new javax.swing.JRadioButton();
        rbtn_semana = new javax.swing.JRadioButton();
        rbtn_mes = new javax.swing.JRadioButton();
        lbl_totalMovimientos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_transaccionesRecientes = new javax.swing.JTable();
        btn_nuevaPartida = new javax.swing.JButton();
        lbl_finanzasEmpresa = new javax.swing.JLabel();
        lbl_empresa = new javax.swing.JLabel();
        lbl_bienvenida = new javax.swing.JLabel();
        Lbl_Nombre_Usuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        bMenu_archivo = new javax.swing.JMenu();
        btnM_salir = new javax.swing.JMenuItem();
        bMenu_vista = new javax.swing.JMenu();
        menu_libroDiario = new javax.swing.JMenuItem();
        menu_libroMayor = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1125, 810));
        setPreferredSize(new java.awt.Dimension(1125, 810));
        setResizable(false);
        getContentPane().setLayout(null);

        btn_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btn_buscar);
        btn_buscar.setBounds(911, 162, 200, 60);
        getContentPane().add(txt_buscar);
        txt_buscar.setBounds(620, 178, 280, 30);

        rbtnG_periodoTiempo.add(rbtn_dia);
        rbtn_dia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        rbtn_dia.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_dia.setText("Este día");
        rbtn_dia.setMinimumSize(new java.awt.Dimension(85, 28));
        rbtn_dia.setPreferredSize(new java.awt.Dimension(85, 28));
        getContentPane().add(rbtn_dia);
        rbtn_dia.setBounds(320, 710, 150, 28);

        rbtnG_periodoTiempo.add(rbtn_semana);
        rbtn_semana.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        rbtn_semana.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_semana.setText("Esta semana");
        rbtn_semana.setMinimumSize(new java.awt.Dimension(85, 28));
        rbtn_semana.setPreferredSize(new java.awt.Dimension(85, 28));
        getContentPane().add(rbtn_semana);
        rbtn_semana.setBounds(170, 710, 140, 30);

        rbtnG_periodoTiempo.add(rbtn_mes);
        rbtn_mes.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        rbtn_mes.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_mes.setText("Este mes");
        rbtn_mes.setPreferredSize(new java.awt.Dimension(102, 28));
        getContentPane().add(rbtn_mes);
        rbtn_mes.setBounds(40, 710, 110, 28);

        lbl_totalMovimientos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_totalMovimientos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_totalMovimientos.setText("Total de Movimientos: $XXXX.xx");
        lbl_totalMovimientos.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lbl_totalMovimientos);
        lbl_totalMovimientos.setBounds(542, 720, 560, 30);

        table_transaccionesRecientes.setModel(modeloTabla);
        table_transaccionesRecientes.setRowHeight(25);
        jScrollPane1.setViewportView(table_transaccionesRecientes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 320, 1070, 340);

        btn_nuevaPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevaPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_nuevaPartida);
        btn_nuevaPartida.setBounds(19, 162, 210, 60);

        lbl_finanzasEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_finanzasEmpresa.setText("Finanzas de la Empresa (Inserte Nombre EMPRESA)");
        lbl_finanzasEmpresa.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lbl_finanzasEmpresa);
        lbl_finanzasEmpresa.setBounds(10, 10, 640, 30);

        lbl_empresa.setForeground(new java.awt.Color(255, 255, 255));
        lbl_empresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_empresa.setText("(Inserte Nombre EMPRESA)");
        lbl_empresa.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lbl_empresa);
        lbl_empresa.setBounds(680, 10, 430, 30);

        lbl_bienvenida.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbl_bienvenida.setForeground(new java.awt.Color(255, 255, 255));
        lbl_bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_bienvenida.setText("Has iniciado sesión como:");
        lbl_bienvenida.setToolTipText("");
        getContentPane().add(lbl_bienvenida);
        lbl_bienvenida.setBounds(670, 70, 440, 21);

        Lbl_Nombre_Usuario.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Lbl_Nombre_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        Lbl_Nombre_Usuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lbl_Nombre_Usuario.setText("Cristian Cubias");
        Lbl_Nombre_Usuario.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(Lbl_Nombre_Usuario);
        Lbl_Nombre_Usuario.setBounds(670, 95, 440, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/PanelPrincipal.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1500, 1500));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1130, 750);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(96, 25));

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

        menu_libroDiario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menu_libroDiario.setText("Libro Diario");
        menu_libroDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_libroDiarioActionPerformed(evt);
            }
        });
        bMenu_vista.add(menu_libroDiario);

        menu_libroMayor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        menu_libroMayor.setText("Libro Mayor");
        menu_libroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_libroMayorActionPerformed(evt);
            }
        });
        bMenu_vista.add(menu_libroMayor);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Ajuste de IVA");
        bMenu_vista.add(jMenuItem3);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Balance de Comprobación");
        bMenu_vista.add(jMenuItem5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Estado de Resultados");
        bMenu_vista.add(jMenuItem4);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Balance General");
        bMenu_vista.add(jMenuItem6);

        jMenuBar1.add(bMenu_vista);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevaPartidaActionPerformed
        NuevaEntrada nuevaEntrada = factory.nuevaEntrada();
        nuevaEntrada.setVisible(true);
    }//GEN-LAST:event_btn_nuevaPartidaActionPerformed

    private void btnM_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnM_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnM_salirActionPerformed

    private void menu_libroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_libroDiarioActionPerformed
        LibroDiario libroDiario = factory.libroDiario(USUARIO);
        libroDiario.setVisible(true);
    }//GEN-LAST:event_menu_libroDiarioActionPerformed

    private void menu_libroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_libroMayorActionPerformed
        LibroMayor libroMayor = factory.libroMayor(USUARIO);
        libroMayor.setVisible(true);
    }//GEN-LAST:event_menu_libroMayorActionPerformed

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
                new Principal(USUARIO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lbl_Nombre_Usuario;
    private javax.swing.JMenu bMenu_archivo;
    private javax.swing.JMenu bMenu_vista;
    private javax.swing.JMenuItem btnM_salir;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_nuevaPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_bienvenida;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_finanzasEmpresa;
    private javax.swing.JLabel lbl_totalMovimientos;
    private javax.swing.JMenuItem menu_libroDiario;
    private javax.swing.JMenuItem menu_libroMayor;
    private javax.swing.ButtonGroup rbtnG_periodoTiempo;
    private javax.swing.JRadioButton rbtn_dia;
    private javax.swing.JRadioButton rbtn_mes;
    private javax.swing.JRadioButton rbtn_semana;
    private javax.swing.JTable table_transaccionesRecientes;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
