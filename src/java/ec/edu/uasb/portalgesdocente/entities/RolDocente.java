/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ROL_DOCENTE", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolDocente.findAll", query = "SELECT r FROM RolDocente r"),
    @NamedQuery(name = "RolDocente.findByRdoCodigo", query = "SELECT r FROM RolDocente r WHERE r.rdoCodigo = :rdoCodigo"),
    @NamedQuery(name = "RolDocente.findByRdoDescripcion", query = "SELECT r FROM RolDocente r WHERE r.rdoDescripcion = :rdoDescripcion"),
    @NamedQuery(name = "RolDocente.findByRdoEstado", query = "SELECT r FROM RolDocente r WHERE r.rdoCodigo = 'D' or (r.rdoEstado = :rdoEstado and r.codNivelAcad = :codNivelAcad)")})
public class RolDocente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "RDO_CODIGO")
    private String rdoCodigo;
    @Size(max = 50)
    @Column(name = "RDO_DESCRIPCION")
    private String rdoDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "RDO_ESTADO")
    private String rdoEstado;
    @Column(name = "COD_NIVELACAD")
    private Short codNivelAcad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolDocente")
    private Collection<ContratoDocente> contratoDocenteCollection;
    @JoinColumn(name = "RUB_CODIGO", referencedColumnName = "RUB_CODIGO")
    @ManyToOne
    private Rubros rubros;

    public RolDocente() {
    }

    public RolDocente(String rdoCodigo) {
        this.rdoCodigo = rdoCodigo;
    }

    public RolDocente(String rdoCodigo, String rdoEstado) {
        this.rdoCodigo = rdoCodigo;
        this.rdoEstado = rdoEstado;
    }

    public String getRdoCodigo() {
        return rdoCodigo;
    }

    public void setRdoCodigo(String rdoCodigo) {
        this.rdoCodigo = rdoCodigo;
    }

    public String getRdoDescripcion() {
        return rdoDescripcion;
    }

    public void setRdoDescripcion(String rdoDescripcion) {
        this.rdoDescripcion = rdoDescripcion;
    }

    public String getRdoEstado() {
        return rdoEstado;
    }

    public void setRdoEstado(String rdoEstado) {
        this.rdoEstado = rdoEstado;
    }

    public Short getCodNivelAcad() {
        return codNivelAcad;
    }

    public void setCodNivelAcad(Short codNivelAcad) {
        this.codNivelAcad = codNivelAcad;
    }

    @XmlTransient
    public Collection<ContratoDocente> getContratoDocenteCollection() {
        return contratoDocenteCollection;
    }

    public void setContratoDocenteCollection(Collection<ContratoDocente> contratoDocenteCollection) {
        this.contratoDocenteCollection = contratoDocenteCollection;
    }

    public Rubros getRubros() {
        return rubros;
    }

    public void setRubros(Rubros rubros) {
        this.rubros = rubros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rdoCodigo != null ? rdoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolDocente)) {
            return false;
        }
        RolDocente other = (RolDocente) object;
        if ((this.rdoCodigo == null && other.rdoCodigo != null) || (this.rdoCodigo != null && !this.rdoCodigo.equals(other.rdoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RolDocente{" + "rdoCodigo=" + rdoCodigo + ", rdoDescripcion=" + rdoDescripcion + ", rdoEstado=" + rdoEstado + ", codNivelAcad=" + codNivelAcad + '}';
    }

}
