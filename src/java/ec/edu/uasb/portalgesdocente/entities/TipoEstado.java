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
@Table(name = "TIPO_ESTADO", schema = "GESTIONDOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstado.findAll", query = "SELECT t FROM TipoEstado t"),
    @NamedQuery(name = "TipoEstado.findByStaCodigo", query = "SELECT t FROM TipoEstado t WHERE t.staCodigo = :staCodigo"),
    @NamedQuery(name = "TipoEstado.findByStaDescripcion", query = "SELECT t FROM TipoEstado t WHERE t.staDescripcion = :staDescripcion")})

public class TipoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STA_CODIGO")
    private String staCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STA_DESCRIPCION")
    private String staDescripcion;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "STA_SIGUIENTE_PROCESO")
    private String staSiguienteProceso;
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "STA_SIGUIENTE_PERFIL")
    private String staSiguientePerfil;
    @Column(name = "STA_FIRMA_USR_SIG_PERFIL")
    private Long staFirmaUsrSigPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STA_ORDEN")
    private Integer staOrden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEstado")
    private Collection<EstadoSolicContrato> estadoSolicContratoCollection;

    public TipoEstado() {
    }

    public TipoEstado(String staCodigo) {
        this.staCodigo = staCodigo;
    }

    public TipoEstado(String staCodigo, String staDescripcion, String staSiguienteProceso, String staSiguientePerfil, Integer staOrden,
            Long staFirmaUsrSigPerfil) {
        this.staCodigo = staCodigo;
        this.staDescripcion = staDescripcion;
        this.staSiguienteProceso = staSiguienteProceso;
        this.staSiguientePerfil = staSiguientePerfil;
        this.staOrden = staOrden;
        this.staFirmaUsrSigPerfil = staFirmaUsrSigPerfil;
    }

    public String getStaCodigo() {
        return staCodigo;
    }

    public void setStaCodigo(String staCodigo) {
        this.staCodigo = staCodigo;
    }

    public String getStaDescripcion() {
        return staDescripcion;
    }

    public void setStaDescripcion(String staDescripcion) {
        this.staDescripcion = staDescripcion;
    }

    public Integer getStaOrden() {
        return staOrden;
    }

    public void setStaOrden(Integer staOrden) {
        this.staOrden = staOrden;
    }

    public String getStaSiguienteProceso() {
        return staSiguienteProceso;
    }

    public void setStaSiguienteProceso(String staSiguienteProceso) {
        this.staSiguienteProceso = staSiguienteProceso;
    }

    public String getStaSiguientePerfil() {
        return staSiguientePerfil;
    }

    public void setStaSiguientePerfil(String staSiguientePerfil) {
        this.staSiguientePerfil = staSiguientePerfil;
    }

    public Long getStaFirmaUsrSigPerfil() {
        return staFirmaUsrSigPerfil;
    }

    public void setStaFirmaUsrSigPerfil(Long staFirmaUsrSigPerfil) {
        this.staFirmaUsrSigPerfil = staFirmaUsrSigPerfil;
    }

    @XmlTransient
    public Collection<EstadoSolicContrato> getEstadoSolicContratoCollection() {
        return estadoSolicContratoCollection;
    }

    public void setEstadoSolicContratoCollection(Collection<EstadoSolicContrato> estadoSolicContratoCollection) {
        this.estadoSolicContratoCollection = estadoSolicContratoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staCodigo != null ? staCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstado)) {
            return false;
        }
        TipoEstado other = (TipoEstado) object;
        if ((this.staCodigo == null && other.staCodigo != null) || (this.staCodigo != null && !this.staCodigo.equals(other.staCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoEstado{" + "staCodigo=" + staCodigo + ", staDescripcion=" + staDescripcion + ", staSiguienteProceso=" + staSiguienteProceso + ", staSiguientePerfil=" + staSiguientePerfil + ", staFirmaUsrSigPerfil=" + staFirmaUsrSigPerfil + ", staOrden=" + staOrden + ", estadoSolicContratoCollection=" + estadoSolicContratoCollection + '}';
    }

}
