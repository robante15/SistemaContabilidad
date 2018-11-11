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
public class Empresa {
    private String nomre_empresa;
    private String forma_juridica;
    private String fecha_constitucion;
    private String direccion;
    private String correo;
    private String registro_legal;
    private int telefono;
    private String dueno;
    private String sector_actividad;
    private String resumen_negocio;

    public Empresa(String nomre_empresa, String forma_juridica, String fecha_constitucion, String direccion, String correo, String registro_legal, int telefono, String dueno, String sector_actividad, String resumen_negocio) {
        this.nomre_empresa = nomre_empresa;
        this.forma_juridica = forma_juridica;
        this.fecha_constitucion = fecha_constitucion;
        this.direccion = direccion;
        this.correo = correo;
        this.registro_legal = registro_legal;
        this.telefono = telefono;
        this.dueno = dueno;
        this.sector_actividad = sector_actividad;
        this.resumen_negocio = resumen_negocio;
    }

    public String getResumen_negocio() {
        return resumen_negocio;
    }

    public void setResumen_negocio(String resumen_negocio) {
        this.resumen_negocio = resumen_negocio;
    }

    public String getNomre_empresa() {
        return nomre_empresa;
    }

    public void setNomre_empresa(String nomre_empresa) {
        this.nomre_empresa = nomre_empresa;
    }

    public String getForma_juridica() {
        return forma_juridica;
    }

    public void setForma_juridica(String forma_juridica) {
        this.forma_juridica = forma_juridica;
    }

    public String getFecha_constitucion() {
        return fecha_constitucion;
    }

    public void setFecha_constitucion(String fecha_constitucion) {
        this.fecha_constitucion = fecha_constitucion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRegistro_legal() {
        return registro_legal;
    }

    public void setRegistro_legal(String registro_legal) {
        this.registro_legal = registro_legal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getSector_actividad() {
        return sector_actividad;
    }

    public void setSector_actividad(String sector_actividad) {
        this.sector_actividad = sector_actividad;
    }
}
