/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.admin.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import ec.edu.uasb.portalgesdocente.session.RolDocenteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "rolesBean")
@ViewScoped
public class RolesJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    @EJB
    private RolDocenteFacadeLocal rolDocenteFacade;
    private List<RolDocente> rolesDocentes = new ArrayList<RolDocente>();

//<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * @return the rolesDocentes
     */
    public List<RolDocente> getRolesDocentes() {
        return rolesDocentes;
    }

    /**
     * @param rolesDocentes the rolesDocentes to set
     */
    public void setRolesDocentes(List<RolDocente> rolesDocentes) {
        this.rolesDocentes = rolesDocentes;
    }

//</editor-fold>
    /**
     * Creates a new instance of RolesJSFManagedBean
     */
    public RolesJSFManagedBean() {
    }


    @Override
    public void init() {
        rolesDocentes = rolDocenteFacade.findAll();
    }
}
