/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions_DB;

import java.io.File;

/**
 *
 * @author Cristian Cubias
 */
public abstract class Abstract_Functions_DB {
   
    
    /* --- Funciones Get --- */
    public abstract String GetUserHomeDirectory();
    public abstract String GetUserName();
    public abstract File GetAppFolder();
    public abstract String GetOSName();
    public abstract String GetDBPath();
    public abstract Boolean GetCreatedDBState();
        
    /* --- Funciones Set --- */
    
    public abstract void SetUserHomeDirectory();
    public abstract void SetOSName();
    public abstract void SetDBPath(String Path);
    public abstract void SetCreatedDB(Boolean State);
        
    /* --- Validaciónes --- */
    public abstract Boolean SearchAppFolder(File AppFolder);
    public abstract Boolean SignIn(String User, String Pass);
    
    /* --- Otras funciones --- */
    
    public abstract void SetAppFolder(String HomeDirectory, String Path);
    public abstract Boolean CreateAppFolder(File AppFolder);
    public abstract Boolean OpenConnection(String DBPath);
    public abstract Boolean CloseConnection();
    public abstract Boolean CreateDatabase();
    public abstract Boolean CreateUserTable();
    public abstract Boolean CreateBussinessTable();
    
      
}
