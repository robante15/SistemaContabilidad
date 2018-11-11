/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Factory.Factory;
import GUI.Login;
import Procesos.*;

/**
 *
 * @author roban
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    private static Factory factory;

    public static void main(String[] args) {
        factory = new Factory();

        Login form = factory.login();
        form.setVisible(true);

    }

}
