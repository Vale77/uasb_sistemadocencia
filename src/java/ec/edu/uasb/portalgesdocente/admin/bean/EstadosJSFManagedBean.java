/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.admin.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.Personal;
import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
import ec.edu.uasb.portalgesdocente.session.PersonalFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.TipoEstadoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "estadosBean")
@ViewScoped
public class EstadosJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120458L;
    @EJB
    private TipoEstadoFacadeLocal tipoEstadoFacade;
    @EJB
    private PersonalFacadeLocal personalFacade;

    private List<TipoEstado> estados = new ArrayList<TipoEstado>();
    private List<Personal> listaPersonal = new ArrayList<Personal>();

//<editor-fold defaultstate="collapsed" desc="Atributos">
    public List<TipoEstado> getEstados() {
        return estados;
    }

    public void setEstados(List<TipoEstado> estados) {
        this.estados = estados;
    }

    public List<Personal> getListaPersonal() {
        return listaPersonal;
    }

//</editor-fold>
    /**
     * Creates a new instance of RolesJSFManagedBean
     */
    public EstadosJSFManagedBean() {
    }

    @PostConstruct
    private void recuperarListados() {
        estados = tipoEstadoFacade.findAll();
        listaPersonal = personalFacade.getListaPersonal();
    }

}
