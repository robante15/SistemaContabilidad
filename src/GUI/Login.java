/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.Usuario;
import Factory.Factory;
import Procesos.BaseDatos;
import java.awt.Color;

/**
 *
 * @author roban
 */
public class Login extends javax.swing.JFrame {

    private static Factory factory;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        factory = new Factory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel_top = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        jpanel_content = new javax.swing.JPanel();
        lbl_usuario = new javax.swing.JLabel();
        lbl_contrasena = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        lbl_registrarse = new javax.swing.JLabel();
        txt_contrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel_top.setBackground(new java.awt.Color(0, 153, 255));

        lbl_titulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setText("Inicio de Sesión");

        javax.swing.GroupLayout jpanel_topLayout = new javax.swing.GroupLayout(jpanel_top);
        jpanel_top.setLayout(jpanel_topLayout);
        jpanel_topLayout.setHorizontalGroup(
            jpanel_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_topLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(lbl_titulo)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jpanel_topLayout.setVerticalGroup(
            jpanel_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_topLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_titulo)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jpanel_top, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        jpanel_content.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_usuario.setText("Usuario");
        jpanel_content.add(lbl_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        lbl_contrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_contrasena.setText("Contraseña");
        jpanel_content.add(lbl_contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        txt_usuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jpanel_content.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 190, -1));

        btn_aceptar.setBackground(new java.awt.Color(0, 153, 255));
        btn_aceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_aceptar.setForeground(new java.awt.Color(255, 255, 255));
        btn_aceptar.setText("Aceptar");
        btn_aceptar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_aceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_aceptarMouseExited(evt);
            }
        });
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jpanel_content.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 110, 40));

        btn_cancelar.setBackground(new java.awt.Color(0, 153, 255));
        btn_cancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cancelarMouseExited(evt);
            }
        });
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jpanel_content.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 110, 40));

        lbl_registrarse.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lbl_registrarse.setForeground(new java.awt.Color(0, 51, 255));
        lbl_registrarse.setText("Registrarse");
        lbl_registrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_registrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_registrarseMouseClicked(evt);
            }
        });
        jpanel_content.add(lbl_registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        txt_contrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jpanel_content.add(txt_contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 190, -1));

        getContentPane().add(jpanel_content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, 380, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        String user = this.txt_usuario.getText();
        String contrasena = String.valueOf(this.txt_contrasena.getPassword());
        BaseDatos baseDatos = factory.baseDatos();
        boolean aprovada = baseDatos.ValidarLogin(user, contrasena);

        if (aprovada == true) {
            System.out.print("Ha sido validado el inicio de sesión");
            Principal principal = factory.principal();
            principal.setVisible(true);
            this.dispose();
        } else {
            System.out.print(aprovada + "No se ha sido validado el inicio de sesión");
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void lbl_registrarseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_registrarseMouseClicked
        RegistroUsuario registroUsuario = factory.registroUsuario();
        registroUsuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbl_registrarseMouseClicked

    private void btn_aceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aceptarMouseEntered
        this.btn_aceptar.setBackground(Color.decode("#01579B"));
    }//GEN-LAST:event_btn_aceptarMouseEntered

    private void btn_aceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aceptarMouseExited
        this.btn_aceptar.setBackground(Color.decode("#0099FF"));
    }//GEN-LAST:event_btn_aceptarMouseExited

    private void btn_cancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelarMouseEntered
        this.btn_cancelar.setBackground(Color.decode("#01579B"));
    }//GEN-LAST:event_btn_cancelarMouseEntered

    private void btn_cancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelarMouseExited
        this.btn_cancelar.setBackground(Color.decode("#0099FF"));
    }//GEN-LAST:event_btn_cancelarMouseExited

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JPanel jpanel_content;
    private javax.swing.JPanel jpanel_top;
    private javax.swing.JLabel lbl_contrasena;
    private javax.swing.JLabel lbl_registrarse;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_usuario;
    private javax.swing.JPasswordField txt_contrasena;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
