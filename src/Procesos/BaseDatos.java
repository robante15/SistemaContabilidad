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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //Verifica la existencia de la carpeta en el cual se creará la base de Datos
    public void verificarDirectorio() {
        if (folder.isDirectory()) {
            System.out.println("El directorio existe");

        } else {
            System.out.println("El directorio no existe");
            if (folder.mkdir()) {
                System.out.println("Se ha creado el directorio correctamente");
            } else {
                System.out.println("Error: No se ha creado el directorio");
            }
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

    /*
    Este metodo lo que hace es la validación del Login, el metodo recibe un nombre de usuario y una contraseña, lo que hace es verificar si en la base de datos
    existe dicho usuario y conicide la contraseña, si eso es cierto, nada más retorna un Booleano TRUE diciendo que a huevo existe ese usuario con esa contra
    y que puede proceder el programa con la siguiente parte del codigo, que sería validar el login, y abrir la pantalla principal (GUI -> Principal)
     */
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

    /*
    Este metodo crea una nueva tabla de usuario, no hace ninguna corrovoración de que si ya existe, debido a que si ya existe el SQL tirará un error
    entonces el try-catch lo recogerá, y no hará nada, por ende no hay ningun problema :v )
     */
    public void crearTablaUsuario(String nombreTabla) throws SQLException {
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

    /*
    Este metodo crea una nueva tabla de empresas, no hace ninguna corrovoración de que si ya existe, debido a que si ya existe el SQL tirará un error
    entonces el try-catch lo recogerá, y no hará nada, por ende no hay ningun problema :v )
     */
    public void crearTablaEmpresas(String nombreTabla) throws SQLException {
        try {
            Statement sentencia = null;
            sentencia = connect.createStatement();
            String sql = "CREATE TABLE " + nombreTabla + " (\n"
                    + "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                    + "    nombre_empresa TEXT NOT NULL,\n"
                    + "    forma_juridica TEXT NOT NULL,\n"
                    + "    fecha_constitucion TEXT  NOT NULL,\n"
                    + "    direccion TEXT NOT NULL,\n"
                    + "    correo TEXT NOT NULL,\n"
                    + "    registro_legal TEXT NOT NULL,\n"
                    + "    telefono INTEGER NOT NULL,\n"
                    + "    dueno TEXT NOT NULL,\n"
                    + "    sector_actividad TEXT NOT NULL,\n"
                    + "    resumen_negocio TEXT NOT NULL);";
            sentencia.execute(sql);
            sentencia.close();
            connect.close();
            System.out.println("Exito al crear la tabla");
        } catch (Exception e) {
            System.out.println("Error al crear la tabla o que ya estaba creada");
        }
    }

    /*
    Este metodo crea una nueva tabla de cuentas, no hace ninguna corrovoración de que si ya existe, debido a que si ya existe el SQL tirará un error
    entonces el try-catch lo recogerá, y no hará nada, por ende no hay ningun problema :v )
     */
    public void crearTablaCuentas(String nombreTabla) throws SQLException {
        try {
            Statement sentencia = null;
            sentencia = connect.createStatement();
            String sql = "CREATE TABLE " + nombreTabla + " (\n"
                    + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                    + "nombre_cuenta TEXT NOT NULL,\n"
                    + "tipoSaldo TEXT NOT NULL,\n"
                    + "clasificacion TEXT NOT NULL\n"
                    + ")";
            sentencia.execute(sql);
            sentencia.close();
            connect.close();
            System.out.println("Exito al crear la tabla");
        } catch (Exception e) {
            System.out.println("Error al crear la tabla o que ya estaba creada");
        }
    }

    //Retorna un objeto de tipo Usuario nada más ingresandole un UserName, sirve para pasarle un objeto de tipo usuario al FormPrincipal
    public Usuario obtenerUsuario(String usuario) {
        factory = new Factory();
        Usuario perfil = null;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "'";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombres = rs.getString("nombre");
                String apellidos = rs.getString("apellido");
                int empresa = rs.getInt("empresa");
                String userName = rs.getString("usuario");
                String contrasena = null;
                String correo = rs.getString("correo");
                int telefono = rs.getInt("telefono");
                int codEmpleado = rs.getInt("codEmpleado");
                String rol = rs.getString("rol");

                perfil = factory.usuario(id, nombres, apellidos, empresa, userName, contrasena, correo, telefono, codEmpleado, rol);
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
        return perfil;
    }

    //Retorna un objeto de tipo cuenta según el nombre de cuenta que uno le ingrese, es utilizado a la hora de relacionar las partidas, con las transacciones
    public Cuenta obtenerCuenta(String cuentaName) {
        factory = new Factory();
        Cuenta cuenta = null;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM cuentas WHERE nombre_cuenta = '" + cuentaName + "'";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre_cuenta = rs.getString("nombre_cuenta");
                String tipoSaldo = rs.getString("tipoSaldo");
                String clasificacion = rs.getString("clasificacion");

                cuenta = factory.cuenta(nombre_cuenta, clasificacion, tipoSaldo, id);
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
        return cuenta;
    }

    //Retorna un objeto de tipo cuenta según el nombre de cuenta que uno le ingrese, es utilizado a la hora de relacionar las partidas, con las transacciones
    public String obtenerNombreCuentaSegunID(int idCuenta) {
        factory = new Factory();
        String nombre_cuenta = null;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT nombre_cuenta FROM cuentas WHERE id = " + idCuenta + "";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                nombre_cuenta = rs.getString("nombre_cuenta");

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
        return nombre_cuenta;
    }

    //Retorna un objeto empresa, especificando el nombre de la empresa
    public Empresa obtenerEmpresa_SegunNombre(String nombre_empresa) {
        factory = new Factory();
        Empresa empresa = null;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM empresas WHERE nombre_empresa='" + nombre_empresa + "'";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nomre_empresa = rs.getString("nombre_empresa");
                String forma_juridica = rs.getString("forma_juridica");
                String fecha_constitucion = rs.getString("fecha_constitucion");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                String registro_legal = rs.getString("registro_legal");
                int telefono = rs.getInt("telefono");
                String dueno = rs.getString("dueno");
                String sector_actividad = rs.getString("sector_actividad");
                String resumen_negocio = rs.getString("resumen_negocio");

                empresa = factory.empresa(id, nomre_empresa, forma_juridica, fecha_constitucion, direccion, correo, registro_legal, telefono, dueno, sector_actividad, resumen_negocio);
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
        return empresa;
    }

    //Retorna un objeto empresa, especificando el ID de la empresa
    public Empresa obtenerEmpresa_SegunID(int idEmpresa) {
        factory = new Factory();
        Empresa empresa = null;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM empresas WHERE id=" + idEmpresa + "";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nomre_empresa = rs.getString("nombre_empresa");
                String forma_juridica = rs.getString("forma_juridica");
                String fecha_constitucion = rs.getString("fecha_constitucion");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                String registro_legal = rs.getString("registro_legal");
                int telefono = rs.getInt("telefono");
                String dueno = rs.getString("dueno");
                String sector_actividad = rs.getString("sector_actividad");
                String resumen_negocio = rs.getString("resumen_negocio");

                empresa = factory.empresa(id, nomre_empresa, forma_juridica, fecha_constitucion, direccion, correo, registro_legal, telefono, dueno, sector_actividad, resumen_negocio);
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
        return empresa;
    }

    //Obtiene la cantidad de transacciones que tiene una partida segun su ID
    public int cantidadTransaccionesSegunPartida(int idPartida) {
        int cantidadTransacciones = 0;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT COUNT(id)\n"
                    + "FROM transacciones\n"
                    + "WHERE idPartida = " + idPartida + "";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                cantidadTransacciones = rs.getInt(1);

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
        return cantidadTransacciones;
    }

    //Retorna un objeto de tipo cuenta según el nombre de cuenta que uno le ingrese, es utilizado a la hora de relacionar las partidas, con las transacciones
    public Partida obtenerPartida(int partidaID) {
        factory = new Factory();
        Partida partida = null;
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM partidas WHERE id = " + partidaID + "";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                int empresaID = rs.getInt("empresaID");
                int usuarioID = rs.getInt("usuarioID");
                int numPartida = rs.getInt("numPartida");
                String fecha = rs.getString("fecha");
                String descripcion = rs.getString("descripcion");
                float totalIngresos = rs.getFloat("totalIngresos");
                float totalEgresos = rs.getFloat("totalEgresos");

                partida = factory.partida(id, empresaID, usuarioID, numPartida, fecha, descripcion, totalIngresos, totalEgresos);
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
        return partida;
    }

    //Retorna un array con las transacciones realizadas por una empresa determinada
    public ArrayList<Transaccion> obtenerTransacciones_SegunEmpresa(int empresaID) {
        factory = new Factory();
        ArrayList<Transaccion> TransaccionesSegunEmpresa = new ArrayList<Transaccion>();
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT transacciones.id, transacciones.idPartida, transacciones.cuenta, transacciones.transaccionIngreso, transacciones.transaccionEgresos\n"
                    + "FROM transacciones \n"
                    + "JOIN partidas ON transacciones.idPartida = partidas.id\n"
                    + "WHERE partidas.empresaID = " + empresaID + "\n"
                    + "ORDER BY transacciones.idPartida ASC";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                int idPartida = rs.getInt("idPartida");
                int cuenta = rs.getInt("cuenta");
                float trasaccionIngreso = rs.getFloat("transaccionIngreso");
                float transaccionEgresos = rs.getFloat("transaccionEgresos");

                Transaccion transaccionOBJ = factory.transaccion(id, idPartida, cuenta, trasaccionIngreso, transaccionEgresos);

                TransaccionesSegunEmpresa.add(transaccionOBJ);
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
        return TransaccionesSegunEmpresa;
    }

    //Retorna un array con las partidas realizadas por una empresa determinada
    public ArrayList<Partida> obtenerPartidas_SegunEmpresa(int empresaID) {
        factory = new Factory();
        ArrayList<Partida> ListadoPartidas = new ArrayList<Partida>();
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * \n"
                    + "FROM partidas\n"
                    + "WHERE empresaID = " + empresaID + "";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                int IDempresa = rs.getInt("empresaID");
                int usuarioID = rs.getInt("usuarioID");
                int numPartida = rs.getInt("numPartida");
                String fecha = rs.getString("fecha");
                String descripcion = rs.getString("descripcion");
                float totalIngresos = rs.getFloat("totalIngresos");
                float totalEgresos = rs.getFloat("totalEgresos");

                Partida partidaOBJ = factory.partida(id, empresaID, usuarioID, numPartida, fecha, descripcion, totalIngresos, totalEgresos);

                ListadoPartidas.add(partidaOBJ);
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
        return ListadoPartidas;
    }

    //Retorna un array de transacciones realizadas y la fecha de realización
    public ArrayList<TransaccionPopulada> obtenerTransaccionesTODOS() {
        factory = new Factory();
        ArrayList<TransaccionPopulada> todasTransacciones = new ArrayList<TransaccionPopulada>();
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT transacciones.*, partidas.fecha, partidas.numPartida, cuentas.nombre_cuenta\n"
                    + "FROM transacciones \n"
                    + "JOIN partidas ON transacciones.idPartida = partidas.id\n"
                    + "JOIN cuentas ON transacciones.cuenta = cuentas.id\n"
                    + "ORDER BY partidas.fecha DESC ";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                int idPartida = rs.getInt("idPartida");
                int numPartida = rs.getInt("numPartida");
                float trasaccionIngreso = rs.getFloat("transaccionIngreso");
                float transaccionEgresos = rs.getFloat("transaccionEgresos");
                String fecha = rs.getString("fecha");
                String nombre_cuenta = rs.getString("nombre_cuenta");

                Date date = null;
                DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

                //Es sumamente ineficiente pero lo que hace es convertir de un tipo de fecha a otro más bunito
                try {
                    date = format.parse(fecha);

                } catch (ParseException ex) {
                    Logger.getLogger(BaseDatos.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
                String fecha2 = df.format(date);

                TransaccionPopulada TransaccionPopuladaOBJ = factory.transaccionPopulada(id, idPartida, numPartida, trasaccionIngreso, transaccionEgresos, fecha2, nombre_cuenta);

                todasTransacciones.add(TransaccionPopuladaOBJ);
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
        return todasTransacciones;
    }

    /*
    Este metodo lo que hace es devolver un array con los nombres de todas las cuentas que hay registradas, esto sirve para que en el registro de una nueva cuenta se pueda
    mostrar un ComboBox con todas las cuentas que hay registradas en la BD
     */
    public ArrayList<String> listarCuentas() {
        factory = new Factory();
        ArrayList<String> listaCuentas = new ArrayList<String>();
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM cuentas ORDER BY nombre_cuenta ASC";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                String nombre_empresa = rs.getString("nombre_cuenta");

                listaCuentas.add(nombre_empresa);
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
        return listaCuentas;
    }

    /*
    Este metodo lo que hace es devolver un array con los nombres de todas las empresas que hay registradas, esto sirve para que en el registro de usuario se pueda
    mostrar un ComboBox con todas las empresas que hay registradas en la BD
     */
    public ArrayList<String> listarEmpresas() {
        factory = new Factory();
        ArrayList<String> listaEmpresas = new ArrayList<String>();
        try {
            if (osName.equals("linux")) {
                System.out.println("Este sistema esta diseñado para correr en Windows");
                //connect = DriverManager.getConnection("jdbc:sqlite:" + urlLinux);
            } else {
                connect = DriverManager.getConnection(url);
            }
            String SQLQuery = "SELECT * FROM empresas ORDER BY nombre_empresa ASC";
            st = connect.prepareStatement(SQLQuery);
            rs = st.executeQuery();

            while (rs.next()) {

                String nombre_empresa = rs.getString("nombre_empresa");

                listaEmpresas.add(nombre_empresa);
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
        return listaEmpresas;
    }

    //Metodos de insersion a la Base de Datos
    /*
    Este metodo recoge un objeto de tipo 'Empresa' el cual es una entidad ubicada en Entidades -> Empresa, este objeto contiene todos los datos de la empresa (nombre,
    form juridica, fecha de constitución, etc) y lo que hace es enviar eso a la base de datos para guardar el registro de la empresa
     */
    public void registrarEmpresa(Empresa empresa) {

        try {
            this.crearTablaEmpresas("empresas");
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
            String SQLQuery = "INSERT INTO empresas (nombre_empresa , forma_juridica , fecha_constitucion , direccion , correo , registro_legal , telefono, dueno , sector_actividad, resumen_negocio ) VALUES(?,?,?,?,?,?,?,?,?,?)";
            st = connect.prepareStatement(SQLQuery);

            st.setString(1, empresa.getNomre_empresa());
            st.setString(2, empresa.getForma_juridica());
            st.setString(3, empresa.getFecha_constitucion());
            st.setString(4, empresa.getDireccion());
            st.setString(5, empresa.getCorreo());
            st.setString(6, empresa.getRegistro_legal());
            st.setInt(7, empresa.getTelefono());
            st.setString(8, empresa.getDueno());
            st.setString(9, empresa.getSector_actividad());
            st.setString(10, empresa.getResumen_negocio());
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

    /*
    Este metodo recoge un objeto de tipo 'Usuario' el cual es una entidad ubicada en Entidades -> Usuario, este objeto contiene todos los datos del usuario (nombre,
    apellido, empresa en la que trabaja, etc) y lo que hace es enviar eso a la base de datos para guardar el registro del usuario
     */
    public void registrarUsuario(Usuario usuario) {

        try {
            this.crearTablaUsuario("usuarios");
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
            st.setInt(3, usuario.getEmpresa());
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

    /*
    Este metodo recoge un objeto de tipo 'Cuenta' el cual es una entidad ubicada en Entidades -> Cuenta, este objeto contiene todos los datos de la cuenta (nombre de cuenta,
    tipo de saldo y clasificación) y lo que hace es enviar eso a la base de datos para guardar el registro de la nueva cuenta
     */
    public void registrarCuenta(Cuenta cuenta) {

        try {
            this.crearTablaUsuario("usuarios");
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
            String SQLQuery = "INSERT INTO cuentas (nombre_cuenta, tipoSaldo, clasificacion) VALUES (?,?,?)";
            st = connect.prepareStatement(SQLQuery);

            st.setString(1, cuenta.getNomreCuenta());
            st.setString(2, cuenta.getTipoSaldo());
            st.setString(3, cuenta.getClasificacion());
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
