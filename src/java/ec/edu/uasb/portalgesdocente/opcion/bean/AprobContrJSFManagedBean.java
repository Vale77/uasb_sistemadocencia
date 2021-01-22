/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.opcion.bean;

import ec.edu.uasb.bean.BaseJSFManagedBean;
import ec.edu.uasb.entities.Asignatura;
import ec.edu.uasb.portalgesdocente.entities.CandidatoDocente;
import ec.edu.uasb.portalgesdocente.entities.ContratoDocente;
import ec.edu.uasb.portalgesdocente.entities.DatosViajero;
import ec.edu.uasb.portalgesdocente.entities.DatosViajeroPK;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContrato;
import ec.edu.uasb.portalgesdocente.entities.EstadoSolicContratoPK;
import ec.edu.uasb.portalgesdocente.entities.Honorarios;
import ec.edu.uasb.portalgesdocente.entities.HonorariosPK;
import ec.edu.uasb.portalgesdocente.entities.PorTutoriaMonografia;
import ec.edu.uasb.portalgesdocente.entities.Rubros;
import ec.edu.uasb.portalgesdocente.session.CandidatoDocenteFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.ContratoDocenteFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.DatosViajeroFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.EstadoSolicContratoFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.HonorariosFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.PorTutoriaMonografiaFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.RubrosFacadeLocal;
import ec.edu.uasb.portalgesdocente.session.TipoEstadoFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.seg.session.UsuarioFacadeLocal;
import ec.edu.uasb.session.AsignaturaFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
@ManagedBean(name = "aprobContrBean")
@ViewScoped
public class AprobContrJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120444L;
    @EJB
    private PorTutoriaMonografiaFacadeLocal porTutoriaMonografiaFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private RubrosFacadeLocal rubrosFacade;
    @EJB
    private AsignaturaFacadeLocal asignaturaFacade;
    @EJB
    private EstadoSolicContratoFacadeLocal estadoSolicContratoFacade;
    @EJB
    private ContratoDocenteFacadeLocal contratoDocenteFacade;
    @EJB
    private CandidatoDocenteFacadeLocal candidatoDocenteFacade;
    @EJB
    private HonorariosFacadeLocal honorariosFacade;
    @EJB
    private DatosViajeroFacadeLocal datosViajeroFacade;
    @EJB
    private TipoEstadoFacadeLocal tipoEstadoFacade;
    private final Usuario usr;
    private final List<Perfil> perfiles;
    private ContratoDocente contratoSelected;
    private ContratoDocente contratoEdit;
    private List<ContratoDocente> contratosDocentes = new ArrayList<ContratoDocente>();
    private List<CandidatoDocente> candidatoDocentes = new ArrayList<CandidatoDocente>();
    private List<PorTutoriaMonografia> tutorias = new ArrayList<PorTutoriaMonografia>();
    private List<EstadoSolicContrato> historiaEstados = new ArrayList<EstadoSolicContrato>();
    private List<Honorarios> viaticos = new ArrayList<Honorarios>();
    private List<Rubros> rubrosViatic = new ArrayList<Rubros>();
    private Date desde;
    private Date hasta;
    private boolean disabledBuscar = true;
    private String estado = "T";
    private String estadoPerfil;
    private String estadoAprob;
    private BigDecimal valorFuncion;
    private BigDecimal totalViaticos;
    private String unidadMedida;
    private DatosViajero datosViaje;
    private DatosViajero residencia;
    private Long rubroResid;
    final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private boolean tieneViatico;
    private boolean descPorResidencia;
    private EstadoSolicContrato esc;
    private Asignatura asignat;
    private Honorarios viaticoEdit;
    private Honorarios viaticoSelected;
    private String perfil;
    private String tipoListado = "O";

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    public String getTipoListado() {
        return tipoListado;
    }

    public void setTipoListado(String tipoListado) {
        this.tipoListado = tipoListado;
    }

    public List<EstadoSolicContrato> getHistoriaEstados() {
        return historiaEstados;
    }

    public List<PorTutoriaMonografia> getTutorias() {
        return tutorias;
    }

    public Asignatura getAsignat() {
        return asignat;
    }

    public boolean isDisabledBuscar() {
        return disabledBuscar;
    }

    public List<ContratoDocente> getContratosDocentes() {
        return contratosDocentes;
    }

    public List<CandidatoDocente> getCandidatoDocentes() {
        return candidatoDocentes;
    }

    public ContratoDocente getContratoSelected() {
        return contratoSelected;
    }

    public void setContratoSelected(ContratoDocente contratoSelected) {
        this.contratoSelected = contratoSelected;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoPerfil() {
        return estadoPerfil;
    }

    public String getEstadoAprob() {
        return estadoAprob;
    }

    public ContratoDocente getContratoEdit() {
        return contratoEdit;
    }

    public void setContratoEdit(ContratoDocente contratoEdit) {
        this.contratoEdit = contratoEdit;
    }

    public Honorarios getViaticoEdit() {
        return viaticoEdit;
    }

    public void setViaticoEdit(Honorarios viaticoEdit) {
        this.viaticoEdit = viaticoEdit;
    }

    public Honorarios getViaticoSelected() {
        return viaticoSelected;
    }

    public void setViaticoSelected(Honorarios viaticoSelected) {
        this.viaticoSelected = viaticoSelected;
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

    public BigDecimal getTotalViaticos() {
        return totalViaticos;
    }

    public boolean isTieneViatico() {
        return tieneViatico;
    }

    public List<Honorarios> getViaticos() {
        return viaticos;
    }

    public Long getRubroResid() {
        return rubroResid;
    }

    public DatosViajero getResidencia() {
        return residencia;
    }

    public boolean isDescPorResidencia() {
        descPorResidencia = (contratoEdit != null && contratoEdit.getCdoDsctoUsoResid() != null && contratoEdit.getCdoDsctoUsoResid().equals("S"));
        return descPorResidencia;
    }

    public EstadoSolicContrato getEsc() {
        return esc;
    }

    public void setEsc(EstadoSolicContrato esc) {
        this.esc = esc;
    }

    public List<Rubros> getRubrosViatic() {
        return rubrosViatic;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="validaciones ">
    public void validateFechas(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        disabledBuscar = false;
        if (value == null) {
            disabledBuscar = true;
            return;
        }

        //Leave the null handling of startDate to required="true"
        Object startDateValue = component.getAttributes().get("fechaInicio");
        if (startDateValue == null) {
            disabledBuscar = true;
            return;
        }

        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            disabledBuscar = true;
            FacesMessage msg = new FacesMessage(" La fecha Hasta debe ser mayor a la fecha Desde", " La fecha Final debe ser mayor a la fecha Desde");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

//</editor-fold>   
    //<editor-fold defaultstate="collapsed" desc="Conceptos">
    public void changeConcepto() {
        BigDecimal x = BigDecimal.ZERO;
        Rubros r = rubrosViatic.get(rubrosViatic.indexOf(new Rubros(viaticoEdit.getHonorariosPK().getRubCodigo())));
        x = r.getRubValorEspe();
        viaticoEdit.setHonValor(x);
        viaticoEdit.getHonorariosPK().setRubCodigo(r.getRubCodigo());
        viaticoEdit.setDescripRubro(r.getRubDescripcion());
        viaticoEdit.setRubros(r);
    }

    public void editarConceptoButton_processAction() {
        try {
            viaticoEdit = viaticoSelected.clone();
            RequestContext.getCurrentInstance().execute("mantWidgetTema.show();");
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SolicContratoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTemaButton_processAction(ActionEvent ae) {
        // si es un registro nuevo no existe un registro seleccionado
        if (viaticoSelected != null) {
            viaticos.remove(viaticoSelected);
        }
        viaticos.add(viaticoEdit);
        sumViaticos();
        RequestContext.getCurrentInstance().update("formMant:viaticos");
        RequestContext.getCurrentInstance().execute("mantWidgetTema.hide();");
    }

    private void resetConcepto() {
        viaticoEdit = new Honorarios();
        viaticoEdit.setHonorariosPK(new HonorariosPK());
        viaticoEdit.setRubros(new Rubros());

    }

    public void candelEditViaticosConcepto() {
        resetConcepto();
    }

    //</editor-fold>   
    /**
     * Creates a new instance of AprobContrJSFManagedBean
     */
    public AprobContrJSFManagedBean() {
//        this.setPaginaTema("/pages/opcion/EditConceptoFin"); // debe ser incluida tambien en el navigationBean        
//        this.setPaginaMant("/pages/opcion/regAprobContrato");
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        // seguridad en perfiles y areas
        perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        for (Perfil pef : perfiles) {
            if (pef.getIdPerfil().equals("RECURSOS")) {
                estadoPerfil = "S"; // solicitadas por las areas
                estadoAprob = "H";
                perfil = "RECURSOS";
                break;
            } else if (pef.getIdPerfil().equals("FINANCIE")) {
                estadoPerfil = "H"; // aprobadas por recursos humanos
                estadoAprob = "F";
                perfil = "FINANCIE";
                break;
            } else if (pef.getIdPerfil().equals("SECRECTO")) {
                estadoPerfil = "F"; // aprobadas por financiero
                estadoAprob = "R";
                perfil = "SECRECTO";
                break;
            } else if (pef.getIdPerfil().equals("VICEREC")) {
                estadoPerfil = "R"; // aprobadas por rectorado
                estadoAprob = "P";
                perfil = "VICEREC";
                break;
            }
        }
        estado = estadoPerfil;
    }

    @PostConstruct
    private void recuperarListados() {
        resetConcepto();
        tipoListado = "O";
        rubrosViatic = rubrosFacade.getRubrosByCateg("V");
        candidatoDocentes = candidatoDocenteFacade.getCandidatosDocentes();
    }

    public void sumViaticos() {
        totalViaticos = BigDecimal.ZERO;
        for (Honorarios viatico : getViaticos()) {
            if (viatico.getHonCantidad() != null && viatico.getHonValor() != null) {
                totalViaticos = totalViaticos.add(viatico.getHonCantidad().multiply(viatico.getHonValor()));
            }
        }
    }

    public void resetFecha(String s) {
        hasta = null;
    }

    public void buscarButton_processAction() {
        if (estado == null) {
            return;
        }
        if (desde == null || hasta == null) {
            return;
        }
        contratosDocentes = contratoDocenteFacade.findByEstado(estado, estadoPerfil, estadoAprob, desde, hasta, null, tipoListado);

    }

//    @Override
//    public void imprimirButton_processAction(ActionEvent ae) {
//        this.setPaginaMant("/pages/opcion/verHistorico");
//        super.getNavigationJSFManagedBean().setBaseJSFManagedBean(this);
//        historiaEstados = estadoSolicContratoFacade.getHistoricoContrato(contratoSelected.getCdoCodigo());
//    }
    
    @Override
    public void editarButton_processAction(ActionEvent ae) {
        tieneViatico = false;
        super.editarButton_processAction(ae);
        try {
            contratoEdit = contratoSelected.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SolicContratoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tipoListado.equals("O")) {
            this.setPaginaMant("/pages/opcion/regAprobContrato");
            if (!contratoEdit.getRolDocente().getRdoCodigo().equals("D")) {
                tutorias = porTutoriaMonografiaFacade.getTutoriasContrato(contratoEdit);
            } else {
                asignat = asignaturaFacade.getAsignatura(contratoEdit);
            }
            valorFuncion = getContratoEdit().getCdoMonto().divide(new BigDecimal(getContratoEdit().getCdoCantUnidad()));
            unidadMedida = getContratoEdit().getRolDocente().getRubros().getRubUnimedida();
            datosViaje = datosViajeroFacade.find(new DatosViajeroPK(contratoEdit.getCdoCodigo(), "P"));
            viaticos = honorariosFacade.findViaticosByContrato(contratoEdit.getCdoCodigo(), "V");
            if ((!viaticos.isEmpty())) {
                tieneViatico = (!viaticos.isEmpty());
                sumViaticos();
            }
            residencia = datosViajeroFacade.find(new DatosViajeroPK(contratoEdit.getCdoCodigo(), "R"));
//        if (residencia != null) {
//            if (residencia.getDviFechaHasta() != null && residencia.getDviFechaDesde() != null) { // CALCULO DE RESIDENCIA
//                diasResid = (int) ((residencia.getDviFechaHasta().getTime() - residencia.getDviFechaDesde().getTime()) / DAY_IN_MILLIS);
//            }
//        }            
        } else {
            this.setPaginaMant("/pages/opcion/regAprobContratoBecaInvest");
        }
        if (contratoEdit.getEstadoContrato().equals(estadoPerfil)) {
            esc = new EstadoSolicContrato(new EstadoSolicContratoPK());
        } else {
            esc = estadoSolicContratoFacade.getUltimoEstadoContrato(contratoEdit.getCdoCodigo());
        }
        RequestContext.getCurrentInstance().update("formMant:panelMant");
    }

    @Override
    public void guardarButton_processAction(ActionEvent ae) {
        StringBuilder mensaje = new StringBuilder();
        String eMailSigPerfil = null;
        String msgAsunto = null;
        esc.setContratoDocente(contratoEdit);
        esc.setEscCodigoUsr(usr.getUsuCodigo());
        esc.setEscFecha(new Date());
        try {
            boolean regModificado = false;
            if (perfil.equals("FINANCIE")) {
                regModificado = honorariosFacade.hayModificaciones(contratoEdit.getCdoCodigo(), viaticos);
                estadoSolicContratoFacade.create(esc, viaticos);
            } else {
                estadoSolicContratoFacade.create(esc);
            }
            if (!esc.getEstadoSolicContratoPK().getStaCodigo().equals("X")) {
                eMailSigPerfil = tipoEstadoFacade.getEmailSigProcesoByEstado(esc.getEstadoSolicContratoPK().getStaCodigo());
                if (eMailSigPerfil != null) {
                    mensaje = new StringBuilder("Solicitud de Contratación ");
                    if (contratoEdit.getRolDocente().getRdoCodigo().equals("D") || contratoEdit.getRolDocente().getRdoCodigo().equals("I")) {
                        mensaje.append("para: ");
                    } else {
                        mensaje.append("para '").append(contratoEdit.getRolDocente().getRdoDescripcion()).append("' a: ");
                    }
                    mensaje.append("</br></br>");
                    mensaje.append(contratoEdit.getContratado().getNombresApellidos()).append("</br></br>");
                    mensaje.append(contratoDocenteFacade.getMensaje(contratoEdit));
                    if (regModificado) {
                        mensaje.append("</br> Se han hecho modificaciones a los viaticos por el Departamento Financiero.");
                    }
                    msgAsunto = contratoEdit.getRolDocente().getRdoCodigo().equals("I") ? "Solicitud de Contratación por beca de investigación":"Solicitud de Contratación de Docente";
                }
            } else {// POR ANULACION
                Usuario usrOrigen = usuarioFacade.getUsuarioByCodigo(contratoEdit.getCdoUsuCrea());
                if (usrOrigen != null) {
                    eMailSigPerfil = usrOrigen.getUsuEmail(); // email del usuario del area que genero la solicitud
                    if (esc.getEstadoSolicContratoPK().getStaCodigo().equals("X")) {// Por Anulación
                        mensaje = new StringBuilder("Solicitud de Contratación Anulada de Docente:</br></br>").append(eMailSigPerfil).append("</br></br>");
                        mensaje.append(contratoEdit.getContratado().getNombresApellidos()).append("</br></br>");
                        mensaje.append("Ha sido ANULADA. El motivo es:").append("</br></br>");
                        mensaje.append("<span style=\"color:blue;font-weight:bold;\">").append(esc.getEscObservacion().toUpperCase()).append("</span>").append("</br></br>");
//                        mensaje.append("Si tiene alguna pregunta comuniquese con Procuraduría.").append("</br></br>");
                        msgAsunto = "Solicitud de Contratación Anulada de Docente";
                    } else if (esc.getEstadoSolicContratoPK().getStaCodigo().equals("D")) {// Por Devolución
                        mensaje = new StringBuilder("Solicitud de Contratación Devuelta de Docente:</br></br>");
                        mensaje.append(contratoEdit.getContratado().getNombresApellidos()).append("</br></br>");
                        mensaje.append("Ha sido DEVUELTA. El motivo es:").append("</br></br>");
                        mensaje.append("<span style=\"color:blue;font-weight:bold;\">").append(esc.getEscObservacion().toUpperCase()).append("</span>").append("</br></br>");
                        msgAsunto = "Solicitud de Contratación Devuelta de Docente";
                    }
                }
            }
            if (eMailSigPerfil != null) {
                String emailCopia = "";
                if (esc.getEstadoSolicContratoPK().getStaCodigo().equals("R")) {
                    emailCopia = "juancarlos.lozada@uasb.edu.ec";
                }
////                contratoDocenteFacade.enviaCorreo("Soporte Servicios", "victor.barba@uasb.edu.ec", emailCopia, null, msgAsunto, mensaje.toString(), "HTML");
                contratoDocenteFacade.enviaCorreo("Soporte Servicios", eMailSigPerfil, emailCopia, null, msgAsunto, mensaje.toString(), "HTML");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicContratoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        buscarButton_processAction();
        RequestContext.getCurrentInstance().update("formSolic:dataContrato");
        super.guardarButton_processAction(ae);
    }

}
