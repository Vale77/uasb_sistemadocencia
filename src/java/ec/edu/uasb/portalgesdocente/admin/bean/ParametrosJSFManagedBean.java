/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.admin.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.Autoridad;
import ec.edu.uasb.portalgesdocente.entities.Parametros;
import ec.edu.uasb.portalgesdocente.session.AutoridadFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.ParametrosFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "parametrosBean")
@ViewScoped
public class ParametrosJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120458L;
    @EJB
    private ParametrosFacadeLocal parametrosFacade;
    @EJB
    private AutoridadFacadeLocal autoridadFacade;
    private List<Autoridad> autoridades = new ArrayList<Autoridad>();
    private Parametros parametros = new Parametros();
    private final Usuario usr;
    private Long codAutoridad;

//<editor-fold defaultstate="collapsed" desc="Atributos">
    public Long getCodAutoridad() {
        return codAutoridad;
    }

    public void setCodAutoridad(Long codAutoridad) {
        this.codAutoridad = codAutoridad;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public List<Autoridad> getAutoridades() {
        return autoridades;
    }

//</editor-fold>
    /**
     * Creates a new instance of RolesJSFManagedBean
     */
    public ParametrosJSFManagedBean() {
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    @Override
    public void init() {
        autoridades = autoridadFacade.findAll();
        parametros = parametrosFacade.find(new Short("1"));
        if (parametros == null) {
            parametros = new Parametros(new Short("1"));
            parametros.setAutoridad(new Autoridad());
        }
        codAutoridad = parametros.getAutoridad().getAutCodigo();
    }

    public void changeRector() {
        try {
            Autoridad autoridad = autoridadFacade.find(codAutoridad);
            if (autoridad.getCargos().getCarCodigo().equals(new Long("1"))) {//  Siempre es el Código del Rector
                parametros.setPrmEtiquetaRector("Rector");
            } else {
                parametros.setPrmEtiquetaRector("Rector (e)");
            }
            parametros.setAutoridad(autoridad);
        } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        parametros.setPrmUsuModif(usr.getUsuCodigo());
        parametros.setPrmFechaModif(new Date());
        if (parametros.getPrmCodigo() == null) {
            parametrosFacade.create(parametros);
        } else {
            parametrosFacade.edit(parametros);
        }
        init();
        JsfUtil.addSuccessMessage(null, "Parámetro(s) actualizado(s)");

    }

}
