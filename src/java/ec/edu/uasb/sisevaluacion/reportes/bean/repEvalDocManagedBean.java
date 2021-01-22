/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.AnioAcademico;
import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.Programa;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.session.AreaFacadeLocal;
import ec.edu.uasb.session.ProgramaFacadeLocal;
import ec.edu.uasb.sisevaluacion.tutoria.session.ConsultaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "repEvalDocBean")
@ViewScoped
public class repEvalDocManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;

    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private AreaFacadeLocal areaFacade;
    @EJB
    private ProgramaFacadeLocal programaFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;

    private String urlReporte = null;
    private final Usuario usr;
    private final List<Perfil> perfiles;
    private List<AnioAcademico> academicos = new ArrayList<AnioAcademico>();
    private List<String[]> listDocProg = new ArrayList<String[]>();
    private List<Area> areas = new ArrayList<Area>();
    private String filtrarAreas;
    private List<Programa> programas = new ArrayList<Programa>();
    private Long area;
    private Long anio;
    private Programa progra;
    private String smtipo;
    private String[] selectedProgDocMateria;

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public void setProgra(Programa progra) {
        this.progra = progra;
    }

    public Programa getProgra() {
        return progra;
    }

    public String[] getSelectedProgDocMateria() {
        return selectedProgDocMateria;
    }

    public void setSelectedProgDocMateria(String[] selectedProgDocMateria) {
        this.selectedProgDocMateria = selectedProgDocMateria;
    }

    public List<String[]> getListDocProg() {
        return listDocProg;
    }

    public void setListDocProg(List<String[]> listDocProg) {
        this.listDocProg = listDocProg;
    }

    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
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
//</editor-fold>

    /**
     * Creates a new instance of SolicContratoJSFManagedBean
     */
    public repEvalDocManagedBean() {
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
    }

    @PostConstruct
    private void recuperarListados() {
        academicos = anioAcademicoFacade.findAll();
        if (!academicos.isEmpty()) {
            anio = academicos.get(0).getAnio();
        }
        recuperarAreas();
    }

