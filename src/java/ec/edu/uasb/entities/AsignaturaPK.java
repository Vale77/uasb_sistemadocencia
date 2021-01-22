/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class AsignaturaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ACT_CODIGO")
    private Long actCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRP_CODIGO")
    private Long grpCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASS_CODIGO")
    private Long assCodigo;

    public AsignaturaPK() {
    }

    public AsignaturaPK(Long actCodigo, Long grpCodigo, Long assCodigo) {
        this.actCodigo = actCodigo;
        this.grpCodigo = grpCodigo;
        this.assCodigo = assCodigo;
    }

    public Long getActCodigo() {
        return actCodigo;
    }

    public void setActCodigo(Long actCodigo) {
        this.actCodigo = actCodigo;
    }

    public Long getGrpCodigo() {
        return grpCodigo;
    }

    public void setGrpCodigo(Long grpCodigo) {
        this.grpCodigo = grpCodigo;
    }

    public Long getAssCodigo() {
        return assCodigo;
    }

    public void setAssCodigo(Long assCodigo) {
        this.assCodigo = assCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.actCodigo != null ? this.actCodigo.hashCode() : 0);
        hash = 79 * hash + (this.grpCodigo != null ? this.grpCodigo.hashCode() : 0);
        hash = 79 * hash + (this.assCodigo != null ? this.assCodigo.hashCode() : 0);
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
        final AsignaturaPK other = (AsignaturaPK) obj;
        if (this.actCodigo != other.actCodigo && (this.actCodigo == null || !this.actCodigo.equals(other.actCodigo))) {
            return false;
        }
        if (this.grpCodigo != other.grpCodigo && (this.grpCodigo == null || !this.grpCodigo.equals(other.grpCodigo))) {
            return false;
        }
        if (this.assCodigo != other.assCodigo && (this.assCodigo == null || !this.assCodigo.equals(other.assCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AsignaturaPK{" + "actCodigo=" + actCodigo + ", grpCodigo=" + grpCodigo + ", assCodigo=" + assCodigo + '}';
    }

}
