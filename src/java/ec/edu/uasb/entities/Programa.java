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
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramaPK programaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE_PROGRAMA")
    private String nombrePrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL_ACADEMICO")
    private String nivelAcademico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_NIVEL_ACAD")
    private Short codNivelAcad;

    public Programa() {
    }

    public Programa(ProgramaPK programaPK) {
        this.programaPK = programaPK;
    }

    public Programa(ProgramaPK programaPK, String nombrePrograma, String nivelAcademico,Short codNivelAcad) {
        this.programaPK = programaPK;
        this.nombrePrograma = nombrePrograma;
        this.nivelAcademico = nivelAcademico;
        this.codNivelAcad = codNivelAcad;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public Short getCodNivelAcad() {
        return codNivelAcad;
    }

    public void setCodNivelAcad(Short codNivelAcad) {
        this.codNivelAcad = codNivelAcad;
    }

    public ProgramaPK getProgramaPK() {
        return programaPK;
    }

    public void setProgramaPK(ProgramaPK programaPK) {
        this.programaPK = programaPK;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.programaPK != null ? this.programaPK.hashCode() : 0);
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
        final Programa other = (Programa) obj;
        if (this.programaPK != other.programaPK && (this.programaPK == null || !this.programaPK.equals(other.programaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Programa{" + "programaPK=" + programaPK + ", nombrePrograma=" + nombrePrograma + ", nivelAcademico=" + nivelAcademico + ", codNivelAcad=" + codNivelAcad + '}';
    }



}
