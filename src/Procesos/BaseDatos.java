/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Entidades.Usuario;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Entidades.*;
import Factory.Factory;

/**
 *
 * @author roban
 */
public class BaseDatos {

    private static Factory factory;

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
        String SSQL = "SELECT * FROM usuarios WHERE usuario='" + usuario + "' AND contrasena='" + contra + "'";
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

    public String obtenerRol(String usuario) {
        String rol = "";
        Connection conect = null;
        try {
            conect = DriverManager.getConnection(url);
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

    //Crear tabla usuario
    public void crearTabla(String nombreTabla) throws SQLException {
        try {
            Statement sentencia = null;
            sentencia = connect.createStatement();
            String sql = "CREATE TABLE " + nombreTabla + " (\n"
                    + "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                    + "    nombre TEXT NOT NULL,\n"
                    + "    apellido TEXT NOT NULL,\n"
                    + "    empresa TEXT  NOT NULL,\n"
                    + "    usuario TEXT NOT NULL,\n"
                    + "    contrasena TEXT NOT NULL,\n"
                    + "    correo TEXT NOT NULL,\n"
                    + "    telefono INTEGER NOT NULL,\n"
                    + "    codEmpleado INTEGER NOT NULL,\n"
                    + "    rol TEXT NOT NULL);";
            sentencia.execute(sql);
            sentencia.close();
            connect.close();
            System.out.println("Exito al crear la tabla");
        } catch (Exception e) {
            System.out.println("Error al crear la tabla o que ya estaba creada");
        }
    }

    //Metodos de insersion a la Base de Datos
    public void registrarUsuario(Usuario usuario) {

        try {
            this.crearTabla("usuarios");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "INSERT INTO usuarios (nombre, apellido, empresa, usuario, contrasena, correo, telefono, codEmpleado, rol) VALUES (?,?,?,?,?,?,?,?,?)";
            st = connect.prepareStatement(SQLQuery);

            st.setString(1, usuario.getNombres());
            st.setString(2, usuario.getApellidos());
            st.setString(3, usuario.getEmpresa());
            st.setString(4, usuario.getUsuario());
            st.setString(5, usuario.getContrasena());
            st.setString(6, usuario.getCorreo());
            st.setInt(7, usuario.getTelefono());
            st.setInt(8, usuario.getCodEmpleado());
            st.setString(9, usuario.getRol());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nuevo registro agregado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
