/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import javax.swing.JFrame;

/**
 *
 * @author Cristian Cubias
 */
public abstract class Abstract_Functions {
    
    
    /* Funciones Set */
    
    public abstract void SetMainForm(JFrame Form);
    
    /* Funciones Get */
    
    public abstract JFrame GetMainForm();
    
    /* Validaciónes */ 
    
    /* Otras funciones*/ 
    
    public abstract void OpenForm(String FormName, JFrame Parent,  Abstract_Functions Instance);
    public abstract void ReenableForm(JFrame Form);
    public abstract void ShowMessaje(String Title, String Text, String Type);
    

    
}
