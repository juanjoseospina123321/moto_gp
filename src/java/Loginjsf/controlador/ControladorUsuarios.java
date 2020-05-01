/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loginjsf.controlador;

import Loginjsf.modelo.TipoUsuario;
import Loginjsf.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuarios {

    private List<TipoUsuario> tiposUsuarios;
    private List<Usuario> usuarios;

    public ControladorUsuarios() {
        this.iniciarListados();
    }

    public List<TipoUsuario> getTiposUsuarios() {
        return tiposUsuarios;
    }

    public void setTiposUsuarios(List<TipoUsuario> tiposUsuarios) {
        this.tiposUsuarios = tiposUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private void iniciarListados() {
        tiposUsuarios = new ArrayList<>();
        tiposUsuarios.add(new TipoUsuario(1, "administrador"));
        

        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("motogp.com", "moto123", "juan jose",
                tiposUsuarios.get(0)));

      
    }

    public Usuario encontrarUsuarioxCorreo(String correo) {
        Usuario usuarioEncontrado = null;
       //recorre la lista de inicio a fin
        for(Usuario usu:this.usuarios)
        {
           if(usu.getCorreo().equals(correo)) 
           {
               return usu;
           }
        }
        return usuarioEncontrado;
    }
    
}
