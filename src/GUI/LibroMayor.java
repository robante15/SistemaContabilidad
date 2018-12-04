/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.Usuario;
import Factory.Factory;
import Procesos.BaseDatos;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian Cubias
 */
public class LibroMayor extends javax.swing.JFrame {

    /**
     * Creates new form LibroMayor
     */
    private static Factory factory;
    static Usuario USUARIO;
    DefaultTableModel modeloTabla = new DefaultTableModel();
    
    public LibroMayor(Usuario usuario) {
        initComponents();
        USUARIO = usuario;
        factory = new Factory();
        this.setLocationRelativeTo(null);
        this.setTitle("Libro mayor");
        
        /* CARGA DE DATOS */
        
        //Agregamos las columnas a la tabla
        modeloTabla.addColumn("ID cuenta");
        modeloTabla.addColumn("Cuenta");
        modeloTabla.addColumn("Debe");
        modeloTabla.addColumn("Haber");
        modeloTabla.addColumn("Saldo");
        
        //Agregamos las filas a la tabla
        
       BaseDatos base = factory.baseDatos();
       ArrayList<String> Listado = base.ObtenerIDCuentaEnTransacciones(usuario.getEmpresa());
       Set<String> EliminarRepetidos = new LinkedHashSet<>();
       
       EliminarRepetidos.addAll(Listado);
       Listado.clear();
       Listado.addAll(EliminarRepetidos);
       
       
       ArrayList<String> ListadoAUX = new ArrayList<>(Listado);
       int Contador = 0;
       int ContadorAUX = 0;
       Double Saldo;
       Double Ingreso;
       Double Egreso;
       Double SaldoTotal = 0.0;
       Double IngresoTotal = 0.0;
       Double EgresoTotal = 0.0;
       Boolean AgregadaEyE = false;
       
       Double CFI_IN = 0.0;
       Double CFI_EG = 0.0;
       Double DFI_IN = 0.0;
       Double DFI_EG = 0.0;
       Double IVA_TOTAL = 0.0;
       
       modeloTabla.setRowCount(Listado.size() );
       
        System.out.println("\nDATOS DE ARRAY:\n");
       for(int i = 0; i < ListadoAUX.size(); i++)
       {
           System.out.println("\ni = " + i + ": " + Listado.get(i)); 
           
       }
       
       
       TOTAL:
       for(int i = 0; Contador < ListadoAUX.size(); i++)
       {
           ContadorAUX = i + 1;
           OUTER:
           for(int j = 0; j < 2; j++)
           {
               
               if(ListadoAUX.get(Contador).toUpperCase().equals("CAJA") || ListadoAUX.get(Contador).toUpperCase().equals("BANCO") || ListadoAUX.get(Contador).toUpperCase().equals("CUENTA CORRIENTE"))
               {
                   if (!AgregadaEyE) {
                            AgregadaEyE = true;
                            modeloTabla.setValueAt("--", i, 0);
                            modeloTabla.setValueAt("Efectivo y equivalentes", i, 1);            
                            Contador++;      
                            break OUTER;
                        } else {
                            modeloTabla.setValueAt(null, i, 0);
                            Contador++;
                            i--;
                            break OUTER;
                        }
               }

               else if(ListadoAUX.get(Contador).toUpperCase().equals("IVA POR PAGAR") || ListadoAUX.get(Contador).toUpperCase().equals("RESERVA LEGAL")
                       || ListadoAUX.get(Contador).toUpperCase().equals("UTILIDAD NETA") || ListadoAUX.get(Contador).toUpperCase().equals("IMPUESTO SOBRE RENTA")
                       || ListadoAUX.get(Contador).toUpperCase().equals("REMANENTE DE IVA"))
               {
                        modeloTabla.setValueAt(null, i, 0);
                        Contador++;
                        ContadorAUX--;
                        i--;
                        break OUTER;
               }
               else
               {
                    modeloTabla.setValueAt(ListadoAUX.get(Contador), i, j);                      
                    Contador++;     
               }

               
               //Filtro para no agregar ciertas cuentas al libro mayor, para aÃ±adir una cuenta, solo agregar un case enmedio de los demÃ¡s
                /* switch (ListadoAUX.get(Contador).toUpperCase()) {
                    case "CAJA": //Para quitar un filtro, solo borrar el case
                    //Para agregar filtro, quitar este comentario y colocar otro case con el nombre de la cuenta en mayÃºsculas
                    case "BANCO":
                        if (!AgregadaEyE) {
                            AgregadaEyE = true;
                            modeloTabla.setValueAt("--", i, 0);
                            modeloTabla.setValueAt("Efectivo y equivalentes", i, 1);
                            Contador++;
                            ListadoAUX.remove(Contador - 1);
                            ListadoAUX.remove(Contador);

                            break OUTER;
                        } else {
                            Contador++;
                            i--;
                            break OUTER;
                        }
                    case "CREDITO FISCAL IVA":
                    case "DEBITO FISCAL IVA":
                    case "IVA POR PAGAR":
                    case "RESERVA LEGAL":
                    case "UTILIDAD NETA":
                    case "IMPUESTO SOBRE RENTA":
                    case "REMANENTE DE IVA":
                        Contador++;   
                        i--;
                        break OUTER;
                    default:
                        System.out.println("VALOR A ESCRIBIR: " + ListadoAUX.get(Contador));
                        modeloTabla.setValueAt(ListadoAUX.get(Contador), i, j);                      
                        Contador++;
                        break;      
                }
               
               */
           }
       }
       
       System.out.println("\nDATOS DE ARRAY FINAL:\n");
       for(int i = 0; i < ListadoAUX.size(); i++)
       {
           System.out.println("\ni = " + i + ": " + Listado.get(i)); 
           
       }
         
            modeloTabla.setNumRows(ContadorAUX + 1);
            modeloTabla.setValueAt("Total: ", ContadorAUX, 0);
      
       
       for(int i = 0; i < ContadorAUX; i++)
       {
           Saldo = 0.0;
           Ingreso = 0.0;
           Egreso = 0.0;
           
            if(!"--".equals((String)modeloTabla.getValueAt(i, 0)))
            {
                Ingreso = (double) Math.round(Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf((String)modeloTabla.getValueAt(i, 0)))));
                Egreso =  (double) Math.round(Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf((String)modeloTabla.getValueAt(i, 0)))));
                Saldo =  (double) Math.round(Ingreso - Egreso);
                
                
                if(((String)modeloTabla.getValueAt(i, 1)).equals("Credito Fiscal IVA"))
                {
                    CFI_IN = Ingreso;
                    CFI_EG = Egreso;
                }
                else if(((String)modeloTabla.getValueAt(i, 1)).equals("Debito Fiscal IVA"))
                {
                    DFI_IN = Ingreso;
                    DFI_EG = Egreso;
                }
                   
                IngresoTotal += Ingreso;
                EgresoTotal += Egreso;
                SaldoTotal += Saldo;
                
                modeloTabla.setValueAt("$" + Ingreso, i, 2);
                modeloTabla.setValueAt("$" + Egreso, i, 3);
                
                if(Saldo < 0)
                {
                    modeloTabla.setValueAt("$" + Math.abs(Saldo) + " (Acreedor)", i, 4);
                }
                else
                {
                    modeloTabla.setValueAt("$" + Math.abs(Saldo) + " (Deudor)", i, 4);
                }
            }
            else
            {
                for(int j = 0; j < Listado.size(); j++)
                {
                    
                    switch(Listado.get(j).toUpperCase()) {
                        case "BANCO":
                                Ingreso += (double) Math.round(Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1)))));
                                Egreso +=  (double) Math.round(Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1)))));
                            break;
                        case "CAJA":
                                Ingreso += (double) Math.round(Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1)))));
                                Egreso +=  (double) Math.round(Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1)))));
                            break;
                        case "CUENTA CORRIENTE":
                                Ingreso += (double) Math.round(Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1)))));
                                Egreso +=  (double) Math.round(Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1)))));
                            break; 
                        default:
                            break;
                    }
                    
                }                
                
                IngresoTotal += Ingreso;
                EgresoTotal += Egreso;
                Saldo =  Ingreso - Egreso;
                SaldoTotal += Saldo;
                   
                modeloTabla.setValueAt("$" + Ingreso, i, 2);
                modeloTabla.setValueAt("$" + Egreso, i, 3);
                
                if(Saldo < 0)
                {
                    modeloTabla.setValueAt("$" + Math.abs(Saldo) + " (Acreedor)", i, 4);
                }
                else if(Saldo > 0)
                {
                    modeloTabla.setValueAt("$" + Math.abs(Saldo) + " (Deudor)", i, 4);
                }
                else if(Saldo == 0)
                {
                    modeloTabla.setValueAt("$" + Math.abs(Saldo) + " (Nulo)", i, 4);
                }
            }   
       }

            modeloTabla.setValueAt("$" + IngresoTotal, ContadorAUX, 2);
            modeloTabla.setValueAt("$" + EgresoTotal, ContadorAUX, 3);

            
           if(SaldoTotal < 0)
            {
                modeloTabla.setValueAt("$" + Math.abs(SaldoTotal) + " (Acreedor)", ContadorAUX, 4);
            }
            else if(SaldoTotal > 0)
            {
                modeloTabla.setValueAt("$" + Math.abs(SaldoTotal) + " (Deudor)", ContadorAUX, 4);
            }
            else if (SaldoTotal == 0)
            {
                modeloTabla.setValueAt("$" + Math.abs(SaldoTotal) + " (Nulo)", ContadorAUX, 4);
            }
           
           IVA_TOTAL = (double) Math.abs(Math.round((CFI_IN - CFI_EG)) - Math.abs(Math.round(DFI_IN - DFI_EG)));
           LB_CFI.setText("$ " + Math.abs(CFI_IN - CFI_EG));
           LB_DFI.setText("$ " + Math.abs(DFI_IN - DFI_EG));
           
           if(IVA_TOTAL > 0)
           {
               LB_TOTAL.setText("$ " + Math.abs(IVA_TOTAL) + " REMANENTE DE IVA");
           }
           else if(IVA_TOTAL <= 0)
           {
               LB_TOTAL.setText("$ " + Math.abs(IVA_TOTAL) + " IVA POR PAGAR");
           }
           
           
           
           
           /* Decoración UI */
        try
        {
            InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Font sizedFont = font.deriveFont(16f);
                   
            LB_EMPRESA.setFont(sizedFont);
            jTable1.setFont(sizedFont);
            
        }
        catch (FontFormatException | IOException ex)
        {
            
        }
        try
        {
            InputStream is = Login.class.getResourceAsStream("/Resources/OpenSans-Bold.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            Font sizedFont = font.deriveFont(16f);
                   
            LB_CFI.setFont(sizedFont);
            LB_DFI.setFont(sizedFont);
            LB_TOTAL.setFont(sizedFont);
        }
        catch (FontFormatException | IOException ex)
        {
            
        }
        
        LB_EMPRESA.setText(base.obtenerEmpresa_SegunID(USUARIO.getEmpresa()).getNomre_empresa().toUpperCase());
        
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
       jTable1.getColumnModel().getColumn(0).setCellRenderer(R);
        
        BTN_Cerrar.setOpaque(false);
        BTN_Cerrar.setContentAreaFilled(false);
        BTN_Cerrar.setBorderPainted(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LB_CFI = new javax.swing.JLabel();
        LB_DFI = new javax.swing.JLabel();
        LB_TOTAL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTN_Cerrar = new javax.swing.JButton();
        LB_EMPRESA = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1500, 1500));
        setMinimumSize(new java.awt.Dimension(1115, 785));
        setPreferredSize(new java.awt.Dimension(1115, 785));
        setResizable(false);
        getContentPane().setLayout(null);

        LB_CFI.setForeground(new java.awt.Color(255, 255, 255));
        LB_CFI.setText("jLabel1");
        getContentPane().add(LB_CFI);
        LB_CFI.setBounds(420, 626, 270, 16);

        LB_DFI.setForeground(new java.awt.Color(255, 255, 255));
        LB_DFI.setText("jLabel2");
        getContentPane().add(LB_DFI);
        LB_DFI.setBounds(420, 666, 270, 16);

        LB_TOTAL.setForeground(new java.awt.Color(255, 255, 255));
        LB_TOTAL.setText("jLabel3");
        getContentPane().add(LB_TOTAL);
        LB_TOTAL.setBounds(420, 704, 270, 20);

        jTable1.setModel(modeloTabla);
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 150, 1070, 410);

        BTN_Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_Cerrar.setOpaque(false);
        BTN_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CerrarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Cerrar);
        BTN_Cerrar.setBounds(890, 660, 200, 60);

        LB_EMPRESA.setForeground(new java.awt.Color(255, 255, 255));
        LB_EMPRESA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LB_EMPRESA.setText("jLabel4");
        LB_EMPRESA.setToolTipText("");
        getContentPane().add(LB_EMPRESA);
        LB_EMPRESA.setBounds(0, 80, 1140, 16);

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/LibroMayor.png"))); // NOI18N
        BG.setMaximumSize(new java.awt.Dimension(1500, 1500));
        BG.setMinimumSize(new java.awt.Dimension(1125, 785));
        BG.setPreferredSize(new java.awt.Dimension(1125, 785));
        getContentPane().add(BG);
        BG.setBounds(0, 0, 1110, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CerrarActionPerformed
       this.dispose();
    }//GEN-LAST:event_BTN_CerrarActionPerformed

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
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibroMayor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibroMayor(USUARIO).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JLabel LB_CFI;
    private javax.swing.JLabel LB_DFI;
    private javax.swing.JLabel LB_EMPRESA;
    private javax.swing.JLabel LB_TOTAL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
