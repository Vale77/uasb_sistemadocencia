/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "ESTADO_SOLIC_CONTRATO", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSolicContrato.findAll", query = "SELECT e FROM EstadoSolicContrato e"),
    @NamedQuery(name = "EstadoSolicContrato.findByCdoCodigo", query = "SELECT e FROM EstadoSolicContrato e WHERE e.estadoSolicContratoPK.cdoCodigo = :cdoCodigo"),
    @NamedQuery(name = "EstadoSolicContrato.findByStaCodigo", query = "SELECT e FROM EstadoSolicContrato e WHERE e.estadoSolicContratoPK.staCodigo = :staCodigo"),
    @NamedQuery(name = "EstadoSolicContrato.findByEscCodigoUsr", query = "SELECT e FROM EstadoSolicContrato e WHERE e.escCodigoUsr = :escCodigoUsr"),
    @NamedQuery(name = "EstadoSolicContrato.findByEscFecha", query = "SELECT e FROM EstadoSolicContrato e WHERE e.escFecha = :escFecha"),
    @NamedQuery(name = "EstadoSolicContrato.findByEscObservacion", query = "SELECT e FROM EstadoSolicContrato e WHERE e.escObservacion = :escObservacion")})

@SqlResultSetMapping(name = "EstadosResults",
            entities = {
                @EntityResult(entityClass = EstadoSolicContrato.class),
                @EntityResult(entityClass = Usuario.class),
                @EntityResult(entityClass = TipoEstado.class)
            }
    )
public class EstadoSolicContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstadoSolicContratoPK estadoSolicContratoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESC_CODIGO_USR")
    private long escCodigoUsr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESC_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date escFecha;
    @Size(max = 255)
    @Column(name = "ESC_OBSERVACION")
    private String escObservacion;
    @JoinColumn(name = "STA_CODIGO", referencedColumnName = "STA_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoEstado tipoEstado;
    @JoinColumn(name = "CDO_CODIGO", referencedColumnName = "CDO_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContratoDocente contratoDocente;
    @Transient
    private Usuario usuario;

    public EstadoSolicContrato() {
    }

    public EstadoSolicContrato(ContratoDocente contratoDocente) {
        this.contratoDocente = contratoDocente;
    }

    public EstadoSolicContrato(EstadoSolicContratoPK estadoSolicContratoPK) {
        this.estadoSolicContratoPK = estadoSolicContratoPK;
    }

    public EstadoSolicContrato(EstadoSolicContratoPK estadoSolicContratoPK, Long escCodigoUsr, Date escFecha) {
        this.estadoSolicContratoPK = estadoSolicContratoPK;
        this.escCodigoUsr = escCodigoUsr;
        this.escFecha = escFecha;
    }

    public EstadoSolicContrato(Long cdoCodigo, Short escNroEstado, String staCodigo) {
        this.estadoSolicContratoPK = new EstadoSolicContratoPK(cdoCodigo, escNroEstado, staCodigo);
    }

    public EstadoSolicContratoPK getEstadoSolicContratoPK() {
        return estadoSolicContratoPK;
    }

    public void setEstadoSolicContratoPK(EstadoSolicContratoPK estadoSolicContratoPK) {
        this.estadoSolicContratoPK = estadoSolicContratoPK;
    }

    public long getEscCodigoUsr() {
        return escCodigoUsr;
    }

    public void setEscCodigoUsr(long escCodigoUsr) {
        this.escCodigoUsr = escCodigoUsr;
    }

    public Date getEscFecha() {
        return escFecha;
    }

    public void setEscFecha(Date escFecha) {
        this.escFecha = escFecha;
    }

    public String getEscObservacion() {
        return escObservacion;
    }

    public void setEscObservacion(String escObservacion) {
        this.escObservacion = escObservacion;
    }

    public TipoEstado getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoEstado tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public ContratoDocente getContratoDocente() {
        return contratoDocente;
    }

    public void setContratoDocente(ContratoDocente contratoDocente) {
        this.contratoDocente = contratoDocente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoSolicContratoPK != null ? estadoSolicContratoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSolicContrato)) {
            return false;
        }
        EstadoSolicContrato other = (EstadoSolicContrato) object;
        return !((this.estadoSolicContratoPK == null && other.estadoSolicContratoPK != null) || (this.estadoSolicContratoPK != null && !this.estadoSolicContratoPK.equals(other.estadoSolicContratoPK)));
    }

    @Override
    public String toString() {
        return "EstadoSolicContrato{" + "estadoSolicContratoPK=" + estadoSolicContratoPK + ", escCodigoUsr=" + escCodigoUsr + ", escFecha=" + escFecha + ", escObservacion=" + escObservacion + ", tipoEstado=" + tipoEstado + '}';
    }

}
