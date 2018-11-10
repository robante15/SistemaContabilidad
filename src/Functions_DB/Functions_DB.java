/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions_DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Cristian Cubias
 */
public class Functions_DB extends Abstract_Functions_DB{

    String UserHome; //Variable que guarda la ruta home del usuario
    String UserName;
    String Role;
    File AppFolder; //Variable que guarda la ruta completa del programa 
    String OSName; //Variable que guarda el nombre del OS
    String DBPath; //Varuable que guarda la ruta de la DB
    Boolean DBCreated = false; //Variable que verifica si se ha creado la DB
    String SQL; //Variable que guarda sentencias SQL
    Connection Connect; //Variable de conexión
    DatabaseMetaData META; //Variable de metadatos
    Statement ST = null; //Variable de statement
    ResultSet RS = null; //Variable de resultados
    
    
    /* --- FUNCIONES SET --- */
    
    
    @Override
    public void SetUserHomeDirectory() {
        UserHome = System.getProperty("user.home");
    }
    
    @Override
    public void SetOSName() {
        OSName = System.getProperty("os.name").toLowerCase();
    }
    
    @Override
    public void SetDBPath(String Path) {
        DBPath = "jdbc:sqlite:" + Path;
    }
    
    @Override
    public void SetCreatedDB(Boolean State) {
        DBCreated = State;
    }
    
    /* --- FUNCIONES GET --- */
    
    
    @Override
    public String GetUserHomeDirectory() {
        return UserHome;
    }

    @Override
    public File GetAppFolder() {
        return AppFolder;
    }
    
    @Override
    public String GetOSName() {
        return OSName;
    }
    
    @Override
    public String GetDBPath() {
        return DBPath;
    }
    
    @Override
    public Boolean GetCreatedDBState() {
        return DBCreated;
    }
    
    @Override
    public String GetUserName() {
        return UserName;
    }


    
    /* --- VALIDACIÓNES --- */
    
    
    @Override
    public Boolean SearchAppFolder(File AppFolder) {
        return (AppFolder.isDirectory());
    }
    
    
    /* --- OTRAS FUNCIONES --- */

    
    @Override
    public void SetAppFolder(String HomeDirectory, String Path) {
        AppFolder = new File(HomeDirectory + Path);
    }
    
     @Override
    public Boolean CreateAppFolder(File AppFolder) {
        return AppFolder.mkdir();
    }
    
    @Override
    public Boolean OpenConnection(String DBPath) {
        try
        {
            Connect = DriverManager.getConnection(DBPath);
            return (Connect != null); 
        }
        catch(SQLException ex)
        {
            return false;
        }
        
    }

    @Override
    public Boolean CloseConnection() {
        try
        {
            Connect.close();
            Connect = null;
            return true; 
        }
        catch(SQLException ex)
        {
            return false;
        }
    }

    @Override
    public Boolean CreateDatabase() {
        
       try
       {
           if(Connect != null)
           {
                META = Connect.getMetaData();
                System.out.println("The driver name is " + META.getDriverName());
                System.out.println("NUEVA DB CREADA!!");
                return true;
           }         
       }
       catch(SQLException ex)
       {
           return false;
       }    
       return false;
    }

    @Override
    public Boolean CreateUserTable() {
        
        //Se crean la tabla de usuarios
        try
        {
            ST = null;
            ST = Connect.createStatement();
            
            SQL = "CREATE TABLE usuarios (\n"
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
            
            ST.execute(SQL);
            ST.close();
            return true;
        }
        catch(SQLException ex)
        {
            return false;
        }
    }
    
     @Override
    public Boolean CreateBussinessTable() {
    //Se crea la tabla empresas
        try
        {
            ST = null;
            ST = Connect.createStatement();
            
            SQL = "CREATE TABLE empresas (\n"
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
            
            ST.execute(SQL);
            ST.close();
            return true;
        }
        catch(SQLException ex)
        {
            return false; 
        }
    }

    @Override
    public Boolean SignIn(String User, String Pass) {
        
        try
        {
            ST = null;
            ST = Connect.createStatement();
            SQL = "SELECT * FROM usuarios WHERE usuario='" + User + "' AND contrasena='" + Pass + "'";
            
            RS = ST.executeQuery(SQL);
            
            if(RS.next())
            {
                UserName = RS.getString("usuario");
                return true;
            }
        }
        catch(SQLException ex)
        {
            
        }
        return false; 
    }

}
