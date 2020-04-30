/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loginjsf.beans;

import Loginjsf.controlador.ControladorUsuarios;
import Loginjsf.modelo.Usuario;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Juan Jose Ospina B
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    //atributo para panel login oculta o muestra
    private boolean verPanelLogin = false;
    //atributo para capturar texto en el saludo
    private String textoSaludo = "";
    //contador saludos
    private short contadorSaludos = 0;
    //atributo para el correo
    private String correo;
    private String contrasenia;
    // el atributo controlador para gestionar usuarios y tipos
    private ControladorUsuarios controlUsuarios;

    public LoginBean() {
        controlUsuarios = new ControladorUsuarios();
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

    public short getContadorSaludos() {
        return contadorSaludos;
    }

    public void setContadorSaludos(short contadorSaludos) {
        this.contadorSaludos = contadorSaludos;
    }

    public String getTextoSaludo() {
        return textoSaludo;
    }

    public void setTextoSaludo(String textoSaludo) {
        this.textoSaludo = textoSaludo;
    }

    public boolean isVerPanelLogin() {
        return verPanelLogin;
    }

    public void setVerPanelLogin(boolean verPanelLogin) {
        this.verPanelLogin = verPanelLogin;
    }
//metodo para cambiar el valor de la variable y ocultar el panel

    public void habilitarOdeshabilitarLogin() {
        verPanelLogin = !verPanelLogin;
    }

    public void aumentarSaludos() {
        contadorSaludos++;
    }

    public String ingresar() {
        Usuario usuarioEncontrado = controlUsuarios.encontrarUsuarioxCorreo(correo);
        if (usuarioEncontrado != null) {
            //encontro el usuario
            if (usuarioEncontrado.getContrasenia().equals(contrasenia)) 
            {
                return "home";
            }

        } 
            //no encontro el susario
            JsfUtil.addErrorMessage("los datos son malos ");

        

        return null;
    }
}
