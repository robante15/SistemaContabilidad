/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;
import GUI.*;
import Procesos.*;
import Entidades.*;

/**
 *
 * @author roban
 */
public class Factory {

    public BaseDatos baseDatos() {
        return new BaseDatos();
    }
    
     /*----------------------CARGA DE LAS GUI-----------------------------*/
    public Principal principal(){
        return new Principal();
    }
    
    public RegistroUsuario registroUsuario(){
        return new RegistroUsuario();
    }
    
    public Login login(){
        return new Login();
    }
    
    /*----------------------ENTIDADES--------------------------*/
    public Usuario usuario(String nombres, String apellidos, String empresa, String usuario, String contrasena, String correo, int telefono, int codEmpleado, String rol){
        return new Usuario(nombres, apellidos, empresa, usuario, contrasena, correo, telefono, codEmpleado, rol);
    }
    
}
