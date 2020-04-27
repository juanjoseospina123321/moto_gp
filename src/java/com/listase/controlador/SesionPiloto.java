/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listase.excepciones.PilotoExcepcion;
import com.listase.modelo.ListaPiloto;
import com.listase.modelo.NodoPiloto;
import com.listase.modelo.Piloto;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author lfpatiño
 */
@Named(value = "sesionPiloto")
@SessionScoped
public class SesionPiloto implements Serializable {
    private ListaPiloto listaPilotos;
    private Piloto piloto;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private NodoPiloto ayudante;   
    private String textoVista="Gráfico";
    
    private List listadoPilotos;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
    
    private ControladorLocalidades controlLocalidades;
    
    private String codigoDeptoSel;
    
    private short pilotoSeleccionado;
    
    private Piloto pilotoDiagrama;
    
    /**
     * Creates a new instance of SesionInfante
     */
    public SesionPiloto() {        
    }
    
    @PostConstruct
    private void inicializar()
    {
        controlLocalidades = new ControladorLocalidades();
        //inicializando el combo en el primer depto
        codigoDeptoSel = controlLocalidades.getDepartamentos().get(0).getCodigo();
        
        listaPilotos = new ListaPiloto();        
        //LLenado de la bds
        listaPilotos.adicionarNodo(new Piloto("Juan Jose ",(short) 1, (byte)2, true,
                controlLocalidades.getCiudades().get(0).getNombre()));
        
        listaPilotos.adicionarNodo(new Piloto("Maria Camila",(short) 2, (byte)3, false,
        controlLocalidades.getCiudades().get(3).getNombre()));
        listaPilotos.adicionarNodo(new Piloto("Carmen",(short) 3, (byte)1,false,
        controlLocalidades.getCiudades().get(1).getNombre()));
        listaPilotos.adicionarNodoAlInicio(new Piloto("Lupe",(short) 4, (byte)5,false,
        controlLocalidades.getCiudades().get(2).getNombre()));
        ayudante = listaPilotos.getCabeza();
        piloto = ayudante.getDato();     
        //Me llena el objeto List para la tabla
        listadoPilotos = listaPilotos.obtenerListaPilotos();
        pintarLista();
   }

    public Piloto getPilotoDiagrama() {
        return pilotoDiagrama;
    }

    public void setPilotoDiagrama(Piloto pilotoDiagrama) {
        this.pilotoDiagrama = pilotoDiagrama;
    }
    
    

    public short getPilotoSeleccionado() {
        return pilotoSeleccionado;
    }

    public void setPilotoSeleccionado(short pilotoSeleccionado) {
        this.pilotoSeleccionado = pilotoSeleccionado;
    }
    
    

    public String getCodigoDeptoSel() {
        return codigoDeptoSel;
    }

    public void setCodigoDeptoSel(String codigoDeptoSel) {
        this.codigoDeptoSel = codigoDeptoSel;
    }

    
    
    public ControladorLocalidades getControlLocalidades() {
        return controlLocalidades;
    }

    public void setControlLocalidades(ControladorLocalidades controlLocalidades) {
        this.controlLocalidades = controlLocalidades;
    }
     
    
    
    public DiagramModel getModel() {
        return model;
    }
     
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
         
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
         
