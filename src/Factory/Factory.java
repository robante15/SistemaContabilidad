/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;
import GUI.*;
import Procesos.*;
import Entidades.*;
//import Entidades.*;
//import GUI.*;
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
    
    public Login login(){
        return new Login();
    }
    
}
