/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author roban
 */
public class BaseDatos {

    String homeUsuario = System.getProperty("user.home"); //Obtiene el directorio Home del Usuario
    File folder = new File(homeUsuario + "\\AppData\\Local\\SistemaContable"); //En el directorio Home verifica la existencia de la carpeta SistemasContables
    String url = "jdbc:sqlite:" + homeUsuario + "\\AppData\\Local\\SistemaContable\\SistemaContable.db";
    String osName = System.getProperty("os.name").toLowerCase();
    Connection connect;
    PreparedStatement st = null;
    ResultSet rs = null;

    //Verifica la existencia del directorio en el cual se creará la base de Datos
    public void verificarDirectorio() {
        if (folder.isDirectory()) {
            System.out.println("El directorio existe");

        } else {
            System.out.println("El directorio no existe");
            folder.mkdir();
        }
    }

    public void connect() {
        try {

            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            //System.out.print(Conector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Error al cerrar la conexion");
        }
    }

    //Crea la base de Datos (Comprobando primero que el directorio exista)
    public void crearBaseDatos() {
        this.verificarDirectorio();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El driver que se esta usando es:" + meta.getDriverName());
                System.out.println("Se ha creado una nueva Base de Datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean ValidarLogin(String usuario, String contra) {

        boolean aproved = false;
        //String SSQL = "SELECT * FROM usuarios WHERE usuario='" + usuario + "' AND clave=sha1('" + contra + "')";
        String SSQL = "SELECT * FROM usuario WHERE usuario='" + usuario + "' AND contrasena='" + contra + "'";
        Connection conect = null;
        try {
            conect = DriverManager.getConnection(url);
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            if (rs.next()) {
                aproved = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex + "Error de conexión");
        } finally {
            try {
                conect.close();
            } catch (SQLException ex) {
                System.out.println(ex + "Error de desconexión");
            }
        }
        return aproved;
    }

    /*public boolean ValidarLogin(String usuario, String contra) {
        //this.crearBaseDatos();
        boolean aproved = false;
        try {
            connect = DriverManager.getConnection(url);
            String SQLQuery = "SELECT * FROM usuario WHERE usuario='" + usuario + "' AND contrasena='" + contra + "'";
            //String SQLQuery = "SELECT * FROM usuario";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();
            System.out.print(rs.next());

            if (rs.next()) {
                System.out.println("Hay un next");
                /*while (rs.next()) {
                    String BD_usuario = rs.getString("usuario");
                    //System.out.print("USUARIO" + BD_usuario);
                    String BD_contra = rs.getString("contrasena");
                    //System.out.print("CONTRA" + BD_contra);
                    if (usuario.equals(BD_usuario) && contra.equals(BD_contra)) {
                        System.out.println("Usuario y contraseña correctos");
                        aproved = true;
                    } else {
                        
                    }
                }
}

} catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return aproved;
    }*/

    public String obtenerRol(String usuario) {
        String rol = "";
        try {
            connect = DriverManager.getConnection(url);
            String SQLQuery = "SELECT rol FROM public.usuario WHERE usr_name='" + usuario + "'";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {
                rol = rs.getString("rol");
                System.out.print(rol);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rol;
    }

}
