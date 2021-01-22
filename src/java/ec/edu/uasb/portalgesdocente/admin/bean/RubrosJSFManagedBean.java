/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.admin.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.Rubros;
import ec.edu.uasb.portalgesdocente.session.RubrosFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "rubrosBean")
@ViewScoped
public class RubrosJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    @EJB
    private RubrosFacadeLocal rubrosFacade;
    private List<Rubros> rubros = new ArrayList<Rubros>();
    private String tipoRubro;

//<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * @return the rubros
     */
    public List<Rubros> getRubros() {
        return rubros;
    }

    /**
     * @return the tipoRubro
     */
    public String getTipoRubro() {
        return tipoRubro;
    }

    /**
     * @param tipoRubro the tipoRubro to set
     */
    public void setTipoRubro(String tipoRubro) {
        this.tipoRubro = tipoRubro;
    }
//</editor-fold>

    /**
     * Creates a new instance of RolesJSFManagedBean
     */
    public RubrosJSFManagedBean() {
    }

    @PostConstruct
    public void recuperarListados() {
        if (tipoRubro != null) {
            rubros = rubrosFacade.getRubrosByCateg(tipoRubro);
        }
    }

    public void onRowEdit(RowEditEvent event) {
        Rubros rubro = (Rubros) event.getObject();
        rubrosFacade.edit(rubro);
        recuperarListados();
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
    }

    public void onRowCancel(RowEditEvent event) {
    }
}
