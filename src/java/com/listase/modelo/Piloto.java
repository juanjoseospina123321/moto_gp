/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;


public class Piloto implements  Serializable{
    private String nombre; //null
    private short codigo; //0
    private byte edad; //0
    private boolean genero;
    private String ciudadNacimiento;
    

    public Piloto() {
        this.edad=18;
       
    }    
    
    public Piloto(String nombre, short codigo, byte edad, boolean genero, String ciudadNacimiento) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.edad = edad;
        this.genero = genero;
        this.ciudadNacimiento= ciudadNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    
    
    @Override
    public String toString() {
       return this.nombre; 
    }
    
    
    
}
