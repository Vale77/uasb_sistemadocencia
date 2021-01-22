/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.opcion.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.session.EstadoSolicContratoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "verHistoricoBean")
@RequestScoped
public class VerHistoricoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120444L;
    @EJB
    private EstadoSolicContratoFacadeLocal estadoSolicContratoFacade;
    private ContratoDocente contratoSelected;
    private List<EstadoSolicContrato> historiaEstados = new ArrayList<EstadoSolicContrato>();

    public ContratoDocente getContratoSelected() {
        return contratoSelected;
    }

    public void setContratoSelected(ContratoDocente contratoSelected) {
        this.contratoSelected = contratoSelected;
    }

    public List<EstadoSolicContrato> getHistoriaEstados() {
        return historiaEstados;
    }

    public VerHistoricoJSFManagedBean() {
    }

    public void verButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/opcion/verHistorico");
        super.getNavigationJSFManagedBean().setBaseJSFManagedBean(this);
        contratoSelected = (ContratoDocente) ae.getComponent().getAttributes().get("contrato");
        historiaEstados = estadoSolicContratoFacade.getHistoricoContrato(contratoSelected.getCdoCodigo());
        if (ae.getComponent().getClientId().indexOf("linkImpr") > 0) {
            RequestContext.getCurrentInstance().execute("widgetContrato.selectRow(" + contratoSelected.getFila() + ");");
            RequestContext.getCurrentInstance().update("formMant:panelMant");
        }
    }

}
