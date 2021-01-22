/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.reportes.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.AnioAcademico;
import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.Programa;
import ec.edu.uasb.portalgesdocente.entities.AsignaturaSyllabus;
import ec.edu.uasb.portalgesdocente.session.AsignaturaSyllabusFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.session.AreaFacadeLocal;
import ec.edu.uasb.session.ProgramaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "silaboBean")
@ViewScoped
public class SilaboJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;

    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private AreaFacadeLocal areaFacade;
    @EJB
    private ProgramaFacadeLocal programaFacade;
    @EJB
    private AsignaturaSyllabusFacadeLocal asignaturaSyllabusFacade;

    private final Usuario usr;
    private final List<Perfil> perfiles;
    private AsignaturaSyllabus asignaturaSelected;
    private List<AsignaturaSyllabus> asignaturaSyllabuses = new ArrayList<AsignaturaSyllabus>();
    private List<AnioAcademico> academicos = new ArrayList<AnioAcademico>();
    private List<Area> areas = new ArrayList<Area>();
  //  private List<AsignaturaSyllabus> filtroParalelo = new ArrayList<AsignaturaSyllabus>();
    private String filtrarAreas;
    private List<Programa> programas = new ArrayList<Programa>();
    private Long area;
    private Long anio;
    private Programa progra;
    
   //  private ArrayList<AsignaturaSyllabus> filtroParalelo = new ArrayList<>();
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public void setProgra(Programa progra) {
        this.progra = progra;
    }

    public Programa getProgra() {
        return progra;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public List<AnioAcademico> getAcademicos() {
        return academicos;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

    public List<AsignaturaSyllabus> getAsignaturaSyllabuses() {
        return asignaturaSyllabuses;
    }

    public AsignaturaSyllabus getAsignaturaSelected() {
        return asignaturaSelected;
    }

    public void setAsignaturaSelected(AsignaturaSyllabus asignaturaSelected) {
        this.asignaturaSelected = asignaturaSelected;
    }

   

//</editor-fold>
    /**
     * Creates a new instance of SolicContratoJSFManagedBean
     */
    public SilaboJSFManagedBean() {
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        // seguridad en perfiles y areas
        perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarAreas");
        for (Perfil pef : perfiles) {
            if (pef.getIdPerfil().equals("SEC_GREC") || pef.getIdPerfil().equals("US_BIBLI")) {
                filtrarAreas = "T";
                break;
            }
        }
    }

    @Override
    public void init() {
        programas.clear();
        areas.clear();
        area = null;
        progra = null;
        asignaturaSelected = null;
    }

    @PostConstruct
    private void recuperarListados() {
        academicos = anioAcademicoFacade.findAll();
        if (!academicos.isEmpty()) {
            anio = academicos.get(0).getAnio();
        }
        recuperarAreas();
        recupContratosAsignaturas();
    }

    //<editor-fold defaultstate="collapsed" desc="Anio, Areas y programas">
    public void recupContratosAsignaturas() {
        asignaturaSelected = null;
        asignaturaSyllabuses.clear();
        if (anio != null && area != null && progra != null) {
            asignaturaSyllabuses = asignaturaSyllabusFacade.getAsignatSyllabus(anio, area, progra.getProgramaPK().getPrgCodigo());
        }
    }

    private void recuperarAreas() {
        if (filtrarAreas.equals("T")) {
            areas = areaFacade.findAll();
        } else if (filtrarAreas.equals("A")) {
            areas = areaFacade.getAreasBySecre(usr.getUsuCodigo());
        }
        area = areas.size() >= 1 ? areas.get(0).getAreCodigo() : null;
        if (area != null) {
            recuperarProgramas();
        }
    }

    private void recuperarProgramas() {
        programas.clear();
        if (area != null && anio != null) {
            programas = programaFacade.getProgramasByArea(area, anio);
            if (programas.size() == 1) {
                progra = programas.get(0);
            }
        }
    }

    public void handleAreaAnioProgChange(String select) {
        if (!select.equals("silabos")) {
            progra = null;
            recuperarProgramas();
        }
        recupContratosAsignaturas();
        RequestContext.getCurrentInstance().execute("widgetSilabo.unselectAllRows();");

    }

    //</editor-fold>
    @Override
    public void imprimirButton_processAction(ActionEvent ae) {
        String urlReporte ="tipo=pdf"
                + "&anio=" + asignaturaSelected.getAnio()
                + "&asig=" + asignaturaSelected.getAsignaturaSyllabusPK().getCodigoMateria()
                + "&per=" + asignaturaSelected.getAsignaturaSyllabusPK().getCodigoNivel()
                + "&par=" + asignaturaSelected.getAsignaturaSyllabusPK().getCodParalelo()
                + "&prog=" + asignaturaSelected.getAsignaturaSyllabusPK().getCodPrograma();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
    }

}
