/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listase.excepciones.PilotoExcepcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ListaPiloto implements Serializable{
    private NodoPiloto cabeza;

    public ListaPiloto() {
    }

    public NodoPiloto getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoPiloto cabeza) {
        this.cabeza = cabeza;
    }
    
    
    
    
    
    
    
    
    public void adicionarNodo(Piloto piloto)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoPiloto(piloto);
        }
        else
        {
            //Lamo a mi ayudante
            NodoPiloto temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new NodoPiloto(piloto));
            temp.getSiguiente().setAnterior(temp);
            
        }        
    }
    
    public void adicionarNodoAlInicio(Piloto piloto)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoPiloto(piloto);
        }
        else
        {
            NodoPiloto temp= new NodoPiloto(piloto);
            temp.setSiguiente(cabeza);
            cabeza.setAnterior(temp);
            cabeza= temp;
        }
    }
    
       public short contarNodos()
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            NodoPiloto temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    public String obtenerListadoPilotos()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione en el string
        
        return listarPilotos("");
    }
    
    public String listarPilotos(String listado)
    {
        if(cabeza !=null)
        {
            NodoPiloto temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay pilotos";
    }
    
    
     public List obtenerListaPilotos()
    {
        List<Piloto> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarPilotos(listado);
        return listado;
    }
    
    public void listarPilotos(List listado)
    {
        if(cabeza !=null)
        {
            NodoPiloto temp= cabeza;            
            while(temp!=null)
            {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp=temp.getSiguiente();                
            }            
        }
        
    }
    
    public float promediarEdades()
    {
        int sumaEdades= 0;
        int contador=0;
        if(cabeza !=null)
        {
            NodoPiloto temp= cabeza;            
            while(temp!=null)
            {          
                //sumaEdades= sumaEdades+ temp.getDato().getEdad();
                sumaEdades += temp.getDato().getEdad();
                contador++;
                temp=temp.getSiguiente();                
            }   
            return sumaEdades/(float) contador;
        }
        return 0;
        
    }
    
    
    public void invertirLista()
    {
        if(cabeza!=null)
        {
            //Crear una lista temporal la cabeza de la lista temporal está vacía
            ListaPiloto listaTemporal = new ListaPiloto();
            // Llamo un ayudante
            NodoPiloto temp= cabeza;
            //Recorro la lista de principio a fin
            while(temp!=null)
            {         
               //Parado en cada nodo , se extrae la información y se
                // envía a la otra lista al inicio
                listaTemporal.adicionarNodoAlInicio(temp.getDato());
                temp=temp.getSiguiente();                
            }   
            //Igualo la cabeza de mi lista principal a la cabeza de la lista temporal
            cabeza= listaTemporal.getCabeza();
        }
    }
    
    public short contarPilotosxGenero(boolean genero)
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            NodoPiloto temp= cabeza;
            short cont=0;
            while(temp!=null)
            {
                if(temp.getDato().isGenero()==genero)
                {
                  cont++;   
                }                
                temp=temp.getSiguiente();
                
            }
            return cont;
        }
    }
    
    public void eliminarPiloto(short codigo ) throws PilotoExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {
                cabeza=cabeza.getSiguiente();
                cabeza.setAnterior(null);
                return;
            }
            else
            {
                NodoPiloto temp=cabeza;
                while(temp.getSiguiente()!=null)
                {
                    if(temp.getSiguiente().getDato().getCodigo()== codigo)
                    {
                        //el que sigue es el que hay que eliminar
                        temp.setSiguiente(temp.getSiguiente().getSiguiente());
                        if(temp.getSiguiente()!=null)
                            temp.getSiguiente().setAnterior(temp);
                        return;
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new PilotoExcepcion("El código  "+codigo +" no existe en la carrera");
            }
        }
        throw new PilotoExcepcion("La carrera de motoGP no tiene pilotos inscritos");
    }
    
      public Piloto obtenerPiloto(short codigo ) throws PilotoExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {                
                return cabeza.getDato();
            }
            else
            {
                NodoPiloto temp=cabeza;
                while(temp!=null)
                {
                    if(temp.getDato().getCodigo()== codigo)
                    {                                                
                        return temp.getDato();
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new PilotoExcepcion("El código "+codigo +" no existe en la carrera");
            }
        }
        throw new PilotoExcepcion("La lista de pilotos está vacía");
    }
    
    
}
