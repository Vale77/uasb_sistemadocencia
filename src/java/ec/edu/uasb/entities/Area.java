/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "AREA")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARE_CODIGO")
    private Long areCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE_AREA")
    private String nombreArea;

    public Area() {
    }

    public Area(Long areCodigo) {
        this.areCodigo = areCodigo;
    }

    public Area(Long areCodigo, String nombreArea) {
        this.areCodigo = areCodigo;
        this.nombreArea = nombreArea;
    }

    public Long getAreCodigo() {
        return areCodigo;
    }

    public void setAreCodigo(Long areCodigo) {
        this.areCodigo = areCodigo;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.areCodigo != null ? this.areCodigo.hashCode() : 0);
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
        final Area other = (Area) obj;
        if (this.areCodigo != other.areCodigo && (this.areCodigo == null || !this.areCodigo.equals(other.areCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Area{" + "areCodigo=" + areCodigo + ", nombreArea=" + nombreArea + '}';
    }

}
