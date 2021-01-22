/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.entities.Area;
import ec.edu.uasb.entities.Programa;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "CONTRATO_DOCENTE", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoDocente.findAll", query = "SELECT c FROM ContratoDocente c")
    ,
    @NamedQuery(name = "ContratoDocente.findByCdoCodigo", query = "SELECT c FROM ContratoDocente c WHERE c.cdoCodigo = :cdoCodigo")
    ,
    @NamedQuery(name = "ContratoDocente.findByPrfCodigo", query = "SELECT c FROM ContratoDocente c WHERE c.prfCodigo = :prfCodigo")
    ,
    @NamedQuery(name = "ContratoDocente.findByAreCodigo", query = "SELECT c FROM ContratoDocente c WHERE c.areCodigo = :areCodigo")
    ,
    @NamedQuery(name = "ContratoDocente.findByAsgCodigo", query = "SELECT c FROM ContratoDocente c WHERE c.asgCodigo = :asgCodigo")
    ,
    @NamedQuery(name = "ContratoDocente.findByPrlCodigo", query = "SELECT c FROM ContratoDocente c WHERE c.prlCodigo = :prlCodigo")
    ,
    @NamedQuery(name = "ContratoDocente.findByAspCodigo", query = "SELECT c FROM ContratoDocente c WHERE c.aspCodigo = :aspCodigo")
    ,
    @NamedQuery(name = "ContratoDocente.findByCdoNumero", query = "SELECT c FROM ContratoDocente c WHERE c.cdoNumero = :cdoNumero")})

/*    SQL PUEDE SERVIR ALGUNA VEZ
 @NamedNativeQuery(
 name = "findAllContratosByArea", query = "WITH CONTRATOS AS (SELECT CONTRATO_DOCENTE.*, TIPO_ESTADO.*, AREA.NOMBRE_AREA,P.NOMBRE_PROGRAMA, "
 + " PROFESOR.CED_PAS_PROFESOR DNIPRS, PROFESOR.NOMBRE_PROFESOR +' '+ PROFESOR.APELLIDO_PROFESOR NOMBRES_APELLIDOS,PROFESOR.DOMICILIO, "
 + " ESTADO_SOLIC_CONTRATO.ESC_FECHA,ESTADO_SOLIC_CONTRATO.ESC_OBSERVACION, "
 + " ROW_NUMBER() OVER(PARTITION BY CONTRATO_DOCENTE.CDO_CODIGO ORDER BY  ESTADO_SOLIC_CONTRATO.ESC_FECHA DESC) AS rk "
 + " FROM (GESTIONDOCENTE.ESTADO_SOLIC_CONTRATO ESTADO_SOLIC_CONTRATO "
 + " INNER JOIN GESTIONDOCENTE.TIPO_ESTADO TIPO_ESTADO ON (ESTADO_SOLIC_CONTRATO.STA_CODIGO = TIPO_ESTADO.STA_CODIGO)) "
 + " INNER JOIN  GESTIONDOCENTE.CONTRATO_DOCENTE CONTRATO_DOCENTE ON (ESTADO_SOLIC_CONTRATO.CDO_CODIGO = CONTRATO_DOCENTE.CDO_CODIGO)  "
 + " INNER JOIN AREA ON (AREA.ARE_CODIGO = CONTRATO_DOCENTE.ARE_CODIGO) "
 + " INNER JOIN PROFESOR PROFESOR  ON (CONTRATO_DOCENTE.PRF_CODIGO = PROFESOR.CODIGO_PROFESOR) "
 + " INNER JOIN V_GRUPO_ASIGNATURA_PROGRAMA P ON (P.ASS_CODIGO = CONTRATO_DOCENTE.ASG_CODIGO and "
 + " P.ACT_CODIGO =CONTRATO_DOCENTE.ASP_CODIGO and P.GRP_CODIGO =CONTRATO_DOCENTE.PRL_CODIGO AND P.PRG_CODIGO = CONTRATO_DOCENTE.PRG_CODIGO ) ) "
 + " SELECT C.*  FROM CONTRATOS C WHERE C.rk = 1 and C.ARE_CODIGO = ? and C.PRG_CODIGO = ? and C.CDO_ANIO_ACAD = ?",
 resultSetMapping = "ContratosResults"),

 */
