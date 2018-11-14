/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author roban
 */
public class TransaccionPopulada {

    private int id;
    private int idPartida;
    private int numPartida;
    private float trasaccionIngreso;
    private float transaccionEgresos;
    private String fecha;
    private String nombre_cuenta;

    public TransaccionPopulada(int id, int idPartida, int numPartida, float trasaccionIngreso, float transaccionEgresos, String fecha, String nombre_cuenta) {
        this.id = id;
        this.idPartida = idPartida;
        this.numPartida = numPartida;
        this.trasaccionIngreso = trasaccionIngreso;
        this.transaccionEgresos = transaccionEgresos;
        this.fecha = fecha;
        this.nombre_cuenta = nombre_cuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getNumPartida() {
        return numPartida;
    }

    public void setNumPartida(int numPartida) {
        this.numPartida = numPartida;
    }

    public float getTrasaccionIngreso() {
        return trasaccionIngreso;
    }

    public void setTrasaccionIngreso(float trasaccionIngreso) {
        this.trasaccionIngreso = trasaccionIngreso;
    }

    public float getTransaccionEgresos() {
        return transaccionEgresos;
    }

    public void setTransaccionEgresos(float transaccionEgresos) {
        this.transaccionEgresos = transaccionEgresos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

}
