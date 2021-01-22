/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.tutoria.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.AnioAcademico;
import ec.edu.uasb.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.sisevaluacion.tutoria.entities.Encuesta;
import ec.edu.uasb.sisevaluacion.tutoria.session.ConsultaFacadeLocal;

import ec.edu.uasb.sisevaluacion.tutoria.session.EncuestaCalendarioFacadeLocal;
import ec.edu.uasb.sisevaluacion.tutoria.session.EncuestaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "reaperturaEvalEstATutor")
@ViewScoped
public class ReaperturaEvalEstATutorManagedBean extends BaseJSFManagedBean implements Serializable {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");   
    private List<String[]> listTutoriaReapertura = new ArrayList<String[]>();
    private List<String[]> listTutoriaFiltro;
    private List<AnioAcademico> ciclos = new ArrayList<AnioAcademico>();
    private List<Encuesta> listEncuesta = new ArrayList<Encuesta>();
    private String sencuesta = null;
    private String smciclo = null;
    private Date fecinicio = null;
    @EJB
    private EncuestaFacadeLocal encuestaFacade;
    @EJB
    private EncuestaCalendarioFacadeLocal encuestaCalendarioFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private AnioAcademicoFacadeLocal cicloAcademicoFacade;

    // <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">

    public List<String[]> getListTutoriaReapertura() {
        return listTutoriaReapertura;
    }

    public void setListTutoriaReapertura(List<String[]> listTutoriaReapertura) {
        this.listTutoriaReapertura = listTutoriaReapertura;
    }

    public List<String[]> getListTutoriaFiltro() {
        return listTutoriaFiltro;
    }

    public void setListTutoriaFiltro(List<String[]> listTutoriaFiltro) {
        this.listTutoriaFiltro = listTutoriaFiltro;
    }
   

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

    public String getSencuesta() {
        return sencuesta;
    }

    public void setSencuesta(String sencuesta) {
        this.sencuesta = sencuesta;
    }

    public Date getFecinicio() {
        return fecinicio;
    }

    public void setFecinicio(Date fecinicio) {
        this.fecinicio = fecinicio;
    }

    

    // </editor-fold> 
    public ReaperturaEvalEstATutorManagedBean() {
        this.setPaginaMant("/pages/tutoria/reaperturaEvalEstATutor");
    }

    @Override
    public void init() {
        ciclos = cicloAcademicoFacade.findAll();
    }

    
    // <editor-fold defaultstate="collapsed" desc="CARGAR LAS ENCUESTAS HABILITADAS"> 

    public List<Encuesta> getListEncuesta() {
        listEncuesta.clear();
        listEncuesta = encuestaFacade.findAllActivo('T');
        return listEncuesta;
    }

