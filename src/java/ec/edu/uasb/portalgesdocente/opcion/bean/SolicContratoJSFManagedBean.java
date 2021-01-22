/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.opcion.bean;

import ec.edu.uasb.portalgesdocente.reportes.bean.SilaboJSFManagedBean;
import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.AnioAcademico;
import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.Asignatura;
import ec.edu.uasb.entities.AsignaturaPK;
import ec.edu.uasb.entities.Programa;
import ec.edu.uasb.portalgesdocente.entities.CandidatoDocente;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.DatosViajero;
import ec.edu.uasb.portalgesdocente.entities.DatosViajeroPK;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContratoPK;
import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import ec.edu.uasb.portalgesdocente.entities.HonorariosPK;
import ec.edu.uasb.portalgesdocente.entities.PorTutoriaMonografia;
import ec.edu.uasb.portalgesdocente.entities.RolDocente;
import ec.edu.uasb.portalgesdocente.entities.Rubros;
import ec.edu.uasb.portalgesdocente.entities.Tesis;
import ec.edu.uasb.portalgesdocente.entities.TipoEstado;
import ec.edu.uasb.portalgesdocente.entities.TiposFormaPago;
import ec.edu.uasb.portalgesdocente.session.CandidatoDocenteFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.ContratoDocenteFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.DatosViajeroFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.EstadoSolicContratoFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.HonorariosFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.PorTutoriaMonografiaFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.RolDocenteFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.RubrosFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.TesisFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.TipoEstadoFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.TiposFormaPagoFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.session.AreaFacadeLocal;
import ec.edu.uasb.session.AsignaturaFacadeLocal;
import ec.edu.uasb.session.ProgramaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "solicContratoBean")
@ViewScoped
public class SolicContratoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;

    @EJB
    private PorTutoriaMonografiaFacadeLocal porTutoriaMonografiaFacade;
    @EJB
    private TesisFacadeLocal tesisFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private TipoEstadoFacadeLocal tipoEstadoFacade;
    @EJB
    private EstadoSolicContratoFacadeLocal estadoSolicContratoFacade;
    @EJB
    private DatosViajeroFacadeLocal datosViajeroFacade;
    @EJB
    private HonorariosFacadeLocal honorariosFacade;
    @EJB
    private CandidatoDocenteFacadeLocal candidatoDocenteFacade;
    @EJB
    private AsignaturaFacadeLocal asignaturaFacade;
    @EJB
    private RolDocenteFacadeLocal rolDocenteFacade;
    @EJB
    private AreaFacadeLocal areaFacade;
    @EJB
    private ProgramaFacadeLocal programaFacade;
    @EJB
    private ContratoDocenteFacadeLocal contratoDocenteFacade;
    @EJB
    private TiposFormaPagoFacadeLocal tiposFormaPagoFacade;
    @EJB
    private RubrosFacadeLocal rubrosFacade;

    private final Usuario usr;
    private final List<Perfil> perfiles;
    private ContratoDocente contratoSelected;
    private ContratoDocente contratoEdit;
    private Long area;
    private String tipoEstado;
    private List<AnioAcademico> academicos = new ArrayList<AnioAcademico>();
    private List<ContratoDocente> contratosDocentes = new ArrayList<ContratoDocente>();
    private List<CandidatoDocente> candidatos = new ArrayList<CandidatoDocente>();
    private List<RolDocente> rolesDoc = new ArrayList<RolDocente>();
    private List<TiposFormaPago> formaPagos = new ArrayList<TiposFormaPago>();
    private List<Asignatura> asignaturas = new ArrayList<Asignatura>();
    private String codAsig;
    private final List<TipoEstado> estados = new ArrayList<TipoEstado>();
    private List<Area> areas = new ArrayList<Area>();
    private final String filtrarAreas;
    private List<Programa> programas = new ArrayList<Programa>();
    private BigDecimal valorFuncion;
    private BigDecimal totalViaticos;
    private String unidadMedida;
    private DatosViajero datosViaje;
    private DatosViajero residencia;
    private Honorarios viaticoSelected;
    private Honorarios viaticoEdit;
    private List<Honorarios> viaticos = new ArrayList<Honorarios>();
    private List<Rubros> rubrosResid = new ArrayList<Rubros>();
    private List<Rubros> rubrosViatic = new ArrayList<Rubros>();
    private List<PorTutoriaMonografia> tutorias = new ArrayList<PorTutoriaMonografia>();
    private List<Tesis> listaTesis = new ArrayList<Tesis>();
    private Tesis tesis;
    private PorTutoriaMonografia tutMonSelected;
    private Long rubroResid;
    private boolean tieneViatico;
    private boolean disableAddViatico;
    final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    private boolean descPorResidencia;
    private Long anio;
    private Programa progra;
    private boolean tieneTitulo = false;
    private CandidatoDocente candidatoDocente;
    private Short maxHoras;
    private RolDocente rolDocente;
    private final BigDecimal prcPagoxAsistAcad = new BigDecimal("0.6");
    private final BigDecimal prcPagoxHorasPracticas = new BigDecimal("0.5625");
    private Short horasPracticas;

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public void setProgra(Programa progra) {
        this.progra = progra;
    }

    public Programa getProgra() {
        return progra;
    }

    public void setRolDocente(RolDocente rolDocente) {
        this.rolDocente = rolDocente;
    }

    public RolDocente getRolDocente() {
        return rolDocente;
    }

    public Short getMaxHoras() {
        return maxHoras;
    }

    public List<Tesis> getListaTesis() {
        return listaTesis;
    }

    public void setCandidatoDocente(CandidatoDocente candidatoDocente) {
        this.candidatoDocente = candidatoDocente;
    }

    public CandidatoDocente getCandidatoDocente() {
        return candidatoDocente;
    }

    public Tesis getTesis() {
        return tesis;
    }

    public void setTesis(Tesis tesis) {
        this.tesis = tesis;
    }

    public boolean isTieneTitulo() {
        return tieneTitulo;
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

    public boolean isDescPorResidencia() {
        descPorResidencia = (contratoEdit != null && contratoEdit.getCdoDsctoUsoResid() != null && contratoEdit.getCdoDsctoUsoResid().equals("S"));
        return descPorResidencia;
    }

    public void setDescPorResidencia(boolean descPorResidencia) {
        this.descPorResidencia = descPorResidencia;
    }

    public PorTutoriaMonografia getTutMonSelected() {
        return tutMonSelected;
    }

    public void setTutMonSelected(PorTutoriaMonografia tutMonSelected) {
        this.tutMonSelected = tutMonSelected;
    }

    public Honorarios getViaticoSelected() {
        return viaticoSelected;
    }

    public void setViaticoSelected(Honorarios viaticoSelected) {
        this.viaticoSelected = viaticoSelected;
    }

    public List<PorTutoriaMonografia> getTutorias() {
        return tutorias;
    }

    public boolean isDisableAddViatico() {
        return disableAddViatico;
    }

    public BigDecimal getTotalViaticos() {
        return totalViaticos;
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

    public List<ContratoDocente> getContratosDocentes() {
        return contratosDocentes;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public List<TipoEstado> getEstados() {
        return estados;
    }

    public ContratoDocente getContratoSelected() {
        return contratoSelected;
    }

    public void setContratoSelected(ContratoDocente contratoSelected) {
        this.contratoSelected = contratoSelected;
    }

    public ContratoDocente getContratoEdit() {
        return contratoEdit;
    }

    public void setContratoEdit(ContratoDocente contratoEdit) {
        this.contratoEdit = contratoEdit;
    }

    public List<RolDocente> getRolesDoc() {
        return rolesDoc;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public String getCodAsig() {
        return codAsig;
    }

    public void setCodAsig(String codAsig) {
        this.codAsig = codAsig;
    }

    public List<CandidatoDocente> getCandidatos() {
        return candidatos;
    }

    public List<TiposFormaPago> getFormaPagos() {
        return formaPagos;
    }

    public BigDecimal getValorFuncion() {
        return valorFuncion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public DatosViajero getDatosViaje() {
        return datosViaje;
    }

    public void setDatosViaje(DatosViajero datosViaje) {
        this.datosViaje = datosViaje;
    }

    public DatosViajero getResidencia() {
        return residencia;
    }

    public void setResidencia(DatosViajero residencia) {
        this.residencia = residencia;
    }

    public List<Rubros> getRubrosResid() {
        return rubrosResid;
    }

    public Long getRubroResid() {
        return rubroResid;
    }

    public void setRubroResid(Long rubroResid) {
        this.rubroResid = rubroResid;
    }

    public List<Rubros> getRubrosViatic() {
        return rubrosViatic;
    }

    public List<Honorarios> getViaticos() {
        return viaticos;
    }

    public boolean isTieneViatico() {
        return tieneViatico;
    }

    public void setTieneViatico(boolean tieneViatico) {
        this.tieneViatico = tieneViatico;
    }

    public Honorarios getViaticoEdit() {
        return viaticoEdit;
    }

    public void setViaticoEdit(Honorarios viaticoEdit) {
        this.viaticoEdit = viaticoEdit;
    }

//</editor-fold>
    /**
     * Creates a new instance of SolicContratoJSFManagedBean
     */
    public SolicContratoJSFManagedBean() {
        super.setPosicionYMant("top");
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
//        System.out.println(usr);
        // seguridad en perfiles y areas
        perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarAreas");
    }

    @Override
    public void init() {
        programas.clear();
        areas.clear();
        area = null;
        progra = null;
        resetSolicContrato();
    }

    @PostConstruct
    private void recuperarListados() {
        formaPagos = tiposFormaPagoFacade.findAll();
        candidatos = candidatoDocenteFacade.getCandidatosDocentes();
        rubrosResid = rubrosFacade.getRubrosByCateg("R");
        rubrosViatic = rubrosFacade.getRubrosByCateg("V");
        academicos = anioAcademicoFacade.findAll();
        if (!academicos.isEmpty()) {
            anio = academicos.get(0).getAnio();
        }
        recuperarAreas();
        recupContratosAsignaturas();
    }

    //<editor-fold defaultstate="collapsed" desc="validaciones ">
    public void validateFechas(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        //Leave the null handling of startDate to required="true"
        Object startDateValue = component.getAttributes().get("fechaInicio");
        if (startDateValue == null) {
            return;
        }

        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            FacesMessage msg = new FacesMessage(" La fecha Final debe ser mayor a la fecha Inicial", " La fecha Final debe ser mayor a la fecha Inicial");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

    private class MyAsignaturaComparable implements Comparator<Asignatura> {

        @Override
        public int compare(Asignatura o1, Asignatura o2) {
            return (o1.getIdAsignatura().concat("-").concat(o1.getAssNombre()).concat("-").concat(o1.getGrpNombre()).compareTo(
                    o2.getIdAsignatura().concat("-").concat(o2.getAssNombre()).concat("-").concat(o2.getGrpNombre())));
        }
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Anio, Areas y programas">
    public void recupContratosAsignaturas() {
        contratoSelected = null;
        contratosDocentes.clear();
        asignaturas.clear();
        if (anio != null && area != null && progra != null) {
            rolesDoc = rolDocenteFacade.getRolDocenteByEstadoAndNivel("A", progra.getCodNivelAcad());
            contratosDocentes = contratoDocenteFacade.findByAreaAndProg(area, progra.getProgramaPK().getPrgCodigo(), anio);
            asignaturas = asignaturaFacade.getAsignaturasByAreaProg(area, progra.getProgramaPK().getPrgCodigo(), anio);
            for (Programa pr : programas) {
                if (pr.getProgramaPK().equals(progra.getProgramaPK())) {
                    progra = pr;
                    break;
                }
            }
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
//            if (filtrarAreas.equals("T")) {
            programas = programaFacade.getProgramasByArea(area, anio);
//            } else if (filtrarAreas.equals("A")) {
//                programas = programaFacade.getProgramasByProgArea(usr.getUsuCodigo(), area, anio);
//            }
            if (programas.size() == 1) {
                progra = programas.get(0);
            }
        }
    }

    public void handleAreaAnioProgChange(String select) {
        if (!select.equals("contratos")) {
            progra = null;
            recuperarProgramas();
        }
        recupContratosAsignaturas();
        RequestContext.getCurrentInstance().execute("widgetContrato.unselectAllRows();");

    }

    public void handleAsignaturaChange() {
        if (codAsig != null) {
            maxHoras = 0;
            horasPracticas = null;
            Long act = new Long(codAsig.substring(0, codAsig.indexOf("-")));
            Long grp = new Long(codAsig.substring(codAsig.indexOf("-") + 1, codAsig.lastIndexOf("-")));
            Long asg = new Long(codAsig.substring(codAsig.lastIndexOf("-") + 1));
            contratoEdit.setCdoCantUnidad(new Short("0"));
            for (Asignatura a : asignaturas) {
                if (a.getAsignaturaPK().equals(new AsignaturaPK(act, grp, asg))) {
                    maxHoras = a.getnClasesPlanificadas();
                    horasPracticas = a.getnClasePractica();
                    contratoEdit.setCdoCantUnidad(a.getnClasesPlanificadas());
                    break;
                }
            }
            this.genPeriodo("MES");
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Valores">
    private void getValResidencia() {
//        if (residencia.getDviFechaHasta() != null && residencia.getDviFechaDesde() != null) { // CALCULO DE RESIDENCIA
//            diasResid = (int) ((residencia.getDviFechaHasta().getTime() - residencia.getDviFechaDesde().getTime()) / DAY_IN_MILLIS);
        Rubros r = rubrosResid.get(rubrosResid.indexOf(new Rubros(rubroResid)));
        residencia.setDviValorDiario(r.getRubValorEspe());
        residencia.setRubros(r);
//        }
    }

    public void genPeriodo(String tipo) {
        if (tipo.equals("MES")) {
            recuperarValorRubro();
//            System.out.println("genPeriodo "+valorFuncion);
            if (valorFuncion != null) {
                if (contratoEdit.getContratado().getAsistenteAcad() != null && contratoEdit.getContratado().getAsistenteAcad().equals("1")) {
                    contratoEdit.setCdoMonto(valorFuncion.multiply(horasPracticas == null ? prcPagoxAsistAcad : prcPagoxHorasPracticas).multiply(new BigDecimal(contratoEdit.getCdoCantUnidad().intValue())));
                } else {
                    contratoEdit.setCdoMonto(valorFuncion.multiply(horasPracticas == null ? new BigDecimal("1") : prcPagoxHorasPracticas).multiply(new BigDecimal(contratoEdit.getCdoCantUnidad().intValue())));
                }
            } else {
                contratoEdit.setCdoMonto(new BigDecimal(0));
            }
            if (contratoEdit.getRolDocente().getRdoCodigo().equals("C") && contratoEdit.getCdoFecini() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(contratoEdit.getCdoFecini());
                cal.add(Calendar.MONTH, contratoEdit.getCdoCantUnidad().intValue());
                contratoEdit.setCdoFecfin(cal.getTime());
            }

        } else {
            if (rubroResid != null) {
                getValResidencia();
            } else {
                residencia.setDviCantidad(null);
                residencia.setDviValorDiario(null);
                residencia.setRubros(null);
                residencia.setDviFechaDesde(null);
                residencia.setDviFechaHasta(null);
            }
            RequestContext.getCurrentInstance().reset("formMant:pnlFechaResid");
        }
    }

    public void genPasage() {
        RequestContext.getCurrentInstance().reset("formMant:pnlRuta");
        RequestContext.getCurrentInstance().reset("formMant:pnlRutaFecha");
        datosViaje.setDviCantidad(new Short("1"));
        if (datosViaje.getDviTipoPasaje() == null) {
            datosViaje.setDviFechaDesde(null);
            datosViaje.setDviFechaHasta(null);
            datosViaje.setDviRutaPasaje(null);
            datosViaje.setRubros(null);
        }
    }

    public void sumViaticos() {
        totalViaticos = BigDecimal.ZERO;
        for (Honorarios viatico : viaticos) {
            if (viatico.getHonCantidad() != null && viatico.getHonValor() != null) {
                totalViaticos = totalViaticos.add(viatico.getHonCantidad().multiply(viatico.getHonValor()));
            }
        }
    }

    private void recuperarValorRubro() {
        valorFuncion = null;
        Rubros r = rolDocente.getRubros();
        if (progra.getNivelAcademico() != null) {
            valorFuncion = (progra.getCodNivelAcad() == 1 ? r.getRubValorEspe() : progra.getCodNivelAcad() == 2 ? r.getRubValorMaes() : r.getRubValorDocto());
        }
        unidadMedida = r.getRubUnimedida();
    }

//</editor-fold>
    public void handleCandidatoChange(String t) {
        if (t.equals("docente")) {
            codAsig = null;
            tieneTitulo = false;
            contratoEdit.setPrfCodigo(candidatoDocente.getPrfCodigo());
            for (CandidatoDocente cand : candidatos) {
                if (cand.getPrfCodigo().equals(contratoEdit.getPrfCodigo())) {
                    if (cand.getTitulo() == null) {
                        JsfUtil.addErrorMessage("formMant:selectOneDocentes", " El Docente " + cand.getNombresApellidos().toUpperCase() + " no registra Título");
                        break;
                    }
                    contratoEdit.setContratado(cand);
                    tieneTitulo = true;
                    break;
                }
            }
        } else if (t.equals("funcion")) {
            codAsig = null;
            contratoEdit.setCdoFecfin(null);
            contratoEdit.setCdoFecini(null);
            contratoEdit.setCdoMonto(BigDecimal.ZERO);
            contratoEdit.setCdoCantUnidad(new Short("0"));
            recuperarValorRubro();
            contratoEdit.setRolDocente(rolDocente);
            tutorias = porTutoriaMonografiaFacade.getTutoriasContrato(contratoEdit);
            RequestContext.getCurrentInstance().reset("formMant:pnlSolicAsig");
            RequestContext.getCurrentInstance().reset("formMant:pnlHonorarios");
        }
        listaTesis.clear();
    }

    private void resetSolicContrato() {
        this.horasPracticas = null;
        this.maxHoras = 0;
        this.tieneTitulo = false;
        this.setDisabledSalvar(false);
        this.rolDocente = null;
        this.codAsig = null;

        candidatoDocente = new CandidatoDocente();
        contratoEdit = new ContratoDocente();
        if (progra != null) {
            contratoEdit.setPrgCodigo(progra.getProgramaPK().getPrgCodigo());
        }
        contratoEdit.setCdoAnioAcad(anio);
        contratoEdit.setAreCodigo(area);
        contratoEdit.setEstadoContrato("I");
        contratoEdit.setCdoFechaCrea(new Date());
        if (rolesDoc.size() == 1) {
            rolDocente = rolesDoc.get(0);
            recuperarValorRubro();
        }
        contratoEdit.setTiposFormaPago(new TiposFormaPago());
        contratoEdit.setCdoMonto(BigDecimal.ZERO);
        contratoEdit.setCdoCantUnidad(new Short("0"));
        contratoSelected = null;

        datosViaje = new DatosViajero(new DatosViajeroPK(null, "P"));
        datosViaje.setDviCantidad(new Short("1"));
        tieneViatico = false;
        viaticos.clear();
        totalViaticos = BigDecimal.ZERO;

        tutorias.clear();
        tutMonSelected = null;
        tesis = null;

        residencia = new DatosViajero(new DatosViajeroPK(null, "R"));
        residencia.setRubros(new Rubros());
        residencia.setDviValorDiario(BigDecimal.ZERO);
        residencia.setDviCantidad(null);
        rubroResid = null;
    }

    //<editor-fold defaultstate="collapsed" desc="Tesis Alumnos">
    private boolean okTutorias() {
        boolean ok = true;
        if (tutorias.isEmpty()) {
            ok = false;
        } else {
            for (PorTutoriaMonografia t : tutorias) {
                if (t.getAluCodigo() == null) {
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    public void nuevoAlumnoButton_processAction(ActionEvent ae) {
        this.setPaginaTema("/pages/opcion/regMongrafiaTesis");
        tesis = null;
        tutMonSelected = null;
        listaTesis = tesisFacade.findByDocente(candidatoDocente.getPrfCodigo(), rolDocente.getCodNivelAcad());
        RequestContext.getCurrentInstance().execute("mantWidgetTema.show();");
    }

    public void eliminarAlumnoButton_processAction() {
        tutorias.remove(tutMonSelected);
        tutMonSelected = null;
        contratoEdit.setCdoCantUnidad((short) tutorias.size());
        contratoEdit.setCdoMonto(valorFuncion.multiply(new BigDecimal(contratoEdit.getCdoCantUnidad())));
    }

    public void editarAlumnoButton_processAction() {
        this.setPaginaTema("/pages/opcion/regMongrafiaTesis");
        tesis = tesisFacade.find(tutMonSelected.getAluCodigo());
        RequestContext.getCurrentInstance().execute("mantWidgetTema.show();");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Conceptos">
    public void changeConcepto() {
        BigDecimal x = BigDecimal.ZERO;
        Rubros r = rubrosViatic.get(rubrosViatic.indexOf(new Rubros(viaticoEdit.getHonorariosPK().getRubCodigo())));
//        if (nivelProg != null) {
//            x = (nivelProg.equals("ESPECIALIZACIÓN") ? r.getRubValorEspe() : nivelProg.equals("MAESTRIA") ? r.getRubValorMaes() : r.getRubValorDocto());
//        }
        x = r.getRubValorEspe();
        viaticoEdit.setHonValor(x);
        viaticoEdit.getHonorariosPK().setRubCodigo(r.getRubCodigo());
        viaticoEdit.setDescripRubro(r.getRubDescripcion());
        viaticoEdit.setRubros(r);
    }

    public void nuevoConceptoButton_processAction(ActionEvent ae) {
        this.setPaginaTema("/pages/opcion/regConcepto");
        viaticoSelected = null;
        viaticoEdit = new Honorarios();
        viaticoEdit.setHonorariosPK(new HonorariosPK());
        viaticoEdit.setRubros(new Rubros());
        RequestContext.getCurrentInstance().execute("mantWidgetTema.show();");
    }

    public void eliminarConceptoButton_processAction() {
//        Honorarios v = honorariosFacade.find(viaticoSelected.getHonorariosPK());
        viaticos.remove(viaticoSelected);
        honorariosFacade.remove(viaticoSelected);
        sumViaticos();
        viaticoSelected = null;
    }

    public void editarConceptoButton_processAction() {
        this.setPaginaTema("/pages/opcion/regConcepto");
        try {
            viaticoEdit = viaticoSelected.clone();
            RequestContext.getCurrentInstance().execute("mantWidgetTema.show();");
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SilaboJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void candelEditViaticosConcepto() {
        if (tieneViatico == false) {
            viaticos.clear();
            totalViaticos = BigDecimal.ZERO;
            this.setDisabledSalvar(false);
            disableAddViatico = true;
        } else {
            disableAddViatico = false;
        }
        viaticoSelected = null;
    }

    //</editor-fold>     
    public void guardarTemaButton_processAction(ActionEvent ae) {
        if (this.getPaginaTema().equals("/pages/opcion/regConcepto")) {
            // si es un registro nuevo no existe un registro seleccionado
            if (viaticoSelected != null) {
                viaticos.remove(viaticoSelected);
            }
            viaticos.add(viaticoEdit);
            sumViaticos();
            RequestContext.getCurrentInstance().update("formMant:viaticos");
        }
        if (this.getPaginaTema().equals("/pages/opcion/regMongrafiaTesis")) {
            if (tutMonSelected != null) {
                tutorias.remove(tutMonSelected);
            }
            PorTutoriaMonografia newTutor = new PorTutoriaMonografia();
            newTutor.setAluCodigo(tesis.getCodNum());
            newTutor.setTtmFechaIniprog(tesis.getFechaIni());
            newTutor.setTtmFechaFinprog(tesis.getFechaFin());
            newTutor.setTtmTitulo(tesis.getTema());
            newTutor.setContratoDocente(new ContratoDocente());
            newTutor.setNombres(tesis.getNombres());
            tutorias.add(newTutor);
            contratoEdit.setCdoCantUnidad((short) tutorias.size());
            contratoEdit.setCdoMonto(valorFuncion.multiply(new BigDecimal(contratoEdit.getCdoCantUnidad())));
        } else {

        }
        RequestContext.getCurrentInstance().execute("mantWidgetTema.hide();");
    }

    @Override
    public void nuevoButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/opcion/regSolicContrato");
        super.nuevoButton_processAction(ae);
        resetSolicContrato();
        RequestContext.getCurrentInstance().execute("widgetCandidatos");
    }

    public void preSendMail() {
        if (chekPreUpdate() == false) {
            return;
        }
        RequestContext.getCurrentInstance().execute("confirmeEnviar.show()");
    }

    private boolean chekPreUpdate() {
        if (!(contratoEdit.getRolDocente().getRdoCodigo().equals("C") || contratoEdit.getRolDocente().getRdoCodigo().equals("D"))) {
            if (!okTutorias()) {
                JsfUtil.addErrorMessage("formMant:tesMon", "No se ha registrado Tesis/Monografía alguna !");
                return false;
            }
        }
        if (tieneViatico && (viaticos.isEmpty() || totalViaticos == BigDecimal.ZERO)) {
            JsfUtil.addErrorMessage("formMant:selectBooleanCheckboxViaticos", "Se ha definido Viáticos, pero no se han añadido conceptos o sus valores");
            return false;
        }
        return true;
    }

    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        StringBuilder mensaje;
        if (chekPreUpdate() == false) {
            return;
        }
        if (codAsig != null) {
            contratoEdit.setAspCodigo(new Long(codAsig.substring(0, codAsig.indexOf("-"))));
            contratoEdit.setPrlCodigo(new Long(codAsig.substring(codAsig.indexOf("-") + 1, codAsig.lastIndexOf("-"))));
            contratoEdit.setAsgCodigo(new Long(codAsig.substring(codAsig.lastIndexOf("-") + 1)));
        }
        contratoEdit.setCdoDsctoUsoResid(descPorResidencia ? "S" : "N");
        if (contratoEdit.getCdoCodigo() == null) {
            contratoEdit.setCdoUsuCrea(usr.getUsuCodigo());
            contratoDocenteFacade.create(contratoEdit, viaticos, tutorias, datosViaje, residencia);
        } else {
            contratoEdit.setCdoFechaModif(new Date());
            contratoEdit.setCdoUsuModif(usr.getUsuCodigo());
            contratoDocenteFacade.edit(contratoEdit, viaticos, tutorias, datosViaje, residencia);
        }
        if (ae.getComponent().getClientId().equals("commandButtonEnviar")) {
            EstadoSolicContrato esc = new EstadoSolicContrato(new EstadoSolicContratoPK());
            esc.getEstadoSolicContratoPK().setStaCodigo("S");
            esc.setContratoDocente(contratoEdit);
            esc.setEscCodigoUsr(usr.getUsuCodigo());
            esc.setEscFecha(new Date());
            estadoSolicContratoFacade.create(esc);
            try {
                String eMailSigPerfil = tipoEstadoFacade.getEmailSigProcesoByEstado("S");
                if (eMailSigPerfil != null) {
                    mensaje = new StringBuilder("Solicitud de Contratación ");
//                    System.out.println("contratoEdit.getRolDocente() "+contratoEdit.getRolDocente().getRdoDescripcion());
                    if (contratoEdit.getRolDocente().getRdoCodigo().equals("D")) {
                        mensaje.append("para: ");
                    } else {
                        mensaje.append("para '").append(contratoEdit.getRolDocente().getRdoDescripcion()).append("' a: ");
                    }
                    mensaje.append("</br></br>");
                    mensaje.append(contratoEdit.getContratado().getNombresApellidos()).append("</br></br>");
                    mensaje.append(contratoDocenteFacade.getMensaje(contratoEdit));
//                    contratoDocenteFacade.enviaCorreo("Soporte Servicios", "victor.barba@uasb.edu.ec", "", "juancarlos.lozada@uasb.edu.ec;", "Solicitud de Contratación Docente", mensaje.toString(), "HTML");
                    contratoDocenteFacade.enviaCorreo("Soporte Servicios", eMailSigPerfil, "", "", "Solicitud de Contratación de Docente", mensaje.toString(), "HTML");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SilaboJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        resetSolicContrato();
        contratosDocentes = contratoDocenteFacade.findByAreaAndProg(area, progra.getProgramaPK().getPrgCodigo(), anio);
        JsfUtil.addSuccessMessage(null, "Dato(s) actualizado(s)");
        RequestContext.getCurrentInstance().update("formSolic:dataContrato");
        RequestContext.getCurrentInstance().execute("mantWidget.hide();");
    }

    @Override
    public void eliminarButton_processAction(ActionEvent ae) {
        contratoDocenteFacade.remove(contratoSelected);
        contratosDocentes = contratoDocenteFacade.findByAreaAndProg(area, progra.getProgramaPK().getPrgCodigo(), anio);
        super.eliminarButton_processAction(ae);
    }

    @Override
    public void editarButton_processAction(ActionEvent ae) {
        this.setPaginaMant("/pages/opcion/regSolicContrato");
        super.editarButton_processAction(ae);
        try {
            tieneTitulo = false;
            contratoEdit = contratoSelected.clone();
            descPorResidencia = contratoEdit.getCdoDsctoUsoResid().equals("S");
            for (CandidatoDocente cand : candidatos) {
                if (cand.getPrfCodigo().equals(contratoEdit.getPrfCodigo())) {
                    tieneTitulo = true;
                    break;
                }
            }
            rolDocente = contratoEdit.getRolDocente();
            candidatoDocente = candidatoDocenteFacade.getCandidato(contratoEdit.getPrfCodigo());
            if (contratoEdit.getAsgCodigo() != null) {
                codAsig = contratoEdit.getAspCodigo() + "-" + contratoEdit.getPrlCodigo() + "-" + contratoEdit.getAsgCodigo();
                for (Asignatura a : asignaturas) {
                    if (a.getAsignaturaPK().equals(new AsignaturaPK(contratoEdit.getAspCodigo(), contratoEdit.getPrlCodigo(), contratoEdit.getAsgCodigo()))) {
                        maxHoras = a.getnClasesPlanificadas();
                        horasPracticas = a.getnClasePractica();
                        break;
                    }
                }
            }
            valorFuncion = contratoEdit.getCdoMonto().divide(new BigDecimal(contratoEdit.getCdoCantUnidad()));
            tutorias = porTutoriaMonografiaFacade.getTutoriasContrato(contratoEdit);
            int j = rolesDoc.indexOf(contratoEdit.getRolDocente());
            if (j > 0) {
                unidadMedida = rolesDoc.get(j).getRubros().getRubUnimedida();
            }
            datosViaje = datosViajeroFacade.find(new DatosViajeroPK(contratoEdit.getCdoCodigo(), "P"));
            if (datosViaje == null) {
                datosViaje = new DatosViajero(new DatosViajeroPK(null, "P"));
                datosViaje.setContratoDocente(contratoEdit);
                datosViaje.getDatosViajeroPK().setCdoCodigo(contratoEdit.getCdoCodigo());
            }

            viaticos = honorariosFacade.findViaticosByContrato(contratoEdit.getCdoCodigo(), "V");
            tieneViatico = (!viaticos.isEmpty());
            sumViaticos();
            residencia = datosViajeroFacade.find(new DatosViajeroPK(contratoEdit.getCdoCodigo(), "R"));
            if (residencia != null) {
                rubroResid = residencia.getRubros().getRubCodigo();
                getValResidencia();
            } else {
                residencia = new DatosViajero(new DatosViajeroPK(null, "R"));
                residencia.setRubros(new Rubros());
                residencia.setContratoDocente(contratoEdit);
                residencia.getDatosViajeroPK().setCdoCodigo(contratoEdit.getCdoCodigo());
            }
            RequestContext.getCurrentInstance().update("formMant:panelMant");

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SilaboJSFManagedBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void cancelarButton_processAction(ActionEvent ae) {
        if (rolDocente != null && rolDocente.getRdoCodigo().equals("S")) {
            RequestContext.getCurrentInstance().execute("datatableTutoriasWidget.unselectAllRows();");
        }
        RequestContext.getCurrentInstance().execute("datatableViaticosWidget.unselectAllRows();");
        resetSolicContrato();
        contratosDocentes = contratoDocenteFacade.findByAreaAndProg(area, progra.getProgramaPK().getPrgCodigo(), anio);
//        this.setPaginaMant("/WEB-INF/templates/includes/sinContenido");
    }

    @Override
    public void imprimirButton_processAction(ActionEvent ae) {
        String urlReporte = "nroSolicitud=" + contratoSelected.getCdoCodigo().toString()
                + "&reporte=" + (contratoSelected.getRolDocente().getRdoCodigo().equals("D") ? "solicitud" : "solicitudTesis")
                + "&contexto=PortalGesDocente";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);

    }

}
