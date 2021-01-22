/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PARAMETROS", schema = "GESTIONADMIN")
@XmlRootElement
public class Parametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRM_CODIGO")
    private Short prmCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRM_ETIQUETA_RECTOR")
    private String prmEtiquetaRector;
    @Column(name = "PRM_USU_MODIF")
    private Long prmUsuModif;
    @Column(name = "PRM_FECHA_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prmFechaModif;    
    @JoinColumn(name = "PRM_COD_RECTOR", referencedColumnName = "AUT_CODIGO")
    @ManyToOne(optional = false)
    private Autoridad autoridad;

    public Parametros() {
    }

    public Parametros(Short prmCodigo) {
        this.prmCodigo = prmCodigo;
    }

    public Parametros(Short prmCodigo, String prmEtiquetaRector) {
        this.prmCodigo = prmCodigo;
        this.prmEtiquetaRector = prmEtiquetaRector;
    }

    public Short getPrmCodigo() {
        return prmCodigo;
    }

    public void setPrmCodigo(Short prmCodigo) {
        this.prmCodigo = prmCodigo;
    }

    public String getPrmEtiquetaRector() {
        return prmEtiquetaRector;
    }

    public void setPrmEtiquetaRector(String prmEtiquetaRector) {
        this.prmEtiquetaRector = prmEtiquetaRector;
    }

    public Long getPrmUsuModif() {
        return prmUsuModif;
    }

    public void setPrmUsuModif(Long prmUsuModif) {
        this.prmUsuModif = prmUsuModif;
    }

    public Date getPrmFechaModif() {
        return prmFechaModif;
    }

    public void setPrmFechaModif(Date prmFechaModif) {
        this.prmFechaModif = prmFechaModif;
    }

    public Autoridad getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Autoridad autoridad) {
        this.autoridad = autoridad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prmCodigo != null ? prmCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        return !((this.prmCodigo == null && other.prmCodigo != null) || (this.prmCodigo != null && !this.prmCodigo.equals(other.prmCodigo)));
    }

    @Override
    public String toString() {
        return "Parametros{" + "prmCodigo=" + prmCodigo + ", prmEtiquetaRector=" + prmEtiquetaRector + ", prmUsuModif=" + prmUsuModif + ", prmFechaModif=" + prmFechaModif + ", autoridad=" + autoridad + '}';
    }


}
