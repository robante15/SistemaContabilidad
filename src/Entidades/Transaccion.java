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
public class Transaccion {

    private int id;
    private int idPartida;
    private int cuenta;
    private float trasaccionIngreso;
    private float transaccionEgresos;

    public Transaccion(int id, int idPartida, int cuenta, float trasaccionIngreso, float transaccionEgresos) {
        this.id = id;
        this.idPartida = idPartida;
        this.cuenta = cuenta;
        this.trasaccionIngreso = trasaccionIngreso;
        this.transaccionEgresos = transaccionEgresos;
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

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
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

}
