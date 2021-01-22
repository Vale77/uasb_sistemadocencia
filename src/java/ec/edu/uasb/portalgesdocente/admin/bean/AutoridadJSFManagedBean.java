/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.admin.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.portalgesdocente.entities.Autoridad;
import ec.edu.uasb.portalgesdocente.entities.Cargos;
import ec.edu.uasb.portalgesdocente.entities.Personal;
import ec.edu.uasb.portalgesdocente.session.AutoridadFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.CargosFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.PersonalFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "autoridadBean")
@ViewScoped
public class AutoridadJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    @EJB
    private AutoridadFacadeLocal autoridadFacade;
    @EJB
    private CargosFacadeLocal cargosFacade;
    @EJB
    private PersonalFacadeLocal personalFacade;

    private List<Autoridad> autoridades = new ArrayList<Autoridad>();
    private List<Cargos> listaCargos = new ArrayList<Cargos>();
    private Autoridad autoridadEdit = new Autoridad();
    private Autoridad autoridadSelected;
    private List<Personal> listaPersonal = new ArrayList<Personal>();

//<editor-fold defaultstate="collapsed" desc="Atributos">
    public List<Cargos> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargos> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public Autoridad getAutoridadEdit() {
        return autoridadEdit;
    }

    public void setAutoridadEdit(Autoridad autoridadEdit) {
        this.autoridadEdit = autoridadEdit;
    }

    public List<Autoridad> getAutoridades() {
        return autoridades;
    }

    public void setAutoridades(List<Autoridad> autoridades) {
        this.autoridades = autoridades;
    }

    public List<Personal> getListaPersonal() {
        return listaPersonal;
    }

    public Autoridad getAutoridadSelected() {
        return autoridadSelected;
    }

    public void setAutoridadSelected(Autoridad autoridadSelected) {
        this.autoridadSelected = autoridadSelected;
    }

//</editor-fold>
    /**
     * Creates a new instance of RolesJSFManagedBean
     */
    public AutoridadJSFManagedBean() {
        this.setPaginaMant("/pages/admin/regAutoridad");
    }

    @PostConstruct
    private void recuperarListados() {
        autoridades = autoridadFacade.findAll();
        listaPersonal = personalFacade.getListaPersonal();
        listaCargos = cargosFacade.findAll();
        autoridadEdit = new Autoridad();
        autoridadEdit.setCargos(new Cargos());
        autoridadSelected = null;

    }

    public void asignarNombres() {
        Personal p = personalFacade.find(autoridadEdit.getAutCodPersonal());
        autoridadEdit.setAutNombres(p.getNombres() + ' ' + p.getApellidos());
    }

    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        autoridadEdit = new Autoridad();
        autoridadEdit.setCargos(new Cargos());
        super.nuevoButton_processAction(ae);

    }

    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        if (autoridadEdit.getCargos().getCarCodigo() != null) {
            int i = listaCargos.indexOf(autoridadEdit.getCargos());
            autoridadEdit.setCargos(listaCargos.get(i));
        }
        if (autoridadEdit.getAutCodigo() == null) {
            autoridadFacade.create(autoridadEdit);
        } else {
            autoridadFacade.edit(autoridadEdit);
        }
        RequestContext.getCurrentInstance().update("formAutoridad:dataAutoridad");
        super.guardarButton_processAction(ae);
        recuperarListados();
    }

    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/admin/regAutoridad");
        super.editarButton_processAction(ae);
        try {
            autoridadEdit = autoridadSelected.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AutoridadJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
