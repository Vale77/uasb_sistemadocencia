/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.bean;

import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.NoneScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "base")
@NoneScoped
public class BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120423L;
    private boolean disabledNuevo = false;
    private boolean disabledEliminar = true;
    private boolean disabledEditar = true;
    private boolean disabledSalvar = true;
    private boolean disabledRefrescar = false;
    private boolean disabledRegresar = false;
    private String paginaMant = "/WEB-INF/templates/includes/sinContenido";
    private String paginaTema = "/WEB-INF/templates/includes/sinContenido";
    @ManagedProperty(value = "#{navigationMgmtBean}")
    private NavigationJSFManagedBean navigationJSFManagedBean;
    private String posicionY = "middle";
    private String posicionYMant = "middle";

    //<editor-fold defaultstate="collapsed" desc="Propiedades">
    /**
     * @return the paginaTema
     */
    public String getPaginaTema() {
        return paginaTema;
    }

    /**
     * @param paginaTema the paginaTema to set
     */
    public void setPaginaTema(String paginaTema) {
        this.paginaTema = paginaTema;
    }

    /**
     * @return the disabledNuevo
     */
    public boolean isDisabledNuevo() {
        return disabledNuevo;
    }

    /**
     * @param disabledNuevo the disabledNuevo to set
     */
    public void setDisabledNuevo(boolean disabledNuevo) {
        this.disabledNuevo = disabledNuevo;
    }

    /**
     * @return the disabledEliminar
     */
    public boolean isDisabledEliminar() {
        return disabledEliminar;
    }

    /**
     * @param disabledEliminar the disabledEliminar to set
     */
    public void setDisabledEliminar(boolean disabledEliminar) {
        this.disabledEliminar = disabledEliminar;
    }

    /**
     * @return the disabledEditar
     */
    public boolean isDisabledEditar() {
        return disabledEditar;
    }

    /**
     * @param disabledEditar the disabledEditar to set
     */
    public void setDisabledEditar(boolean disabledEditar) {
        this.disabledEditar = disabledEditar;
    }

    /**
     * @return the disabledSalvar
     */
    public boolean isDisabledSalvar() {
        return disabledSalvar;
    }

    /**
     * @param disabledSalvar the disabledSalvar to set
     */
    public void setDisabledSalvar(boolean disabledSalvar) {
        this.disabledSalvar = disabledSalvar;
    }

    /**
     * @return the disabledRefrescar
     */
    public boolean isDisabledRefrescar() {
        return disabledRefrescar;
    }

    /**
     * @param disabledRefrescar the disabledRefrescar to set
     */
    public void setDisabledRefrescar(boolean disabledRefrescar) {
        this.disabledRefrescar = disabledRefrescar;
    }

    /**
     * @return the disabledRegresar
     */
    public boolean isDisabledRegresar() {
        return disabledRegresar;
    }

    /**
     * @param disabledRegresar the disabledRegresar to set
     */
    public void setDisabledRegresar(boolean disabledRegresar) {
        this.disabledRegresar = disabledRegresar;
    }

    /**
     * @return the navigationJSFManagedBean
     */
    public NavigationJSFManagedBean getNavigationJSFManagedBean() {
        return navigationJSFManagedBean;
    }

    /**
     * @param navigationJSFManagedBean the navigationJSFManagedBean to set
     */
    public void setNavigationJSFManagedBean(NavigationJSFManagedBean navigationJSFManagedBean) {
        this.navigationJSFManagedBean = navigationJSFManagedBean;
    }

    /**
     * @return the paginaMant
     */
    public String getPaginaMant() {
        return paginaMant;
    }

    /**
     * @param paginaMant the paginaMant to set
     */
    public void setPaginaMant(String paginaMant) {
        this.paginaMant = paginaMant;
    }

    /**
     * @return the posicionY
     */
    public String getPosicionY() {
        return posicionY;
    }

    /**
     * @param posicionY the posicionY to set
     */
    public void setPosicionY(String posicionY) {
        this.posicionY = posicionY;
    }

    /**
     * @return the posicionYMant
     */
    public String getPosicionYMant() {
        return posicionYMant;
    }

    /**
     * @param posicionYMant the posicionYMant to set
     */
    public void setPosicionYMant(String posicionYMant) {
        this.posicionYMant = posicionYMant;
    }

    //</editor-fold>
    /**
     * Creates a new instance of BaseJSFManagedBean
     */
    public BaseJSFManagedBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void onRowSelect(SelectEvent event) {

    }

    public void onRowUnselect(UnselectEvent event) {

    }

    public void nuevoButton_processAction(ActionEvent ae) {
        navigationJSFManagedBean.setBaseJSFManagedBean(this);
//        System.out.println("nuevoButton_processAction " + this.getClass().getName());
    }

    public void eliminarButton_processAction(ActionEvent ae) {
        JsfUtil.addSuccessMessage(null, "Dato eliminado ");
    }

    public void editarButton_processAction(ActionEvent ae) {
        navigationJSFManagedBean.setBaseJSFManagedBean(this);
    }

    public void postGuardar_processAction(ActionEvent ae) {
        imprimirButton_processAction(ae);
        init();
    }

    public void guardarButton_processAction(ActionEvent ae) {
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        RequestContext.getCurrentInstance().reset("formMant:panelMant");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    public void imprimirButton_processAction(ActionEvent ae) {
        System.out.println("imprimirButton_processAction " + this.getClass().getName());
    }

    public void cancelarButton_processAction(ActionEvent ae) {
        init();
        RequestContext.getCurrentInstance().reset("formMant:panelMant");
    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

}
