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
       ArrayList<String> ListadoAUX = Listado;
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
       
       for(int i = 0; Contador < Listado.size(); i++)
       {
           ContadorAUX = i + 1;
           OUTER:
           for(int j = 0; j < 2; j++)
           {
               //Filtro para no agregar ciertas cuentas al libro mayor, para aÃ±adir una cuenta, solo agregar un case enmedio de los demÃ¡s
                switch (Listado.get(Contador).toUpperCase()) {
                    case "CAJA": //Para quitar un filtro, solo borrar el case
                    //Para agregar filtro, quitar este comentario y colocar otro case con el nombre de la cuenta en mayÃºsculas
                    case "BANCO":
                    case "CUENTA CORRIENTE":
                        if (!AgregadaEyE) {
                            AgregadaEyE = true;
                            modeloTabla.setValueAt("--", i, 0);
                            modeloTabla.setValueAt("Efectivo y equivalentes", i, 1);
                            Contador++;
                            break OUTER;
                        } else {
                            Contador++;
                            i--;
                            break OUTER;
                        }
                    case "IVA POR PAGAR":
                    case "RESERVA LEGAL":
                    case "UTILIDAD NETA":
                    case "IMPUESTO SOBRE RENTA":
                    case "REMANENTE DE IVA":
                        Contador++;
                        i--;
                        break OUTER;
                    default:
                        modeloTabla.setValueAt(Listado.get(Contador), i, j);
                        Contador++;
                        break;      
                }
           }
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
                    switch (Listado.get(j).toUpperCase()) {
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
                else
                {
                    modeloTabla.setValueAt("$" + Math.abs(Saldo) + " (Deudor)", i, 4);
                }
            }   
       }
       
       modeloTabla.setValueAt("$" + IngresoTotal, ContadorAUX, 2);
       modeloTabla.setValueAt("$" + EgresoTotal, ContadorAUX, 3);
       
        if(SaldoTotal < 0)
        {
            modeloTabla.setValueAt("$" + Math.abs(SaldoTotal) + " (Acreedor)", ContadorAUX, 4);
        }
        else
        {
            modeloTabla.setValueAt("$" + Math.abs(SaldoTotal) + " (Deudor)", Contador, 4);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
