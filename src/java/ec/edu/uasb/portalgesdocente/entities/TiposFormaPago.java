/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "TIPOS_FORMA_PAGO", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposFormaPago.findAll", query = "SELECT t FROM TiposFormaPago t"),
    @NamedQuery(name = "TiposFormaPago.findByTfpCodigo", query = "SELECT t FROM TiposFormaPago t WHERE t.tfpCodigo = :tfpCodigo"),
    @NamedQuery(name = "TiposFormaPago.findByTfpDescripcion", query = "SELECT t FROM TiposFormaPago t WHERE t.tfpDescripcion = :tfpDescripcion")})
public class TiposFormaPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TFP_CODIGO")
    private Short tfpCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TFP_DESCRIPCION")
    private String tfpDescripcion;
    @OneToMany(mappedBy = "tiposFormaPago")
    private Collection<ContratoDocente> contratoDocenteCollection;

    public TiposFormaPago() {
    }

    public TiposFormaPago(Short tfpCodigo) {
        this.tfpCodigo = tfpCodigo;
    }

    public TiposFormaPago(Short tfpCodigo, String tfpDescripcion) {
        this.tfpCodigo = tfpCodigo;
        this.tfpDescripcion = tfpDescripcion;
    }

    public Short getTfpCodigo() {
        return tfpCodigo;
    }

    public void setTfpCodigo(Short tfpCodigo) {
        this.tfpCodigo = tfpCodigo;
    }

    public String getTfpDescripcion() {
        return tfpDescripcion;
    }

    public void setTfpDescripcion(String tfpDescripcion) {
        this.tfpDescripcion = tfpDescripcion;
    }

    @XmlTransient
    public Collection<ContratoDocente> getContratoDocenteCollection() {
        return contratoDocenteCollection;
    }

    public void setContratoDocenteCollection(Collection<ContratoDocente> contratoDocenteCollection) {
        this.contratoDocenteCollection = contratoDocenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tfpCodigo != null ? tfpCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposFormaPago)) {
            return false;
        }
        TiposFormaPago other = (TiposFormaPago) object;
        if ((this.tfpCodigo == null && other.tfpCodigo != null) || (this.tfpCodigo != null && !this.tfpCodigo.equals(other.tfpCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.portalgesdocente.entities.TiposFormaPago[ tfpCodigo=" + tfpCodigo + " ]";
    }
    
}
