/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import GUI.*;
import Procesos.*;
import Entidades.*;
import java.util.Date;

/**
 *
 * @author roban
 */
public class Factory {

    public BaseDatos baseDatos() {
        return new BaseDatos();
    }

    public Fechas fechas(){
        return new Fechas();
    }
    
    /*----------------------CARGA DE LAS GUI-----------------------------*/
    public Principal principal(Usuario usuario) {
        return new Principal(usuario);
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
    public Usuario usuario(int id, String nombres, String apellidos, String empresa, String usuario, String contrasena, String correo, int telefono, int codEmpleado, String rol) {
        return new Usuario(id, nombres, apellidos, empresa, usuario, contrasena, correo, telefono, codEmpleado, rol);
    }

    public Empresa empresa(String nomre_empresa, String forma_juridica, String fecha_constitucion, String direccion, String correo, String registro_legal, int telefono, String dueno, String sector_actividad, String resumen_negocio) {
        return new Empresa(nomre_empresa, forma_juridica, fecha_constitucion, direccion, correo, registro_legal, telefono, dueno, sector_actividad, resumen_negocio);
    }

    public Cuenta cuenta(String nomreCuenta, String clasificacion, String tipoSaldo, int codigo) {
        return new Cuenta(nomreCuenta, clasificacion, tipoSaldo, codigo);
    }

    public Partida partida(int id, int numPartida, String fecha, String descripcion, float totalIngresos, float totalEgresos) {
        return new Partida(id, numPartida, fecha, descripcion, totalIngresos, totalEgresos);
    }

    public Transaccion transaccion(int id, int idPartida, float trasaccionIngreso, float transaccionEgresos) {
        return new Transaccion(id, idPartida, trasaccionIngreso, transaccionEgresos);
    }
    
    public TransaccionPopulada transaccionPopulada(int id, int idPartida, int numPartida, float trasaccionIngreso, float transaccionEgresos, String fecha, String nombre_cuenta) {
        return new TransaccionPopulada(id, idPartida, numPartida, trasaccionIngreso, transaccionEgresos, fecha, nombre_cuenta);
    }
    
    public RangoFecha rangoFecha(Date inicio, Date fin){
        return new RangoFecha(inicio, fin);
    }

}
