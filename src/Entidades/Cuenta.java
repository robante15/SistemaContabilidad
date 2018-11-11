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
public class Cuenta {

    private String nomreCuenta;
    private String clasificacion;
    private String tipoSaldo;
    private int codigo;

    public Cuenta(String nomreCuenta, String clasificacion, String tipoSaldo, int codigo) {
        this.nomreCuenta = nomreCuenta;
        this.clasificacion = clasificacion;
        this.tipoSaldo = tipoSaldo;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomreCuenta() {
        return nomreCuenta;
    }

    public void setNomreCuenta(String nomreCuenta) {
        this.nomreCuenta = nomreCuenta;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(String tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

}
