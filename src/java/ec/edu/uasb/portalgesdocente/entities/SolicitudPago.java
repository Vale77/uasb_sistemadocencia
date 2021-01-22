/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "SOLICITUD_PAGO", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudPago.findAll", query = "SELECT s FROM SolicitudPago s"),
    @NamedQuery(name = "SolicitudPago.findBySlpCodigo", query = "SELECT s FROM SolicitudPago s WHERE s.slpCodigo = :slpCodigo"),
    @NamedQuery(name = "SolicitudPago.findBySlpFecha", query = "SELECT s FROM SolicitudPago s WHERE s.slpFecha = :slpFecha"),
    @NamedQuery(name = "SolicitudPago.findBySlpDescripPago", query = "SELECT s FROM SolicitudPago s WHERE s.slpDescripPago = :slpDescripPago"),
    @NamedQuery(name = "SolicitudPago.findBySlpMontoPago", query = "SELECT s FROM SolicitudPago s WHERE s.slpMontoPago = :slpMontoPago"),
    @NamedQuery(name = "SolicitudPago.findBySlpHorasClase", query = "SELECT s FROM SolicitudPago s WHERE s.slpHorasClase = :slpHorasClase"),
    @NamedQuery(name = "SolicitudPago.findBySlpEstado", query = "SELECT s FROM SolicitudPago s WHERE s.slpEstado = :slpEstado"),
    @NamedQuery(name = "SolicitudPago.findBySlpFechaAprob", query = "SELECT s FROM SolicitudPago s WHERE s.slpFechaAprob = :slpFechaAprob"),
    @NamedQuery(name = "SolicitudPago.findBySlpUsuarioAprob", query = "SELECT s FROM SolicitudPago s WHERE s.slpUsuarioAprob = :slpUsuarioAprob")})
public class SolicitudPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SLP_CODIGO")
    private Long slpCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SLP_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date slpFecha;
    @Size(max = 255)
    @Column(name = "SLP_DESCRIP_PAGO")
    private String slpDescripPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SLP_MONTO_PAGO")
    private BigDecimal slpMontoPago;
    @Column(name = "SLP_HORAS_CLASE")
    private Short slpHorasClase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SLP_ESTADO")
    private String slpEstado;
    @Column(name = "SLP_FECHA_APROB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date slpFechaAprob;
    @Size(max = 10)
    @Column(name = "SLP_USUARIO_APROB")
    private String slpUsuarioAprob;
    @JoinColumn(name = "CDO_CODIGO", referencedColumnName = "CDO_CODIGO")
    @ManyToOne(optional = false)
    private ContratoDocente contratoDocente;

    public SolicitudPago() {
    }

    public SolicitudPago(Long slpCodigo) {
        this.slpCodigo = slpCodigo;
    }

    public SolicitudPago(Long slpCodigo, Date slpFecha, BigDecimal slpMontoPago, String slpEstado) {
        this.slpCodigo = slpCodigo;
        this.slpFecha = slpFecha;
        this.slpMontoPago = slpMontoPago;
        this.slpEstado = slpEstado;
    }

    public Long getSlpCodigo() {
        return slpCodigo;
    }

    public void setSlpCodigo(Long slpCodigo) {
        this.slpCodigo = slpCodigo;
    }

    public Date getSlpFecha() {
        return slpFecha;
    }

    public void setSlpFecha(Date slpFecha) {
        this.slpFecha = slpFecha;
    }

    public String getSlpDescripPago() {
        return slpDescripPago;
    }

    public void setSlpDescripPago(String slpDescripPago) {
        this.slpDescripPago = slpDescripPago;
    }

    public BigDecimal getSlpMontoPago() {
        return slpMontoPago;
    }

    public void setSlpMontoPago(BigDecimal slpMontoPago) {
        this.slpMontoPago = slpMontoPago;
    }

    public Short getSlpHorasClase() {
        return slpHorasClase;
    }

    public void setSlpHorasClase(Short slpHorasClase) {
        this.slpHorasClase = slpHorasClase;
    }

    public String getSlpEstado() {
        return slpEstado;
    }

    public void setSlpEstado(String slpEstado) {
        this.slpEstado = slpEstado;
    }

    public Date getSlpFechaAprob() {
        return slpFechaAprob;
    }

    public void setSlpFechaAprob(Date slpFechaAprob) {
        this.slpFechaAprob = slpFechaAprob;
    }

    public String getSlpUsuarioAprob() {
        return slpUsuarioAprob;
    }

    public void setSlpUsuarioAprob(String slpUsuarioAprob) {
        this.slpUsuarioAprob = slpUsuarioAprob;
    }

    public ContratoDocente getContratoDocente() {
        return contratoDocente;
    }

    public void setContratoDocente(ContratoDocente contratoDocente) {
        this.contratoDocente = contratoDocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slpCodigo != null ? slpCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudPago)) {
            return false;
        }
        SolicitudPago other = (SolicitudPago) object;
        if ((this.slpCodigo == null && other.slpCodigo != null) || (this.slpCodigo != null && !this.slpCodigo.equals(other.slpCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.portalgesdocente.entities.SolicitudPago[ slpCodigo=" + slpCodigo + " ]";
    }
    
}