//<editor-fold defaultstate="collapsed" desc="Anio, Areas y programas">
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
        if (select.equals("anio") || select.equals("areas")) {
            progra = null;
            smtipo = null;
            recuperarProgramas();
        }
        if (select.equals("programa")) {
            smtipo = null;
        }
        if (select.equals("reporte")) {
            if (smtipo.equals("D") || smtipo.equals("T") || smtipo.equals("C")) {
                recDocPrograma();
            }
        }
    }

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Validar">
    public int validar() {
        int resp = 0;
        String ls_reporte = null;
        String ls_tipo = null;
        String ls_codProfesor = null;
        urlReporte = "";
        if (anio == null) {
            resp = -1;
        } else if (smtipo == null) {
            resp = -1;
        } else {
            ls_tipo = "P";
            ls_codProfesor = "T";
            if (smtipo.equalsIgnoreCase("D")) {
                if (selectedProgDocMateria != null) {
                    ls_tipo = "D";
                    ls_codProfesor = selectedProgDocMateria[0];
                }
                ls_reporte = "EvalDeEstudAlDocenteAnual";
            } else if (smtipo.equalsIgnoreCase("CD")) {
                ls_reporte = "EvalDeEstudAlDocenteConsol";
            } else if (smtipo.equalsIgnoreCase("A")) {
                ls_reporte = "EvalDeEstudAAsignaturaAnual";
            } else if (smtipo.equalsIgnoreCase("CA")) {
                ls_reporte = "EvalDeEstudAAsignaturaConsol";
            } else if (smtipo.equalsIgnoreCase("T")) {
                if (selectedProgDocMateria != null) {
                    ls_tipo = "D";
                    ls_codProfesor = selectedProgDocMateria[0];
                }
                ls_reporte = "EvalDeEstudAlTutorAnual";
            } else if (smtipo.equalsIgnoreCase("CT")) {
                ls_reporte = "EvalDeEstudAlTutorConsol";
            } else if (smtipo.equalsIgnoreCase("P")) {
                ls_reporte = "EvalDeEstudAlProgramaAnual";
            } else if (smtipo.equalsIgnoreCase("CP")) {
                ls_reporte = "EvalDeEstudAlProgramaConsol";
            } else if (smtipo.equalsIgnoreCase("CPM")) {
                ls_reporte = "EvalDeEstudAlProgramaMInvestigacionConsol";
            } else if (smtipo.equalsIgnoreCase("CPD")) {
                ls_reporte = "EvalDeEstudAlProgramaDoctoConsol";
            } else if (smtipo.equalsIgnoreCase("C")) {
                if (selectedProgDocMateria != null) {
                    ls_tipo = "D";
                    ls_codProfesor = selectedProgDocMateria[0];
                }
                ls_reporte = "EvalDeCoordAlDocenteAnual";
            } else if (smtipo.equalsIgnoreCase("CC")) {
                ls_reporte = "EvalDeCoordAlDocenteConsol";
            } else if (smtipo.equalsIgnoreCase("COP")) {
                ls_reporte = "EvalDeCoordAlProgramaAnual";
            } else if (smtipo.equalsIgnoreCase("CCOP")) {
                ls_reporte = "EvalDeCoordAlProgramaConsol";
            }
            urlReporte = "&tipReporte=" + ls_tipo
                    + "&reporte=" + ls_reporte
                    + "&anio=" + anio
                    + "&codProfesor=" + ls_codProfesor
                    + "&codArea=T"
                    + "&codEsp=" + progra.getProgramaPK().getPrgCodigo()
                    + "&codTrim=T";
        }

        return resp;
    }

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Imprimir">
    public void imprimirPrograma_processAction(ActionEvent ae) {
        selectedProgDocMateria = null;
        imprimirButton_processAction(ae);
    }

    @Override
    public void imprimirButton_processAction(ActionEvent ae) {
        if (validar() == 0) {
            urlReporte = urlReporte
                    + "&tipo=pdf"
                    + "&contexto=PortalGesDocente";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
        }
    }

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Recuperar Docente Programa">
    public void recDocPrograma() {
        listDocProg.clear();
        String ls_codencuesta = null;
        Vector v = new Vector();
        StringBuilder sql = new StringBuilder();
        if (smtipo.equalsIgnoreCase("D") || smtipo.equalsIgnoreCase("C")) {

            sql.append("SELECT DISTINCT  mat.CODIGO_PROFESOR, "
                    + "mat.APELLIDO_PROFESOR +' '+   "
                    + "mat.NOMBRE_PROFESOR nombre "
                    + "FROM interfaseOcu.dbo.VISTA_COORDINADOR_PROGRAMA mat "
                    + "INNER JOIN  interfaseOcu.evaluacion.ENCUESTA_REALIZADA encre on mat.ANIO = encre.ANIO "
                    + "and mat.CICLO = encre.CICLO "
                    + "AND mat.CODIGO_ESP = encre.CODIGO_ESP "
                    + "AND mat.CODIGO_NIVEL = encre.CODIGO_NIVEL "
                    + "and mat.COD_PARALELO = encre.CODIGO_PARALELO "
                    + "and mat.CODIGO_MATERIA = encre.CODIGO_MATERIA  "
                    + "and mat.CODIGO_PROFESOR = encre.CODIGO_PROFESOR "
                    + "WHERE encre.CODIGO_ENCUESTA = 17 "
                    + "AND encre.ANIO = " + anio
                    + "AND mat.codigo_esp = " + progra.getProgramaPK().getPrgCodigo()
                    + "ORDER BY nombre ASC");
        }
        if (smtipo.equalsIgnoreCase("T")) {

            sql.append("DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0), nombre_profesor VARCHAR(32), apellido_profesor VARCHAR(35) ) "
                    + "	INSERT  INTO @PROFESOR(CODIGO_PROFESOR,nombre_profesor, apellido_profesor) "
                    + "	SELECT CODIGO_PROFESOR,nombre_profesor, apellido_profesor "
                    + "	FROM interfaseOcu.dbo.PROFESOR "
                    + "SELECT DISTINCT prof.codigo_profesor, "
                    + " prof.APELLIDO_PROFESOR+' '+prof.NOMBRE_PROFESOR nombre"
                    + " FROM interfaseOcu.EVALUACION.ENCUESTA_REALIZADA encre"
                    + " INNER JOIN @PROFESOR prof ON encre.CODIGO_PROFESOR = prof.CODIGO_PROFESOR"
                    + " WHERE encre.CODIGO_ENCUESTA = 16"
                    + " AND encre.ANIO=" + anio
                    + " AND encre.CODIGO_ESP = " + progra.getProgramaPK().getPrgCodigo()
                    + "ORDER BY nombre ASC");

        }
        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] programaciclo;
                programaciclo = new String[2];
                programaciclo[0] = object[0].toString();
                programaciclo[1] = object[1].toString();
                listDocProg.add(i, programaciclo);
            }
        }
    }

    //</editor-fold>
}
