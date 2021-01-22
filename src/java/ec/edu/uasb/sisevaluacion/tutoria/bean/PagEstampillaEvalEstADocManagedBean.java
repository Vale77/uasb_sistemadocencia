/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.AnioAcademico;
import ec.edu.uasb.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.tutoria.session.ConsultaFacadeLocal;
import ec.edu.uasb.sisevaluacion.tutoria.session.EncuestaCalendarioFacadeLocal;

import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "pagEstampEvalEstADoc")
@ViewScoped
public class PagEstampillaEvalEstADocManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private List<String[]> listtrimestre = new ArrayList<String[]>();    
    private List<String[]> listAsigPagEst = new ArrayList<String[]>();
    private List<String[]> listAsigPagEstFiltro;
    private List<AnioAcademico> ciclos = new ArrayList<AnioAcademico>();    
    private String smcedestudiante = null;    
    private String smciclo = null;
        
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private AnioAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private EncuestaCalendarioFacadeLocal encuestaCalendarioFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
       public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public List<AnioAcademico> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<AnioAcademico> ciclos) {
        this.ciclos = ciclos;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public String getSmcedestudiante() {
        return smcedestudiante;
    }

    public void setSmcedestudiante(String smcedestudiante) {
        this.smcedestudiante = smcedestudiante;
    }

   
  
    public List<String[]> getListtrimestre() {
        return listtrimestre;
    }

    public void setListtrimestre(List<String[]> listtrimestre) {
        this.listtrimestre = listtrimestre;
    }

    public List<String[]> getListAsigPagEst() {
        return listAsigPagEst;
    }

    public void setListAsigPagEst(List<String[]> listAsigPagEst) {
        this.listAsigPagEst = listAsigPagEst;
    }

    public List<String[]> getListAsigPagEstFiltro() {
        return listAsigPagEstFiltro;
    }

    public void setListAsigPagEstFiltro(List<String[]> listAsigPagEstFiltro) {
        this.listAsigPagEstFiltro = listAsigPagEstFiltro;
    }
    
    
    // </editor-fold> 
    public PagEstampillaEvalEstADocManagedBean() {
        //this.setPaginaMant("/pages/asignatura/reaperturaAsignatura");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAll();    
    }

    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    
     private List<String[]> selectedasignatura = new ArrayList<String[]>();

    public List<String[]> getSelectedasignatura() {
        return selectedasignatura;
    }

    public void setSelectedasignatura(List<String[]> selectedasignatura) {
        this.selectedasignatura = selectedasignatura;
    }

    

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS ">   
    private void recuperaAsignatura() {
        Vector v = new Vector();
        listAsigPagEst.clear();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT are_codigo cod_facultad," 
                +" are_nombre nombre_facultad,"
                +" nest.codigo_esp, "
                +" nombre_programa,"
                +" nest.cod_estudiante,"
                +" est.CED_PAS_ESTUDIANTE, "
                +" concat (est.APELL_ESTUDIANTE, ' ', est.nom_estudiante)nombre_estudiante, "               
                +" nest.codigo_nivel ,"
                + "nest.cic_anio, "
                + " nest.NOMBRE_NIVEL"
                +" FROM v_notas_estudiante nest INNER JOIN programa prog "
                +" ON nest.cic_anio = prog.anio "
                +" AND nest.CODIGO_ESP = prog.prg_codigo INNER JOIN estudiante est"
                +" ON nest.COD_ESTUDIANTE = est.COD_ESTUDIANTE "
                +" WHERE nest.evaluacion_realizada >0"
                +" AND nest.estado_pagestampilla =0 "
                +" AND nest.cic_anio = ").append(smciclo).append(""
                +" AND est.CED_PAS_ESTUDIANTE ='").append(smcedestudiante).append("'"                        
                //+" AND nest.codigo_nivel =").append(strimestre).append(""
                + "ORDER BY nombre_estudiante");

        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[11];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());                      
                asign[8] = (object[8] == null ? null : object[8].toString());                      
                asign[9] = (object[9] == null ? null : object[9].toString());                      
                asign[10] = "A";
                listAsigPagEst.add(i, asign);
            }
        }
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  
    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        if (selectedasignatura != null) {
            if (selectedasignatura.size() > 0) {
                for (int i = 0; i < selectedasignatura.size(); i++) {
                    String[] asign;
                    asign = new String[11];                    
                    asign = selectedasignatura.get(i);                
                    encuestaCalendarioFacade.pagEstampillaEvalEstADocente(asign);
                }
                RequestContext.getCurrentInstance().update("fpagestampilla:tcalendario");
                RequestContext.getCurrentInstance().execute("mantWidget.hide();");
                JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
                trimestrevalueChangeListener();   
                limpiar();
            }
        }

    }
    // </editor-fold> 
     public void limpiar() {
        selectedasignatura = new ArrayList<String[]>();   
        listAsigPagEstFiltro =listAsigPagEst; 
    }
    public void trimestrevalueChangeListener() {                
        if ( smciclo != null && smcedestudiante != null) {            
            recuperaAsignatura();            
        }
        
    }
}
