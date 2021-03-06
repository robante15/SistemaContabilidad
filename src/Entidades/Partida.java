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
public class Partida {
    private int id;
    private int empresaID;
    private int usuarioID;
    private int numPartida;
    private String fecha;
    private String descripcion;
    private float totalIngresos;
    private float totalEgresos;

    public Partida(int id, int empresaID, int usuarioID, int numPartida, String fecha, String descripcion, float totalIngresos, float totalEgresos) {
        this.id = id;
        this.empresaID = empresaID;
        this.usuarioID = usuarioID;
        this.numPartida = numPartida;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.totalIngresos = totalIngresos;
        this.totalEgresos = totalEgresos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(int empresaID) {
        this.empresaID = empresaID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public int getNumPartida() {
        return numPartida;
    }

    public void setNumPartida(int numPartida) {
        this.numPartida = numPartida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(float totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public float getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(float totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

}
