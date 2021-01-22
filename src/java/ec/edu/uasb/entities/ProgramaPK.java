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
public class ProgramaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PRG_CODIGO")
    private Long prgCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARE_CODIGO")
    private Long areCodigo;

    public ProgramaPK() {
    }

    public ProgramaPK(Long prgCodigo, Long areCodigo) {
        this.prgCodigo = prgCodigo;
        this.areCodigo = areCodigo;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.prgCodigo != null ? this.prgCodigo.hashCode() : 0);
        hash = 47 * hash + (this.areCodigo != null ? this.areCodigo.hashCode() : 0);
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
        final ProgramaPK other = (ProgramaPK) obj;
        if (this.prgCodigo != other.prgCodigo && (this.prgCodigo == null || !this.prgCodigo.equals(other.prgCodigo))) {
            return false;
        }
        if (this.areCodigo != other.areCodigo && (this.areCodigo == null || !this.areCodigo.equals(other.areCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProgramaPK{" + "prgCodigo=" + prgCodigo + ", areCodigo=" + areCodigo + '}';
    }

}