        return conn;
    }

    public short getCodigoEliminar() {
        return codigoEliminar;
    }

    public void setCodigoEliminar(short codigoEliminar) {
        this.codigoEliminar = codigoEliminar;
    }

    
    
    public String getTextoVista() {
        return textoVista;
    }

    public void setTextoVista(String textoVista) {
        this.textoVista = textoVista;
    }

    
    
    public List getListadoPilotos() {
        return listadoPilotos;
    }

    public void setListadoPilotos(List listadoPilotos) {
        this.listadoPilotos = listadoPilotos;
    }
    
    

    public boolean isDeshabilitarFormulario() {
        return deshabilitarFormulario;
    }

    public void setDeshabilitarFormulario(boolean deshabilitarFormulario) {
        this.deshabilitarFormulario = deshabilitarFormulario;
    }

  
    
    

    public String getAlInicio() {
        return alInicio;
    }

    public void setAlInicio(String alInicio) {
        this.alInicio = alInicio;
    }
    
    public ListaPiloto getListaPiloto() {
        return listaPilotos;
    }

    public void setListaPilotos(ListaPiloto listaPilotos) {
        this.listaPilotos = listaPilotos;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setInfante(Piloto piloto) {
        this.piloto = piloto;
    }
    
    
    
    public void guardarPiloto()
    {
        //obtiene el consecutivo
        piloto.setCodigo((short)(listaPilotos.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaPilotos.adicionarNodoAlInicio(piloto);
        }
        else
        {
            listaPilotos.adicionarNodo(piloto);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoPilotos = listaPilotos.obtenerListaPilotos();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El Piloto se ha inscrito satisfactoriamente");
        
    }
    
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        piloto = new Piloto();
    }
    
    public void irAnterior()
    {
        if(ayudante.getAnterior()!=null)
        {
            ayudante = ayudante.getAnterior();
            piloto = ayudante.getDato();
        }        
    }
    
    
    public void irSiguiente()
    {
        if(ayudante.getSiguiente()!=null)
        {
            ayudante = ayudante.getSiguiente();
            piloto = ayudante.getDato();
        }        
    }
    
    
    
    
    public void irPrimero()
    {
        if(listaPilotos.getCabeza()!=null)
        {
            ayudante = listaPilotos.getCabeza();
            piloto = ayudante.getDato();
            
        }
        else
        {
            piloto = new Piloto();
        }
        listadoPilotos = listaPilotos.obtenerListaPilotos();
        pintarLista();
             
    }
    
    public void irUltimo()
    {
        if(listaPilotos.getCabeza()!=null)
        {            
            while(ayudante.getSiguiente()!=null)
            {
                ayudante = ayudante.getSiguiente();
            }
            piloto=ayudante.getDato();
        }
    }
    
    public void cambiarVistaPiloto()
    {
        if(textoVista.compareTo("Tabla")==0)
        {
            textoVista = "Gráfico";
        }
        else
        {
            textoVista = "Tabla";
        }
    }
    
    public void invertirLista(){
        //Invierte la lista
        listaPilotos.invertirLista();
        irPrimero();
    }
    
    
    public void pintarLista() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaPilotos.getCabeza() != null) {
            //llamo a mi ayudante
            NodoPiloto temp = listaPilotos.getCabeza();
            int posX=2;
            int posY=2;
            //recorro la lista de principio a fin
            while(temp !=null)
            {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getCodigo()+" "+
                        temp.getDato().getNombre(), 
                        posX+"em", posY+"em");
                ele.setId(String.valueOf(temp.getDato().getCodigo()));
                //adiciona un conector al cuadrito
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                model.addElement(ele);                    
                temp=temp.getSiguiente();
                posX=  posX+5;
                posY= posY+6;
            }            
           
            //Pinta las flechas            
            for(int i=0; i < model.getElements().size() -1; i++)
            {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1), 
                        model.getElements().get(i+1).getEndPoints().get(0), "Sig"));
                
                
                model.connect(createConnection(model.getElements().get(i+1).getEndPoints().get(2), 
                        model.getElements().get(i).getEndPoints().get(3), "Ant"));
            }
            
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
         
        pilotoSeleccionado = Short.valueOf(id.replaceAll("frmPiloto:diagrama-", ""));
        
    }

  
    
    
    public void elimPiloto()
    {
        try {
            listaPilotos.eliminarPiloto(pilotoSeleccionado);
            irPrimero();
        } catch (PilotoExcepcion ex) {
             JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    
   
    
    
    public void eliminarPiloto()
    {
        if(codigoEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                listaPilotos.eliminarPiloto(codigoEliminar);
                irPrimero();
                JsfUtil.addSuccessMessage("piloto "+codigoEliminar +" eliminado.");
            }
            catch(PilotoExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+codigoEliminar+ " no es válido");
        }
    }
    
    
    public void obtenerPilotoDiagrama()
    {
        try {
            pilotoDiagrama = listaPilotos.obtenerPiloto(pilotoSeleccionado);
        } catch (PilotoExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlFinal()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Piloto infTemporal = listaPilotos.obtenerPiloto(pilotoSeleccionado);
            // Eliminar el nodo
            listaPilotos.eliminarPiloto(pilotoSeleccionado);
            // Adicionarlo al final
            listaPilotos.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (PilotoExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlInicio()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Piloto infTemporal = listaPilotos.obtenerPiloto(pilotoSeleccionado);
            // Eliminar el nodo
            listaPilotos.eliminarPiloto(pilotoSeleccionado);
            // Adicionarlo al inicio
            listaPilotos.adicionarNodoAlInicio(infTemporal);
            
            pintarLista();
        } catch (PilotoExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    
}

