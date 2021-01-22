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

/**
 *
 * @author victor.barba
 */
@Embeddable
public class HonorariosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_CODIGO")
    private Long cdoCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUB_CODIGO")
    private Long rubCodigo;

    public HonorariosPK() {
    }

    public HonorariosPK(Long cdoCodigo, Long rubCodigo) {
        this.cdoCodigo = cdoCodigo;
        this.rubCodigo = rubCodigo;
    }

    public Long getCdoCodigo() {
        return cdoCodigo;
    }

    public void setCdoCodigo(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public Long getRubCodigo() {
        return rubCodigo;
    }

    public void setRubCodigo(Long rubCodigo) {
        this.rubCodigo = rubCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.cdoCodigo != null ? this.cdoCodigo.hashCode() : 0);
        hash = 61 * hash + (this.rubCodigo != null ? this.rubCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HonorariosPK)) {
            return false;
        }
        HonorariosPK other = (HonorariosPK) object;
        if (this.cdoCodigo != other.cdoCodigo) {
            return false;
        }
        if (this.rubCodigo != other.rubCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.portalgesdocente.entities.HonorariosPK[ cdoCodigo=" + cdoCodigo + ", rubCodigo=" + rubCodigo + " ]";
    }

}
