/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Functions.Abstract_Functions;
import Functions.Functions;
import Functions_DB.Abstract_Functions_DB;
import Functions_DB.Functions_DB;

/**
 *
 * @author Cristian Cubias
 */
public class Engine {
    
    public Abstract_Functions StartFunctions()
    {
        Abstract_Functions Factory = new Functions();
        return Factory;
    }
    
    public Abstract_Functions_DB StartFunctionsDB()
    {
        Abstract_Functions_DB Factory = new Functions_DB();
        return Factory;
    }
    
}