    public void setListEncuesta(List<Encuesta> listEncuesta) {
        this.listEncuesta = listEncuesta;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="SELECCION DE FILA PARA EDICION">  
    private String[] selectedasignatura = new String[19];

    public String[] getSelectedasignatura() {
        return selectedasignatura;
    }

    public void setSelectedasignatura(String[] selectedasignatura) {
        this.selectedasignatura = selectedasignatura;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="CARGAR TABLA ASIGNATURAS ">   
    private void recuperaAsignatura(Long anio) {
        Vector v = new Vector();
        listTutoriaReapertura.clear();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT (case when  enccal.anio is null then concat(ESTUDIANTE.ANIO,'-',ESTUDIANTE.CODIGO_TUTOR,'-',ESTUDIANTE.codigo_esp) else concat(ENCCAL.ANIO,'-','-',ENCCAL.CODIGO_ENCUESTA,'-',ENCCAL.CODIGO_PROFESOR,'-',ENCCAL.codigo_nivel,'-',ENCCAL.codigo_esp) end)cod,"
                +" (case when enccal.ANIO is null then ESTUDIANTE.anio else enccal.ANIO end) ANIO, "
                +" (CASE WHEN enccal.CICLO IS NULL THEN 1 ELSE enccal.CICLO END)CICLO,"
                +" (CASE WHEN enccal.CODIGO_MATERIA IS NULL THEN -1 ELSE enccal.CODIGO_MATERIA END ) CODIGO_MATERIA,"
                +" (CASE WHEN enccal.CODIGO_PROFESOR IS NULL THEN ESTUDIANTE.CODIGO_TUTOR ELSE enccal.CODIGO_PROFESOR END) CODIGO_PROFESOR,"
                +" (CASE WHEN enccal.CODIGO_ENCUESTA is null then 16 else enccal.CODIGO_ENCUESTA end ) CODIGO_ENCUESTA,"
                +" (CASE WHEN enccal.cod_estudiante IS NULL  THEN ESTUDIANTE.CODIGO_ESTUDIANTE ELSE enccal.cod_estudiante END) cod_estudiante ,"
                +" (CASE WHEN enccal.codigo_esp IS NULL  THEN ESTUDIANTE.CODIGO_ESP ELSE enccal.codigo_esp END) CODIGO_ESP, "
                +" (CASE WHEN enccal.codigo_nivel IS NULL  THEN -1 ELSE enccal.codigo_nivel END)CODIGO_NIVEL,"
                +" enccal.FECHA_INICIO,"
                +" enccal.FECHA_FIN,"
                +" (case enccal.ESTADO_ENCUESTA when 'A' then 'Abierta' when'C' then 'Cerrada' end )ESTADO_ENCUESTA , "
                +" ESTUDIANTE.NOM_TUTOR  profesor,"
                +" '' NOMBRE_MATERIA,"
                +" ENC.TITULO, "
                +" ESTUDIANTE.PROGRAMA,"
                +" (case (SELECT COUNT(1)  from EVALUACION.ENCUESTA_REALIZADA WITH (NOLOCK) "
                +" where codigo_alumno = ESTUDIANTE.CODIGO_ESTUDIANTE "
                +" and anio = enccal.ANIO and ciclo = enccal.CICLO and codigo_esp = ESTUDIANTE.CODIGO_ESP and "
                +" codigo_materia = enccal.CODIGO_MATERIA and codigo_encuesta = ENC.CODIGO_ENCUESTA "
                +" and codigo_profesor = enccal.CODIGO_PROFESOR  and TIPO_REGISTRO = 'A' AND CODIGO_NIVEL = -1) when 1 then 'SI' else 'NO' END ) ESTADO,"
                + " ESTUDIANTE.NOMBRES "
                +" FROM VISTA_ESTUDIANTE_TESIS_CICLO ESTUDIANTE  LEFT OUTER JOIN evaluacion.ENCUESTA_CALENDARIO as enccal "
                +" ON ESTUDIANTE.ANIO =  enccal.ANIO "
                +" AND ESTUDIANTE.CODIGO_ESP = enccal.CODIGO_ESP "
                +" AND ENCCAL.CODIGO_nivel  =-1 "
                +" AND enccal.CODIGO_MATERIA  = -1 "
               +" AND ESTUDIANTE.CODIGO_TUTOR = enccal.CODIGO_PROFESOR "
                +" AND ESTUDIANTE.CODIGO_ESTUDIANTE = ENCCAL.COD_ESTUDIANTE"
                +" AND (ENCCAL.tipo_encuesta = 'T' or ENCCAL.tipo_encuesta is null)  LEFT OUTER JOIN EVALUACION.ENCUESTA ENC "
                +" ON ENCCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA "
                +" WHERE ESTUDIANTE.ANIO = ").append(smciclo).append(""
                +" AND (ENCCAL.eSTADO_ENCUESTA <>'C' or ENCCAL.eSTADO_ENCUESTA is null) "
                +" AND ESTUDIANTE.F_RES_TUTOR IS NOT NULL "
                +" AND ESTUDIANTE.CODIGO_TUTOR IS NOT NULL "
                +" ORDER BY ESTADO_ENCUESTA, enccal.FECHA_FIN desc");
        
        v = (Vector) consultaFacade.ejecutaSqlList(sql.toString());
        if (v.size() > 0) {
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] asign;
                asign = new String[20];
                asign[0] = (object[0] == null ? null : object[0].toString());
                asign[1] = (object[1] == null ? null : object[1].toString());
                asign[2] = (object[2] == null ? null : object[2].toString());
                asign[3] = (object[3] == null ? null : object[3].toString());
                asign[4] = (object[4] == null ? null : object[4].toString());
                asign[5] = (object[5] == null ? null : object[5].toString());
                asign[6] = (object[6] == null ? null : object[6].toString());
                asign[7] = (object[7] == null ? null : object[7].toString());
                asign[8] = (object[8] == null ? null : object[8].toString());
                asign[9] = (object[9] == null ? null : formato.format(object[9]));
                asign[10] = (object[10] == null ? null : formato.format(object[10]));
                asign[11] = (object[11] == null ? null : object[11].toString());
                asign[12] = (object[12] == null ? null : object[12].toString());
                asign[13] = (object[13] == null ? null : object[13].toString());
                asign[14] = (object[14] == null ? null : object[14].toString());
                /*ASIGNO EL TIPO DE ENCUESTA "A" PARA ASIGNATURA */
               asign[15] = "T";
                 /*ASIGNO EL CODIGO DE PARALELO -1 PARA ESTA ENCUESTA */
                asign[16] = "-1";
                asign[17] = (object[15] == null ? null :object[15].toString());
                asign[18] = (object[16] == null ? null : object[16].toString());                
                asign[19] = (object[17] == null ? null : object[17].toString());
                listTutoriaReapertura.add(i, asign);

            }
        }
    }

    // </editor-fold> 
    public void onRowEdit(RowEditEvent event) {
        selectedasignatura = (String[]) event.getObject();
        if (sencuesta != null) {
            selectedasignatura[5] = sencuesta;
        }
        selectedasignatura[9] = formato.format(fecinicio);
        encuestaCalendarioFacade.reaperturaEncuesta(selectedasignatura);
        RequestContext.getCurrentInstance().update("freaperturaEstATut:tcalendario");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        ciclovalueChangeListener();
    }

    public void ciclovalueChangeListener() {
        if (smciclo != null) {
        recuperaAsignatura(Long.valueOf(smciclo));
        }
   }

}
