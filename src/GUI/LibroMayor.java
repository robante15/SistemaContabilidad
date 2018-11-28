/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.Usuario;
import Factory.Factory;
import Procesos.BaseDatos;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
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
                Ingreso = Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf((String)modeloTabla.getValueAt(i, 0))));
                Egreso =  Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf((String)modeloTabla.getValueAt(i, 0))));
                Saldo =  Ingreso - Egreso;
                
                
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
                                Ingreso += Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1))));
                                Egreso +=  Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1))));
                            break;
                        case "CAJA":
                                Ingreso += Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1))));
                                Egreso +=  Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1))));
                            break;
                        case "CUENTA CORRIENTE":
                                Ingreso += Double.valueOf(base.ObtenerIngresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1))));
                                Egreso +=  Double.valueOf(base.ObtenerEgresosDeCuenta(USUARIO.getEmpresa(), Integer.valueOf(Listado.get(j - 1))));
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(105, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
