/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Entity
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignaturaPK asignaturaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ASSIGNATURA")
    private String idAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASS_NOMBRE")
    private String assNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRP_NOMBRE")
    private String grpNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NCLASES_PLANIFICADAS")
    private Short nClasesPlanificadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMCLASE_PRACTICA")
    private Short nClasePractica;

    public Asignatura() {
    }

    public Asignatura(AsignaturaPK asignaturaPK, String idAsignatura, String assNombre, String grpNombre,
            Short nClasesPlanificadas, Short nClasePractica) {
        this.asignaturaPK = asignaturaPK;
        this.idAsignatura = idAsignatura;
        this.assNombre = assNombre;
        this.grpNombre = grpNombre;
        this.nClasesPlanificadas = nClasesPlanificadas;
        this.nClasePractica = nClasePractica;
    }

    public Asignatura(AsignaturaPK asignaturaPK) {
        this.asignaturaPK = asignaturaPK;
    }

    public AsignaturaPK getAsignaturaPK() {
        return asignaturaPK;
    }

    public void setAsignaturaPK(AsignaturaPK asignaturaPK) {
        this.asignaturaPK = asignaturaPK;
    }

    public String getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(String idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getAssNombre() {
        return assNombre;
    }

    public void setAssNombre(String assNombre) {
        this.assNombre = assNombre;
    }

    public String getGrpNombre() {
        return grpNombre;
    }

    public void setGrpNombre(String grpNombre) {
        this.grpNombre = grpNombre;
    }

    public Short getnClasesPlanificadas() {
        return nClasesPlanificadas;
    }

    public void setnClasesPlanificadas(Short nClasesPlanificadas) {
        this.nClasesPlanificadas = nClasesPlanificadas;
    }

    public Short getnClasePractica() {
        return nClasePractica;
    }

    public void setnClasePractica(Short nClasePractica) {
        this.nClasePractica = nClasePractica;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.asignaturaPK != null ? this.asignaturaPK.hashCode() : 0);
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
        final Asignatura other = (Asignatura) obj;
        return !(this.asignaturaPK != other.asignaturaPK && (this.asignaturaPK == null || !this.asignaturaPK.equals(other.asignaturaPK)));
    }

    @Override
    public String toString() {
        return "Asignatura{" + "asignaturaPK=" + asignaturaPK + ", idAsignatura=" + idAsignatura + ", assNombre=" + assNombre + ", grpNombre=" + grpNombre + ", nClasesPlanificadas=" + nClasesPlanificadas + ", nClasePractica=" + nClasePractica + '}';
    }

}
