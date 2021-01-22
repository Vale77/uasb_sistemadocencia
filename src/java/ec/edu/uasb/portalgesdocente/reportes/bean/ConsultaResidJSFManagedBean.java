/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.reportes.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.session.ContratoDocenteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "consultaResideBean")
@ViewScoped
public class ConsultaResidJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120444L;

    @EJB
    private ContratoDocenteFacadeLocal contratoDocenteFacade;

    private ContratoDocente contratoSelected;
    private List<ContratoDocente> contratosDocentes = new ArrayList<ContratoDocente>();
    private Date desde;
    private boolean disabledBuscar = true;
    private String estado = "R";

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * @return the disabledBuscar
     */
    public boolean isDisabledBuscar() {
        return disabledBuscar;
    }

    /**
     * @return the contratoDocentes
     */
    public List<ContratoDocente> getContratosDocentes() {
        return contratosDocentes;
    }

    /**
     * @return the contratoSelected
     */
    public ContratoDocente getContratoSelected() {
        return contratoSelected;
    }

    /**
     * @param contratoSelected the contratoSelected to set
     */
    public void setContratoSelected(ContratoDocente contratoSelected) {
        this.contratoSelected = contratoSelected;
    }

    /**
     * @return the desde
     */
    public Date getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(Date desde) {
        this.desde = desde;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    //</editor-fold>
    /**
     * Creates a new instance of ConsultaResidJSFManagedBean
     */
    public ConsultaResidJSFManagedBean() {
    }

    public void changeFecha() {
        disabledBuscar = false;

    }

    public void buscarButton_processAction() {
        contratosDocentes = contratoDocenteFacade.findAprobadosByEstado(estado, desde);
    }

    @Override
    public void imprimirButton_processAction(ActionEvent ae) {
        String urlReporte = "nroSolicitud=" + contratoSelected.getCdoCodigo().toString()
                + "&reporte=solicitud&tipo=pdf"
                + "&contexto=PortalGesDocente";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
    }

}