//@NamedNativeQueries({
//    @NamedNativeQuery(
//            name = "findAllContratosByArea", query = "SELECT C.*,TIPO_ESTADO.*,AREA.NOMBRE_AREA, "
////            + "(SELECT P.NOMBRE_PROGRAMA FROM V_GRUPO_ASIGNATURA_PROGRAMA P WHERE P.ASS_CODIGO = C.ASG_CODIGO and "
////            + " P.ACT_CODIGO = C.ASP_CODIGO and P.GRP_CODIGO =C.PRL_CODIGO AND P.PRG_CODIGO = C.PRG_CODIGO) NOMBRE_PROGRAMA, "
//            + " PROFESOR.CED_PAS_PROFESOR DNIPRS, PROFESOR.APELLIDO_PROFESOR +' '+ PROFESOR.NOMBRE_PROFESOR NOMBRES_APELLIDOS,PROFESOR.DOMICILIO "
//            + " FROM GESTIONDOCENTE.CONTRATO_DOCENTE C "
//            + " INNER JOIN GESTIONDOCENTE.TIPO_ESTADO ON (TIPO_ESTADO.STA_CODIGO = C.STA_CODIGO) "
//            + " INNER JOIN AREA ON (AREA.ARE_CODIGO = C.ARE_CODIGO) "
//            + " INNER JOIN PROFESOR PROFESOR  ON (C.PRF_CODIGO = PROFESOR.CODIGO_PROFESOR) "
//            //            + " LEFT OUTER JOIN GESTIONDOCENTE.getEstados() E ON (E.CDO_CODIGO = C.CDO_CODIGO) "
//            + " WHERE C.ARE_CODIGO = ? and C.PRG_CODIGO = ? and C.CDO_ANIO_ACAD = ?  ORDER BY PROFESOR.APELLIDO_PROFESOR,PROFESOR.NOMBRE_PROFESOR",
//            resultSetMapping = "ContratosResults")
//})
@NamedNativeQueries({
    @NamedNativeQuery(name = "findAllContratosByArea", query = "DECLARE @temp_table table(CODIGO_PROFESOR bigint,CED_PAS_PROFESOR varchar(15),NOMBRE_PROFESOR varchar(100),APELLIDO_PROFESOR varchar(100),DOMICILIO varchar(512)) "
            + " DECLARE @temp_programas table (ASS_CODIGO bigint,ACT_CODIGO bigint,GRP_CODIGO bigint, PRG_CODIGO bigint,NOMBRE_PROGRAMA varchar(1024)) "
            + " insert into @temp_table select CODIGO_PROFESOR,CED_PAS_PROFESOR,NOMBRE_PROFESOR,APELLIDO_PROFESOR,DOMICILIO FROM PROFESOR "
            + " insert into @temp_programas select ASS_CODIGO,ACT_CODIGO,GRP_CODIGO, PRG_CODIGO,NOMBRE_PROGRAMA FROM V_GRUPO_ASIGNATURA_PROGRAMA "
            + " SELECT C.*,TIPO_ESTADO.*,AREA.NOMBRE_AREA, E.*,"
            + " (SELECT P.NOMBRE_PROGRAMA FROM @temp_programas P WHERE P.ASS_CODIGO = C.ASG_CODIGO and "
            + " P.ACT_CODIGO = C.ASP_CODIGO and P.GRP_CODIGO =C.PRL_CODIGO AND P.PRG_CODIGO = C.PRG_CODIGO) NOMBRE_PROGRAMA, "
            + " PROFESOR.CED_PAS_PROFESOR DNIPRS, PROFESOR.APELLIDO_PROFESOR +' '+ PROFESOR.NOMBRE_PROFESOR NOMBRES_APELLIDOS,PROFESOR.DOMICILIO "
            + " FROM GESTIONDOCENTE.CONTRATO_DOCENTE C "
            + " INNER JOIN GESTIONDOCENTE.TIPO_ESTADO ON (TIPO_ESTADO.STA_CODIGO = C.STA_CODIGO) "
            + " INNER JOIN AREA ON (AREA.ARE_CODIGO = C.ARE_CODIGO) "
            + " INNER JOIN @temp_table PROFESOR  ON (C.PRF_CODIGO = PROFESOR.CODIGO_PROFESOR) "
            + " INNER JOIN GESTIONDOCENTE.getEstados() E ON (E.CDO_CODIGO = C.CDO_CODIGO) "
            + " WHERE C.ARE_CODIGO = ? and C.PRG_CODIGO = ? and C.CDO_ANIO_ACAD = ? "
            + " ORDER BY PROFESOR.APELLIDO_PROFESOR,PROFESOR.NOMBRE_PROFESOR",
            resultSetMapping = "ContratosResultsEstado")
})

