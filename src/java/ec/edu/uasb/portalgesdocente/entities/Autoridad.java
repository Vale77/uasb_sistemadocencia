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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AUTORIDAD", schema = "GESTIONADMIN")
@XmlRootElement
public class Autoridad implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AUT_CODIGO")
    private Long autCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 6)
    @Column(name = "AUT_APELATIVO")
    private String autApelativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AUT_NOMBRES")
    private String autNombres;
    @Column(name = "AUT_COD_PERSONAL")
    private Long autCodPersonal;
    @JoinColumn(name = "CAR_CODIGO", referencedColumnName = "CAR_CODIGO")
    @ManyToOne
    private Cargos cargos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autoridad")
    private Collection<Parametros> parametrosCollection;

    public Autoridad() {
    }

    public Autoridad(Long autCodigo) {
        this.autCodigo = autCodigo;
    }

    public Autoridad(Long autCodigo, String autNombres) {
        this.autCodigo = autCodigo;
        this.autNombres = autNombres;
    }

    public Long getAutCodigo() {
        return autCodigo;
    }

    public void setAutCodigo(Long autCodigo) {
        this.autCodigo = autCodigo;
    }

    public String getAutApelativo() {
        return autApelativo;
    }

    public void setAutApelativo(String autApelativo) {
        this.autApelativo = autApelativo;
    }

    public String getAutNombres() {
        return autNombres;
    }

    public void setAutNombres(String autNombres) {
        this.autNombres = autNombres;
    }

    public Long getAutCodPersonal() {
        return autCodPersonal;
    }

    public void setAutCodPersonal(Long autCodPersonal) {
        this.autCodPersonal = autCodPersonal;
    }

    public Cargos getCargos() {
        return cargos;
    }

    public void setCargos(Cargos cargos) {
        this.cargos = cargos;
    }

    @XmlTransient
    public Collection<Parametros> getParametrosCollection() {
        return parametrosCollection;
    }

    public void setParametrosCollection(Collection<Parametros> parametrosCollection) {
        this.parametrosCollection = parametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autCodigo != null ? autCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autoridad)) {
            return false;
        }
        Autoridad other = (Autoridad) object;
        return !((this.autCodigo == null && other.autCodigo != null) || (this.autCodigo != null && !this.autCodigo.equals(other.autCodigo)));
    }

    @Override
    public String toString() {
        return "Autoridad{" + "autCodigo=" + autCodigo + ", autApelativo=" + autApelativo + ", autNombres=" + autNombres + ", autCodPersonal=" + autCodPersonal + ", cargos=" + cargos + '}';
    }

    @Override
    public Autoridad clone() throws CloneNotSupportedException {
        return (Autoridad) super.clone(); 
    }

}
