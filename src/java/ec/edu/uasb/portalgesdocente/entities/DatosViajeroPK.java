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
public class DatosViajeroPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_CODIGO")
    private Long cdoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DVI_TIPO_REGISTRO")
    private String dviTipoRegistro;

    public DatosViajeroPK() {
    }

    public DatosViajeroPK(Long cdoCodigo, String dviTipoRegistro) {
        this.cdoCodigo = cdoCodigo;
        this.dviTipoRegistro = dviTipoRegistro;
    }

    public Long getCdoCodigo() {
        return cdoCodigo;
    }

    public void setCdoCodigo(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public String getDviTipoRegistro() {
        return dviTipoRegistro;
    }

    public void setDviTipoRegistro(String dviTipoRegistro) {
        this.dviTipoRegistro = dviTipoRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.cdoCodigo != null ? this.cdoCodigo.hashCode() : 0);
        hash = 19 * hash + (this.dviTipoRegistro != null ? this.dviTipoRegistro.hashCode() : 0);
        return hash;
    }

 

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosViajeroPK)) {
            return false;
        }
        DatosViajeroPK other = (DatosViajeroPK) object;
        if (this.cdoCodigo != other.cdoCodigo) {
            return false;
        }
        if ((this.dviTipoRegistro == null && other.dviTipoRegistro != null) || (this.dviTipoRegistro != null && !this.dviTipoRegistro.equals(other.dviTipoRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.portalgesdocente.entities.DatosViajeroPK[ cdoCodigo=" + cdoCodigo + ", dviTipoRegistro=" + dviTipoRegistro + " ]";
    }
    
}
