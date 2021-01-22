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
import javax.persistence.Id;
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
@Table(name = "CARGOS", schema = "GESTIONADMIN")
@XmlRootElement

public class Cargos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAR_CODIGO")
    private Long carCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CAR_NOMBRE")
    private String carNombre;
    @OneToMany(mappedBy = "cargos")
    private Collection<Autoridad> autoridadCollection;

    public Cargos() {
    }

    public Cargos(Long carCodigo) {
        this.carCodigo = carCodigo;
    }

    public Cargos(Long carCodigo, String carNombre) {
        this.carCodigo = carCodigo;
        this.carNombre = carNombre;
    }

    public Long getCarCodigo() {
        return carCodigo;
    }

    public void setCarCodigo(Long carCodigo) {
        this.carCodigo = carCodigo;
    }

    public String getCarNombre() {
        return carNombre;
    }

    public void setCarNombre(String carNombre) {
        this.carNombre = carNombre;
    }

    @XmlTransient
    public Collection<Autoridad> getAutoridadCollection() {
        return autoridadCollection;
    }

    public void setAutoridadCollection(Collection<Autoridad> autoridadCollection) {
        this.autoridadCollection = autoridadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carCodigo != null ? carCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargos)) {
            return false;
        }
        Cargos other = (Cargos) object;
        if ((this.carCodigo == null && other.carCodigo != null) || (this.carCodigo != null && !this.carCodigo.equals(other.carCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cargos{" + "carCodigo=" + carCodigo + ", carNombre=" + carNombre + '}';
    }

}
