/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.*;
import Factory.Factory;
import Procesos.BaseDatos;
import static java.lang.Float.parseFloat;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roban
 */
public class NuevaEntrada extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    static Usuario usuario;
    private static Factory factory;
    String descripcion;
    String estafecha;
    
    
    //array para la transacciones
    public ArrayList<String> trancuenta  = new ArrayList<>();
    public ArrayList<String> traningre  = new ArrayList<>();
    public ArrayList<String> tranegre  = new ArrayList<>();
    
    public NuevaEntrada() {
        initComponents();
        cargarColumnasTabla();
        cargarModeloTabla();
        this.setLocationRelativeTo(null);
        factory = new Factory();
    }

    DefaultTableModel modeloTabla = new DefaultTableModel();

    /*
    Este metodo define las columnas que va a tener la tabla
     */
    private void cargarColumnasTabla() {

        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Partida N°");
        modeloTabla.addColumn("Ingreso");
        modeloTabla.addColumn("Egreso");

    }

    private void cargarModeloTabla() {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date fecha = new Date();
        int columnas = 1;
        modeloTabla.setNumRows(columnas);
        modeloTabla.setValueAt(df.format(fecha), 0, 0);
        estafecha = df.format(fecha);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtnG_ingresoEgreso = new javax.swing.ButtonGroup();
        jframe_agregarCuenta = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        lbl_nombreCuenta = new javax.swing.JLabel();
        lbl_clasificacion = new javax.swing.JLabel();
        lbl_tipoSaldo = new javax.swing.JLabel();
        btn_agregarCuentaOK = new javax.swing.JButton();
        btn_cancelarCuenta = new javax.swing.JButton();
        txt_nombreCuenta = new javax.swing.JTextField();
        cbox_clasificacionCuenta = new javax.swing.JComboBox<>();
        rbtn_deudor = new javax.swing.JRadioButton();
        rbtn_acreedor = new javax.swing.JRadioButton();
        rbtnG_deudorAcreedor = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_NuevaEntrada = new javax.swing.JTable();
        lbl_cuenta = new javax.swing.JLabel();
        cbox_cuentas = new javax.swing.JComboBox<>();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        lbl_monto = new javax.swing.JLabel();
        montoTXT = new javax.swing.JTextField();
        rbtn_ingreso = new javax.swing.JRadioButton();
        rbtn_egreso = new javax.swing.JRadioButton();
        btn_añadir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcionTXT = new javax.swing.JTextArea();
        lbl_descripcion = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btn_agregarCuenta = new javax.swing.JButton();

        jframe_agregarCuenta.setMinimumSize(new java.awt.Dimension(400, 425));
        jframe_agregarCuenta.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerrarCuadroCuenta(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbl_titulo.setText("Agregar Nueva Cuenta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbl_titulo)
                .addContainerGap(235, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lbl_titulo)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        lbl_nombreCuenta.setText("Nombre de la cuenta");

        lbl_clasificacion.setText("Clasificación");

        lbl_tipoSaldo.setText("Tipo de Saldo");

        btn_agregarCuentaOK.setText("Agregar");
        btn_agregarCuentaOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarCuentaOKActionPerformed(evt);
            }
        });

        btn_cancelarCuenta.setText("Cancelar");
        btn_cancelarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarCuentaActionPerformed(evt);
            }
        });

        cbox_clasificacionCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Pasivo", "Capital", "Ingresos", "Costos", "Gastos" }));

        rbtnG_deudorAcreedor.add(rbtn_deudor);
        rbtn_deudor.setSelected(true);
        rbtn_deudor.setText("Deudor");

        rbtnG_deudorAcreedor.add(rbtn_acreedor);
        rbtn_acreedor.setText("Acreedor");

        javax.swing.GroupLayout jframe_agregarCuentaLayout = new javax.swing.GroupLayout(jframe_agregarCuenta.getContentPane());
        jframe_agregarCuenta.getContentPane().setLayout(jframe_agregarCuentaLayout);
        jframe_agregarCuentaLayout.setHorizontalGroup(
            jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jframe_agregarCuentaLayout.createSequentialGroup()
                .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jframe_agregarCuentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nombreCuenta)
                            .addComponent(lbl_clasificacion)
                            .addComponent(lbl_tipoSaldo))
                        .addGap(18, 18, 18)
                        .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jframe_agregarCuentaLayout.createSequentialGroup()
                                .addComponent(rbtn_deudor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(rbtn_acreedor))
                            .addComponent(txt_nombreCuenta)
                            .addComponent(cbox_clasificacionCuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jframe_agregarCuentaLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btn_cancelarCuenta)
                        .addGap(100, 100, 100)
                        .addComponent(btn_agregarCuentaOK)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jframe_agregarCuentaLayout.setVerticalGroup(
            jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jframe_agregarCuentaLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombreCuenta)
                    .addComponent(txt_nombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_clasificacion)
                    .addComponent(cbox_clasificacionCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tipoSaldo)
                    .addComponent(rbtn_deudor)
                    .addComponent(rbtn_acreedor))
                .addGap(18, 18, 18)
                .addGroup(jframe_agregarCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregarCuentaOK)
                    .addComponent(btn_cancelarCuenta))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                focusGained(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                apertura(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Libro Diario: Nueva entrada");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(24, 24, 24))
        );

        tabla_NuevaEntrada.setModel(modeloTabla);
        jScrollPane1.setViewportView(tabla_NuevaEntrada);

        lbl_cuenta.setText("Cuenta");

        cbox_cuentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        lbl_monto.setText("Monto");

        rbtnG_ingresoEgreso.add(rbtn_ingreso);
        rbtn_ingreso.setSelected(true);
        rbtn_ingreso.setText("Ingreso");

        rbtnG_ingresoEgreso.add(rbtn_egreso);
        rbtn_egreso.setText("Egreso");

        btn_añadir.setText("Agregar");
        btn_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_añadirActionPerformed(evt);
            }
        });

        descripcionTXT.setColumns(20);
        descripcionTXT.setRows(5);
        jScrollPane2.setViewportView(descripcionTXT);

        lbl_descripcion.setText("Descripción");

        btn_agregarCuenta.setText("+");
        btn_agregarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarCuentaActionPerformed(evt);
            }
        });

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
                        .addComponent(btn_cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_aceptar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_cuenta)
                                    .addComponent(lbl_monto))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbox_cuentas, 0, 215, Short.MAX_VALUE)
                                    .addComponent(montoTXT))
                                .addGap(18, 18, 18)
                                .addComponent(btn_agregarCuenta))
                            .addComponent(btn_añadir))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rbtn_ingreso)
                                .addGap(31, 31, 31)
                                .addComponent(rbtn_egreso))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbl_descripcion)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbox_cuentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_cuenta)
                            .addComponent(btn_agregarCuenta))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_monto)
                            .addComponent(montoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_añadir))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_descripcion, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtn_ingreso)
                            .addComponent(rbtn_egreso)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_aceptar)
                    .addComponent(btn_cancelar))
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

    private void btn_agregarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarCuentaActionPerformed
        this.jframe_agregarCuenta.setVisible(true);
    }//GEN-LAST:event_btn_agregarCuentaActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
        estafecha = null;
        descripcion = null;
        factory.baseDatos().numPartida = 0;
        trancuenta.clear();
        traningre.clear();
        tranegre.clear();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_cancelarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarCuentaActionPerformed
        this.jframe_agregarCuenta.setVisible(false);
        BaseDatos base = factory.baseDatos();
        ArrayList<String> tipoCuentas = base.listarCuentas();
        this.cbox_cuentas.removeAllItems();
        for (int i = 0; i < tipoCuentas.size(); i++) {
            this.cbox_cuentas.addItem(tipoCuentas.get(i));
        }
    }//GEN-LAST:event_btn_cancelarCuentaActionPerformed

    private void cargarComboBox() {
        this.jframe_agregarCuenta.setVisible(false);
        BaseDatos base = factory.baseDatos();
        ArrayList<String> tipoCuentas = base.listarCuentas();
        this.cbox_cuentas.removeAllItems();
        for (int i = 0; i < tipoCuentas.size(); i++) {
            this.cbox_cuentas.addItem(tipoCuentas.get(i));
        }
    }

    private void apertura(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_apertura
        BaseDatos base = factory.baseDatos();
        ArrayList<String> tipoCuentas = base.listarCuentas();
        this.cbox_cuentas.removeAllItems();
        for (int i = 0; i < tipoCuentas.size(); i++) {
            this.cbox_cuentas.addItem(tipoCuentas.get(i));
        }
    }//GEN-LAST:event_apertura

    private void btn_agregarCuentaOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarCuentaOKActionPerformed
        String nombreCuenta = this.txt_nombreCuenta.getText();
        String clasificacion = this.cbox_clasificacionCuenta.getSelectedItem().toString();
        String tipoSaldo = null;
        if (this.rbtn_deudor.isSelected()) {
            tipoSaldo = "Deudor";
        } else {
            if (this.rbtn_acreedor.isSelected()) {
                tipoSaldo = "Acreedor";
            }
        }

        Cuenta cuenta = factory.cuenta(nombreCuenta, clasificacion, tipoSaldo, 0);
        BaseDatos baseDatos = factory.baseDatos();
        baseDatos.registrarCuenta(cuenta);
    }//GEN-LAST:event_btn_agregarCuentaOKActionPerformed

    private void cerrarCuadroCuenta(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerrarCuadroCuenta
        BaseDatos base = factory.baseDatos();
        ArrayList<String> tipoCuentas = base.listarCuentas();
        this.cbox_cuentas.removeAllItems();
        for (int i = 0; i < tipoCuentas.size(); i++) {
            this.cbox_cuentas.addItem(tipoCuentas.get(i));
        }
    }//GEN-LAST:event_cerrarCuadroCuenta

    private void focusGained(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_focusGained
        this.cargarComboBox();
    }//GEN-LAST:event_focusGained

    private void btn_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_añadirActionPerformed
        //para agrega partidas
        //agregar datos a la tabla
        
        String cuenta = (String)cbox_cuentas.getSelectedItem(); //accede al tipo de cuenta
        float monto = parseFloat(montoTXT.getText()); //el monto a cargar 
        descripcion = descripcionTXT.getText(); //optiene la descripcion
        boolean lado; //para saber si va en el debe o en el haber
        float debe = 0, haber =  0;
        
        
        if(this.rbtn_ingreso.isSelected()){
            debe = monto;
        }else{
            haber = monto;
        }
        Object filaNueva[] = {null, cuenta, debe, haber};
        modeloTabla.addRow(filaNueva);
        
        
        trancuenta.add(cuenta);
        traningre.add(Float.toString(debe));
        tranegre.add(Float.toString(haber));
    }//GEN-LAST:event_btn_añadirActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        // TODO add your handling code here:
        //para validar todo
        float sumadebe =0;
        float sumahaber =0;
        float ingresos = 0, egresos = 0;
        
        //suma toda la columna de los ingresos
        for(int i=1; i<tabla_NuevaEntrada.getRowCount(); i++){
            float sumatoria =0;
            sumatoria = parseFloat(tabla_NuevaEntrada.getValueAt(i, 2).toString());
            sumadebe += sumatoria;
        }
        ingresos = sumadebe; //los ingresos totales
        
        //suma toda la columna de los egresos
        for(int i=1; i<tabla_NuevaEntrada.getRowCount(); i++){
            float sumatoria1 =0;
            sumatoria1 = parseFloat(tabla_NuevaEntrada.getValueAt(i, 3).toString());
            sumahaber += sumatoria1;
        }
        egresos = sumahaber; //los egresos totales
        
        
        try {
            factory.baseDatos().numeroPartida();
        } catch (SQLException ex) {
            Logger.getLogger(NuevaEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(ingresos == egresos){
            
            for(int i=0; i < trancuenta.size(); i++){
                System.out.print("\n");
                System.out.print("numero partida: "+factory.baseDatos().numPartida +"\n");
                System.out.print("empresa: " +factory.baseDatos().idempresa +"\n");
                System.out.print("cuenta: " + trancuenta.get(i) + "\n");
                factory.baseDatos().obtenerID(trancuenta.get(i));
                System.out.print("ingresos: " + traningre.get(i) + "\n");
                System.out.print("egresos: " + tranegre.get(i) + "\n");
                System.out.print("\n");
            }
            
            
            JOptionPane.showMessageDialog(this, "Son iguales los ingresos con los egresos");
            Partida partidaOBJ = factory.partida(WIDTH, factory.baseDatos().idempresa, factory.baseDatos().idusuario
                    , factory.baseDatos().numPartida , estafecha, descripcion, ingresos, egresos);
            
            factory.baseDatos().nuevaPartida(partidaOBJ);
            factory.baseDatos().nuevaTransaccion();
            
            System.out.print("id = " + partidaOBJ.getId());
            System.out.print("empresa = " + partidaOBJ.getEmpresaID());
            System.out.print("usuario = " + partidaOBJ.getUsuarioID());
            System.out.print("numPartida = " + partidaOBJ.getNumPartida());
            System.out.print("fecha = " + partidaOBJ.getFecha());
            System.out.print("descripcion = " + partidaOBJ.getDescripcion());
            System.out.print("ingresos = " + partidaOBJ.getTotalIngresos());
            System.out.print("egresos = " + partidaOBJ.getTotalEgresos());
            
        }else if(ingresos > egresos){
            JOptionPane.showMessageDialog(this, "Los ingresos son mayores que los egresos");
        }else{
            JOptionPane.showMessageDialog(this, "Los egresos son mayores que los ingresos");
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

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
            java.util.logging.Logger.getLogger(NuevaEntrada.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaEntrada.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaEntrada.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaEntrada.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaEntrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregarCuenta;
    private javax.swing.JButton btn_agregarCuentaOK;
    private javax.swing.JButton btn_añadir;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cancelarCuenta;
    private javax.swing.JComboBox<String> cbox_clasificacionCuenta;
    private javax.swing.JComboBox<String> cbox_cuentas;
    private javax.swing.JTextArea descripcionTXT;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFrame jframe_agregarCuenta;
    private javax.swing.JLabel lbl_clasificacion;
    private javax.swing.JLabel lbl_cuenta;
    private javax.swing.JLabel lbl_descripcion;
    private javax.swing.JLabel lbl_monto;
    private javax.swing.JLabel lbl_nombreCuenta;
    private javax.swing.JLabel lbl_tipoSaldo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JTextField montoTXT;
    private javax.swing.ButtonGroup rbtnG_deudorAcreedor;
    private javax.swing.ButtonGroup rbtnG_ingresoEgreso;
    private javax.swing.JRadioButton rbtn_acreedor;
    private javax.swing.JRadioButton rbtn_deudor;
    private javax.swing.JRadioButton rbtn_egreso;
    private javax.swing.JRadioButton rbtn_ingreso;
    private javax.swing.JTable tabla_NuevaEntrada;
    private javax.swing.JTextField txt_nombreCuenta;
    // End of variables declaration//GEN-END:variables
}
