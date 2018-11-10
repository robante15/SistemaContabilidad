/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Cubias
 */
public class Functions extends Abstract_Functions{
    
    //Variable que guarda el Formulario Principal
    JFrame MainForm;
    
    
    //Función que abre los formularios para evitar escribir el mismo código en cada ventana
    @Override
    public void OpenForm(String FormName, JFrame Parent,  Abstract_Functions Instance) {
        switch(FormName)
        {
            case "Main":
                UI.Main_Menu.Main Window1 = new UI.Main_Menu.Main(Instance);
                Window1.setVisible(true);
                Parent.setVisible(false);
                Parent.dispose();
                break;
                
            case "Daily":
                UI.Daily.Main Window2 = new UI.Daily.Main(Instance);
                Window2.setVisible(true);
                
                /* Se desactiva el formulario principal para que no pueda interactuar con el usuario
                mientras tiene otra ventana abierta, esto es útil para evitar agregar botones
                de regresar al menú principal en cada ventana ya que el formulario principal siempre estará visible */
                Parent.disable();        
                break;         
        }
    }
    
    //Función que establece cuál es el formulario principal para la funcion modal.
    @Override
    public void SetMainForm(JFrame Form) {
        MainForm = Form;
    }

    //Función que retorna el formulario principal
    @Override
    public JFrame GetMainForm() {
        return MainForm;
    }

    //Función que vuelve a habilitar el formulario principal
    @Override
    public void ReenableForm(JFrame Form) {
        Form.enable();
    }

    @Override
    public void ShowMessaje(String Title, String Text, String Type) {
        switch(Type)
        {
            case "Information":
                JOptionPane.showMessageDialog(null, Text, Title, JOptionPane.INFORMATION_MESSAGE);
                break;     
            case "Error":
                JOptionPane.showMessageDialog(null, Text, Title, JOptionPane.ERROR_MESSAGE);
                break;
            case "Warning":
                JOptionPane.showMessageDialog(null, Text, Title, JOptionPane.WARNING_MESSAGE);
                break;
        }      
    }


    
}
