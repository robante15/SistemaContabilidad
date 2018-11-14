/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Entidades.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import Procesos.*;
import Factory.Factory;
/**
 *
 * @author roban
 */
public class Fechas {
    private static Factory factory;
    
    public RangoFecha EstaSemana(){
        //DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        factory = new Factory();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        
        RangoFecha rango = factory.rangoFecha(start, end);
        return rango;
        /*String inicio = df.format(start);
        String finSemana = df.format(end);
        
        System.out.println(start + " - " + end);
        System.out.println(inicio + " - " + finSemana);*/
        
    }
   
   public RangoFecha EsteMes(){
        //DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        factory = new Factory();
        int longitud = YearMonth.now().lengthOfMonth() -1;
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_MONTH) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i -1);
        Date start = c.getTime();
        c.add(Calendar.DATE, longitud);
        Date end = c.getTime();
        
        RangoFecha rango = factory.rangoFecha(start, end);
        return rango;
        
        /*String inicio = df.format(start);
        String finSemana = df.format(end);
        
        System.out.println(start + " - " + end);
        System.out.println(inicio + " - " + finSemana);*/
           
    }
   
   public RangoFecha Ahora(){
       //DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
       Date date = new Date();
       factory = new Factory();
       RangoFecha rango = factory.rangoFecha(date, date);
       return rango;
       
       //System.out.println(df.format(date));
   }
   
}

