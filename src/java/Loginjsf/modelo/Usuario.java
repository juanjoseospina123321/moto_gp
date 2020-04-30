/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loginjsf.modelo;

import java.io.Serializable;


public class Usuario implements Serializable{
    private String correo;
    private String contrasenia;
    private String nombreCompleto;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String correo, String contrasenia, String nombreCompleto, TipoUsuario tipoUsuario) {
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
    }

    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }   

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
    
}
