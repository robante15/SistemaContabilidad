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
    public Principal principal() {
        return new Principal();
    }

    public NuevaEntrada nuevaEntrada() {
        return new NuevaEntrada();
    }
    
    public RegistroUsuario registroUsuario() {
        return new RegistroUsuario();
    }
    
    public RegistroEmpresa registroEmpresa() {
        return new RegistroEmpresa();
    }

    public Login login() {
        return new Login();
    }

    /*----------------------ENTIDADES--------------------------*/
    public Usuario usuario(String nombres, String apellidos, String empresa, String usuario, String contrasena, String correo, int telefono, int codEmpleado, String rol) {
        return new Usuario(nombres, apellidos, empresa, usuario, contrasena, correo, telefono, codEmpleado, rol);
    }

    public Empresa empresa(String nomre_empresa, String forma_juridica, String fecha_constitucion, String direccion, String correo, String registro_legal, int telefono, String dueno, String sector_actividad, String resumen_negocio) {
        return new Empresa(nomre_empresa, forma_juridica, fecha_constitucion, direccion, correo, registro_legal, telefono, dueno, sector_actividad, resumen_negocio);
    }

}
