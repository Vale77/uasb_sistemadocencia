/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class EstadoSolicContratoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_CODIGO")
    private Long cdoCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESC_NRO_ESTADO")
    private Short escNroEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STA_CODIGO")
    private String staCodigo;

    public EstadoSolicContratoPK() {
    }

    public EstadoSolicContratoPK(Long cdoCodigo, Short escNroEstado, String staCodigo) {
        this.cdoCodigo = cdoCodigo;
        this.escNroEstado = escNroEstado;
        this.staCodigo = staCodigo;
    }

    public Long getCdoCodigo() {
        return cdoCodigo;
    }

    public void setCdoCodigo(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public Short getEscNroEstado() {
        return escNroEstado;
    }

    public void setEscNroEstado(Short escNroEstado) {
        this.escNroEstado = escNroEstado;
    }

    public String getStaCodigo() {
        return staCodigo;
    }

    public void setStaCodigo(String staCodigo) {
        this.staCodigo = staCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.cdoCodigo != null ? this.cdoCodigo.hashCode() : 0);
        hash = 53 * hash + (this.escNroEstado != null ? this.escNroEstado.hashCode() : 0);
        hash = 53 * hash + (this.staCodigo != null ? this.staCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstadoSolicContratoPK other = (EstadoSolicContratoPK) obj;
        if (this.cdoCodigo != other.cdoCodigo && (this.cdoCodigo == null || !this.cdoCodigo.equals(other.cdoCodigo))) {
            return false;
        }
        if (this.escNroEstado != other.escNroEstado && (this.escNroEstado == null || !this.escNroEstado.equals(other.escNroEstado))) {
            return false;
        }
        if ((this.staCodigo == null) ? (other.staCodigo != null) : !this.staCodigo.equals(other.staCodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstadoSolicContratoPK{" + "cdoCodigo=" + cdoCodigo + ", escNroEstado=" + escNroEstado + ", staCodigo=" + staCodigo + '}';
    }

}