@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ContratosBecasInvestResultsEstado",
            entities = {
                @EntityResult(entityClass = ContratoDocente.class)
                ,
                @EntityResult(entityClass = TipoEstado.class)
                ,
                @EntityResult(entityClass = Area.class)
                ,
                @EntityResult(entityClass = Programa.class)
                ,
                @EntityResult(entityClass = CandidatoDocente.class)
                ,
                @EntityResult(entityClass = EstadoSolicContrato.class)
            }, columns = {
                @ColumnResult(name = "TITULO")
                ,@ColumnResult(name = "CAI_DESCRIPCION")
                ,@ColumnResult(name = "FECHA_APROB_INFO")
            }
    )
    ,
    @SqlResultSetMapping(name = "ContratosResultsEstado",
            entities = {
                @EntityResult(entityClass = ContratoDocente.class)
                ,
                @EntityResult(entityClass = TipoEstado.class)
                ,
                @EntityResult(entityClass = Area.class)
                ,
                @EntityResult(entityClass = Programa.class)
                ,
                @EntityResult(entityClass = CandidatoDocente.class)
                ,
                @EntityResult(entityClass = EstadoSolicContrato.class)
            }
    )
    ,
    @SqlResultSetMapping(name = "ContratosResults",
            entities = {
                @EntityResult(entityClass = ContratoDocente.class)
                ,
                @EntityResult(entityClass = TipoEstado.class)
                ,
                @EntityResult(entityClass = Area.class)
                ,
                @EntityResult(entityClass = Programa.class)
                ,
                @EntityResult(entityClass = CandidatoDocente.class)
                ,
                @EntityResult(entityClass = EstadoSolicContrato.class)
                ,
                @EntityResult(entityClass = DatosViajero.class)
            }
    )}
)

