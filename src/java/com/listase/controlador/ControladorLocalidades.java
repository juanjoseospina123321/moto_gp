/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listase.modelo.Ciudad;
import com.listase.modelo.Departamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carloaiza
 */
public class ControladorLocalidades {
    private List<Ciudad> ciudades;
    private List<Departamento> departamentos;

    public ControladorLocalidades() {
        //llenar las ciudades y los departamentos
        llenarDepartamentos();
        llenarCiudades();
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    
    
    private void llenarDepartamentos()
    {
        departamentos = new ArrayList<>();
        departamentos.add(new Departamento("17", "Caldas"));
        departamentos.add(new Departamento("66", "Risaralda"));
        departamentos.add(new Departamento("63", "Quindío"));
        
    }
    
    private void llenarCiudades()
    {
        ciudades= new ArrayList<>();
        ciudades.add(new Ciudad("17001","Manizales", departamentos.get(0)));
        ciudades.add(new Ciudad("66001","Pereira", departamentos.get(1)));
        ciudades.add(new Ciudad("63001","Armenia", departamentos.get(2)));
        ciudades.add(new Ciudad("17486","Neira", departamentos.get(0)));
        ciudades.add(new Ciudad("17873","Villamaría", departamentos.get(0)));
    }
    
    public List<Ciudad> obtenerCiudadesxDepto(String codigo)
    {
        List<Ciudad> ciudadesxDepto= new ArrayList<>();
        for(Ciudad ciu: this.ciudades)
        {
            if(ciu.getDepartamento().getCodigo().compareTo(codigo)==0)
            {
                ciudadesxDepto.add(ciu);
            }
        }
        
        return ciudadesxDepto;
    }
    
    
    
    
}
