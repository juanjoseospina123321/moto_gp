/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listase.excepciones.InfanteExcepcion;
import com.listase.modelo.Infante;
import com.listase.modelo.ListaSE;
import com.listase.modelo.Nodo;
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
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author carloaiza
 */
@Named(value = "sesionInfante")
@SessionScoped
public class SesionInfante implements Serializable {
    private ListaSE listaInfantes;
    private Infante infante;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private Nodo ayudante;   
    private String textoVista="Gráfico";
    
    private List listadoInfantes;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
    
    private ControladorLocalidades controlLocalidades;
    
    private String codigoDeptoSel;
    
    private short infanteSeleccionado;
    
    private Infante infanteDiagrama;
    
    /**
     * Creates a new instance of SesionInfante
     */
    public SesionInfante() {        
    }
    
    @PostConstruct
    private void inicializar()
    {
        controlLocalidades = new ControladorLocalidades();
        //inicializando el combo en el primer depto
        codigoDeptoSel = controlLocalidades.getDepartamentos().get(0).getCodigo();
        
        listaInfantes = new ListaSE();        
        //LLenado de la bds
        listaInfantes.adicionarNodo(new Infante("Carlitos",(short) 1, (byte)2, true,
                controlLocalidades.getCiudades().get(0).getNombre()));
        listaInfantes.adicionarNodo(new Infante("Juanita",(short) 2, (byte)3, false,
        controlLocalidades.getCiudades().get(3).getNombre()));
        listaInfantes.adicionarNodo(new Infante("Martina",(short) 3, (byte)1,false,
        controlLocalidades.getCiudades().get(1).getNombre()));
        listaInfantes.adicionarNodoAlInicio(new Infante("Mariana",(short) 4, (byte)5,false,
        controlLocalidades.getCiudades().get(2).getNombre()));
        ayudante = listaInfantes.getCabeza();
        infante = ayudante.getDato();     
        //Me llena el objeto List para la tabla
        listadoInfantes = listaInfantes.obtenerListaInfantes();
        pintarLista();
   }

    public Infante getInfanteDiagrama() {
        return infanteDiagrama;
    }

    public void setInfanteDiagrama(Infante infanteDiagrama) {
        this.infanteDiagrama = infanteDiagrama;
    }
    
    

    public short getInfanteSeleccionado() {
        return infanteSeleccionado;
    }

    public void setInfanteSeleccionado(short infanteSeleccionado) {
        this.infanteSeleccionado = infanteSeleccionado;
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

    
    
    public List getListadoInfantes() {
        return listadoInfantes;
    }

    public void setListadoInfantes(List listadoInfantes) {
        this.listadoInfantes = listadoInfantes;
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
    
    public ListaSE getListaInfantes() {
        return listaInfantes;
    }

    public void setListaInfantes(ListaSE listaInfantes) {
        this.listaInfantes = listaInfantes;
    }

    public Infante getInfante() {
        return infante;
    }

    public void setInfante(Infante infante) {
        this.infante = infante;
    }
    
    
    
    public void guardarInfante()
    {
        //obtiene el consecutivo
        infante.setCodigo((short)(listaInfantes.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaInfantes.adicionarNodoAlInicio(infante);
        }
        else
        {
            listaInfantes.adicionarNodo(infante);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoInfantes = listaInfantes.obtenerListaInfantes();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El infante se ha guardado exitosamente");
        
    }
    
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        infante = new Infante();
    }
    
    public void irSiguiente()
    {
        if(ayudante.getSiguiente()!=null)
        {
            ayudante = ayudante.getSiguiente();
            infante = ayudante.getDato();
        }        
    }
    
    public void irPrimero()
    {
        if(listaInfantes.getCabeza()!=null)
        {
            ayudante = listaInfantes.getCabeza();
            infante = ayudante.getDato();
            
        }
        else
        {
            infante = new Infante();
        }
        listadoInfantes = listaInfantes.obtenerListaInfantes();
        pintarLista();
             
    }
    
    public void irUltimo()
    {
        if(listaInfantes.getCabeza()!=null)
        {            
            while(ayudante.getSiguiente()!=null)
            {
                ayudante = ayudante.getSiguiente();
            }
            infante=ayudante.getDato();
        }
    }
    
    public void cambiarVistaInfantes()
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
        listaInfantes.invertirLista();
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
        if (listaInfantes.getCabeza() != null) {
            //llamo a mi ayudante
            Nodo temp = listaInfantes.getCabeza();
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
            }
            
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
         
        infanteSeleccionado = Short.valueOf(id.replaceAll("frmInfante:diagrama-", ""));
        
    }

    public void eliminarInfante()
    {
        if(codigoEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                listaInfantes.eliminarInfante(codigoEliminar);
                irPrimero();
                JsfUtil.addSuccessMessage("Infante "+codigoEliminar +" eliminado.");
            }
            catch(InfanteExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+codigoEliminar+ " no es válido");
        }
    }
    
    
    public void obtenerInfanteDiagrama()
    {
        try {
            infanteDiagrama = listaInfantes.obtenerInfante(infanteSeleccionado);
        } catch (InfanteExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlFinal()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Infante infTemporal = listaInfantes.obtenerInfante(infanteSeleccionado);
            // Eliminar el nodo
            listaInfantes.eliminarInfante(infanteSeleccionado);
            // Adicionarlo al final
            listaInfantes.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (InfanteExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlInicio()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Infante infTemporal = listaInfantes.obtenerInfante(infanteSeleccionado);
            // Eliminar el nodo
            listaInfantes.eliminarInfante(infanteSeleccionado);
            // Adicionarlo al inicio
            listaInfantes.adicionarNodoAlInicio(infTemporal);
            
            pintarLista();
        } catch (InfanteExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    
    
}