public class ContratoDocente implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CDO_CODIGO")
    private Long cdoCodigo;
    @Basic(optional = false)
    @Column(name = "CDO_ANIO_ACAD")
    private Long cdoAnioAcad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRF_CODIGO")
    private Long prfCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRG_CODIGO")
    private Long prgCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARE_CODIGO")
    private Long areCodigo;
    @Column(name = "ASG_CODIGO")
    private Long asgCodigo;
    @Column(name = "PRL_CODIGO")
    private Long prlCodigo;
    @Column(name = "ASP_CODIGO")
    private Long aspCodigo;
    @Column(name = "STA_CODIGO")
    private String staCodigo;
    @Size(max = 20)
    @Column(name = "CDO_NUMERO")
    private String cdoNumero;
    @Column(name = "CDO_FECHA_GEN_CONTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFechaGenContr;
    @Column(name = "CDO_FECINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFecini;
    @Column(name = "CDO_FECFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFecfin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_MONTO")
    private BigDecimal cdoMonto;
    @Column(name = "CDO_CANT_UNIDAD")
    private Short cdoCantUnidad;
//    @Size(max = 1)
//    @Column(name = "CDO_SOLO_VIATICO")
//    private String cdoSoloViatico;
    @Size(max = 1)
    @Column(name = "CDO_DSCTO_USO_RESID")
    private String cdoDsctoUsoResid;
    @Size(max = 1024)
    @Column(name = "CDO_OBSERVACIONES")
    private String cdoObservaciones;
    @Size(max = 50)
    @Column(name = "CDO_REVISADO_POR")
    private String cdoRevisadoPor;
    @Lob
    @Column(name = "CDO_TEXTO")
    private String cdoTexto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_USU_CREA")
    private Long cdoUsuCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFechaCrea;
    @Column(name = "CDO_USU_MODIF")
    private Long cdoUsuModif;
    @Column(name = "CDO_FECHA_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFechaModif;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDocente")
    private Collection<Honorarios> honorariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDocente")
    private Collection<SolicitudPago> solicitudPagoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDocente")
    private Collection<EstadoSolicContrato> estadoSolicContratoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDocente")
    private Collection<DatosViajero> datosViajeroCollection;
    @JoinColumn(name = "TFP_CODIGO", referencedColumnName = "TFP_CODIGO")
    @ManyToOne
    private TiposFormaPago tiposFormaPago;
    @JoinColumn(name = "RDO_CODIGO", referencedColumnName = "RDO_CODIGO")
    @ManyToOne(optional = false)
    private RolDocente rolDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoDocente")
    private Collection<PorTutoriaMonografia> porTutoriaMonografiaCollection;
    @Transient
    private CandidatoDocente contratado;
    @Transient
    private String estadoContrato;
    @Transient
    private String areaContrato;
    @Transient
    private String programaContrato;
    @Transient
    private String siguienteProceso;
    @Transient
    private EstadoSolicContrato estadoSolicContrato;
    @Transient
    private DatosViajero residencia;
    @Transient
    private Long fila;
    @Transient
    private String tituloInvest;    
    @Transient
    private String categInvest;        
    @Transient
    private Date fechaAprobInvest;        

    public String getTituloInvest() {
        return tituloInvest;
    }

    public void setTituloInvest(String tituloInvest) {
        this.tituloInvest = tituloInvest;
    }

    public String getCategInvest() {
        return categInvest;
    }

    public void setCategInvest(String categInvest) {
        this.categInvest = categInvest;
    }

    public Date getFechaAprobInvest() {
        return fechaAprobInvest;
    }

    public void setFechaAprobInvest(Date fechaAprobInvest) {
        this.fechaAprobInvest = fechaAprobInvest;
    }

    public Long getFila() {
        return fila;
    }

    public void setFila(Long fila) {
        this.fila = fila;
    }

    public ContratoDocente() {
    }

    public ContratoDocente(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public ContratoDocente(Long cdoCodigo, Long cdoAnioAcad, Long prfCodigo, Long prgCodigo, Long areCodigo, String staCodigo,
            Date cdoFecini, BigDecimal cdoMonto, Long cdoUsuCrea, Date cdoFechaCrea, Long fila) {
        this.cdoCodigo = cdoCodigo;
        this.cdoAnioAcad = cdoAnioAcad;
        this.prfCodigo = prfCodigo;
        this.prgCodigo = prgCodigo;
        this.areCodigo = areCodigo;
        this.staCodigo = staCodigo;
        this.cdoFecini = cdoFecini;
        this.cdoMonto = cdoMonto;
        this.cdoUsuCrea = cdoUsuCrea;
        this.cdoFechaCrea = cdoFechaCrea;
        this.fila = fila;
    }

    public Long getCdoCodigo() {
        return cdoCodigo;
    }

    public void setCdoCodigo(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public Long getCdoAnioAcad() {
        return cdoAnioAcad;
    }

    public void setCdoAnioAcad(Long cdoAnioAcad) {
        this.cdoAnioAcad = cdoAnioAcad;
    }

    public Long getPrfCodigo() {
        return prfCodigo;
    }

    public void setPrfCodigo(Long prfCodigo) {
        this.prfCodigo = prfCodigo;
    }

    public Long getPrgCodigo() {
        return prgCodigo;
    }

    public void setPrgCodigo(Long prgCodigo) {
        this.prgCodigo = prgCodigo;
    }

    public Long getAreCodigo() {
        return areCodigo;
    }

    public void setAreCodigo(Long areCodigo) {
        this.areCodigo = areCodigo;
    }

    public String getStaCodigo() {
        return staCodigo;
    }

    public void setStaCodigo(String staCodigo) {
        this.staCodigo = staCodigo;
    }

    public Long getAsgCodigo() {
        return asgCodigo;
    }

    public void setAsgCodigo(Long asgCodigo) {
        this.asgCodigo = asgCodigo;
    }

    public Long getPrlCodigo() {
        return prlCodigo;
    }

    public void setPrlCodigo(Long prlCodigo) {
        this.prlCodigo = prlCodigo;
    }

    public Long getAspCodigo() {
        return aspCodigo;
    }

    public void setAspCodigo(Long aspCodigo) {
        this.aspCodigo = aspCodigo;
    }

    public String getCdoNumero() {
        return cdoNumero;
    }

    public void setCdoNumero(String cdoNumero) {
        this.cdoNumero = cdoNumero;
    }

    public Date getCdoFecini() {
        return cdoFecini;
    }

    public void setCdoFecini(Date cdoFecini) {
        this.cdoFecini = cdoFecini;
    }

    public Date getCdoFecfin() {
        return cdoFecfin;
    }

    public void setCdoFecfin(Date cdoFecfin) {
        this.cdoFecfin = cdoFecfin;
    }

    public BigDecimal getCdoMonto() {
        return cdoMonto;
    }

    public void setCdoMonto(BigDecimal cdoMonto) {
        this.cdoMonto = cdoMonto;
    }

    public Short getCdoCantUnidad() {
        return cdoCantUnidad;
    }

    public void setCdoCantUnidad(Short cdoCantUnidad) {
        this.cdoCantUnidad = cdoCantUnidad;
    }

//    public String getCdoSoloViatico() {
//        return cdoSoloViatico;
//    }
//
//    public void setCdoSoloViatico(String cdoSoloViatico) {
//        this.cdoSoloViatico = cdoSoloViatico;
//    }
    public String getCdoDsctoUsoResid() {
        return cdoDsctoUsoResid;
    }

    public void setCdoDsctoUsoResid(String cdoDsctoUsoResid) {
        this.cdoDsctoUsoResid = cdoDsctoUsoResid;
    }

    public String getCdoObservaciones() {
        return cdoObservaciones;
    }

    public void setCdoObservaciones(String cdoObservaciones) {
        this.cdoObservaciones = cdoObservaciones;
    }

    public String getCdoTexto() {
        return cdoTexto;
    }

    public void setCdoTexto(String cdoTexto) {
        this.cdoTexto = cdoTexto;
    }

    public Long getCdoUsuCrea() {
        return cdoUsuCrea;
    }

    public void setCdoUsuCrea(Long cdoUsuCrea) {
        this.cdoUsuCrea = cdoUsuCrea;
    }

    public Date getCdoFechaCrea() {
        return cdoFechaCrea;
    }

    public void setCdoFechaCrea(Date cdoFechaCrea) {
        this.cdoFechaCrea = cdoFechaCrea;
    }

    public Long getCdoUsuModif() {
        return cdoUsuModif;
    }

    public void setCdoUsuModif(Long cdoUsuModif) {
        this.cdoUsuModif = cdoUsuModif;
    }

    public Date getCdoFechaModif() {
        return cdoFechaModif;
    }

    public void setCdoFechaModif(Date cdoFechaModif) {
        this.cdoFechaModif = cdoFechaModif;
    }

    @XmlTransient
    public Collection<Honorarios> getHonorariosCollection() {
        return honorariosCollection;
    }

    public void setHonorariosCollection(Collection<Honorarios> honorariosCollection) {
        this.honorariosCollection = honorariosCollection;
    }

    @XmlTransient
    public Collection<SolicitudPago> getSolicitudPagoCollection() {
        return solicitudPagoCollection;
    }

    public void setSolicitudPagoCollection(Collection<SolicitudPago> solicitudPagoCollection) {
        this.solicitudPagoCollection = solicitudPagoCollection;
    }

    @XmlTransient
    public Collection<EstadoSolicContrato> getEstadoSolicContratoCollection() {
        return estadoSolicContratoCollection;
    }

    public void setEstadoSolicContratoCollection(Collection<EstadoSolicContrato> estadoSolicContratoCollection) {
        this.estadoSolicContratoCollection = estadoSolicContratoCollection;
    }

    @XmlTransient
    public Collection<DatosViajero> getDatosViajeroCollection() {
        return datosViajeroCollection;
    }

    public void setDatosViajeroCollection(Collection<DatosViajero> datosViajeroCollection) {
        this.datosViajeroCollection = datosViajeroCollection;
    }

    public TiposFormaPago getTiposFormaPago() {
        return tiposFormaPago;
    }

    public void setTiposFormaPago(TiposFormaPago tiposFormaPago) {
        this.tiposFormaPago = tiposFormaPago;
    }

    public RolDocente getRolDocente() {
        return rolDocente;
    }

    public void setRolDocente(RolDocente rolDocente) {
        this.rolDocente = rolDocente;
    }

    @XmlTransient
    public Collection<PorTutoriaMonografia> getPorTutoriaMonografiaCollection() {
        return porTutoriaMonografiaCollection;
    }

    public void setPorTutoriaMonografiaCollection(Collection<PorTutoriaMonografia> porTutoriaMonografiaCollection) {
        this.porTutoriaMonografiaCollection = porTutoriaMonografiaCollection;
    }

    public CandidatoDocente getContratado() {
        return contratado;
    }

    public void setContratado(CandidatoDocente contratado) {
        this.contratado = contratado;
    }

    public String getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public String getAreaContrato() {
        return areaContrato;
    }

    public void setAreaContrato(String areaContrato) {
        this.areaContrato = areaContrato;
    }

    public String getProgramaContrato() {
        return programaContrato;
    }

    public void setProgramaContrato(String programaContrato) {
        this.programaContrato = programaContrato;
    }

    public String getSiguienteProceso() {
        return siguienteProceso;
    }

    public void setSiguienteProceso(String siguienteProceso) {
        this.siguienteProceso = siguienteProceso;
    }

    public EstadoSolicContrato getEstadoSolicContrato() {
        return estadoSolicContrato;
    }

    public void setEstadoSolicContrato(EstadoSolicContrato estadoSolicContrato) {
        this.estadoSolicContrato = estadoSolicContrato;
    }

    public DatosViajero getResidencia() {
        return residencia;
    }

    public void setResidencia(DatosViajero residencia) {
        this.residencia = residencia;
    }

    public Date getCdoFechaGenContr() {
        return cdoFechaGenContr;
    }

    public void setCdoFechaGenContr(Date cdoFechaGenContr) {
        this.cdoFechaGenContr = cdoFechaGenContr;
    }

    public String getCdoRevisadoPor() {
        return cdoRevisadoPor;
    }

    public void setCdoRevisadoPor(String cdoRevisadoPor) {
        this.cdoRevisadoPor = cdoRevisadoPor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.cdoCodigo != null ? this.cdoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoDocente)) {
            return false;
        }
        ContratoDocente other = (ContratoDocente) object;
        if ((this.cdoCodigo == null && other.cdoCodigo != null) || (this.cdoCodigo != null && !this.cdoCodigo.equals(other.cdoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public ContratoDocente clone() throws CloneNotSupportedException {
        return (ContratoDocente) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ContratoDocente{" + "cdoCodigo=" + cdoCodigo + ", cdoAnioAcad=" + cdoAnioAcad + ", prfCodigo=" + prfCodigo + ", prgCodigo=" + prgCodigo + ", areCodigo=" + areCodigo + ", asgCodigo=" + asgCodigo + ", prlCodigo=" + prlCodigo + ", aspCodigo=" + aspCodigo + ", staCodigo=" + staCodigo + ", cdoNumero=" + cdoNumero + ", cdoFechaGenContr=" + cdoFechaGenContr + ", cdoFecini=" + cdoFecini + ", cdoFecfin=" + cdoFecfin + ", cdoMonto=" + cdoMonto + ", cdoCantUnidad=" + cdoCantUnidad + ", cdoDsctoUsoResid=" + cdoDsctoUsoResid + ", cdoObservaciones=" + cdoObservaciones + ", cdoRevisadoPor=" + cdoRevisadoPor + ", cdoUsuCrea=" + cdoUsuCrea + ", cdoFechaCrea=" + cdoFechaCrea + ", contratado=" + contratado + ", estadoContrato=" + estadoContrato + ", areaContrato=" + areaContrato + ", programaContrato=" + programaContrato + ", siguienteProceso=" + siguienteProceso + ", estadoSolicContrato=" + estadoSolicContrato + ", fila=" + fila + '}';
    }

}
