/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.admin.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import ec.edu.uasb.portalgesdocente.entities.TiposFormaPago;
import ec.edu.uasb.portalgesdocente.session.RolDocenteFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.TiposFormaPagoFacade;
import ec.edu.uasb.portalgesdocente.session.TiposFormaPagoFacadeLocal;
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
@ManagedBean(name = "formaPagoBean")
@ViewScoped
public class FormaPagoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    @EJB
    private TiposFormaPagoFacadeLocal formaPagoFacadeLocal;
    private List<TiposFormaPago> tiposFormaPago = new ArrayList<TiposFormaPago>();

//<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * @return the tiposFormaPago
     */
    public List<TiposFormaPago> getTiposFormaPago() {
        return tiposFormaPago;
    }

    /**
     * @param tiposFormaPago the tiposFormaPago to set
     */
    public void setTiposFormaPago(List<TiposFormaPago> tiposFormaPago) {
        this.tiposFormaPago = tiposFormaPago;
    }

//</editor-fold>
    /**
     * Creates a new instance of RolesJSFManagedBean
     */
    public FormaPagoJSFManagedBean() {
    }

    @PostConstruct
    private void recuperarListados() {
        tiposFormaPago = formaPagoFacadeLocal.findAll();
    }

}
