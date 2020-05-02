/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listase.excepciones.InfanteExcepcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carloaiza
 */
public class ListaDE implements Serializable{
    private NodoDE cabeza;

    public ListaDE() {
    }

    public NodoDE getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoDE cabeza) {
        this.cabeza = cabeza;
    }
    
    public void adicionarNodo(Infante infante)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoDE(infante);
        }
        else
        {
            //Lamo a mi ayudante
            NodoDE temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new NodoDE(infante));
            temp.getSiguiente().setAnterior(temp);
            
        }        
    }
       public NodoDE retornarPosicion(int pos) {
        NodoDE temp = null;
        if (!esVacio()) {//noesvacio
            temp = this.cabeza;
            for (int i = 0; i < pos; i++) {
                temp = temp.getSiguiente();
            }
        }
        return temp;
    }
       
       
     public boolean esVacio() {
        return (contarNodos() == 0);
    }
    
    public void adicionarNodoAlInicio(Infante infante)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoDE(infante);
        }
        else
        {
            NodoDE temp= new NodoDE(infante);
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
            NodoDE temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
      public NodoDE buscar(short referencia){
              NodoDE aux = this.cabeza;
        // Bandera para indicar si el valor existe.
        boolean encontrado = false;
        // Recorre la lista hasta encontrar el elemento o hasta 
        // llegar al final de la lista.
        while (aux != null && encontrado != true) {
            // Consulta si el valor del nodo es igual al de referencia.
            if ((aux.getDato().getCodigo()==referencia)) {
                // Canbia el valor de la bandera.
                encontrado = true;
            } else {
                // Avansa al siguiente. nodo.
                aux = aux.getSiguiente();
            }
        }
       return aux;
      }
    
      
      
      public Infante BuscarInfante(Short datob) {
      
        if (this.cabeza !=null) {
           NodoDE temp = this.cabeza;
            // comparacion de cadenas con equal
            while ((temp != null) && (temp.getDato().getCodigo() != datob)) {
                temp = temp.getSiguiente();

            }//fin mientras
            if (temp != null) {
                return temp.getDato();
            }
        }
        return null;
      }
       public int BuscarInfantePos(Short datob) {
        boolean encontro = false;
        int pos;
        if (this.cabeza !=null) {
           NodoDE temp = this.cabeza;
           pos=1;
            // comparacion de cadenas con equal
            while ((temp != null) && (temp.getDato().getCodigo() != datob)) {
                temp = temp.getSiguiente();
                       pos++;
               

            }//fin mientras
            if (temp != null) {
                return pos;
            }
        }
        return 0;
      }
       public int cantidad ()
    {
        int cant = 0;
        NodoDE reco = this.cabeza;
        while (reco != null) {
            reco = reco.getSiguiente();
            cant++;
        }
        return cant;
    }
        public void insertarPos (int pos, Infante x)
    {
        if (pos <= cantidad () + 1)    {
            NodoDE nuevo = new NodoDE(x);
           
            if (pos == 1){
                nuevo.setSiguiente(this.cabeza); 
                if (this.cabeza!=null)
                    
                this.cabeza.setAnterior(nuevo);
                this.cabeza = nuevo;
            } else
                if (pos == cantidad () + 1)    {
                    NodoDE reco = this.cabeza;
                    while (reco.getSiguiente() != null) {
                        reco = reco.getSiguiente();
                    }
                    reco.setSiguiente(nuevo);
                    nuevo.setAnterior(reco);
                    nuevo.setSiguiente(null);
                } else {
                    NodoDE reco = this.cabeza;
                    for (int f = 1 ; f <= pos - 2 ; f++)
                        reco = reco.getSiguiente();
                    NodoDE siguiente = reco.getSiguiente();
                    reco.setSiguiente(nuevo) ;
                    nuevo.setAnterior(reco);
                    nuevo.setSiguiente(siguiente); 
                    siguiente.setAnterior(nuevo);
                }
        }
    }
    public String obtenerListadoInfantes()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        
        return listarInfantes("");
    }
    
    public String listarInfantes(String listado)
    {
        if(cabeza !=null)
        {
            NodoDE temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay infantes";
    }
    
    
     public List obtenerListaInfantes()
    {
        List<Infante> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarInfantes(listado);
        return listado;
    }
    
    public void listarInfantes(List listado)
    {
        if(cabeza !=null)
        {
            NodoDE temp= cabeza;            
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
            NodoDE temp= cabeza;            
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
            ListaDE listaTemporal = new ListaDE();
            // Llamo un ayudante
            NodoDE temp= cabeza;
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
    
    public short contarInfantesxGenero(boolean genero)
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            NodoDE temp= cabeza;
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
    
    public void eliminarInfante(short codigo ) throws InfanteExcepcion
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
                NodoDE temp=cabeza;
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
                
                throw new InfanteExcepcion("El código "+codigo +" no existe en la lista");
            }
        }
        throw new InfanteExcepcion("La lista de infantes está vacía");
    }
    
      public Infante obtenerInfante(short codigo ) throws InfanteExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {                
                return cabeza.getDato();
            }
            else
            {
                NodoDE temp=cabeza;
                while(temp!=null)
                {
                    if(temp.getDato().getCodigo()== codigo)
                    {                                                
                        return temp.getDato();
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new InfanteExcepcion("El código "+codigo +" no existe en la lista");
            }
        }
        throw new InfanteExcepcion("La lista de infantes está vacía");
    }
    
    
}
